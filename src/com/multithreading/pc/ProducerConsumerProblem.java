package com.multithreading.pc;

import java.util.ArrayList;
import java.util.Stack;

public class ProducerConsumerProblem {

    public static void main(String[] args){
    ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

    }
}

class ProducerConsumer {
    private Stack<Integer> data = new Stack<Integer>();
    private int capacity = 2;
    private static int val =0;

    public synchronized void produce() throws InterruptedException{
        while(true){
            if(this.data.size() == capacity){
                this.wait();
            }
            System.out.println("Producing: "+val);
            this.data.push(new Integer(val++));
            this.notify();
            Thread.sleep(1000);
        }

    }


    public synchronized void consume() throws InterruptedException{
        while(true){
            if(this.data.size() == 0){
                this.wait();
            }
            System.out.println("Consuming: "+this.data.pop());
            this.notify();
            Thread.sleep(1000);
        }

    }


}



