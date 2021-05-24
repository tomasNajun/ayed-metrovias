package main.java.com.austral.ayed.metrovias;

public class Ticket {
    private final int number;
    private final int price;

    public Ticket(int number, int price) {
        this.number = number;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
