package edu.hm.cbrammer;

import java.util.*;

/**
 * A test class.
 */
public class SomeClass
{
    @RenderMe
    private int foo;

    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    private int[] array = {1, 2, 3,};

    @RenderMe
    private Date date = new Date(123456789);

    /**
     * Basic constructor.
     * @param foo A value.
     */
    public SomeClass(int foo)
    {
        this.foo = foo;
    }

    @RenderMe
    public int doSomething(){
        return 42;
    }
}

