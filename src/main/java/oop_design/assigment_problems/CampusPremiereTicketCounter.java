package abstraction.assigment_problems;

import java.util.HashSet;
import java.util.Set;

public class CampusPremiereTicketCounter {

    interface Seat {
        double getPrice();
        String getCategory();
    }

    static class RegularSeat implements Seat {

        private final String seatNumber;

        public RegularSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public double getPrice() {
            return 150.0;
        }

        public String getCategory() {
            return "Regular";
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }

    static class PremiumSeat implements Seat {

        private final String seatNumber;

        public PremiumSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public double getPrice() {
            return 250.0;
        }

        public String getCategory() {
            return "Premium";
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }

    static class ReclinerSeat implements Seat {

        private final String seatNumber;

        public ReclinerSeat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        public double getPrice() {
            return 400.0;
        }

        public String getCategory() {
            return "Recliner";
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }

    static class Customer {

        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Show {

        private final String showTime;
        private final Set<String> bookedSeats;

        public Show(String showTime) {
            this.showTime = showTime;
            this.bookedSeats = new HashSet<>();
        }

        public boolean isSeatAvailable(String seatNumber) {
            return !bookedSeats.contains(seatNumber);
        }

        private boolean bookSeat(String seatNumber) {
            return bookedSeats.add(seatNumber);
        }

        private void releaseSeat(String seatNumber) {
            bookedSeats.remove(seatNumber);
        }

        public String getShowTime() {
            return showTime;
        }
    }

    static class Booking {

        private final Customer customer;
        private final Show show;
        private final Set<Seat> seats;

        private boolean cancelled;

        public Booking(Customer customer,
                       Show show,
                       Set<Seat> seats) {

            this.customer = customer;
            this.show = show;
            this.seats = seats;
            this.cancelled = false;
        }

        public double getTotal() {

            double total = 0;

            for (Seat seat : seats) {
                total += seat.getPrice();
            }

            return total;
        }

        public String confirm() {

            if (seats.isEmpty()) {
                return "Booking failed: no seats selected.";
            }

            if (seats.size() > 6) {
                return "Booking failed: maximum 6 seats allowed.";
            }

            for (Seat seat : seats) {

                String seatNumber = getSeatNumber(seat);

                if (!show.isSeatAvailable(seatNumber)) {
                    return "Seat "
                            + seatNumber
                            + " is already booked for this show.";
                }
            }

            for (Seat seat : seats) {
                show.bookSeat(getSeatNumber(seat));
            }

            return "Booking confirmed for "
                    + customer.getName()
                    + ": "
                    + getSeatNumbers()
                    + ". Total: ₹"
                    + String.format("%.2f", getTotal());
        }

        public String cancel() {

            if (cancelled) {
                return "Booking is already cancelled.";
            }

            for (Seat seat : seats) {
                show.releaseSeat(getSeatNumber(seat));
            }

            cancelled = true;

            return customer.getName()
                    + "'s booking cancelled. Seats "
                    + getSeatNumbers()
                    + " released.";
        }

        private String getSeatNumbers() {

            StringBuilder result = new StringBuilder();

            for (Seat seat : seats) {

                if (result.length() > 0) {
                    result.append(", ");
                }

                result.append(getSeatNumber(seat));
            }

            return result.toString();
        }

        private String getSeatNumber(Seat seat) {

            if (seat instanceof RegularSeat) {
                return ((RegularSeat) seat).getSeatNumber();
            }

            if (seat instanceof PremiumSeat) {
                return ((PremiumSeat) seat).getSeatNumber();
            }

            return ((ReclinerSeat) seat).getSeatNumber();
        }
    }

    public static void main(String[] args) {

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Show show = new Show("7 PM");

        Set<Seat> ashaSeats = new HashSet<>();

        ashaSeats.add(new RegularSeat("A1"));
        ashaSeats.add(new RegularSeat("A2"));
        ashaSeats.add(new PremiumSeat("F5"));

        Booking ashaBooking =
                new Booking(asha, show, ashaSeats);

        System.out.println(ashaBooking.confirm());

        Set<Seat> raviSeats = new HashSet<>();
        raviSeats.add(new RegularSeat("A2"));

        Booking raviBooking =
                new Booking(ravi, show, raviSeats);

        System.out.println(raviBooking.confirm());

        Set<Seat> raviRecliner = new HashSet<>();
        raviRecliner.add(new ReclinerSeat("R1"));

        Booking raviBooking2 =
                new Booking(ravi, show, raviRecliner);

        System.out.println(raviBooking2.confirm());

        System.out.println(ashaBooking.cancel());

        Set<Seat> nehaSeats = new HashSet<>();
        nehaSeats.add(new RegularSeat("A2"));

        Booking nehaBooking =
                new Booking(neha, show, nehaSeats);

        System.out.println(nehaBooking.confirm());
    }
}