package abstraction.assigment_problems;

public class WakeUpCircuit {

    public static void ringAll(Ringable[] devices) {

        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }
}