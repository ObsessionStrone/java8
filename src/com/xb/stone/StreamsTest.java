package com.xb.stone;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ${TYPE_NAME} 修改为中文名称.
 *
 * @author QSH8523
 * @time 2018/5/24 8:58
 * @since JDK 1.8
 */
public class StreamsTest{

    private enum Status {
        OPEN, CLOSED
    };

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    final static Collection< Task > tasks = Arrays.asList(
            new Task( Status.OPEN, 5 ),
            new Task( Status.OPEN, 13 ),
            new Task( Status.CLOSED, 8 )
    );

    public static void main(String[] args) throws Exception{

        final long totalPointsOfOpenTasks = tasks
                .stream()
                .filter( task -> task.getStatus() == Status.OPEN )
                .mapToInt( Task::getPoints ) // 可以替换为 task -> task.getPoints()
                .sum();

        System.out.println( "Total points: " + totalPointsOfOpenTasks );

        /**
         *  steam的另一个价值是创造性地支持并行处理（parallel processing）
         */
        final double totalPoints = tasks
                .stream()
                .parallel()
                .map( task -> task.getPoints() ) // or map( Task::getPoints )
                // 初始值,计算模型 , .reduce( 0, (sum, item) -> sum + item )
                .reduce( 0, Integer::sum );

        System.out.println( "Total points (all tasks): " + totalPoints );

        // Calculate the weight of each tasks (as percent of total points)
        final Collection< String > result = tasks
                .stream()                                        // Stream< String >
                .mapToInt( Task::getPoints )                     // IntStream
                .asLongStream()                                  // LongStream
                .mapToDouble( points -> points / totalPoints )   // DoubleStream
                .boxed()                                         // Stream< Double >
                .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
                .mapToObj( percentage -> percentage + "%" )      // Stream< String>
                .collect( Collectors.toList() );                 // List< String >

        System.out.println( result );

        /**
         *  Steam API不仅可以作用于Java集合，传统的IO操作（从文件或者网络一行一行得读取数据）可以受益于steam处理
         */
        String filename = StreamsTest.class.getResource("StreamsTest.class").getFile();

        final Path path = new File( filename ).toPath();
        try( Stream< String > lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
            lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
        }
    }
}
