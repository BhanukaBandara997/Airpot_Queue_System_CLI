package airport_queue_system;

/**
 *
 * @author BhanukaBandara
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassengerQueue {

    
    public int maxSize = 21;
    Passenger[] passenger = new Passenger[maxSize];
    Passenger p;
    static int a = 1;
    ArrayList<Passenger> passengerQueue;
    int first;
    int last;
    static int i = 0;
    
    //adding a passanger to the passenger reference variable
    public void add(Passenger ps) {
        if (a < passenger.length) {
            passenger[a++] = ps;
        } else {
            System.out.println("list is full");
        }

    }

    //remove passenger from the arraylist
    public void removePassenger(int no) {
        if (validateNumber(no)) {
            if (passenger[no] != null) {
                passenger[no] = null;
                System.err.println("You have succesfully removed the passenger");
                
            } else {
                System.err.println("You Have Already Deleted the Passenger");
            }
        } else {
            System.err.println("Invalid input enter a valid passenger number");
        }
    }
    
    //validate when user tries to delete a passenger details
    public boolean validateNumber(int no) {
        PassengerQueue pq = new PassengerQueue();
        if (no >= 1 & no < pq.maxSize) {
            return true;
        }
        return false;
    }
    
//displaying the passenger whom has been added in the programme
    public void display() {
        int count = 1;
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] != null) {
                p = passenger[i];

                System.out.println("Passenger No " + count + " First Name : " + p.getfName());
                System.out.println("Passenger No " + count + " Last Name  :  " + p.getsName());
                System.out.println("Passenger No " + count + " Seconds Queue : " + p.getSecQueue());

                System.out.println("\n");
                count++;
            }

        }
    }

    //saving the details into a text file
    public void saveIntoTxt() {
        try {
            File f = new File("passenger.txt");
            
            f.createNewFile();

            FileWriter fw = new FileWriter(f, true);

            BufferedWriter bw = new BufferedWriter(fw);

            for (int x = 0; x < passenger.length; x++) {
                if (passenger[x] != null) {
                    bw.write(passenger[x].getfName() + "#" + passenger[x].getsName() + "#" + passenger[x].getSecQueue());
                    bw.newLine();
                }
            }
            bw.flush();
            bw.newLine();
            fw.close();
            System.out.println("---Your Data Has Being Saved To The Text File---");

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    //loading details into a array
    public void loadIntoArray() {

        BufferedReader br = null;
        String[] lines;
        Passenger ps = null;
        List<String> list;
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("passenger.txt")));

            while ((line = br.readLine()) != null) {
                lines = new String[3];
                String[] data = line.split("#");
                String fName = data[0];
                String lName = data[1];
                int x = Integer.parseInt(data[2]);
                ps = new Passenger(fName, lName, x);
                if(i<passenger.length){
                passenger[i++] = ps;
                }else{
                    i=0;
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int count = 0;
    static ArrayList<Passenger> helpforSim;

        //load passenger details into a arraylist
    private ArrayList<String> loadDatIntoArray() {
        BufferedReader br = null;
        ArrayList<String> namelist = new ArrayList<>();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("passengers.dat")));

            while ((line = br.readLine()) != null) {
                namelist.add(line);
            }
            Collections.shuffle(namelist);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return namelist;
    }
    
    //starting the simulation
    public void startSimulation() {
        removeFileBeforeBegin();
        helpforSim = new ArrayList<>();
        ArrayList<String> nameList = loadDatIntoArray();
        while (!nameList.isEmpty()) {
            runSimulation(nameList);
        }
        reportDetails();
        helpforSim = null;
    }
    
    //removing the file before starting the simulation
    private void removeFileBeforeBegin() {
        FileWriter fw = null;
        try {
            File file = new File("report.dat");
            file.delete();
            file.createNewFile();
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\t\t\t\t Report\n");
            bw.flush();
            bw.newLine();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(PassengerQueue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //functions in the simulation
    public void runSimulation(ArrayList<String> nameList) {
        int count = addingPassengerCount();
        int size = nameList.size();

        if (size >= count) {
            ArrayList<String> joiningNameList = joinToList(count, nameList);
            passengerQueue = loadArrivers(joiningNameList, helpforSim);
            createReport(passengerQueue);
        }
    }


//getting the arrivers from a random number
    private int addingPassengerCount() {

        int dNum = 1 + (int) (Math.random() * 6);
        return dNum;
    }
    
    private ArrayList<String> joinToList(int count, ArrayList<String> namelist) {
        ArrayList<String> joiningList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            if (i < namelist.size()) {
                String name = namelist.get(i);
                joiningList.add(name);
                namelist.remove(i);
            }

        }

        return joiningList;
    }

    //generate delay time for those arrivers
    private int generateDelayTime() {
        int dNum1 = 1 + (int) (Math.random() * 6);
        int dNum2 = 1 + (int) (Math.random() * 6);
        int dNum3 = 1 + (int) (Math.random() * 6);

        return dNum1 + dNum2 + dNum3;
    }

    //load details of the arrivers from passenger array into passengerQ
    private ArrayList<Passenger> loadArrivers(ArrayList<String> joiningNameList, ArrayList<Passenger> helpforSim) {
        String[] names;
        ArrayList<Passenger> passengerQ = new ArrayList<>();
        for (int i = 0; i < joiningNameList.size(); i++) {

            String namefromlist = joiningNameList.get(i);
            names = namefromlist.split(" ");
            String fname = names[0];
            String lname = names[1];
            int delaytime = generateDelayTime();

            Passenger pessenger = new Passenger(fname, lname, delaytime);
            passengerQ.add(pessenger);
            helpforSim.add(pessenger);
        }

        return passengerQ;
    }

    //creating a report 
    private void createReport(ArrayList<Passenger> passengerQueue) {
        try {
            File file = new File("report.dat");

            FileWriter fw = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fw);

            for (int x = 0; x < passengerQueue.size(); x++) {
                p = passengerQueue.get(x);

                bw.write("--- First Name : " + p.getfName() + "\t--- Last Name : " + p.getsName() + "\t--- Delay Time : " + p.getSecQueue());

                bw.newLine();
            }

            bw.flush();
            bw.newLine();
            fw.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    //report details in detailed
    private void reportDetails() {
        int max, min;
        double average = 0;
        int count = 0;
        int total = 0;
        Passenger p;
        max = helpforSim.get(0).getSecQueue();
        min = helpforSim.get(0).getSecQueue();
        for (int i = 0; i < helpforSim.size(); i++) {
            count++;
            p = helpforSim.get(i);
            total += p.getSecQueue();

            if (max < p.getSecQueue()) {
                max = p.getSecQueue();
            }
            if (min > p.getSecQueue()) {
                min = p.getSecQueue();
            }
        }
        average = total / count;

        try {
            File file = new File("report.dat");

            FileWriter fw = new FileWriter(file, true);

            BufferedWriter bw = new BufferedWriter(fw);

            for (int x = 0; x < passengerQueue.size(); x++) {
                p = passengerQueue.get(x);

                bw.write("\n\n--- Maximum waiting time is : " + max + "\n\n" + "--- Minimum waiting time is : " + min + "\n\n" + "--- Average waiting time is : " + average+"\n\n--- The maximum length of the queue attained : "+count);

                bw.newLine();
            }

            bw.flush();
            bw.newLine();
            fw.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    
    public double getMaxSize() {
        int i = 0;
        int count = 0;
        while (passenger[i] != null) {
            count++;
            i++;
        }
        return count;
    }
    
     public boolean isEmpty() {
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] == null) {
                return true;
            }

        }
        return false;
    }

    public boolean isFull() {
        int count = 0;
        for (int i = 0; i < passenger.length; i++) {
            if (passenger[i] != null) {
                count++;

            }
        }
        if (count == maxSize) {
            return false;
        }
        return true;
    }
    
}
