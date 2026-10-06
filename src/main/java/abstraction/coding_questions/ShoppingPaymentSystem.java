package abstraction.class_problems;

import java.util.ArrayList;
import java.util.List;

class Product {

    private String name;
    private double price;
    private int quantity;

    public Product(
            String name,
            double price,
            int quantity) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public String getName() {
        return name;
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

interface PaymentMethod {

    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.println(
                "Processing Credit Card payment of $"
                        + amount
        );

        return true;
    }
}

class PayPalPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.println(
                "Processing PayPal payment of $"
                        + amount
        );

        return false;
    }
}

class BankTransferPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {

        System.out.println(
                "Processing Bank Transfer payment of $"
                        + amount
        );

        return true;
    }
}

class Order {

    private Customer customer;
    private List<Product> products;
    private String status;

    public Order(Customer customer) {

        this.customer = customer;
        this.products = new ArrayList<>();
        this.status = "Pending";
    }

    public void addProduct(Product product) {

        products.add(product);

        System.out.println(
                product.getName()
                        + " added to order."
        );
    }

    public double calculateTotal() {

        double total = 0;

        for (Product product : products) {
            total += product.getTotalPrice();
        }

        return total;
    }

    public void pay(PaymentMethod paymentMethod) {

        if (products.isEmpty()) {

            System.out.println(
                    "Cannot process payment for an empty order."
            );

            return;
        }

        double total = calculateTotal();

        System.out.println(
                "Payment initiated for order of "
                        + customer.getName()
        );

        boolean success =
                paymentMethod.processPayment(total);

        if (success) {

            status = "Paid";

            System.out.println(
                    "Payment successful."
            );

        } else {

            System.out.println(
                    "Payment failed."
            );

            System.out.println(
                    "Order status remains: "
                            + status
            );
        }
    }

    public String getStatus() {
        return status;
    }
}

public class ShoppingPaymentSystem {

    public static void main(String[] args) {

        Customer customerX =
                new Customer("Customer X");

        Customer customerY =
                new Customer("Customer Y");

        Customer customerZ =
                new Customer("Customer Z");

        // Customer X creates an order
        Order orderX =
                new Order(customerX);

        orderX.addProduct(
                new Product("Product A", 100, 2)
        );

        orderX.addProduct(
                new Product("Product B", 50, 1)
        );

        orderX.pay(
                new CreditCardPayment()
        );

        System.out.println(
                "Order status: "
                        + orderX.getStatus()
        );

        System.out.println();

        // Customer Y creates an empty order
        Order orderY =
                new Order(customerY);

        orderY.pay(
                new CreditCardPayment()
        );

        System.out.println();

        // Customer Z creates an order
        Order orderZ =
                new Order(customerZ);

        orderZ.addProduct(
                new Product("Product C", 200, 1)
        );

        orderZ.pay(
                new PayPalPayment()
        );

        System.out.println(
                "Order status: "
                        + orderZ.getStatus()
        );
    }
}