package week3.assigment_problems;

public class CardPayment extends Payment {

    public void payWithProcessingFee(double amount) {

        double total = amount * 1.02;

        System.out.printf(
                "Charged (card, incl. fee): Rs %.1f%n",
                total
        );
    }
}