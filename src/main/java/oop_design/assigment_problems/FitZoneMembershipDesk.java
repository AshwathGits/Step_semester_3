package abstraction.assigment_problems;

public class FitZoneMembershipDesk {

    interface MembershipPlan {
        double calculateFee();
        String getPlanName();
    }

    static class MonthlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000.0;
        }

        public String getPlanName() {
            return "Monthly";
        }
    }

    static class QuarterlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000.0 * 3 * 0.90;
        }

        public String getPlanName() {
            return "Quarterly";
        }
    }

    static class AnnualPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000.0 * 12 * 0.75;
        }

        public String getPlanName() {
            return "Annual";
        }
    }

    enum MembershipStatus {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    static class Member {

        private final String name;

        public Member(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Membership {

        private final Member member;
        private final MembershipPlan plan;
        private final double fee;

        private MembershipStatus status;

        public Membership(Member member,
                          MembershipPlan plan) {

            this.member = member;
            this.plan = plan;
            this.fee = plan.calculateFee();
            this.status = MembershipStatus.ACTIVE;
        }

        public String getStatus() {
            return status.toString();
        }

        public String buyMembership() {

            return plan.getPlanName()
                    + " membership created for "
                    + member.getName()
                    + ". Fee: ₹"
                    + String.format("%.2f", fee)
                    + ". Status: "
                    + status;
        }

        public String checkIn() {

            if (status != MembershipStatus.ACTIVE) {
                return "Check-in denied: "
                        + member.getName()
                        + "'s membership is "
                        + status
                        + ".";
            }

            return member.getName()
                    + " checked in successfully.";
        }

        public String freeze() {

            if (status == MembershipStatus.EXPIRED) {
                return "Cannot freeze an Expired membership.";
            }

            if (status == MembershipStatus.FROZEN) {
                return "Membership is already Frozen.";
            }

            status = MembershipStatus.FROZEN;

            return member.getName()
                    + "'s membership frozen. Status: "
                    + status;
        }

        public String unfreeze() {

            if (status == MembershipStatus.EXPIRED) {
                return "Cannot unfreeze an Expired membership.";
            }

            if (status == MembershipStatus.ACTIVE) {
                return "Membership is already Active.";
            }

            status = MembershipStatus.ACTIVE;

            return member.getName()
                    + "'s membership unfrozen. Status: "
                    + status;
        }

        public String expire() {

            if (status == MembershipStatus.EXPIRED) {
                return "Membership is already Expired.";
            }

            status = MembershipStatus.EXPIRED;

            return member.getName()
                    + "'s membership expired. Status: "
                    + status;
        }
    }

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
                new Membership(asha, new QuarterlyPlan());

        Membership raviMembership =
                new Membership(ravi, new MonthlyPlan());

        System.out.println(ashaMembership.buyMembership());
        System.out.println(raviMembership.buyMembership());

        System.out.println(ashaMembership.checkIn());

        System.out.println(ashaMembership.freeze());

        System.out.println(ashaMembership.checkIn());

        System.out.println(raviMembership.expire());

        System.out.println(raviMembership.freeze());
    }
}