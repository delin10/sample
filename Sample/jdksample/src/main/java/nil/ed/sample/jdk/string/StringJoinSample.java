package nil.ed.sample.jdk.string;

import javax.xml.stream.events.Characters;
import java.io.CharArrayReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lidelin
 * @date 2019/08/11 09:48
 */
public class StringJoinSample {
    public static void main(String[] args) {
        String[] test = {"1", "2", "3", "4", "5", "6"};
        StringBuilder builder = new StringBuilder();
        for (String s : test) {
            builder.append(s);
            builder.append(",");
        }

        String res = builder.length() == 0 ? "" : builder.delete(builder.length() - 1, builder.length()).toString();
        System.out.println(res);
        //或者
        StringBuilder formatterBuilder = new StringBuilder();
        IntStream.range(0, test.length).forEach(i -> formatterBuilder.append("%s,"));
        String formatter = builder.length() == 0 ? "" : formatterBuilder.delete(formatterBuilder.length() - 1, formatterBuilder.length()).toString();
        System.out.println(String.format(formatter, test));

        StringJoiner testJoiner = new StringJoiner(",");
        Arrays.stream(test).forEach(testJoiner::add);
        System.out.println(testJoiner.toString());

        String[] s = {"1", "2", "3"};
        List<String> ls = Arrays.asList("wo", "shi", "shei");
        // CharSequence
        System.out.println(String.join("+", s));
        System.out.println(String.join("+", ls));

        // java 8
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        StringJoiner joiner2 = new StringJoiner(",", "[", "]");
        String[] stringArr = {"a", "b", "c"};
        String[] stringArr2 = {"d", "e", "f"};
        Arrays.stream(stringArr).forEach(joiner::add);
        System.out.println(joiner.toString());
        Arrays.stream(stringArr2).forEach(joiner2::add);
        System.out.println(joiner.toString());
        System.out.println(joiner.merge(joiner2));

        char[] ip = {0, 0, 0, 0};
        StringJoiner ipJoiner = new StringJoiner(".");

        IntStream.range(0, ip.length)
                .mapToObj(i -> ip[i])
                .map(String::valueOf)
                .forEach(ipJoiner::add);
        // 如何将char数组stream化
            IntStream.range(0, ip.length)
                    .map(i -> ip[i])
                    .mapToObj(String::valueOf)
                    .forEach(ipJoiner::add);

        new String(ip).chars().mapToObj(String::valueOf).forEach(ipJoiner::add);
        System.out.println(ipJoiner.toString());

        // Collectors.joining内部实现使用的是StringJoiner
        System.out.println(new String(ip).chars().mapToObj(String::valueOf).collect(Collectors.joining(".")));
        System.out.println(IntStream.range(0, ip.length).map(i -> ip[i]).mapToObj(String::valueOf).collect(Collectors.joining(".")));
    }

    static class JoinerDelegate {

    }
}
