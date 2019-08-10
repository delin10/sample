package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import java.util.Iterator;

/**
 * @author lidelin
 * @since 2019/07/25 13:57:29
 */
public interface IByteBufferPool<T> extends Iterable<T> {
    /**
     * 在可用的下一位写入字节
     *
     * @param b
     * @return
     */
    void put(byte b);

    void put(byte[] arr, int src, int len);

    /**
     * 在字节偏移量为byteOffset的位置写入字节，需要遍历页链表，性能低
     *
     * @param byteOffset 在池中的字节偏移量
     * @param b          字节数据
     * @return
     */
    void putAt(int byteOffset, byte b);

    /**
     * 在页号为pageNo的页里，页偏移量为pageOffset的位置写入字节
     *
     * @param pageNo     页号
     * @param pageOffset 页偏移量
     * @param b          字节数据
     * @return
     */
    void putAt(int pageNo, int pageOffset, byte b);

    /**
     * 获得最后一个字节
     *
     * @return
     */
    byte get();

    /**
     * 获得某个字节偏移量位置的字节
     *
     * @param byteOffset
     * @return
     */
    byte getAt(int byteOffset);

    /**
     * 获得某个页号下页面便宜量为pageOffset的字节
     *
     * @param pageNo     页号
     * @param pageOffset 页偏移量
     * @return
     */
    byte getAt(int pageNo, int pageOffset);

    /**
     * 获取池中页面数量
     *
     * @return
     */
    int getPageCount();

    /**
     * 获得page中的字节数
     *
     * @return
     */
    int getByteCount();

    Iterator<Byte> byteIterator();
}
