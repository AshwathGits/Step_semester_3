package abstraction.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Room {

    protected String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 100;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 150;
    }
}

class Suite extends Room {

    public Suite(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 250;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Reservation {

    private Customer customer;
    private Room room;
    private String startDate;
    private String endDate;
    private int days;
    private boolean cancelled;

    public Reservation(
            Customer customer,
            Room room,
            String startDate,
            String endDate,
            int days) {

        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.cancelled = false;
    }

    public double calculatePrice() {
        return room.calculatePrice(days);
    }

    public void cancel() {

        if (cancelled) {
            System.out.println(
                    "Reservation already cancelled."
            );
            return;
        }

        cancelled = true;

        System.out.println(
                "Reservation for "
                        + customer.getName()
                        + " cancelled successfully."
        );
    }

    public Room getRoom() {
        return room;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}

public class HotelBookingSystem {

    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelBookingSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean isAvailable(
            Room room,
            String startDate,
            String endDate) {

        for (Reservation reservation : reservations) {

            if (reservation.getRoom() == room
                    && !reservation.isCancelled()
                    && datesOverlap(
                            startDate,
                            endDate,
                            reservation.getStartDate(),
                            reservation.getEndDate())) {

                return false;
            }
        }

        return true;
    }

    private boolean datesOverlap(
            String start1,
            String end1,
            String start2,
            String end2) {

        /*
         * Simple date comparison for the sample.
         * Dates are assumed to be in the same month/year.
         */
        int s1 = extractDay(start1);
        int e1 = extractDay(end1);
        int s2 = extractDay(start2);
        int e2 = extractDay(end2);

        return s1 <= e2 && s2 <= e1;
    }

    private int extractDay(String date) {

        String[] parts = date.split(" ");

        return Integer.parseInt(parts[1]);
    }

    public void makeReservation(
            Customer customer,
            Room room,
            String startDate,
            String endDate,
            int days) {

        if (!isAvailable(room, startDate, endDate)) {

            System.out.println(
                    room.getRoomNumber()
                            + " is not available from "
                            + startDate
                            + " to "
                            + endDate
            );

            return;
        }

        Reservation reservation =
                new Reservation(
                        customer,
                        room,
                        startDate,
                        endDate,
                        days
                );

        reservations.add(reservation);

        System.out.println(
                "Reservation confirmed for "
                        + customer.getName()
                        + ", "
                        + room.getRoomNumber()
                        + " ("
                        + startDate
                        + "-"
                        + endDate
                        + ")"
        );

        System.out.println(
                "Price: $"
                        + reservation.calculatePrice()
        );
    }

    public static void main(String[] args) {

        HotelBookingSystem system =
                new HotelBookingSystem();

        Room standard =
                new StandardRoom("Standard Room 101");

        Room deluxe =
                new DeluxeRoom("Deluxe Room 201");

        system.addRoom(standard);
        system.addRoom(deluxe);

        Customer customerA =
                new Customer("Customer A");

        Customer customerC =
                new Customer("Customer C");

        // Customer A books Standard Room 101
        system.makeReservation(
                customerA,
                standard,
                "Jan 1",
                "Jan 5",
                4
        );

        // Customer A tries overlapping dates
        system.makeReservation(
                customerA,
                standard,
                "Jan 3",
                "Jan 7",
                4
        );

        // Customer C books Deluxe Room
        system.makeReservation(
                customerC,
                deluxe,
                "Feb 10",
                "Feb 12",
                2
        );
    }
}