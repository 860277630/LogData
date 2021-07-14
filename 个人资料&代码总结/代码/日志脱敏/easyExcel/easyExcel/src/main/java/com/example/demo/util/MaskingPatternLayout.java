package com.example.demo.util;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaskingPatternLayout extends PatternLayout {
    /*
     * 日志脱敏开关
     * */
    private static Boolean converterCanRun = Boolean.TRUE;

    /*
     * list  用来存储正则表达式以及脱敏范围
     * */
    private static List<String> regulars = new ArrayList<>();

    public void addMaskPattern(String maskPattern) {
        System.out.println(maskPattern);
        regulars.add(maskPattern);
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        return maskMessage(super.doLayout(event));
    }

    private String maskMessage(String message) {
        //创建二维数组  用来存储  获取到的配置文件
        String[][] values = new String[regulars.size()][3];
        //然后依次分解 存入数组中
        for(int order = 0;order < regulars.size();order++){
            String tmp = regulars.get(order);
            //以---（英文）为分隔符  然后进行存储
            String[] regs = tmp.split("---");
            if(regs.length == 3){
                values[order] = regs;
            }

        }

        //存入二维数组后  遍历  进行正则的匹配
        if(!converterCanRun){
            return message;
        }
        for(int order = 0; order <values.length ;order++){
            if(message.contains(values[order][0])){
                Matcher matcher = Pattern.compile(values[order][1]).matcher(message);
                message = matcher.replaceAll(values[order][2]);
            }
        }
        return message;
    }
}
