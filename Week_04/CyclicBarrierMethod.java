package com.pzsjk.work04;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierMethod {

    private volatile Integer value = null;
    private CyclicBarrier cyclicBarrier;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public void sum(int num1, int num2) {
        this.value = num1 + num2;
    }


    public static void main(String[] args) {
        final CyclicBarrierMethod cyclicBarrierMethod = new CyclicBarrierMethod();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            public void run() {
                Integer value = cyclicBarrierMethod.getValue();
                System.out.println(value);
            }
        });
        cyclicBarrierMethod.setCyclicBarrier(cyclicBarrier);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                cyclicBarrierMethod.sum(1, 3);
            }
        });
        thread.start();
        //取值
        Integer value = cyclicBarrierMethod.value;
        System.out.println(value);
    }
}
