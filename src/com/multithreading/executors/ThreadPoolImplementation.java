package com.multithreading.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolImplementation {
    public static void main(String[] args){

        Runnable t1 = new Task("Task 1");
        Runnable t2 = new Task("Task 2");
        Runnable t3 = new Task("Task 3");
        Runnable t4 = new Task("Task 4");
        Runnable t5 = new Task("Task 5");

        //System.out.println(Runtime.getRuntime().availableProcessors());

        //fix pool with 3 threads..In case a thread exit unexpectedly with error and not handled, then new thread will take its place
         ExecutorService pool = Executors.newFixedThreadPool(3);
        //cache pool, creates thread on need basis. If thread is inactive for 60 seconds, that thread will die and new thread will be created later if needed.
        //ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.submit(t1);

        pool.shutdown();

        //As pool already shutdown, no more tasks can be submitted, thus this line will throw RejectedExecutionException
        // pool.execute(t4);
    }
}

class Task implements Runnable{
    private String name;
    Task(String name){
        this.name = name;
    }

    @Override
    public void run() throws NullPointerException{
        for(int i=1;i<=2;i++){
          /*  Throwing execption for one task : if(this.name.equals("Task 3")){
                throw new NullPointerException();
            }*/
            System.out.println(this.name+" times: "+i);
        }
        try {
            Thread.sleep(2000);
            System.out.println(this.name+" completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
