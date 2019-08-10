import java.util.*;
public class TryBlockClass{
	public static void main(String...args)throws Exception{
		try{
			Object a = new Object();
			throw new Exception();
		}catch(Exception e){
			Integer i = 1;
			throw e;
		}finally{
			try{
				System.out.println("ok");
			}finally{
				System.err.println("no");
			}
		}
	}
}