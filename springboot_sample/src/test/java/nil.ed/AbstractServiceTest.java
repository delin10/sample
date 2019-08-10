package nil.ed;

import com.alibaba.fastjson.JSON;
import nil.ed.test.App;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class AbstractServiceTest {
    public void printAsJsonString(Object object) {
        System.out.println(JSON.toJSONString(object));
    }
}
