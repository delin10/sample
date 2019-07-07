package  nil.ed;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * StringTokenizer
 * delim为字符列表，分隔符只能用字符
 * returnDelims为true时，一个字符为一个token
 */
public class TestStringTokenizer
{
    @Test
    public void test() {
        StringTokenizer stringTokenizer=new StringTokenizer("AAAA,BBBB/C.E3E|D,.|F",",/.|",false);
//        内部调用了hasMoreToken
//        while (stringTokenizer.hasMoreElements()){
//            System.out.println(stringTokenizer.nextElement());
//        }
        while (stringTokenizer.hasMoreTokens()){
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
