package nil.ed.springboot_servlet_sample.template.spring;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;

/**
 * @author lidelin
 * @date 2019/08/01 15:36
 */
public class ImageIOSupportTypeTest {
    /**
     * [image/png,image/vnd.wap.wbmp,image/x-png,image/jpeg,image/bmp,image/gif]
     * [image/vnd.wap.wbmp,image/png,image/x-png,image/jpeg,image/bmp,image/gif]
     * [JPG,jpg,bmp,BMP,gif,GIF,WBMP,png,PNG,wbmp,jpeg,JPEG]
     */
    @Test
    public void test() {
        String readerTypes = Arrays.stream(ImageIO.getReaderMIMETypes())
                .collect(joining(",","[","]"));
        String writerTypes = Arrays.stream(ImageIO.getWriterMIMETypes())
                .collect(joining(",","[","]"));
        String formatTypes = Arrays.stream(ImageIO.getWriterFormatNames())
                .collect(joining(",","[","]"));
        System.out.println(readerTypes);
        System.out.println(writerTypes);
        System.out.println(formatTypes);
    }
}
