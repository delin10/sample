package nil.ed.sample.springmongo.model;

import lombok.Data;
import lombok.Getter;
import nil.ed.sample.springmongo.common.AutoIncKey;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidelin
 * @date 2019/07/22 14:10
 */
@Data
@Document("test_model")
public class TestModel implements Serializable {
    @Id
    @AutoIncKey
    private Long id;

    private String content;

    @Field("test_list")
    private List<String> testList;
}
