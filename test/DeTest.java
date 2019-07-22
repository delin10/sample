import java.util.Arrays;
import java.util.Iterator;
import java.util.List;






public class DeTest
{
  public static void main(String... paramVarArgs) {
    List list = Arrays.asList(new Integer[] { null, null, null, null, (new Integer[5][3] = (new Integer[5][2] = (new Integer[5][1] = (new Integer[5][0] = Integer.valueOf(1)).valueOf(2)).valueOf(3)).valueOf(4)).valueOf(5) });
    
    for (Iterator iterator = list.iterator(); iterator.hasNext(); i = ((Integer)iterator.next()).intValue());
  }
}