package nil.ed.springboot_servlet_sample.processor.impl;

import nil.ed.springboot_servlet_sample.processor.IMultiPartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author lidelin
 * @date 2019/07/24 18:22
 */
public abstract class ApacheFileUploadMultiPartResolverImpl implements IMultiPartResolver {
    private Map<String, String[]> stringPartsMap;
    private Map<String, InputStream[]> filePartsMap;

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
    public boolean isResolved() {
        return false;
    }
}
