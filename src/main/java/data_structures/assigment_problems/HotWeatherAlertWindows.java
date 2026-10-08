package data_structures.assigment_problems;

public class HotWeatherAlertWindows {

    static int countHotDays(int[] temperatures, int limit) {

        int count = 0;

        for (int temp : temperatures) {
            if (temp > limit) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] temperatures = {32, 35, 31, 38, 40, 29, 36};
        int limit = 35;

        System.out.println(countHotDays(temperatures, limit));
    }
}