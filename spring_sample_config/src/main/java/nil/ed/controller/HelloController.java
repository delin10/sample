package nil.ed.controller;

import nil.ed.bean.HelloWorldBean;
import nil.ed.entities.TestEntity;
import nil.ed.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private HelloWorldBean bean;

    @RequestMapping("/hello.do")
    @ResponseBody
    public String sayhello() {
        bean.hello();
        return "ok";
    }

    @RequestMapping("/hello2.do")
    @ResponseBody
    public String sayhello2() {
        bean.hello();
        return "ok";
    }

    @Autowired
    private JdbcRepository jdbcRepository;
    @RequestMapping("/test.do")
    @ResponseBody
    public List jdbcTemplate() {
        jdbcRepository.addObject();
        return jdbcRepository.listObjects();
    }
}
