package com.example.demo.utils;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MdcThreadUtils {

    /*
    * 以runnable形式  维持异步中的MDC传输
    * */

    public static void threadExecute(Runnable command){

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        executorService.execute(() ->{
            MDC.setContextMap(contextMap);

            try {
                command.run();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                contextMap.clear();
            }
        });
        executorService.shutdown();
    }


}
