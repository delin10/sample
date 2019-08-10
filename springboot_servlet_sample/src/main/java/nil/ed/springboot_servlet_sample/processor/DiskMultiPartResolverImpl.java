package nil.ed.springboot_servlet_sample.processor;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author lidelin
 * @date 2019/07/26 16:38
 */
public class DiskMultiPartResolverImpl implements IMultiPartResolver {
    @Override
    public void resolve(HttpServletRequest request) {

    }

    @Override
    public Map<String, List<String>> getStringParts() {
        return null;
    }

    @Override
    public Map<String, List<InputStream>> getFileParts() {
        return null;
    }

    @Override
    public List<FileItem> getItems() {
        return null;
    }

    @Override
    public boolean isResolved() {
        return false;
    }
}
