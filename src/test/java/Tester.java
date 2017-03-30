import edu.hm.cbrammer.Renderer;
import edu.hm.cbrammer.SomeClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by cbram on 29.03.2017.
 */
@RunWith(Parameterized.class)
public class Tester
{
    private SomeClass toRender;
    private Renderer renderer;
    private int input;
    private int expected;

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(0,1,2,3,4,5,6);
    }

    public Tester(int intput)
    {
        this.input = input;
        this.expected = input;
    }
    @Before
    public void setUp()
    {
        toRender = new SomeClass(input);
        renderer = new Renderer(toRender);
    }


    @Test()
    public void SomeTest() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        String test = renderer.render();
        System.out.println(test);
        assertEquals("Instance of edu.hm.cbrammer.SomeClass:\n" +
                "foo (Type int): " + expected + "\n" +
                "array (Type int[]): [1, 2, 3]\n" +
                "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n" +
                "doSomething (Return Type int): 42\n", test);
    }
}
