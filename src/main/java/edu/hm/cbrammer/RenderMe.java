package edu.hm.cbrammer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe
{
    String with() default "";
}
