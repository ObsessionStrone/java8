package com.xb.stone;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamTest.
 *
 * 聚合操作是Java 8针对集合类，使编程更为便利的方式，可以与Lambda表达式一起使用，达到更加简洁的目的。

 * 前面例子中，对聚合操作的使用可以归结为3个部分：

 * 创建Stream:通过stream()方法，取得集合对象的数据集。
 * Intermediate:通过一系列中间（Intermediate）方法，对数据集进行过滤、检索等数据集的再次处理。如使用filter()方法来对数据集进行过滤。
 * Terminal通过最终（terminal）方法完成对数据集中元素的处理。如上例中，使用forEach()完成对过滤后元素的打印。
 *
 * 在一次聚合操作中，可以有多个Intermediate，但是有且只有一个Terminal。也就是说，在对一个Stream可以进行多次转换操作，
 * 并不是每次都对Stream的每个元素执行转换。并不像for循环中，循环N次，其时间复杂度就是N。转换操作是lazy(惰性求值)的，只
 * 有在Terminal操作执行时，才会一次性执行。可以这么认为，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，
 * 在 Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。
 *
 * @author xiaobo.qin
 * @time 2018/5/23 10:49
 * @since JDK 1.8
 */
public class StreamTest {

    static List<Student> list = Arrays.asList(
            new Student(1, "A", "M", 184),
            new Student(2, "B", "G", 163),
            new Student(3, "C", "M", 175),
            new Student(4, "D", "G", 158),
            new Student(5, "E", "M", 170));

    public static void main(String[] args) {
        counts();

        //1.第一种:通过collection集合提供的stream方法生成
        List<String> list = Arrays.asList("1","2","3","4");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::print);

        //2.第二种:通过Arrays提供的stream方法生成
        String[] s = new String[]{"1","2","3","4"};
        Stream<String> stream2 = Arrays.stream(s);
        stream2.forEach(System.out::print);

        //3.第三种：利用steam的静态方法of
        Stream<String> stream3 = Stream.of("1","2","3","4");
        stream3.forEach(System.out::print);

        //4.第四种：创建无限流
        //选代流
        //Stream<Integer> stream4 = Stream.iterate(0,(x)->x+2);
        //stream4.forEach(System.out::print);
        Random random = new Random();
        IntStream intStream = random.ints(0, 100);
        intStream.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(Math::random);
        stream5.limit(5).forEach(System.out::print);
    }

    /**
     *  像filter这样只描述Stream，最终不产生新集合的方法叫作惰性求值方法；而像count这样最终会从Stream产生值的方法叫作及早求值方法
     *  在一个Stream操作中，可以有多次惰性求值，但有且仅有一次及早求值。
     */
    public static void counts(){
        long count = list.stream()
                .filter(s ->
                {
                    System.out.println(s.getName());
                    return s.getHeight() > 170;
                })
                .count();
        System.out.println("res count: "+count);

        String res = IntStream.range(0, 100).summaryStatistics().toString();
        System.out.println("statistics: "+res);
    }

    /**
     *  stream 创建
     */



}
