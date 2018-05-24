package com.xb.stone;

import java.util.function.Supplier;

/**
 * 函数式接口Supplier  以及 IntSupplier
 */  
public class Test12Supplier {  
    public static void main(String[] args) {  
        Supplier<String> supplier = () -> "hello world";  
  
        //get方法不接受参数，返回一个结果  
        System.out.println("supplier = [" + supplier.get() + "]");  
  
        //替代不接受参数的工厂方法  
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());  
  
        //因为Student的构造方法不接受参数，返回一个结果，符合Supplier接口的要求，可以简写如下：  
        Supplier<Student> studentSupplier2 = Student::new;  
        System.out.println(studentSupplier2.get());  
    }  
}  
  
