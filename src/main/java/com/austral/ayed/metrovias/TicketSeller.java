package main.java.com.austral.ayed.metrovias;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class TicketSeller {
    public static final int TICKET_PRICE = 100;

    private final int number;
    private final Queue<Customer> customersQueue;
    private int totalAttentionSpan;
    private int revenue;
    private int attendedCustomers;

    public TicketSeller(final int number) {
        this.number = number;
        customersQueue = new ArrayDeque<>();
    }

    public void enqueueCustomer(final Customer customer) {
        customersQueue.add(customer);
    }

    /**
     * Because customer can be null we wrap customer in an optional so we prevent null pointers
     * @return Optional of customer
     */
    public Optional<Customer> attendCustomer(final int attentionTime) {
        final Customer customer = customersQueue.poll();
        if(customer == null)
            return Optional.empty();

        final Ticket ticket = generateTicket();
        customer.assignTicket(ticket);
        customer.calculateAndSetAttentionSpan(attentionTime);
        updateTotalAttentionSpan(customer.getAttentionSpan());
        updateRevenue(ticket.getPrice());
        updateAttendedCustomers();
        return Optional.of(customer);
    }

    public int getNumber() {
        return number;
    }

    public double getAverageAttentionSpan() {
        return (double) totalAttentionSpan / (double) attendedCustomers;
    }

    public void updateTotalAttentionSpan(int attentionSpan) {
        totalAttentionSpan += attentionSpan;
    }

    public void updateRevenue(final int ticketPrice) {
        revenue += ticketPrice;
    }

    public int getRevenue() {
        return revenue;
    }

    private Ticket generateTicket() {
        return new Ticket(generateTicketNumber(), TICKET_PRICE);
    }

    /**
     *
     * @return random ticket number of 5 decimals [10000, 99999]
     */
    private int generateTicketNumber() {
        return (int) (10000 + Math.random() * (99999 - 10000));
    }

    private void updateAttendedCustomers() {
        attendedCustomers++;
    }

}
