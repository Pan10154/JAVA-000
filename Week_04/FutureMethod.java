package com.pzsjk.work04;

import java.util.concurrent.*;

public class FutureMethod implements Callable{

    private volatile Integer value = null;
    private Integer num1;
    private Integer num2;

    public FutureMethod(Integer num1, Integer num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public Integer sum() {
        this.value = this.num1 + this.num2;
        return this.value;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        final FutureMethod furtherMethod = new FutureMethod(1,3);
        Future future = executorService.submit(furtherMethod);
        Integer value = (Integer) future.get();
        System.out.println(value);
        executorService.shutdown();
    }

    public Object call() throws Exception {
        return sum();
    }
}
