package edu.hm.cbrammer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interface which marks a field / method to be rendered.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RenderMe {
    /**
     * Defines a special renderer for the field / method.
     * @return The name of the class.
     */
    String with() default "";
}
