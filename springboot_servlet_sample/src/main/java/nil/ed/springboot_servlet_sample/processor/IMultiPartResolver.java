package nil.ed.springboot_servlet_sample.processor;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author lidelin
 * @date 2019/07/24 18:07
 */
public interface IMultiPartResolver {
    /**
     * 解析request
     *
     * @param request
     */
    void resolve(HttpServletRequest request);

    /**
     * 获取字符串域
     *
     * @return
     */
    Map<String, List<String>> getStringParts();

    /**
     * 获取文件域
     *
     * @return
     */
    Map<String, List<InputStream>> getFileParts();

    /**
     * 获取string和file part的列表
     *
     * @return
     */
    List<FileItem> getItems();

    /**
     * 是否解析成功，调用其他函数必须返回true
     * @return
     */
    boolean isResolved();
}
