package nil.ed.springboot.sample.request.controller;

import nil.ed.springboot.sample.request.entity.TestEntity;
import nil.ed.springboot.sample.request.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lidelin
 * @date 2019/07/26 17:35
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TransactionService transactionService;

    /**
     * 设置为text/plain，406 No Acceptable，返回结果只能为String类型
     *
     * @param response
     * @param requestBody
     * @param a
     * @param httpHeaders
     * @param headers
     * @param map
     * @return
     */
    @RequestMapping(value = "/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public TestEntity test(HttpServletResponse response,
                           @RequestParam(value = "a", required = false) Optional<Integer> avv,
                           @RequestPart(value = "a", required = false) String av,
                           @RequestBody(required = false) TestEntity requestBody,
                           @CookieValue(name = "a", required = false) String a,
                           @RequestHeader(value = "a", required = false) Integer value,
                           @RequestHeader HttpHeaders httpHeaders,
                           @RequestHeader Map<String, String> headers,
                           @RequestHeader MultiValueMap<String, String> map) {
        String result = map.entrySet().stream().map(Map.Entry::toString).collect(Collectors.joining(";\n"));
        transactionService.serviceInTranscation();
        // 这样就ok
        httpHeaders.set("gg", "hh");
        response.setHeader("cc", "xx");
        response.addCookie(new Cookie("a", "b"));
        System.out.println("cookie a :" + a);
        System.out.println(requestBody);
        TestEntity entity = new TestEntity();
        return entity;
    }
}
