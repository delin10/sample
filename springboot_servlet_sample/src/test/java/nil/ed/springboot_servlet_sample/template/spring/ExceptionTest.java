package nil.ed.springboot_servlet_sample.template.spring;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception.ExceptionCodeEnum;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception.PageOffsetOutOfBoundsException;
import org.junit.Test;

/**
 * @author lidelin
 * @date 2019/07/26 10:09
 */
public class ExceptionTest {
    @Test
    public void test() {
        throw new PageOffsetOutOfBoundsException(ExceptionCodeEnum.PAGE_PUT_EXP, "offset great than pageSize!");
    }
}
