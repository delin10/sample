package nil.ed.springboot_servlet_sample.processor;

import org.apache.commons.fileupload.FileItem;

public interface Handler {
	public void handle(FileItem item, int i);
}