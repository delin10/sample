package nil.ed.springboot_servlet_sample.processor;

import nil.ed.springboot_servlet_sample.processor.fileitem.HeapMemoryFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lidelin
 * @date 2019/07/26 10:54
 */
public class MultiPartResolverImpl implements IMultiPartResolver {
    enum FieldType {STRING, FILE}

    ;
    private FileItemFactory factory;
    private List<FileItem> items;
    private Map<FieldType, List<FileItem>> itemsCache;
    private Map<String, List<String>> stringPartsCache;
    private Map<String, List<InputStream>> filePartsCache;
    private boolean resolved;

    public MultiPartResolverImpl(FileItemFactory factory) {
        this.factory = factory;
    }

    @Override
    public void resolve(HttpServletRequest request) {
        FileUpload fileUpload = new ServletFileUpload(factory);
        try {
            items = fileUpload.parseRequest(new ServletRequestContext(request));
            resolved = true;
        } catch (FileUploadException e) {
            throw new RuntimeException("MultiPartResolveException");
        }
    }

    @Override
    public Map<String, List<String>> getStringParts() {
        checkState();
        analyzeItems();
        if (stringPartsCache == null) {
            stringPartsCache = itemsCache.entrySet().stream().filter(fieldTypeListEntry -> fieldTypeListEntry.getKey() == FieldType.STRING)
                    .flatMap(stringParts -> stringParts.getValue().stream())
                    .collect(Collectors.groupingBy(FileItem::getFieldName,
                            Collectors.mapping(FileItem::getString, Collectors.toList())));
        }
        return stringPartsCache;
    }

    @Override
    public Map<String, List<InputStream>> getFileParts() {
        checkState();
        analyzeItems();
        if (filePartsCache == null) {
            // CollectorsX
            filePartsCache = itemsCache.entrySet().stream().filter(fieldTypeListEntry -> fieldTypeListEntry.getKey() == FieldType.FILE)
                    .flatMap(fileParts -> fileParts.getValue().stream())
                    .collect(Collectors.groupingBy(FileItem::getFieldName,
                            Collectors.mapping(fileItem -> {
                                try {
                                    return fileItem.getInputStream();
                                } catch (IOException e) {
                                    return null;
                                }
                            }, Collectors.toList())));
        }
        return filePartsCache;
    }

    @Override
    public List<FileItem> getItems() {
        checkState();
        return items;
    }

    @Override
    public boolean isResolved() {
        return resolved;
    }

    private void analyzeItems() {
        checkState();

        if (itemsCache != null) {
            return;
        }

        itemsCache = items.stream().collect(Collectors.groupingBy(
                fileItem -> fileItem.isFormField() ? FieldType.STRING : FieldType.FILE,
                HashMap::new,
                Collectors.mapping(fileItem -> fileItem, Collectors.toList())));
    }

    private void checkState() {
        if (!isResolved()) {
            throw new RuntimeException("MultiPartResolveException");
        }
    }
}
