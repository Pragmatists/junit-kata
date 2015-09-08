package $1_hello;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;


public class BeforeAndAfterTest {

    /**
     * -- Jaka będzie kolejność wywołań metod adnotowanych @BeforeClass, @Before, @AfterClass, @After ?
     * -- Ile razy wywołane zostaną metody @Before i @After oraz @BeforeClass, @AfterClass ?
     * -- Jaką wartość countera wypiszą metody testowe?
     * <p>
     * Uruchom ten test i przyjrzyj sie outputowi na konsoli.
     * Eclipse: "Alt+Shift+X, ,T" , ponowne uruchomienie: Ctrl + F11
     * Idea: "Ctrl + Shift + F10" , ponowne uruchomienie: Shift + F10 lub Ctrl +F5
     */

    private int counter = 42;

    @BeforeClass
    public static void setUpContext() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void tearDownForTheWholeClass() {
        System.out.println("afterClass");
    }

    @Before
    public void commonSetupForEachTestMethod() {
        System.out.println("before");
    }

    @After
    public void commonTearDownForEachTestMethod() {
        System.out.println("after");
    }

    @Test
    public void test1() throws Exception {
        System.out.println("test1: " + counter++);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("test2: " + counter++);
    }
}
