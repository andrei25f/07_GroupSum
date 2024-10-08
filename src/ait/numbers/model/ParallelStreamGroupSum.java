package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum {
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO use parallel stream
        return Arrays.stream(numberGroups)
                .parallel()
                .mapToInt(x -> Arrays.stream(x).sum())
                .sum();
    }
}
