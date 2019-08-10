package nil.ed.springboot_servlet_sample.processor.mem.buffer.page;

import java.nio.ByteBuffer;

/**
 * @author lidelin
 * @date 2019/07/26 09:57
 */
public class CustomPageNode extends PageNode {

    public CustomPageNode(ByteBuffer page) {
        super(page.capacity(), page);
    }
}
