package airport_queue_system;

/**
 *
 * @author BhanukaBandara
 */

//passenger class with private variables 
public class Passenger {

    private String fName, sName;
    private int secQueue;

    public Passenger(String fName, String sName, int secQueue) {
        this.fName = fName;
        this.sName = sName;
        this.secQueue = secQueue;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getSecQueue() {
        return secQueue;
    }

    public void setSecQueue(int secQueue) {
        this.secQueue = secQueue;
    }
    
    public Passenger() {
        
    }


}
