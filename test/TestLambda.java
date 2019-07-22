import java.util.function.*;
public class TestLambda{
	public static void main(String...args){
		call(System.out::println);//函数引用
		call(x->{});//显式定义lambda
		Runnable run=()->{};
	}
	
	public static void call(Consumer<Integer> consumer){
		int a=0;
		consumer.accept(a);
	}
}
//javap -c TestLambda.class
/*
PS C:\Users\lidelin\Desktop\2019\test> javap -c .\TestLambda.class
Compiled from "TestLambda.java"
public class TestLambda {
  public TestLambda();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String...);
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: dup
       4: invokevirtual #3                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
       7: pop
       8: invokedynamic #4,  0              // InvokeDynamic #0:accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
      13: invokestatic  #5                  // Method call:(Ljava/util/function/Consumer;)V
      16: invokedynamic #6,  0              // InvokeDynamic #1:accept:()Ljava/util/function/Consumer;
      21: invokestatic  #5                  // Method call:(Ljava/util/function/Consumer;)V
      24: invokedynamic #7,  0              // InvokeDynamic #2:run:()Ljava/lang/Runnable;
      29: astore_1
      30: return

  public static void call(java.util.function.Consumer<java.lang.Integer>);
    Code:
       0: iconst_0
       1: istore_1
       2: aload_0
       3: iload_1
       4: invokestatic  #8                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       7: invokeinterface #9,  2            // InterfaceMethod java/util/function/Consumer.accept:(Ljava/lang/Object;)V
      12: return
}
*/

