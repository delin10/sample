package nil.ed.sample.springmongo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * 监听Mongo事件，主要用于主键的自增
 *
 * @author lidelin 
 * @since 2019/07/22 15:00:19
 */
@Component
public class SaveEventListener extends AbstractMongoEventListener<Object> {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void onBeforeConvert(final BeforeConvertEvent<Object> event) {
		final Object source = event.getSource();
		if (source != null) {
			ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
				@Override
				public void doWith(final java.lang.reflect.Field field)
						throws IllegalArgumentException, IllegalAccessException {
					ReflectionUtils.makeAccessible(field);
					if (field.isAnnotationPresent(AutoIncKey.class) && field.get(source) == null) {
						field.set(source, getNextId(event.getCollectionName()));
					}
				}
			});
		}
	}

	/**
	 * 获取下一个自增ID
	 * 
	 * @param collName 集合（这里用类名，就唯一性来说最好还是存放长类名）名称
	 * @return 序列值
	 */
	private Long getNextId(String collName) {
		Query query = new Query(Criteria.where("collName").is(collName));
		Update update = new Update();
		update.inc("seqId", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.upsert(true);
		options.returnNew(true);
		SeqInfo seq = mongoTemplate.findAndModify(query, update, options, SeqInfo.class);
		return seq.getSeqId();
	}
}