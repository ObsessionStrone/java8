package com.xb.stone;

import java.util.Arrays;

/**
 * Lambda表达式和函数式接口.
 *
 * Lambda表达式（也称为闭包）
 * 它允许我们将函数当成参数传递给某个方法，或者把代码本身当作数据处理
 *
 * @author xioabo.qin
 * @time 2018/5/23 13:37
 * @since JDK 1.8
 */
public class LambdaTest {

    public static void main(String[] args) {

        /**
         * 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成：
         */
        Arrays.asList( "a", "b", "d" ).forEach(e -> System.out.println( e ) );

        /**
         * 如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体
         */
        Arrays.asList( "a", "b", "d" ).forEach( e -> {
            System.out.print( e );
            System.out.print( e );
        } );

        // Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的）,下面两种是一样的效果
        //  int count = 0; //必须不可被后面的代码修改   // count++;
        String separator = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> {System.out.print( e + separator );
                } );

        final String separator1 = ",";
        Arrays.asList( "a", "b", "d" ).forEach(
                ( String e ) -> System.out.print( e + separator1 ) );


    }

    /**
     *  函数接口指的是只有一个函数的接口，这样的接口可以隐式转换为Lambda表达式
     */
    @FunctionalInterface
    public interface Functional {
        void method();
    }

    /**
     * 不过有一点需要注意，默认方法和静态方法不会破坏函数式接口的定义，因此如下的代码是合法的。
     */
    @FunctionalInterface
    public interface FunctionalDefaultMethods {
        void method();

        default void defaultMethod() {
        }
    }

}
