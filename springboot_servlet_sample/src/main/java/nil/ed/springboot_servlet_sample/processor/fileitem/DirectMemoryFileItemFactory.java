package nil.ed.springboot_servlet_sample.processor.fileitem;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

/**
 * @author lidelin
 * @date 2019/07/25 19:08
 */
public class DirectMemoryFileItemFactory implements FileItemFactory {
    @Override
    public FileItem createItem(String fieldName, String contentType, boolean isFormField, String fileName) {
        DirectMemoryFileItem fileItem = new DirectMemoryFileItem(contentType, fileName);
        fileItem.setFieldName(fieldName);
        fileItem.setFormField(isFormField);
        return fileItem;
    }
}
