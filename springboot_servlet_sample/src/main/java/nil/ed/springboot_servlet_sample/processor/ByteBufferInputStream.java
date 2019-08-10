package nil.ed.springboot_servlet_sample.processor;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.IByteBufferPool;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author lidelin
 * @date 2019/07/25 17:47
 */
public class ByteBufferInputStream extends InputStream {
    private Iterator<Byte> iterator;
    private PageNode currentNode;
    private int offset;

    public ByteBufferInputStream(IByteBufferPool<PageNode> pool) {
        this.iterator = pool.byteIterator();
    }

    @Override
    public int read() throws IOException {
        if (!iterator.hasNext()) {
            return -1;
        }
        return 0xFF & iterator.next();
    }
}
