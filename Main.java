import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    /*
     *This method simply prints the method and allows the user to choose where they want to go.
     */
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        String[] flightInfo = new String[4];

        while (option != 4 || option != 5) {
            System.out.println("WELCOME TO BENCKWITZ AIRLINES.\n" +
                "Please select a choice below [1-5]\n" +
                "1. Add flight\n" +
                "2. View trip\n" +
                "3. Manage trip\n" +
                "4. Checkout\n" +
                "5. Exit Benckwitz Airlines\n");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    flightInfo = addFlight();
                    break;
                case 2:
                    viewTrip(flightInfo);
                    break;
                case 3:
                    flightInfo = manageTrip(flightInfo);
                    break;
                case 4:
                    checkout(flightInfo);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Not an option, please try again.");
            }
        }
    }

    /*The following method will print the text file and take in user input for what they want.
     *Not many edge cases are in this method. Will need to update to make it stronger from invalid inputs.
     */
    public static String[] addFlight() {
        String[] trash = new String[1];
        try {
            //print flights to add
            String found = "";
            String find = "";
            Scanner sc = new Scanner(System.in);
            File f = new File("flight.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            System.out.println("What flight would you like to get? Enter the name of your city.");
            String word = sc.nextLine();
            System.out.println("What are you purchasing?\n1. One-way\n2.Round trip");
            int trip = sc.nextInt();
            System.out.println("How many seats are you purchasing?");
            int amount = sc.nextInt();
            Scanner myReader2 = new Scanner(f);
            while (myReader2.hasNextLine()) {
                find = myReader2.nextLine();
                if (find.indexOf(word) != -1) {
                    break;
                }
            }
            System.out.println("FLIGHT = " + find);
            System.out.println("FLIGHT ADDED");
            String[] sp = find.split(" ");
            String typeTrip = "";
            float total = 000;
            if (trip == 1) {
                typeTrip = "One Way";
                float fl = Float.parseFloat(sp[1]);
                total = amount * fl;
            } else if (trip == 2) {
                typeTrip = "Round Trip";
                float fl = Float.parseFloat(sp[2]);
                total = amount * fl;
            }
            String[] ans = {
                sp[0],
                String.valueOf(total),
                typeTrip,
                String.valueOf(amount)
            };
            return ans;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return trash;

    }

    /*
     *The following method just prints the contents of the flight info. If there is none, nothing happens.
     */
    public static void viewTrip(String[] flightInfo) {
        System.out.println("----------TRIP INFORMATION----------");
        if (flightInfo[0] != null) {
            System.out.println("El Paso to " + flightInfo[0] + " [TOTAL] " + flightInfo[1] + " [TYPE] " + flightInfo[2] + " [SEATS] " + flightInfo[3]);
        }
    }

    /*The following method gives the option to remove the flight within the system.
     *If no is selected, nothing is changed and we return to menu.
     */
    public static String[] manageTrip(String[] flightInfo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you sure you want to clear the cart?");
        String ans = sc.nextLine();
        if (ans.equals("yes")) {
            Arrays.fill(flightInfo, null);
        } else {
            System.out.println("Cart not changed. Going back to menu.");
        }
        return flightInfo;
    }

    /*The following method will end the program if there is a flight.
     *If there is no flight, it will go back to the main menu. Checks for 16 digit number as well.
     */
    public static void checkout(String[] flightInfo) {
        Scanner sc = new Scanner(System.in);
        long credit = 0;
        if (flightInfo[0] != null) {
            System.out.println("Your current total is: " + flightInfo[1]);
            System.out.println("Please enter your 16 digit credit card number");
            credit = sc.nextLong();
            while (String.valueOf(credit).length() != 16) {
                System.out.println("Invalid credit card number. Try again.");
                credit = sc.nextLong();
            }
            System.out.println("Your purchase for:\nEl Paso to " + flightInfo[0] + " [TOTAL] " + flightInfo[1] + " [TYPE] " + flightInfo[2] + " [SEATS] " + flightInfo[3] + " was successful! You paid " + flightInfo[1] + " with credit card number: " + credit + "\nHave fun on your trip!");
            System.exit(0);
        }
    }

}
