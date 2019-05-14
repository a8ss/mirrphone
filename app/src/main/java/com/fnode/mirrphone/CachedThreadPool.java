package com.fnode.mirrphone;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void submit(Runnable runnable){
        executorService.submit(runnable);
    }
}
