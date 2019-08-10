package nil.ed.springboot_servlet_sample.processor.fileitem;

import nil.ed.springboot_servlet_sample.processor.ByteBufferInputStream;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.AbstractByteBufferPool;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.DirectByteBufferPoolImpl;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.IByteBufferPool;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

import java.io.*;
import java.util.Iterator;

/**
 * @author lidelin
 * @date 2019/07/25 11:25
 */
public class ByteBufferOutputStream extends OutputStream {
    private static final int DEFAULT_SIZE = 1;

    private IByteBufferPool<PageNode> pool;

    public ByteBufferOutputStream() {
        pool = new DirectByteBufferPoolImpl(DEFAULT_SIZE);
    }

    @Override
    public void write(int b) {
        pool.put((byte) b);
    }

    @Override
    public void write(byte[] b, int off, int len) {
        pool.put(b, off, len);
    }

    public void writeTo(OutputStream out) throws IOException {
    }

    public void reset() {

    }

    public byte[] toByteArray() {
        //System.out.println("start to invoke toByteArray");
        Iterator<PageNode> iterator = pool.iterator();
        int byteCount = pool.getByteCount();
        byte[] bytes = new byte[byteCount];
        int originPos = 0;
        while (iterator.hasNext()) {
            PageNode node = iterator.next();
            //System.out.println(String.format("originPos = %s, used = %s, free = %s,byteCount = %s", originPos, node.getUsed(), node.getFree(), byteCount));
            node.batchCopyTo(bytes, originPos, node.getUsed());
            originPos += node.getUsed();
        }
        return bytes;
    }

    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String toString(String charsetName) throws UnsupportedEncodingException {
        return null;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    @Override
    public void flush() {

    }

    public InputStream getInputStream() {
        return new ByteBufferInputStream(pool);
    }

    public int getSize() {
        return pool.getByteCount();
    }
//    private void ensureCapacity(int preparedSize) {
//        if (storageBuffer.remaining() > preparedSize) {
//            return;
//        }
//        int growSize = BUFFER_SIZE;
//        // 把下次分配内存合并到此次
//        boolean arriveEagerThredhold = (BUFFER_SIZE - buffer.position()) + storageBuffer.remaining() < EAGER_THREDHOLD;
//        if (isEagerGrow && arriveEagerThredhold) {
//            growSize = growSize + BUFFER_SIZE;
//        }
//
//    }
}
