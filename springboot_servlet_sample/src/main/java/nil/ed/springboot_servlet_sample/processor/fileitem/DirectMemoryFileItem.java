package nil.ed.springboot_servlet_sample.processor.fileitem;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.AbstractByteBufferPool;
import nil.ed.springboot_servlet_sample.processor.mem.Constants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author lidelin
 * @date 2019/07/25 11:17
 */
public class DirectMemoryFileItem extends AbstractMemoryFileItem {
    /**
     * 最大文件限制
     */
    private static final int MAX_FILE_SIZE = 1 * Constants._1GB;
    /**
     * 默认文件大小阈值
     */
    private static final int DEFAULT_THREDHOLD = MAX_FILE_SIZE;

    private ByteBufferOutputStream byteBufferOutputStream;

    public DirectMemoryFileItem(String contentType, String fileName) {
        super(DEFAULT_THREDHOLD, contentType, fileName);
    }

    public DirectMemoryFileItem(int thredhold, String contentType, String fileName) {
        super(thredhold, contentType, fileName);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return ((ByteBufferOutputStream) getOutputStream()).getInputStream();
    }

    @Override
    public boolean isInMemory() {
        return byteBufferOutputStream != null;
    }

    @Override
    public long getSize() {
        return byteBufferOutputStream.getSize();
    }

    @Override
    public byte[] get() {
        if (!isInMemory()){
            throw new IllegalStateException();
        }
        return byteBufferOutputStream.toByteArray();
    }

    @Override
    public void write(File file) throws Exception {

    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (byteBufferOutputStream == null){
            byteBufferOutputStream = new ByteBufferOutputStream();
        }
        return byteBufferOutputStream;
    }
}
