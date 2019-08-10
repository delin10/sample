package nil.ed.springboot_servlet_sample.processor.mem.buffer;

import nil.ed.springboot_servlet_sample.processor.mem.buffer.page.PageNode;

public interface IPageNodeFactory {
    PageNode createPageNode(int pageSize);
}
