package nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception;

/**
 * @author lidelin
 * @date 2019/07/26 10:07
 */
public class PageOffsetOutOfBoundsException extends WithExceptionCodeIndexOutOfBoundsException {
    public PageOffsetOutOfBoundsException(ExceptionCodeEnum exceptionCode) {
        super(exceptionCode);
    }

    public PageOffsetOutOfBoundsException(ExceptionCodeEnum exceptionCode, String s) {
        super(exceptionCode, s);
    }
}
