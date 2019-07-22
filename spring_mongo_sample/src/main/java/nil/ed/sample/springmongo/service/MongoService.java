package nil.ed.sample.springmongo.service;

import nil.ed.sample.springmongo.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lidelin
 * @date 2019/07/22 14:01
 */
@Service
public class MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public TestModel insert(TestModel testModel) {
        return mongoTemplate.insert(testModel);
    }

    public long updateById(TestModel testModel) {
        Query query = Query.query(Criteria.where("id").is(testModel.getId()));
        Update update = Update.update("content", testModel.getContent())
                .set("test_list", testModel.getTestList());

        return mongoTemplate.updateMulti(query, update, TestModel.class).getModifiedCount();
    }

    public long deleteById(Long id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.remove(query, TestModel.class).getDeletedCount();
    }

    public List<TestModel> listAllByPage(int pageNo, int pageSize) {
        int pageStart = pageNoToPageStart(pageNo, pageSize);
        if (pageNo < 0 || pageSize < 0 || pageStart < 0) {
            throw new IllegalArgumentException("params error");
        }
        Query query = new Query();
        query.skip(pageStart).limit(pageSize);
        List<TestModel> testModelList = mongoTemplate.find(query, TestModel.class);
        return testModelList;
    }

    private int pageNoToPageStart(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}
