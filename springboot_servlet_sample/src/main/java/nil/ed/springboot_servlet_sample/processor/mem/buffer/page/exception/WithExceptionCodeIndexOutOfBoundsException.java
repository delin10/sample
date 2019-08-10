package nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception;

/**
 * @author lidelin
 * @date 2019/07/26 10:14
 */
public class WithExceptionCodeIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * 异常错误码
     *
     * @see ExceptionCodeEnum
     */
    private ExceptionCodeEnum exceptionCode;

    public WithExceptionCodeIndexOutOfBoundsException(ExceptionCodeEnum exceptionCode) {
        super(exceptionCode.toString());
        this.exceptionCode = exceptionCode;
    }

    public WithExceptionCodeIndexOutOfBoundsException(ExceptionCodeEnum exceptionCode, String s) {
        super(exceptionCode + s);
        this.exceptionCode = exceptionCode;
    }
}
