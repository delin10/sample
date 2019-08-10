import java.util.*;
public class ForeachClass{
	public static void main(String...args){
		int[] a = {1,2,3,4,5};
		
		List<Integer> ls = Arrays.asList(1,2,3,4,5);
		
		label:for (int x : a);
		
		for (int x : ls);
	}
}

/*
int i = 0;
int len = arr.length;
while(true){
	if (i == len){
		break;
	}
	// 处理元素
	++i;
}
70: arraylength
71: istore        4
73: iconst_0
74: istore        5
76: iload         5
78: iload         4
80: if_icmpge     95
83: aload_3
84: iload         5
86: iaload
87: istore        6
89: iinc          5, 1
92: goto          76
*/

/*
for (Iterator iterator = list.iterator(); iterator.hasNext(); i = ((Integer)iterator.next()).intValue());

96: invokeinterface #5,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
101: astore_3
102: aload_3
103: invokeinterface #6,  1            // InterfaceMethod java/util/Iterator.hasNext:()Z
108: ifeq          128
111: aload_3
112: invokeinterface #7,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
117: checkcast     #2                  // class java/lang/Integer
120: invokevirtual #8                  // Method java/lang/Integer.intValue:()I
123: istore        4
125: goto          102
*/