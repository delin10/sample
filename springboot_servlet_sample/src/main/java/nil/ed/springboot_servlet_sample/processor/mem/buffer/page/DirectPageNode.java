package nil.ed.springboot_servlet_sample.processor.mem.buffer.page;

import java.nio.ByteBuffer;

/**
 * @author lidelin
 * @date 2019/07/25 13:26
 */
public class DirectPageNode extends PageNode {
    public DirectPageNode(int pageSize) {
        super(pageSize, ByteBuffer.allocateDirect(pageSize));
    }
}
