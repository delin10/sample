import java.util.*;
import java.lang.reflect.*;
public class Closure{
	private static Runnable run;
	public static void main(String...args)throws Exception{
		work();
		//为了证明调用栈之外还能引用
		run.run();
		Class<?> clazz = run.getClass();

		System.out.println("run的类型："+clazz+", 父类："+clazz.getSuperclass()+", 实现的接口列表：");
		Class<?>[] interfaces = clazz.getInterfaces();
		Arrays.stream(interfaces).forEach(System.out::println);
		
		Class<?> enclosing = clazz.getDeclaringClass();
		System.out.println("外部类为：" + enclosing);
		System.out.println("StaticInner外部类为：" + StaticInner.class.getDeclaringClass());
		System.out.println("NonStaticInner外部类为：" + NonStaticInner.class.getDeclaringClass());
		System.out.println("包路径：" + clazz.getPackage());
		System.out.println("Closure的包路径："+Closure.class.getPackage());
		
		Field[] fs = clazz.getDeclaredFields();
		Method[] methods = clazz.getDeclaredMethods();
		System.out.println("通过反射获取其成员变量和值：");
		Arrays.stream(fs).map(f->{
			try{
				f.setAccessible(true);
				return String.format("%s=%s",f,f.get(run));
			}catch(Exception e){
				return null;
			}
		}).forEach(System.out::println);
		System.out.println(clazz+"方法列表；");
		Arrays.stream(methods).forEach(System.out::println);
	}
	
	public static void work(){
		// Java lambda闭包
		// 如果函数调用栈撤回那么这两个变量存放在何处？
		// 不撤回函数调用栈管理会相当复杂
		final Object obj = new Object();
		int a = 9;
		final Object obj1 = new Object();
		work(()->{
			System.out.println("输出引用的三个外部变量：（以----区分）");
			System.out.println(obj + " ---- " + obj1 + " ----- " + a);
		});
	}
	
	public static void work(Runnable run){
		Closure.run = run;
		run.run();
	}
	
	//用于证明getDeclaringClass可以获取到外部类
	//静态内部类
	public static class StaticInner{
		
	}
	//非静态内部类
	public class NonStaticInner{
		
	}
}

