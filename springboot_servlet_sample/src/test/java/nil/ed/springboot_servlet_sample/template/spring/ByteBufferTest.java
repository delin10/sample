package nil.ed.springboot_servlet_sample.template.spring;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/07/25 11:47
 */
public class ByteBufferTest {
    @Test
    public void test() {
        Class<?> clazz =null;
        Class<Object> c = (Class<Object>)clazz;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        IntStream.range(0, 5).forEach(i -> {
            System.out.println("index: " + i);
            System.out.println("remaining: " + buffer.remaining());
            System.out.println("position: " + buffer.position());
            System.out.println("limit: " + buffer.limit());
            System.out.println("capacity: " + buffer.capacity());
            buffer.put((byte) i);
        });
    }
}
