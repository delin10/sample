package nil.ed.sample.springmongo.common;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * mongodb自增序列类
 * 
 * @author snakelu
 * @since 2018.11.12
 */
@Document(collection = "home_layout_sequence")
public class SeqInfo {

	/**
	 * 主键
	 */
	@Id
	private String id;

	/**
	 * 列名
	 */
	@Field
	private String collName;

	/**
	 * 序列Id
	 */
	@Field
	private Long seqId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCollName() {
		return collName;
	}

	public void setCollName(String collName) {
		this.collName = collName;
	}

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}

}