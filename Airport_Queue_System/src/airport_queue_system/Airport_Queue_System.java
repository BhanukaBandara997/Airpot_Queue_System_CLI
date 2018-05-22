package airport_queue_system;

/*	   Name: Bhanuka Bandara
		   UOW ID: w162266302
		   IIT ID: 2016147	*/

/*
This program is about a Airport Queue and this is the Main Interface of the program
 */


/**
 *
 * @author BhanukaBandara
 */
import java.util.*;

public class Airport_Queue_System {

    public static void main(String[] args) {
        PassengerQueue pq = new PassengerQueue();
        Passenger p = new Passenger();
        do {
            mainInterface(pq, p);
        } while (mainMenu());
    }

    //Selecting the user input and check the user input
    public static void mainInterface(PassengerQueue pQueue, Passenger p) {
        Scanner input = new Scanner(System.in);
        System.out.println("\t\t\t\t\t --- Welcome to Melbourne Airport ---");
        System.out.println("\t\t\t (1) 'A': Add a passenger to the PassengerQueue\n\t\t\t (2) 'V': View the passengerQueue\n\t\t\t (3) 'D': Delete passenger from passengerQueue\n\t\t\t (4) 'S': Store PassengerArray data into text file\n\t\t\t (5) 'L': Load Passenger data back from the file into PassengerArray\n\t\t\t (6) 'R': Run the Simulation and produce report \n");
        System.out.print("Enter your selection option : ");
        String userInput = input.nextLine();
        
        if (userInput.equals("A") || userInput.equals("a")|| userInput.equals("V") || userInput.equals("v")|| userInput.equals("D") || userInput.equals("d")|| userInput.equals("S") ||userInput.equals("s")|| userInput.equals("L") ||userInput.equals("l")|| userInput.equals("R")||userInput.equals("r")) {

            if (userInput.equals("A") || userInput.equals("a")) {

                do {
                    if (pQueue.isFull()) {
                        System.out.print("Please Enter the First name : ");
                        String fName = input.next();
                        System.out.print("Please Enter the Last name : ");
                        String lName = input.next();
                        System.out.print("Please Enter the seconds in Queue : ");
                        int secQueue = input.nextInt();
                        
                        if (validation(fName, lName, secQueue)) {
                            Passenger passenger = new Passenger(fName, lName, secQueue);
                            pQueue.add(passenger);
                        }
                    } else {
                        System.err.println("Sry!!! Booking list is full for today");
                    }
                } while (run());

            } else if (userInput.equalsIgnoreCase("V")) {
                pQueue.display();
            } else if (userInput.equalsIgnoreCase("D")) {
                System.out.print("Enter passenger number for delete : ");
                int no = input.nextInt();
                pQueue.removePassenger(no);
            } else if (userInput.equalsIgnoreCase("S")) {
                pQueue.saveIntoTxt();
            } else if (userInput.equalsIgnoreCase("L")) {
                pQueue.loadIntoArray();
            } else if (userInput.equalsIgnoreCase("R")) {
                pQueue.startSimulation();
            }

        }

    }

    //check if user want to continue again in the mainInterface
    public static boolean mainMenu() {
        Scanner input = new Scanner(System.in);
        System.err.print("Do you want to continue the programme? press Y/N ");
        String quit = input.nextLine();

        if (quit.equals("Y") || quit.equals("y")  ) {
            return true;
        }
        return false;
    }

    //adding another passenger after adding one passenger
    public static boolean run() {
        Scanner input = new Scanner(System.in);
        System.err.print("Do you want to add another Passenger? press Y/N ");
        String quit = input.nextLine();

        if (quit.equals("Y")|| quit.equals("y")) {
            return true;
        }
        return false;
    }
    
    //check user inputs valid or not 
    public static boolean validation(String fname, String lname, int qNum) {
        if (fname.length() > 0) {
            if (lname.length() > 0) {
                if (qNum >= 0 || qNum <= 20) {
                    return true;
                } else {
                    System.err.println("You have entered invalid Queue number... Enter a valid Queue number");
                }
            } else {
                System.err.println("Please Enter the Last Name ");
            }
        } else {
            System.err.println("Please Enter the First Name ");
        }

        return false;
    }

}




