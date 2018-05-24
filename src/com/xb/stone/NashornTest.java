package com.xb.stone;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Nashorn JavaScript引擎.
 *
 * @author xiaobo.qin
 * @time 2018/5/24 9:30
 * @since JDK 1.8
 */
public class NashornTest {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }
}
