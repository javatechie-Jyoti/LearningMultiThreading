package com.multithreading.executors;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.io.FileNotFoundException;
import java.util.concurrent.*;

public class RunnableVsCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //executorService.submit(new LoggerTask());

        Future<Integer> future = executorService.submit(new RandomResultTask());
        System.out.println(future.get());
        //System.out.println("Waiting for Callable task to complete");
        //System.out.println("All tasks completed");
        //If above future.get() throws an exception which is not handled, then below line to shutdonw the executorService will never get invoked and program keeps running.
        executorService.shutdownNow();
    }
}

class RandomResultTask implements Callable {
    public Integer call() throws Exception{
       // Thread.sleep(5000);
         throw new FileNotFoundException();
        //return new Integer(5);
    }
}

class LoggerTask implements Runnable{
    public void run(){
        System.out.println("Logging Thread Information");
    }
}
