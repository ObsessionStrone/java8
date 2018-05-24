package com.xb.stone;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 参数.
 *
 * @author QSH8523
 * @time 2018/5/24 8:39
 * @since JDK 1.8
 */
public class ParameterTest {


    public static void main(String[] args) throws Exception {

        // parameter 测试
        Method method = ParameterTest.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }

        // 类型推断 测试
        final Value< String > value = new Value<>();
        value.getOrDefault( "22", Value.defaultValue() );
    }
}

class Value< T > {
    public static< T > T defaultValue() {
        return null;
    }

    public T getOrDefault( T value, T defaultValue ) {
        return ( value != null ) ? value : defaultValue;
    }
}

