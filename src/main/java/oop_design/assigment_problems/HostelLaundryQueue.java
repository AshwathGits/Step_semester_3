package abstraction.assigment_problems;

import java.util.HashSet;
import java.util.Set;

public class HostelLaundryQueue {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20.0;
        }

        public String getName() {
            return "Quick";
        }
    }

    static class NormalWash implements WashType {
        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30.0;
        }

        public String getName() {
            return "Normal";
        }
    }

    static class HeavyWash implements WashType {
        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45.0;
        }

        public String getName() {
            return "Heavy";
        }
    }

    static class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class WashingMachine {
        private final String machineId;
        private boolean busy;

        public WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        public String getMachineId() {
            return machineId;
        }

        public boolean isBusy() {
            return busy;
        }

        private void setBusy(boolean busy) {
            this.busy = busy;
        }
    }

    static class WashCycle {
        private final Student student;
        private final WashingMachine machine;
        private final WashType washType;

        public WashCycle(Student student,
                         WashingMachine machine,
                         WashType washType) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        public String startWash() {
            if (machine.isBusy()) {
                return "Machine " + machine.getMachineId()
                        + " is currently busy.";
            }

            machine.setBusy(true);

            return washType.getName()
                    + " wash started on "
                    + machine.getMachineId()
                    + " for "
                    + student.getName()
                    + " ("
                    + washType.getDuration()
                    + " min). Charge: ₹"
                    + String.format("%.2f", washType.getCharge());
        }

        public String completeWash() {
            machine.setBusy(false);

            return machine.getMachineId()
                    + " cycle completed. Machine is now free.";
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        WashCycle quick = new WashCycle(
                asha, m1, new QuickWash()
        );

        WashCycle heavy = new WashCycle(
                ravi, m2, new HeavyWash()
        );

        WashCycle normal = new WashCycle(
                neha, m1, new NormalWash()
        );

        System.out.println(quick.startWash());
        System.out.println(heavy.startWash());

        System.out.println(normal.startWash());

        System.out.println(quick.completeWash());

        System.out.println(normal.startWash());
        System.out.println(normal.completeWash());
    }
}