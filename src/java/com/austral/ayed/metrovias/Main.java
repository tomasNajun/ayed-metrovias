package java.com.austral.ayed.metrovias;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static final int INVALID_OPTION = -1;
    public static final int SIMULATION_TIME_PERIOD = 30;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int numberOfTicketSellers = getNumberOfCounters(scanner);
        final Metrovias metrovias = new Metrovias(numberOfTicketSellers);
        final TimeManager timer = new TimeManager();

        int choice = showMenuAndGetChoice(scanner);
        while (choice == 1) {
            simulate(metrovias, timer);
            choice = showMenuAndGetChoice(scanner);
        }
        if (choice == 2)
            exit(metrovias);
    }

    private static int getNumberOfCounters(final Scanner scanner) {
        System.out.println("Ingrese el número de ventanillas, número entero entre 5 y 25.");
        int counters = requestIntNumber(scanner);
        while (counters < 5 || counters > 25) {
            System.out.println("Opción inválida: ingrese un número entero entre 5 y 25.");
            counters = requestIntNumber(scanner);
        }
        return counters;
    }

    private static int showMenuAndGetChoice(final Scanner scanner) {
        System.out.println("Ingrese 1 para avanzar 30'' en la simulación o 2 para salir.");
        int choice = requestIntNumber(scanner);
        while (choice != 1 && choice != 2) {
            showErrorMessage();
            choice = requestIntNumber(scanner);
        }
        return choice;
    }

    private static void simulate(final Metrovias metrovias, final TimeManager timer) {
        final int time = timer.incrementTimeIn(SIMULATION_TIME_PERIOD);
        metrovias.runSimulationStep(time);
    }

    private static void exit(final Metrovias metrovias) {
        final Stack<Customer> attendedCustomers = metrovias.getAttendedCustomers();
        printStack(attendedCustomers);

        final String revenue = formatRevenue(metrovias.getRevenue());
        System.out.println("Ingresos generados: " + revenue);

        final String formattedAvg = formatTime(metrovias.getAverageAttentionSpan());
        System.out.println("Tiempo medio de espera: " + formattedAvg + "s");
    }

    private static String formatRevenue(final int amount) {
        final NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }

    /**
     *
     * @param averageAttentionSpan average attention span
     * @return format number to two decimals rounding up
     */
    private static String formatTime(final double averageAttentionSpan) {
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);
        return decimalFormat.format(averageAttentionSpan);
    }

    private static void showErrorMessage() {
        System.out.println("Opción inválida. Debe ingresar 1 o 2.");
    }

    private static void printStack(final Stack<Customer> attendedCustomers) {
        // TODO según el enunciado solo debe llamarse a esta función y no implementarla
    }

    private static int requestIntNumber(final Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (final InputMismatchException ex) { // Si el valor ingresado no es un número entero
            scanner.next(); // clear invalid option
            return INVALID_OPTION;
        }
    }
}
