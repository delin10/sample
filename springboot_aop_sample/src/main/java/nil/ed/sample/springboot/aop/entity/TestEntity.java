package nil.ed.sample.springboot.aop.entity;

import lombok.Data;

/**
 * 测试实体
 * @since 2019/07/15 11:54:47
 * @author lidelin
 */
@Data
public class TestEntity {
	private long id;

	private String content;

	private long updateTimestamp;

	private long createTimestamp;
}
