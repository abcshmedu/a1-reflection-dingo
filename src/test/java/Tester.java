import edu.hm.cbrammer.Renderer;
import edu.hm.cbrammer.SomeClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

/**
 * Created by cbram on 29.03.2017.
 */
public class Tester
{
    private SomeClass toRender;
    private Renderer renderer;

    @Before
    public void setUp()
    {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
    }


    @Test()
    public void SomeTest() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        String test = renderer.render();
        System.out.println(test);
        assertEquals("bla", test);
    }
}
