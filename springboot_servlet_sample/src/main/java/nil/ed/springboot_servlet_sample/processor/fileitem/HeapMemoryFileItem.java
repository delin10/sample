package nil.ed.springboot_servlet_sample.processor.fileitem;

import nil.ed.springboot_servlet_sample.processor.mem.Constants;

import java.io.*;
import java.util.Objects;

/**
 * @author lidelin
 * @date 2019/07/24 18:32
 */
public class HeapMemoryFileItem extends AbstractMemoryFileItem {
    /**
     * 最大文件限制
     */
    private static final int MAX_FILE_SIZE = 100 * Constants._1MB;
    /**
     * 默认文件大小阈值
     */
    private static final int DEFAULT_THREDHOLD = MAX_FILE_SIZE;
    /**
     * 文件流内存缓存
     */
    private byte[] fileCache;
    /**
     * 用于接收文件流
     * 只用一次
     */
    private ByteArrayOutputStream byteArrayOutputStream;

    public HeapMemoryFileItem(String contentType, String fileName) {
        this(DEFAULT_THREDHOLD, contentType, fileName);
    }

    public HeapMemoryFileItem(int thredhold, String contentType, String fileName) {
        super(thredhold, contentType, fileName);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!isInMemory()) {
            //抛出异常
            throw new IOException("No inputstream in memory");
        }
        if (Objects.isNull(fileCache)) {
            //抛出空异常
            fileCache = byteArrayOutputStream.toByteArray();
        }
        return new ByteArrayInputStream(fileCache);
    }

    @Override
    public boolean isInMemory() {
        // 未进行文件处理的情况
        return !(Objects.isNull(fileCache) && Objects.isNull(byteArrayOutputStream));
    }

    /**
     * 返回文件流中的字节数目，不代表磁盘中的实际
     * 占用大小
     *
     * @return
     */
    @Override
    public long getSize() {
        if (!isInMemory()) {
            throw new UnsupportedOperationException("No inputstream cache in memory");
        }
        return getCache().length;
    }

    @Override
    public byte[] get() {
        return getCache();
    }



    /**
     * @param file
     * @throws IllegalArgumentException file是一个路径，不撤销创建文件夹的操作
     * @throws IOException
     */
    @Override
    public void write(File file) throws Exception {
        if (!file.exists()) {
            file.mkdirs();
        }

        if (file.isDirectory()) {
            throw new IllegalArgumentException("Not allow to write to a directory!");
        }

        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] cache = get();
            outputStream.write(cache);
        }
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (Objects.isNull(byteArrayOutputStream)) {
            int expectedThredhold = thredhold < 0 ? DEFAULT_THREDHOLD : thredhold;
            byteArrayOutputStream = new ByteArrayOutputStream(expectedThredhold);
        }
        return byteArrayOutputStream;
    }

    /**
     * 如果未进行缓存，进行缓存
     *
     * @return NotNull, 文件字节数组
     */
    private byte[] getCache() {
        if (!isInMemory()) {
            throw new UnsupportedOperationException("No inputstream cache in memory");
        }

        if (Objects.isNull(fileCache)) {
            fileCache = byteArrayOutputStream.toByteArray();
        }

        return fileCache;
    }
}
