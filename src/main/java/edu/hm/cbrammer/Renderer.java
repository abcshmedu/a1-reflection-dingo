package edu.hm.cbrammer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Basic renderer class.
 */
public class Renderer {
    private final Object toRender;

    /**
     * Constructor of a renderer.
     * @param toRender The object to render
     */
    public Renderer(Object toRender) {
        this.toRender = toRender;
    }

    /**
     * Returns the object as a string representation using reflection.
     * @return The object as a string representation.
     * @throws RuntimeException Thrown on any error
     */
    public String render() throws RuntimeException {
        StringBuffer buffer = new StringBuffer();

        try {
            buffer.append(String.format("Instance of %s:\n", toRender.getClass().getName()));
            Field[] fields = toRender.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                if (field.isAnnotationPresent(RenderMe.class)) {
                    RenderMe renderMeAnnotation = field.getAnnotation(RenderMe.class);
                    final String with = renderMeAnnotation.with();
                    System.out.println(with);
                    if (!with.isEmpty()) {
                        Class forNameClass = Class.forName(with);

                        Object newRenderer = forNameClass.newInstance();
                        Method method = newRenderer.getClass().getMethod("render", field.getType());

                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }

                        Object returnValue = method.invoke(newRenderer, field.get(toRender));
                        if (returnValue.getClass() == String.class) {
                            final String value = (String) returnValue;
                            buffer.append(String.format("%s (Type %s): %s\n",
                                    field.getName(),
                                    field.getType().getCanonicalName(),
                                    value));
                        }
                    }
                    else {
                        if (!field.isAccessible()) {
                            field.setAccessible(true);
                        }
                        buffer.append(String.format("%s (Type %s): %s\n",
                                field.getName(),
                                field.getType().getCanonicalName(),
                                field.get(toRender).toString()
                        ));
                    }
                }
            }

            Method[] methods = toRender.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RenderMe.class)) {
                    if (method.getParameterCount() == 0) {
                        if (!method.isAccessible()) {
                            method.setAccessible(true);
                        }
                        Object value = method.invoke(toRender);

                        buffer.append(String.format("%s (Return Type %s): %s\n",
                                method.getName(),
                                method.getReturnType().getCanonicalName(),
                                value.toString()));
                    }
                }
            }
        }
        catch (Exception exception) {
            throw new RuntimeException();
        }
        return buffer.toString();
    }
}
