package main.java.com.austral.ayed.metrovias;

import java.util.Optional;

public class Customer {
    private final int arrivalTime;
    private Ticket ticket;
    private int attentionSpan;

    public Customer(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void assignTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Optional<Ticket> getTicket() {
        return Optional.ofNullable(ticket);
    }

    public int getAttentionSpan() {
        return attentionSpan;
    }

    public void calculateAndSetAttentionSpan(final int attentionTime) {
        this.attentionSpan = attentionTime - this.arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}
