package com.pzsjk.work04;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchMethod{

    private volatile Integer value = null;
    private CountDownLatch countDownLatch;

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public void sum(int num1, int num2) {
        this.value = num1 + num2;
    }


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatchMethod countDownLatchMethod = new CountDownLatchMethod();
        countDownLatchMethod.setCountDownLatch(latch);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                countDownLatchMethod.sum(1, 2);
            }
        });
        thread.start();
        //取值
        Integer value = countDownLatchMethod.value;
        System.out.println(value);
    }
}
