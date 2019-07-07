package nil.ed;

import org.junit.Test;

public class StringAppendTest {
    @Test
    public void test() {
        String str1="1",str2="2",str3="3",res=str1+str2+str3;

        res="";

        for (int i=0;i<200;++i){
            res+=i;
        }
    }
}
