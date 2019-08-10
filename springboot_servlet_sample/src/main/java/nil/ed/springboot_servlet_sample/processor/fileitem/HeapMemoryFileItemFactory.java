package nil.ed.springboot_servlet_sample.processor.fileitem;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;

/**
 * @author lidelin
 * @date 2019/07/25 10:39
 */
public class HeapMemoryFileItemFactory implements FileItemFactory {
    @Override
    public FileItem createItem(String fieldName, String contentType, boolean isFormField, String fileName) {
        FileItem item = new HeapMemoryFileItem(contentType, fileName);
        item.setFormField(isFormField);
        item.setFieldName(fieldName);
        return item;
    }
}
