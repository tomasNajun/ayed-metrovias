package main.java.com.austral.ayed.metrovias;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Metrovias {
    public static final int NUMBER_OF_CUSTOMERS = 5;
    public static final double ATTENTION_PERCENTAGE = 0.5;

    private final List<TicketSeller> ticketSellers;
    private final Stack<Customer> attendedCustomers;

    public Metrovias(final int numberOfSellers) {
        ticketSellers = initializeTicketSellers(numberOfSellers);
        attendedCustomers = new Stack<>();
    }

    public void runSimulationStep(final int time) {
        arriveCustomers(time);
        attendCustomers(time);
    }

    private void arriveCustomers(final int arrivalTime) {
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            final Customer customer = new Customer(arrivalTime);
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
        ticketSeller.attendCustomer(attentionTime).ifPresent(attendedCustomers::push);
    }

    private boolean shouldAttendCustomer() {
        return Math.random() >= ATTENTION_PERCENTAGE;
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

    public Stack<Customer> getAttendedCustomers() {
        return attendedCustomers;
    }

    public int getRevenueOf(final int ticketSellerNumber) {
        return ticketSellers.get(ticketSellerNumber).getRevenue();
    }

    public double getAverageAttentionSpanOf(final int ticketSellerNumber) {
        return ticketSellers.get(ticketSellerNumber).getAverageAttentionSpan();
    }
}
