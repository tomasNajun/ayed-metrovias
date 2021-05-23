package java.com.austral.ayed.metrovias;

public class Customer {
    private final int ticketNumber;
    private final int arrivalTime;
    private int attentionSpan;

    public Customer(int ticketNumber, int arrivalTime) {
        this.ticketNumber = ticketNumber;
        this.arrivalTime = arrivalTime;
    }

    public int getTicketNumber() {
        return ticketNumber;
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
