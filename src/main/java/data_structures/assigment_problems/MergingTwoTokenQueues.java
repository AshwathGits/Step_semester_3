package data_structures.assigment_problems;

import java.util.*;

public class MergingTwoTokenQueues {

    static Queue<Integer> mergeQueues(
            Queue<Integer> q1,
            Queue<Integer> q2) {

        Queue<Integer> result = new LinkedList<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            result.add(q1.poll());
            result.add(q2.poll());
        }

        while (!q1.isEmpty()) {
            result.add(q1.poll());
        }

        while (!q2.isEmpty()) {
            result.add(q2.poll());
        }

        return result;
    }

    public static void main(String[] args) {

        Queue<Integer> q1 =
                new LinkedList<>(Arrays.asList(1, 3, 5));

        Queue<Integer> q2 =
                new LinkedList<>(Arrays.asList(2, 4, 6));

        Queue<Integer> result =
                mergeQueues(q1, q2);

        System.out.println(result);
    }
}