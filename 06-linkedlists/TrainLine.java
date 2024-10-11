public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation newStation = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = newStation;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(newStation);
        }
        // The new station becomes the tail station of the line
        this.tail = newStation;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations


    public TrainStation remove(int position) {
        TrainStation removed = null;
        //Operate this method if position is legal
        if (position > 0 && position < this.numberOfStations) {
            if (position == 1) {
                // Special case, remove head
                removed = this.head;
                this.head = this.head.getNext();
            } else {
                // find station prior to the one to be removed
                TrainStation cursor = this.head;
                for (int i = 1; i < position - 1; i++) {
                    cursor = cursor.getNext();
                }
                // cursor is now @ prior
                if (cursor.getNext() == this.tail) {
                    this.tail = cursor;
                }
                removed = cursor.getNext();
                cursor.setNext(cursor.getNext().getNext());
                removed.setNext(null);
            }
            removed.setNext(null);
            this.numberOfStations - 1;
        }
        return removed;
    } // method remove

    //method to print the trainline
    public void printTrainLine() {
        TrainStation current = this.head;
        while (current != null) {
            System.out.print(current.getName() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    //instert method
    public void insert(String name, int position) {
        // Create the new station
        TrainStation newStation = new TrainStation(name);
    
        // If the position is at the head (position 0)
        if (position == 0) {
            newStation.setNext(this.head);
            this.head = newStation;
            if (this.tail == null) { // If the list was empty, set tail too
                this.tail = newStation;
            }
        } else {
            // Traverse the trainline to find the station before the position
            TrainStation cursor = this.head;
            for (int i = 1; i < position; i++) {
                cursor = cursor.getNext();
            }
    
            // Insert the new station after the cursor
            newStation.setNext(cursor.getNext());
            cursor.setNext(newStation);
    
            // If we're inserting at the end, update the tail
            if (newStation.getNext() == null) {
                this.tail = newStation;
            }
        }
    
        // Update the count of stations
        this.numberOfStations++;
    }
//toString method
public String toString() {
    StringBuilder result = new StringBuilder();
    TrainStation current = this.head;
    int charCount = 0;
    boolean forward = true;  // Keep track of direction (--> or <--)
    String forwardArrow = " --> ";
    String backwardArrow = " <-- ";

    while (current != null) {
        String stationName = current.getName();
        
        // break to a new line if line length exceeds 80 characters
        if (charCount + stationName.length() + forwardArrow.length() > 80) {
            result.append("\n");  // New line
            charCount = 0;        // Reset character count
            forward = !forward;   // Switch direction
        }

        if (forward) {
            // Add station and forward arrow
            result.append(stationName).append(forwardArrow);
            charCount += stationName.length() + forwardArrow.length();
        } else {
            // Add backward arrow and station in reverse
            result.insert(0, backwardArrow + stationName);
            charCount += stationName.length() + backwardArrow.length();
        }

        current = current.getNext();
    }

    // Trim final trailing arrow
    int lastArrowLength = forward ? forwardArrow.length() : backwardArrow.length();
    result.setLength(result.length() - lastArrowLength);
    
    return result.toString();
} // couldn't quite figure this out out fully

    
public static void main(String[] args) {
    // A few station names
    String[] stationNames = { "Howard", "Jarvis", "Morse",
            "Loyola", "Granville", "Thorndale" };
    // A populated trainline
    TrainLine redLineSB = new TrainLine("Red Line SB");
    for (String station : stationNames) {
        redLineSB.add(station);
    }

    // Test case for void insert method
    System.out.println("Insert Morse to position 2");
    redLineSB.insert("Morse", 2);
    redLineSB.printTrainLine();

    // Test case for toString method with extended station list
    System.out.println("Snake-like visualization: ");
    String[] extendedStationNames = { "Howard", "Jarvis", "Morse", "Loyola", "Granville", "Thorndale", 
                            "Addison", "Sheridan", "Wilson", "Argyle", "Bryn Mawr", 
                            "Belmont", "Fullerton", "North/Clybourn", "Clark/Division", 
                            "Roosevelt", "Harrison", "Jackson", "Monroe", "Clark", "Chicago", 
                            "Cermak-Chinatown", "Sox-35th", "47th", "Garfield", "63rd", 
                            "69th", "95th/Dan Ryan", "87th", "79th" };

    for (String station : extendedStationNames) {
        redLineSB.add(station);
    }

    // Print the string representation of the TrainLine
    System.out.println(redLineSB.toString());
} // method main

} // class TrainLine
