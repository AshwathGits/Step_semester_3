package week3.assigment_problems;

public class CanteenPaymentDispatch {

    public static double totalCollected = 0;

    public static void processTransaction(
            Payment payment,
            double amount) {

        if (payment instanceof CardPayment) {

            CardPayment cardPayment =
                    (CardPayment) payment;

            cardPayment.payWithProcessingFee(amount);

            totalCollected += amount * 1.02;

        } else {

            payment.pay(amount);

            totalCollected += amount;
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {
            100,
            50,
            200,
            75,
            120
        };

        for (int i = 0; i < payments.length; i++) {

            processTransaction(
                    payments[i],
                    amounts[i]
            );
        }

        System.out.printf(
                "Total Collected: Rs %.1f%n",
                totalCollected
        );
    }
}