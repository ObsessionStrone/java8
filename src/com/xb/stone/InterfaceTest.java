package com.xb.stone;

import java.util.function.Supplier;

/**
 * 接口.
 *
 * @author xiaobo.qin
 * @time 2018/5/23 21:05
 * @since JDK 1.8
 */
public class InterfaceTest {

    /**
     * 默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要
     */
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    /**
     * Java 8带来的另一个有趣的特性是在接口中可以定义静态方法
     */
    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create( Supplier< Defaulable > supplier ) {
            return supplier.get();
        }
    }

    /**
     *  默认方法和静态方法的使用场景：
     * @param args
     */
    public static void main(String[] args) {
        Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
        System.out.println( defaulable.notRequired() );

        defaulable = DefaulableFactory.create( OverridableImpl::new );
        System.out.println( defaulable.notRequired() );
    }
}
