package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/07/25 13:32
 */
public abstract class AbstractByteBufferPool implements IByteBufferPool<PageNode> {
    protected IPageNodeFactory factory;

    AbstractByteBufferPool(IPageNodeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void putAt(int byteOffset, byte b) {

    }

    @Override
    public void putAt(int pageNo, int pageOffset, byte b) {

    }

    @Override
    public byte getAt(int byteOffset) {
        return 0;
    }

    @Override
    public byte getAt(int pageNo, int pageOffset) {
        return 0;
    }

    /**
     * 分配num个页面结点
     *
     * @param num
     */
    protected void allocate(int num) {
        IntStream.range(0, num)
                .mapToObj(i -> this.createPageNode())
                .forEach(this::addLast);
    }

    protected abstract void addLast(PageNode node);

    protected abstract PageNode createPageNode();
}
