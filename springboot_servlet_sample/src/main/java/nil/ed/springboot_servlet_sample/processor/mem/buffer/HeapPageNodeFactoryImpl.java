package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.HeapPageNode;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

/**
 * @author lidelin
 * @date 2019/07/26 09:55
 */
public class HeapPageNodeFactoryImpl implements IPageNodeFactory {
    @Override
    public PageNode createPageNode(int pageSize) {
        return new HeapPageNode(pageSize);
    }
}
