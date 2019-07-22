package nil.ed.login.controller;

import nil.ed.login.annotation.ConditionRequired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lidelin
 * @date 2019/07/12 19:46
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @ConditionRequired(requireRegister = true)
    @GetMapping(value = "/testA", produces = "text/plain")
    public String testRequireConditionRegister() {
        return "testRequireConditionRegister";
    }

    @ConditionRequired(requireLogin = true)
    @GetMapping(value = "/testB", produces = "text/plain")
    public String testRequireConditionLogin() {
        return "testRequireConditionLogin";
    }

    @GetMapping(value = "/testC", produces = "text/plain")
    public String testNonRequireCondition() {
        return "testNonRequireCondition";
    }
}
