package com.xb.stone;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base64 demo.
 *
 * @author xiaobo.qin
 * @time 2018/5/24 9:34
 * @since JDK 1.8
 */
public class Base64sTest {

    public static void main(String[] args) {
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println( encoded );

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );
        System.out.println( decoded );
    }
}
