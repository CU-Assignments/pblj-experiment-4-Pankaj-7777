import java.util.*;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private final boolean[] seats = new boolean[TOTAL_SEATS];

    public synchronized boolean bookSeat(int seatNumber, String customer) {
        if (seatNumber < 0 || seatNumber >= TOTAL_SEATS) {
            System.out.println(customer + " tried to book an invalid seat.");
            return false;
        }
        if (!seats[seatNumber]) {
            seats[seatNumber] = true;
            System.out.println(customer + " successfully booked seat " + seatNumber);
            return true;
        } else {
            System.out.println(customer + " tried to book seat " + seatNumber + ", but it was already taken.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final String customer;
    private final int seatNumber;

    public BookingThread(TicketBookingSystem system, String customer, int seatNumber, int priority) {
        this.system = system;
        this.customer = customer;
        this.seatNumber = seatNumber;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customer);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();

        List<BookingThread> bookings = new ArrayList<>();

        // VIP Customers (High Priority)
        bookings.add(new BookingThread(system, "VIP Customer 1", 2, Thread.MAX_PRIORITY));
        bookings.add(new BookingThread(system, "VIP Customer 2", 5, Thread.MAX_PRIORITY));

        // Normal Customers (Medium Priority)
        bookings.add(new BookingThread(system, "Customer A", 2, Thread.NORM_PRIORITY));
        bookings.add(new BookingThread(system, "Customer B", 5, Thread.NORM_PRIORITY));

        // Low Priority Customers
        bookings.add(new BookingThread(system, "Late Booker 1", 7, Thread.MIN_PRIORITY));
        bookings.add(new BookingThread(system, "Late Booker 2", 3, Thread.MIN_PRIORITY));

        // Start all booking threads
        for (BookingThread bt : bookings) {
            bt.start();
        }
    }
}