//javap -v ./TestLambda.class
/*
PS C:\Users\lidelin\Desktop\2019\test> javap -v -p .\TestLambda
警告: 二进制文件.\TestLambda包含TestLambda
Classfile /C:/Users/lidelin/Desktop/2019/test/TestLambda.class
  Last modified 2019-7-15; size 1565 bytes
  MD5 checksum 8b54f9ce9f5270fbdcc254b7286b79c1
  Compiled from "TestLambda.java"
public class TestLambda
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #11.#27        // java/lang/Object."<init>":()V
   #2 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
   #3 = Methodref          #11.#30        // java/lang/Object.getClass:()Ljava/lang/Class;
						 // bootstrap methods中的索引	
   #4 = InvokeDynamic      #0:#36         // #0:accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
   #5 = Methodref          #10.#37        // TestLambda.call:(Ljava/util/function/Consumer;)V
   #6 = InvokeDynamic      #1:#39         // #1:accept:()Ljava/util/function/Consumer;
   #7 = InvokeDynamic      #2:#42         // #2:run:()Ljava/lang/Runnable;
   #8 = Methodref          #43.#44        // java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
   #9 = InterfaceMethodref #45.#46        // java/util/function/Consumer.accept:(Ljava/lang/Object;)V
  #10 = Class              #47            // TestLambda
  #11 = Class              #48            // java/lang/Object
  #12 = Utf8               <init>
  #13 = Utf8               ()V
  #14 = Utf8               Code
  #15 = Utf8               LineNumberTable
  #16 = Utf8               main
  #17 = Utf8               ([Ljava/lang/String;)V
  #18 = Utf8               call
  #19 = Utf8               (Ljava/util/function/Consumer;)V
  #20 = Utf8               Signature
  #21 = Utf8               (Ljava/util/function/Consumer<Ljava/lang/Integer;>;)V
  #22 = Utf8               lambda$main$1
  #23 = Utf8               lambda$main$0
  #24 = Utf8               (Ljava/lang/Integer;)V
  #25 = Utf8               SourceFile
  #26 = Utf8               TestLambda.java
  #27 = NameAndType        #12:#13        // "<init>":()V
  #28 = Class              #49            // java/lang/System
  #29 = NameAndType        #50:#51        // out:Ljava/io/PrintStream;
  #30 = NameAndType        #52:#53        // getClass:()Ljava/lang/Class;
  #31 = Utf8               BootstrapMethods
  #32 = MethodHandle       #6:#54         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #33 = MethodType         #55            //  (Ljava/lang/Object;)V
  #34 = MethodHandle       #5:#56         // invokevirtual java/io/PrintStream.println:(Ljava/lang/Object;)V
  #35 = MethodType         #24            //  (Ljava/lang/Integer;)V
  #36 = NameAndType        #57:#58        // accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
  #37 = NameAndType        #18:#19        // call:(Ljava/util/function/Consumer;)V
  #38 = MethodHandle       #6:#59         // invokestatic TestLambda.lambda$main$0:(Ljava/lang/Integer;)V
  #39 = NameAndType        #57:#60        // accept:()Ljava/util/function/Consumer;
  #40 = MethodType         #13            //  ()V
  #41 = MethodHandle       #6:#61         // invokestatic TestLambda.lambda$main$1:()V
  #42 = NameAndType        #62:#63        // run:()Ljava/lang/Runnable;
  #43 = Class              #64            // java/lang/Integer
  #44 = NameAndType        #65:#66        // valueOf:(I)Ljava/lang/Integer;
  #45 = Class              #67            // java/util/function/Consumer
  #46 = NameAndType        #57:#55        // accept:(Ljava/lang/Object;)V
  #47 = Utf8               TestLambda
  #48 = Utf8               java/lang/Object
  #49 = Utf8               java/lang/System
  #50 = Utf8               out
  #51 = Utf8               Ljava/io/PrintStream;
  #52 = Utf8               getClass
  #53 = Utf8               ()Ljava/lang/Class;
  #54 = Methodref          #68.#69        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #55 = Utf8               (Ljava/lang/Object;)V
  #56 = Methodref          #70.#71        // java/io/PrintStream.println:(Ljava/lang/Object;)V
  #57 = Utf8               accept
  #58 = Utf8               (Ljava/io/PrintStream;)Ljava/util/function/Consumer;
  #59 = Methodref          #10.#72        // TestLambda.lambda$main$0:(Ljava/lang/Integer;)V
  #60 = Utf8               ()Ljava/util/function/Consumer;
  #61 = Methodref          #10.#73        // TestLambda.lambda$main$1:()V
  #62 = Utf8               run
  #63 = Utf8               ()Ljava/lang/Runnable;
  #64 = Utf8               java/lang/Integer
  #65 = Utf8               valueOf
  #66 = Utf8               (I)Ljava/lang/Integer;
  #67 = Utf8               java/util/function/Consumer
  #68 = Class              #74            // java/lang/invoke/LambdaMetafactory
  #69 = NameAndType        #75:#79        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #70 = Class              #80            // java/io/PrintStream
  #71 = NameAndType        #81:#55        // println:(Ljava/lang/Object;)V
  #72 = NameAndType        #23:#24        // lambda$main$0:(Ljava/lang/Integer;)V
  #73 = NameAndType        #22:#13        // lambda$main$1:()V
  #74 = Utf8               java/lang/invoke/LambdaMetafactory
  #75 = Utf8               metafactory
  #76 = Class              #83            // java/lang/invoke/MethodHandles$Lookup
  #77 = Utf8               Lookup
  #78 = Utf8               InnerClasses
  #79 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #80 = Utf8               java/io/PrintStream
  #81 = Utf8               println
  #82 = Class              #84            // java/lang/invoke/MethodHandles
  #83 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #84 = Utf8               java/lang/invoke/MethodHandles
{
  public TestLambda();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 2: 0

  public static void main(java.lang.String...);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC, ACC_VARARGS
    Code:
      stack=2, locals=2, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: dup
         4: invokevirtual #3                  // Method java/lang/Object.getClass:()Ljava/lang/Class;
         7: pop
         8: invokedynamic #4,  0              // InvokeDynamic #0:accept:(Ljava/io/PrintStream;)Ljava/util/function/Consumer;
        13: invokestatic  #5                  // Method call:(Ljava/util/function/Consumer;)V
        16: invokedynamic #6,  0              // InvokeDynamic #1:accept:()Ljava/util/function/Consumer;
        21: invokestatic  #5                  // Method call:(Ljava/util/function/Consumer;)V
        24: invokedynamic #7,  0              // InvokeDynamic #2:run:()Ljava/lang/Runnable;
        29: astore_1
        30: return
      LineNumberTable:
        line 4: 0
        line 5: 16
        line 6: 24
        line 7: 30

  public static void call(java.util.function.Consumer<java.lang.Integer>);
    descriptor: (Ljava/util/function/Consumer;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: iconst_0
         1: istore_1
         2: aload_0
         3: iload_1
         4: invokestatic  #8                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
         7: invokeinterface #9,  2            // InterfaceMethod java/util/function/Consumer.accept:(Ljava/lang/Object;)V
        12: return
      LineNumberTable:
        line 10: 0
        line 11: 2
        line 12: 12
    Signature: #21                          // (Ljava/util/function/Consumer<Ljava/lang/Integer;>;)V

  private static void lambda$main$1();
    descriptor: ()V
    flags: ACC_PRIVATE, ACC_STATIC, ACC_SYNTHETIC
    Code:
      stack=0, locals=0, args_size=0
         0: return
      LineNumberTable:
        line 6: 0

  private static void lambda$main$0(java.lang.Integer);
    descriptor: (Ljava/lang/Integer;)V
    flags: ACC_PRIVATE, ACC_STATIC, ACC_SYNTHETIC
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LineNumberTable:
        line 5: 0
}
SourceFile: "TestLambda.java"
InnerClasses:
     public static final #77= #76 of #82; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #32 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #33 (Ljava/lang/Object;)V
      #34 invokevirtual java/io/PrintStream.println:(Ljava/lang/Object;)V
      #35 (Ljava/lang/Integer;)V
  1: #32 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #33 (Ljava/lang/Object;)V
      #38 invokestatic TestLambda.lambda$main$0:(Ljava/lang/Integer;)V
      #35 (Ljava/lang/Integer;)V
  2: #32 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #40 ()V
      #41 invokestatic TestLambda.lambda$main$1:()V
      #40 ()V
*/

//javap -p TestLambda.class
/*
PS C:\Users\lidelin\Desktop\2019\test> javap -p .\TestLambda.class
Compiled from "TestLambda.java"
public class TestLambda {
  public TestLambda();
  public static void main(java.lang.String...);
  public static void call(java.util.function.Consumer<java.lang.Integer>);
  private static void lambda$main$1();
  private static void lambda$main$0(java.lang.Integer);
}
*/

/*
MethodHandle
MethodType
MethodHandles.Lookup

*/