package nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception;

public enum ExceptionCodeEnum {
    PAGE_PUT_EXP((byte) 0b10000000, "PAGE_PUT", "invoke page.put failed");
    private static final String FORMAT_STR = "description: [ exceptionCode = %s, withType = %s, exceptionMessage = %s ]";
    byte exceptionCode;
    String withType;
    String message;

    ExceptionCodeEnum(byte exceptionCode, String withType, String message) {
        this.exceptionCode = exceptionCode;
        this.withType = withType;
        this.message = message;
    }

    public byte getExceptionCode() {
        return exceptionCode;
    }

    public String getWithType() {
        return withType;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_STR, exceptionCode, withType, message);
    }
}
