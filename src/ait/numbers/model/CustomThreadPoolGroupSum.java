package ait.numbers.model;

import ait.numbers.task.OneGroupSum;
import ait.utils.pool.ThreadPool;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolGroupSum extends GroupSum {
    public CustomThreadPoolGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO use ait.utils.pool.ThreadPool
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }
        ThreadPool threadPool = new ThreadPool(numberGroups.length);
        for (int i = 0; i < tasks.length; i++) {
            threadPool.execute(tasks[i]);
        }
        threadPool.shutDown();
        try {
            threadPool.joinToPool(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.stream(tasks).mapToInt(OneGroupSum::getSum).sum();
    }
}
