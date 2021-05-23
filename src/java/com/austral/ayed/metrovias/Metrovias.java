package java.com.austral.ayed.metrovias;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Metrovias {
    public static final int NUMBER_OF_CUSTOMERS = 5;
    public static final int TICKET_PRICE = 100;
    public static final double ATTENTION_PERCENTAGE = 0.5;

    private final List<TicketSeller> ticketSellers;
    private final Stack<Customer> attendedCustomers;
    private int totalAttentionSpan;
    private int revenue;

    public Metrovias(final int numberOfSellers) {
        ticketSellers = initializeTicketSellers(numberOfSellers);
        attendedCustomers = new Stack<>();
    }

    public void runSimulationStep(final int time) {
        arriveCustomers(time);
        attendCustomers(time);
    }

    public double getAverageAttentionSpan() {
        return (double) totalAttentionSpan / (double) attendedCustomers.size();
    }

    private void arriveCustomers(final int arrivalTime) {
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            final Customer customer = new Customer(generateTicketNumber(), arrivalTime);
            final TicketSeller ticketSeller = selectTicketSeller();
            ticketSeller.enqueueCustomer(customer);
        }
    }

    private void attendCustomers(final int attentionTime) {
        ticketSellers.forEach(ticketSeller -> {
            if (!shouldAttendCustomer()) // Si no debe atender continua preguntando a las siguientes ventanillas
                return;

            attendACustomer(attentionTime, ticketSeller);
        });
    }

    private void attendACustomer(final int attentionTime, final TicketSeller ticketSeller) {
        // Atiende un cliente solo si la cola no está vacía
        ticketSeller.attendCustomer().ifPresent(customer -> {
            customer.calculateAndSetAttentionSpan(attentionTime);
            updateTotalAttentionSpan(customer.getAttentionSpan());
            updateRevenue();
            attendedCustomers.push(customer);
        });
    }

    private boolean shouldAttendCustomer() {
        return Math.random() >= ATTENTION_PERCENTAGE;
    }

    private void updateTotalAttentionSpan(final int attentionSpan) {
        totalAttentionSpan += attentionSpan;
    }

    private void updateRevenue() {
        revenue += TICKET_PRICE;
    }

    private TicketSeller selectTicketSeller() {
        final int ticketSellerNumber = (int) (Math.random() * ticketSellers.size());
        return ticketSellers.get(ticketSellerNumber);
    }

    private List<TicketSeller> initializeTicketSellers(final int numberOfSellers) {
        List<TicketSeller> result = new ArrayList<>();
        for (int i = 0; i < numberOfSellers; i++) {
            result.add(new TicketSeller(i));
        }
        return result;
    }

    /**
     *
     * @return random ticket number of 5 decimals [10000, 99999]
     */
    private int generateTicketNumber() {
        return (int) (10000 + Math.random() * (99999 - 10000));
    }

    public Stack<Customer> getAttendedCustomers() {
        return attendedCustomers;
    }

    public int getRevenue() {
        return revenue;
    }
}
