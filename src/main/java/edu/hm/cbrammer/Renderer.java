package edu.hm.cbrammer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Renderer
{
    private final Object toRender;

    public Renderer(Object toRender)
    {
        this.toRender = toRender;
    }

    public String render() throws IllegalAccessException, ClassNotFoundException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append(String.format("Instance of %s:\n",toRender.getClass().getName()));
        Field[] fields = toRender.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            if(field.isAnnotationPresent(RenderMe.class))
            {
                RenderMe renderMeAnnotation = field.getAnnotation(RenderMe.class);
                final String with = renderMeAnnotation.with();
                System.out.println(with);
                if(!with.isEmpty())
                {
                    Class forNameClass = Class.forName(with);

                    Object newRenderer = forNameClass.newInstance();
                    Method method = newRenderer.getClass().getMethod("render",field.getType());

                    if(!method.isAccessible())
                        method.setAccessible(true);

                    Object returnValue = method.invoke(newRenderer, field.get(toRender));
                    if(returnValue.getClass() == String.class)
                    {
                        final String value = (String)returnValue;
                        buffer.append(String.format("%s (Type %s): %s\n",
                                field.getName(),
                                field.getType().getCanonicalName(),
                                value));
                    }
                }
                else
                {
                    renderFromField(toRender,field,buffer);
                }
            }
        }

        return buffer.toString();
    }

    private void renderFromField(Object toRender, Field field, StringBuffer buffer) throws IllegalAccessException
    {
        if(!field.isAccessible())
            field.setAccessible(true);
        buffer.append(String.format("%s (Type %s): %s\n",
                field.getName(),
                field.getType().getCanonicalName(),
                field.get(toRender).toString()
        ));
    }
}
