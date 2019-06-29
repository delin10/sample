package nil.ed.controller;

import nil.ed.bean.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private HelloWorldBean bean;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayhello(){
        bean.hello();
        return "ok";
    }

    @RequestMapping("/hello2")
    @ResponseBody
    public String sayhello2(){
        bean.hello();
        return "ok";
    }
}
