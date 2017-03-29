package edu.hm.cbrammer;

import java.util.*;

public class SomeClass
{
    @RenderMe
    private int foo;

    @RenderMe(with = "edu.hm.renderer.ArrayRenderer")
    int[] array = {1, 2, 3,};

    @RenderMe
    private Date date = new Date(123456789);

    public SomeClass(int foo)
    {
        this.foo = foo;
    }
}

