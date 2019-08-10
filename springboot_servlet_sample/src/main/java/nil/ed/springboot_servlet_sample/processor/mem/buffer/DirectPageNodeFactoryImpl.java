package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.DirectPageNode;
import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

/**
 * @author lidelin
 * @date 2019/07/26 09:54
 */
public class DirectPageNodeFactoryImpl implements IPageNodeFactory {
    @Override
    public PageNode createPageNode(int pageSize) {
        return new DirectPageNode(pageSize);
    }
}
