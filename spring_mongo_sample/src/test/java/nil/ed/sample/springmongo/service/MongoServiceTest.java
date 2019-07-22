package nil.ed.sample.springmongo.service;

import nil.ed.sample.springmongo.AbstractServiceTest;
import nil.ed.sample.springmongo.model.TestModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MongoServiceTest extends AbstractServiceTest {
    @Autowired
    private MongoService mongoService;

    @Test
    public void insert() {
        TestModel model = new TestModel();
        model.setContent("test");
        model.setTestList(Arrays.asList("a", "b", "c"));

        mongoService.insert(model);
    }

    @Test
    public void updateById() {
        TestModel model = new TestModel();
        model.setContent("aaaass");
        model.setTestList(Arrays.asList("a", "b", "c", "d", "es"));
    }

    @Test
    public void deleteById() {
        mongoService.deleteById(1L);
    }

    @Test
    public void listAllByPage() {
        printAsJsonString(mongoService.listAllByPage(1,10));
    }
}