package com.yqw;

import javax.script.*;
import java.io.FileReader;
import java.util.Scanner;
public class ExecuJs {
    public static void main(String[] args) throws Exception {

        // 获取JS执行引擎
        ScriptEngine se = new ScriptEngineManager().getEngineByName("javascript");
        // 获取变量
        Bindings bindings = se.createBindings();
        bindings.put("number", 3);
        se.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        System.out.println("请输入数字");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println("输入的参数【" + a + "】 + 【" + b + "】");
            se.eval(new FileReader(ExecuJs.class.getClassLoader().getResource("fun.js").getPath()));
            // 是否可调用
            if (se instanceof Invocable) {
                Invocable in = (Invocable) se;
                Double result = (Double) in.invokeFunction("add", a, b);
                System.out.println("获得的结果：" + result);
            } else {
                System.out.println("不可调用");
            }
        }
    }
}