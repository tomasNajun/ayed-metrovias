package java.com.austral.ayed.metrovias;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

public class TicketSeller {
    private final int number;
    private final Queue<Customer> customersQueue;

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
    public Optional<Customer> attendCustomer() {
        return Optional.ofNullable(customersQueue.poll());
    }

    public int getNumber() {
        return number;
    }
}
