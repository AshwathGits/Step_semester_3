package week3.assigment_problems;

public class HostelFeeAccount extends FeeAccount {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}