package nil.ed.springboot_servlet_sample.controller;

import com.sun.javafx.iio.ImageStorage;
import nil.ed.springboot_servlet_sample.validator.RequestParamValidator;
import nil.ed.springboot_servlet_sample.validator.annotation.Anno;
import org.springframework.core.MethodIntrospector;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.validation.Constraint;
import javax.validation.constraints.Min;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author lidelin
 * @date 2019/07/24 16:42
 */
@RestController
@Validated
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test")
    public String test(@RequestPart("image") BufferedImage image) throws Exception {
        /**
         * It's Ok
         */
        return "" + ImageIO.write(image, "PNG", new File("D:/temp/image/"));
    }

    @RequestMapping("/validate")
    public String validate(@Anno @RequestParam Integer v) throws Exception {
        return "ok";
    }
}
