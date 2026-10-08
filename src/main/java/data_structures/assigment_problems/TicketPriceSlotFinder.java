package data_structures.assigment_problems;

public class TicketPriceSlotFinder {

    static int findSlot(int[] prices, int target) {

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] prices = {120, 150, 180, 200, 250};
        int target = 180;

        System.out.println(findSlot(prices, target));
    }
}