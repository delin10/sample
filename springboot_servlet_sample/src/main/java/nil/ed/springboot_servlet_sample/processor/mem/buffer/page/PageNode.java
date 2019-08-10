package nil.ed.springboot_servlet_sample.processor.mem.buffer.page;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception.ExceptionCodeEnum;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.exception.PageOffsetOutOfBoundsException;

import java.nio.ByteBuffer;

/**
 * @author lidelin
 * @date 2019/07/25 13:25
 */
public abstract class PageNode {
    private final int pageSize;
    protected final ByteBuffer page;
    private PageNode next, prev;
    private int used, free;

    /**
     * 不进行null和pageSize小于0情况的检查
     *
     * @param pageSize 页面的大小
     * @param page     页Buffer
     */
    PageNode(int pageSize, ByteBuffer page) {
        this.free = this.pageSize = pageSize;
        this.page = page;
        this.used = 0;
    }

    /**
     * 获取链接页的下一个结点
     *
     * @return
     */
    public PageNode getNext() {
        return next;
    }

    /**
     * 设置下一个结点
     *
     * @param next
     */
    public void setNext(PageNode next) {
        this.next = next;
    }

    /**
     * 获取前驱结点
     *
     * @return
     */
    public PageNode getPrev() {
        return prev;
    }

    /**
     * 设置前驱结点
     *
     * @param prev
     */
    public void setPrev(PageNode prev) {
        this.prev = prev;
    }

    /**
     * 获得该结点的页面大小
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 获得页面已经使用的大小
     *
     * @return
     */
    public int getUsed() {
        return used;
    }

    /**
     * 获得页面的空闲容量
     *
     * @return
     */
    public int getFree() {
        return free;
    }

    /**
     * 页面是否已满
     *
     * @return
     */
    public boolean isFull() {
        return free == 0;
    }

    /**
     * 获得页面指定字节偏移量的字节
     *
     * @param index
     * @return
     */
    public byte get(int index) {
        if (index >= this.used) {
            throw new IndexOutOfBoundsException("Page index cannot greater than used bytes size！");
        }
        return page.get(index);
    }

    /**
     * 往buffer中追加字节，used的下一个可用字节
     *
     * @param b
     * @return
     */
    public int put(byte b) {
        int offset = page.position();
        put(offset, b);
        return offset;
    }

    /**
     * 数组arr中的从src至src + len(exclude)范围内的字节放入页中
     *
     * @param arr     原字节数组
     * @param src     src数组起始下标
     * @param len     需要执行put的数组长度
     * @param fullPut true - 如果数组长度大于页的空闲容量，填满页面，返回多余的部分；
     *                false - 不执行操作，返回原数组
     * @return
     */
    public byte[] put(byte[] arr, int src, int len, boolean fullPut) {
        //System.out.println(String.format("entry arr len: %d,before put, [used=%d, free=%d]", len, used, free));
        byte[] rest = arr;
        if (len < free) {
            page.put(arr, src, len);
            afterPut(len);
            rest = new byte[0];
        } else if (isFull()) {
            rest = arr;
        } else {
            if (fullPut) {
                page.put(arr, src, free);
                int restLen = len - free - src;
                rest = new byte[restLen];
                System.arraycopy(arr, src + free, rest, 0, restLen);
                afterPut(free);
            }
        }
        //System.out.println(String.format("entry arr len: %d,after put, [used=%d, free=%d]", len, used, free));
        return rest;
    }

    public void put(int offset, byte b) {
        if (offset >= this.pageSize) {
            throw new PageOffsetOutOfBoundsException(ExceptionCodeEnum.PAGE_PUT_EXP, "offset great than pageSize!");
        }
        page.put(offset, b);
        afterPut(1);
    }

    /**
     * 清除该页面
     *
     * @param copy
     * @return
     */
    public byte[] purge(boolean copy) {
        byte[] copyArray = copy ? getCopyPageArray() : null;
        page.rewind();
        this.free = this.pageSize;
        this.used = 0;
        return copyArray;
    }

    /**
     * 获取页面的字节拷贝
     *
     * @return
     */
    public byte[] getCopyPageArray() {
        int copySize = getUsed() + 1;
        byte[] pageCopy = new byte[copySize];
        batchCopyTo(pageCopy, 0, copySize);
        return pageCopy;
    }

    /**
     * put方法调用之后页面大小信息处理
     *
     * @param grow 增长大小
     */
    private void afterPut(int grow) {
        this.free -= grow;
        this.used += grow;
    }

    /**
     * 获取页面的原始数组
     * warning: 字节数组修改会反映到页面上
     * 只有HeapByteBuffer array返回真实操作数组
     *
     * @return
     */
    public void batchCopyTo(byte[] container, int src, int length) {
        if (length > getUsed()) {
            throw new IndexOutOfBoundsException();
        }
        page.rewind();
        // get根据position读取，读取前需要rewind
        page.get(container, src, length);
    }
}
