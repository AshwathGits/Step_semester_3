package access_modifiers.assigment_problems;

import java.util.Arrays;

public final class BookingReceipt {

    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(
            String bookingId,
            String[] seatNumbers) {

        this.bookingId = bookingId;

        this.seatNumbers =
                Arrays.copyOf(
                        seatNumbers,
                        seatNumbers.length
                );
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {

        return Arrays.copyOf(
                seatNumbers,
                seatNumbers.length
        );
    }

    public BookingReceipt withUpdatedSeat(
            int index,
            String newSeat) {

        String[] updated =
                Arrays.copyOf(
                        seatNumbers,
                        seatNumbers.length
                );

        if (index >= 0 && index < updated.length) {
            updated[index] = newSeat;
        }

        return new BookingReceipt(
                bookingId,
                updated
        );
    }

    public static String processNightlySettlement(
            BookingReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (BookingReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof GroupBookingReceipt) {
                group++;
            } else {
                individual++;
            }
        }

        return processed
                + " processed | "
                + nullSkipped
                + " null skipped | "
                + group
                + " group | "
                + individual
                + " individual";
    }

    public static void main(String[] args) {

        BookingReceipt b =
                new BookingReceipt(
                        "CH-1001",
                        new String[]{"A1", "A2"}
                );

        String[] seats = b.getSeatNumbers();

        seats[0] = "HACKED";

        System.out.println(b.getSeatNumbers()[0]);

        BookingReceipt updated =
                b.withUpdatedSeat(1, "A3");

        System.out.println(
                Arrays.toString(b.getSeatNumbers())
        );

        System.out.println(
                Arrays.toString(updated.getSeatNumbers())
        );
    }
}

class GroupBookingReceipt extends BookingReceipt {

    private final int groupSize;

    public GroupBookingReceipt(
            String bookingId,
            String[] seatNumbers,
            int groupSize) {

        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}