package week1.class_problems;

public class BMICalculator {

    public static String getBMIStatus(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {

        System.out.println("------------------------------------------------");
        System.out.printf("%-10s %-12s %-12s %-15s%n",
                "Person", "Height(m)", "Weight(kg)", "BMI | Status");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);

            String status = getBMIStatus(bmi);

            System.out.printf("%-10d %-12.2f %-12.2f %.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, status);
        }

        System.out.println("------------------------------------------------");
    }

    public static void main(String[] args) {

        double[] heights = {1.75, 1.60};
        double[] weights = {70, 90};

        printWellnessReport(heights, weights);
    }
}