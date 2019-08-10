package nil.ed.springboot_servlet_sample.processor.fileitem;

import nil.ed.springboot_servlet_sample.processor.mem.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.ParameterParser;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author lidelin
 * @date 2019/07/24 19:33
 */
public abstract class AbstractMemoryFileItem implements FileItem {

    /**
     * 默认的字符集
     */
    private static final String DEFAULT_CHARSET = StandardCharsets.ISO_8859_1.name();

    private static final Charset DEFAULT_CHARSET_ENUM = StandardCharsets.ISO_8859_1;

    /**
     * 文件项头，关于文件名、文件类型、编码信息
     */
    private FileItemHeaders headers;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 解析的字符集
     */
    private String charset;

    /**
     * form filed的名字，input中的name属性
     */
    private String fieldName;

    private boolean isFormField;

    /**
     * 自定义文件阈值
     */
    protected int thredhold = -1;

    public AbstractMemoryFileItem(int thredhold, String contentType, String fileName) {
        this.thredhold = thredhold;
        this.contentType = contentType;
        this.fileName = fileName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    /**
     * 返回fileName
     *
     * @return
     */
    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getString(final String encoding) throws UnsupportedEncodingException {
        return new String(get(), encoding);
    }

    @Override
    public String getString() {
        byte[] rawData = get();
        String charset = getCharSet();
        try {
            return new String(rawData, charset);
        } catch (UnsupportedEncodingException ue) {
            return new String(rawData, DEFAULT_CHARSET_ENUM);
        }
    }

    /**
     * 返回解析字符串的字符集
     *
     * @return 如果request中不包含字符集，返回默认ISO_8859_1
     */
    public String getCharSet() {
        if (Objects.isNull(charset)) {
            ParameterParser parser = new ParameterParser();
            parser.setLowerCaseNames(true);
            // Parameter parser can handle null input
            Map<String, String> params = parser.parse(getContentType(), ';');
            charset = params.get("charset");
        }

        if (Objects.isNull(charset)) {
            charset = DEFAULT_CHARSET;
        }

        return charset;
    }

    @Override
    public void setHeaders(FileItemHeaders headers) {
        this.headers = headers;
    }

    @Override
    public FileItemHeaders getHeaders() {
        return headers;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean isFormField() {
        return isFormField;
    }

    @Override
    public void setFormField(boolean isFormField) {
        this.isFormField = isFormField;
    }

    /**
     * GC时资源清理
     */
    @Override
    public void delete() {
    }
}
