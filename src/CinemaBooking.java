import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class CinemaBooking {
    // ANSI escape codes for red text
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int row, col, option;
        String[] history;
        int historyBookSeat = 0;

        while (true) {
            try{
                System.out.print("[+] Insert Row: ");
                String inputRow = scanner.nextLine();
                row = Integer.parseInt(inputRow);
                if (row <= 0){
                    System.out.println("Please Input Positive Number");
                } else break;
            } catch (NumberFormatException e) {
                System.out.println("Please Input Only Number");
            }
        }

        while (true){
            try{
                System.out.print("[+] Insert Column: ");
                String inputColumn = scanner.nextLine();
                col = Integer.parseInt(inputColumn);
                if (col <= 0){
                    System.out.println("Please Input Positive Number");
                } else break;
            }catch (NumberFormatException e) {
                System.out.println("Please Input Only Number");
            }
        }

        String[][] cinema = new String[row][col];
        history = new String[row*col];

        do {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*                                        *");
            System.out.println("*      🎥  WELCOME TO CINEMA ZONE  🎬    *");
            System.out.println("*                                        *");
            System.out.println("*     Sit back, relax, and enjoy the     *");
            System.out.println("*          show on the big screen!       *");
            System.out.println("*                                        *");
            System.out.println("******************************************");
            System.out.println();
            System.out.println(RED + "1." + RESET + " Booking");
            System.out.println(RED + "2." + RESET + " Cancel Booking");
            System.out.println(RED + "3." + RESET + " View The History Of Booking Seat");
            System.out.println(RED + "4." + RESET + " Exit the application");
            System.out.print("\n🎬 Please select an option to proceed: ");
            option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.println("""
                            *************************************************
                            *                                               *
                            *        🎥   Booking YOUR MOVIE TICKET   🎥     *
                            *                                               *
                            *************************************************
                            """);
                    System.out.println();

                    char letter = 'A';
                    for (int i=0 ; i < row; i++){
                        System.out.print("[");
                        for (int j=0; j<col; j++){
                            System.out.print(letter+ "-" + (j + 1) + ": " + (cinema[i][j] == null ? "AV" : "BO"));
                            if (j < col - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                        letter++;
                    }

                    System.out.println("# INSTRUCTION");
                    System.out.println("# Single: A-1");
                    System.out.println("# Multiple (separate by comma): B-1,B-2");
                    System.out.print("> Please select available seat: ");
                    String selectedSeat = scanner.next();

                    String[] seats ;


                    if (selectedSeat.contains(",")){
                        seats = selectedSeat.split(",");
                    } else {
                        seats = new String[]{selectedSeat};
                    }

                    for (String seat : seats) {
                        System.out.println(seat);

                        String[] seatParts = seat.split("-");
                        char rowLetter = seatParts[0].charAt(0);
                        int colNumber = Integer.parseInt(seatParts[1]);

                        int indexLetter = rowLetter - 'A';

                        if (cinema[indexLetter][colNumber - 1] == null) {
                            cinema[indexLetter][colNumber - 1] = "BO";
                            System.out.println(GREEN + "Seat has been booked successfully" + RESET);
                        } else {
                            System.out.println(RED + "Seat is already booked" + RESET);
                        }
                    }



                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dataBooking = String.format("%s DateTime:%s",
                            Arrays.toString(seats),
                            LocalDateTime.now().format(dateTimeFormatter)
                            );

                       if (historyBookSeat < history.length){
                           history[historyBookSeat] = dataBooking;
                           historyBookSeat++;

                       }
//                    break;
                }
                case 2 -> {
                    System.out.println("""
                            *************************************************
                            *                                               *
                            *        🎥   CANCEL YOUR MOVIE TICKET   🎥     *
                            *                                               *
                            *************************************************
                            """);
                    System.out.println();

                    char letter = 'A';
                    for (int i=0 ; i < row; i++){
                        System.out.print("[");
                        for (int j=0; j<col; j++){
                            System.out.print(letter+ "-" + (j + 1) + ": " + (cinema[i][j] == null ? "AV" : "BO"));
                            if (j < col - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                        letter++;
                    }
                    System.out.println("# INSTRUCTION");
                    System.out.println("# Single: C-1");
                    System.out.println("# Multiple (separate by comma): C-1,C-2");
                    System.out.print("> Please select available seat to cancel: ");
                    String selectedSeat = scanner.next();

                    String[] seats ;

                    if (selectedSeat.contains(",")){
                        seats = selectedSeat.split(",");
                    } else {
                        seats = new String[]{selectedSeat};
                    }
                    for (String seat : seats) {
                        String[] seatParts = seat.split("-");
                        char rowLetter = seatParts[0].charAt(0);
                        int colNumber = Integer.parseInt(seatParts[1]);

                        int indexLetter = rowLetter - 'A';
                        if (cinema[indexLetter][colNumber - 1] != null) {
                            cinema[indexLetter][colNumber - 1] = null;


                            for (int i=0; i < historyBookSeat; i++){
                                if (history[i] != null && history[i].contains(seat)){
                                    history[i] = null;
                                    historyBookSeat--;
                                }
                            }

                            System.out.println(GREEN + "Seat cancelled successfully" + RESET);
                        } else {
                            System.out.println(RED + "Cancel not successfully because seat is AV" + RESET);
                        }
                    }



                    break;
                }
                case 3 -> {
                    System.out.println("*************************************************");
                    System.out.println("*                                               *");
                    System.out.println("*     View The History Of Booking Seat          *");
                    System.out.println("*                                               *");
                    System.out.println("*************************************************");

                    char letter = 'A';
                    for (int i=0 ; i < row; i++){
                        System.out.print("[");
                        for (int j=0; j<col; j++){
                            System.out.print(letter+ "-" + (j + 1) + ": " + (cinema[i][j] == null ? "AV" : "BO"));
                            if (j < col - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.println("]");
                        letter++;
                    }

                    if (historyBookSeat == 0) {
                        System.out.println("No booking history available yet.");
                    } else {
                        System.out.println("Booking History:");
                        for (int i = 0; i < historyBookSeat; i++) {
                            System.out.printf("%d. %s\n", i + 1, history[i]);
                        }
                    }
                    System.out.println("Total booking: " + historyBookSeat);

                    break;
                }
            }
        } while (option != 4);
    }
}
