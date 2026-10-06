package oop_design.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    private String vehicleId;
    private boolean available;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateRentalCharge(int days);

    public abstract String getVehicleType();
}

class Sedan extends Vehicle {

    public Sedan(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.0;
    }

    @Override
    public String getVehicleType() {
        return "Sedan";
    }
}

class SUV extends Vehicle {

    public SUV(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 70.0;
    }

    @Override
    public String getVehicleType() {
        return "SUV";
    }
}

class Truck extends Vehicle {

    public Truck(String vehicleId) {
        super(vehicleId);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 90.0;
    }

    @Override
    public String getVehicleType() {
        return "Truck";
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

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public double calculateCharge() {
        return vehicle.calculateRentalCharge(days);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

public class VehicleRentalSystem {

    private List<Vehicle> vehicles;
    private List<Rental> rentals;

    public VehicleRentalSystem() {
        vehicles = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void rentVehicle(Customer customer, String vehicleId, int days) {

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleId().equals(vehicleId)) {

                if (!vehicle.isAvailable()) {
                    System.out.println(
                            vehicleId + " is currently unavailable."
                    );
                    return;
                }

                Rental rental = new Rental(vehicle, customer, days);
                rentals.add(rental);
                vehicle.setAvailable(false);

                System.out.println(
                        vehicleId + " rented successfully by "
                                + customer.getName()
                );

                System.out.println(
                        "Rental charge: $" + rental.calculateCharge()
                );

                return;
            }
        }

        System.out.println("Vehicle not found.");
    }

    public void returnVehicle(String vehicleId) {

        for (Rental rental : rentals) {

            if (rental.getVehicle().getVehicleId().equals(vehicleId)
                    && !rental.getVehicle().isAvailable()) {

                rental.getVehicle().setAvailable(true);

                System.out.println(
                        vehicleId + " returned successfully by "
                                + rental.getCustomer().getName()
                );

                return;
            }
        }

        System.out.println("No active rental found for " + vehicleId);
    }

    public void displayAvailableVehicles() {

        System.out.println("Available Vehicles:");

        for (Vehicle vehicle : vehicles) {

            if (vehicle.isAvailable()) {
                System.out.println(
                        vehicle.getVehicleType()
                                + " - "
                                + vehicle.getVehicleId()
                );
            }
        }
    }

    public static void main(String[] args) {

        VehicleRentalSystem system = new VehicleRentalSystem();

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");
        Vehicle truckC = new Truck("Truck C");

        system.addVehicle(sedanA);
        system.addVehicle(suvB);
        system.addVehicle(truckC);

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        // Customer 1 rents Sedan A for 3 days
        system.rentVehicle(customer1, "Sedan A", 3);

        // Customer 2 attempts to rent the same Sedan
        system.rentVehicle(customer2, "Sedan A", 2);

        // Customer 1 returns Sedan A
        system.returnVehicle("Sedan A");

        // Customer 3 rents SUV B for 5 days
        system.rentVehicle(customer3, "SUV B", 5);

        system.displayAvailableVehicles();
    }
}