package data_structures.assigment_problems;

import java.util.*;

public class MostPopularCanteenOrder {

    static int findMostPopular(int[] orders) {

        HashMap<Integer, Integer> count = new HashMap<>();

        int popular = orders[0];
        int maxCount = 0;

        for (int order : orders) {
            count.put(order, count.getOrDefault(order, 0) + 1);

            if (count.get(order) > maxCount) {
                maxCount = count.get(order);
                popular = order;
            }
        }

        return popular;
    }

    public static void main(String[] args) {

        int[] orders = {101, 102, 101, 103, 102, 101};

        System.out.println(findMostPopular(orders));
    }
}