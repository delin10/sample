package nil.ed;

import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * 该样例用来测试注释
 *
 * @Ignore
 * @BeforeClass
 * @Before
 * @After
 * @AfterClass
 * @FixMethodOrder 其中@BeforeClass所注解的方法必须是静态方法
 * 其余不能为静态方法
 * 1、测试各个注解下方法的执行顺序
 * 2、测试在FixMethodOrder注解下不同值各个test执行的顺序
 * 3、定义多个@Before、@After、@BeforeClass、@AfterClass各个方法的执行顺序
 * 4、测试TestName获得自定义方法名称
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestJUnitAnnotation {
    @Rule
    public static TestName testName = new TestName();//The @Rule 'testName' must be public.The @Rule 'testName' must not be static.

    // Method testBeforeClass() should be static
    @BeforeClass
    public static void testBeforeClass0() {
        System.out.println("BeforeClass0");
    }

    @BeforeClass
    public static void testBeforeClass1() {
        System.out.println("BeforeClass1");
    }

    @AfterClass
    public static void testAfterClass0() {
        System.out.println("AfterClass0");
    }

    @AfterClass
    public static void testAfterClass1() {
        System.out.println("AfterClass1");
    }

    @Before
    public void testBefore0() {
        System.out.println(testName.getMethodName() + "Before0");
    }

    @Before
    public void testBefore1() {
        System.out.println("Before1");
    }

    @After
    public void testAfter0() {
        System.out.println("After0");
    }

    @After
    public void testAfter1() {
        System.out.println("After1");
    }

    @Test
    public void test0() {
        System.out.println("test0");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }


}
