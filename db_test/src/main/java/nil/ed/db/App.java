package nil.ed.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App {
    /**
     * sql注入正则
     */
    private static final String SQL_REG = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
            + "(\\b(select|update|union|and|or|delete|insert|trancate|char|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

    /**
     * sql注入pattern
     */
    private static Pattern sqlPattern = Pattern.compile(SQL_REG, Pattern.CASE_INSENSITIVE);

    private static final Pattern pattern1 = Pattern.compile("1(?:23)");
    private static final Pattern pattern2 = Pattern.compile("1(23)");


    public static void main( String[] args ) {
        String target = "123";
        Matcher matcher = pattern1.matcher(target);
        int count = matcher.groupCount();
        System.out.println(count);

        Matcher matcher2 = pattern2.matcher(target);
        matcher2.matches();
        count = matcher2.groupCount();
        System.out.println(matcher2.group());
        IntStream.range(0,count+1)
                .mapToObj(matcher2::group)
                .forEach(System.out::println);
    }

    private boolean isValidSql(String str){
        return sqlPattern.matcher(str).find();
    }
}
