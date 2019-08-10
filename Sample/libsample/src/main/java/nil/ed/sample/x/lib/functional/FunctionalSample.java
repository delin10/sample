package nil.ed.sample.x.lib.functional;

import static io.vavr.API.*;

import io.vavr.*;

/**
 * @author lidelin
 * @date 2019/08/08 19:00
 */
public class FunctionalSample {
    public static void main(String[] args) {
        int i = 0;
        String v = API.Match(i).of(Case($(0), "one"));
        System.out.println(v);
    }
}
