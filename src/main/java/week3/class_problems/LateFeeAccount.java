package week3.assigment_problems;

public class LateFeeAccount {

    private String regNo;
    private double totalFee;

    public LateFeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    public final double calculateLateFee(int daysLate) {

        if (daysLate <= 0) {
            return 0;
        }

        return totalFee * daysLate / 100.0;
    }

    public final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {

            double lateFee = calculateLateFee(daysLate);

            System.out.printf(
                    "%s | Total Fee: Rs %.2f | Late Fee: Rs %.2f%n",
                    regNo,
                    totalFee,
                    lateFee
            );
        }
    }

    public static void main(String[] args) {

        String[] regNos = {
            "RA001",
            "RA002",
            "RA003",
            "RA004"
        };

        double[] totalFees = {
            200000,
            150000,
            180000,
            220000
        };

        int[] daysLate = {
            10,
            0,
            -2,
            5
        };

        LateFeeAccount[] accounts = new LateFeeAccount[regNos.length];

        for (int i = 0; i < accounts.length; i++) {

            accounts[i] = new LateFeeAccount(
                    regNos[i],
                    totalFees[i]
            );

            accounts[i].printSummary(daysLate[i]);
        }
    }
}