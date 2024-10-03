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

    /*
     * METHOD STUBS TO ENSURE CODE COMPILES. YOU WILL HAVE TO REWRITE THIS CODE TO
     * MATCH THE SPECIFICATIONS AND ALSO YOU'LL HAVE TO WRITE METHOD isEmpty.
     */

    public boolean contains(String name) {
        if (name == null) return false; 
        TrainStation cursor = this.head; 
        while (cursor != null) {
            if (cursor.getName().equals(name)) {
                return true;        // Returns true if the station name is given by the string parameter, and if it exists in a the object.
            }
            cursor = cursor.getNext(); 
        }
        return false;   // returns false if no station is found
    }

    public int indexOf(String name) {
        if (name == null) return -1;
    TrainStation cursor = this.head;
    int index = 0; 
    while (cursor != null) {
        if (cursor.getName().equals(name)) {
            return index; 
        }
        cursor = cursor.getNext();
        index++; 
    }
    return -1;
    }

    public String reverseList() {
        if (this.head == null) return "";  // If the list is empty, it returns an empty string

        TrainStation cursor = this.head;
        String result = "";  // Result string that will hold the reversed station names
    
        while (cursor != null) {
            // add each station name to the result string
            result = cursor.getName() + "\n" + result;
            cursor = cursor.getNext();  // moves to the next station
        }
        
        return result;
    }

    public boolean isEmpty() {
        return this.head == null; // Returns true if there are no stations, otherwise false
    }

    /*public TrainStation remove(int position) {
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
    

    /*******************************************************************************
     * DO NOT REMOVE TESTS FROM THE CODE BELOW. YOU MAY **ADD** YOUR OWN TESTS BUT *
     * YOU MAY NOT REMOVE ANY OF THE EXISTING TEST CODE. *
     ******************************************************************************/
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Guard tests
        redLineSB.indexOf(null);
        redLineSB.contains(null);
        // Test indexOf on existing values
        boolean indexOfTestExisting = true;
        for (int i = 0; i < stationNames.length; i++) {
            indexOfTestExisting = (indexOfTestExisting && (redLineSB.indexOf(stationNames[i]) == i));
        }
        // Test indexOf for non existing station
        boolean indexOfTestNotExisting = (redLineSB.indexOf(randomName) == -1);
        // Test indexOf on empty line
        boolean indexOfTestingEmpty = (brownLineSB.indexOf(stationNames[0]) == -1);
        // Test contains for existing stations
        boolean containsTestExisting = true;
        for (String station : stationNames) {
            containsTestExisting = (containsTestExisting && redLineSB.contains(station));
        }
        // Test contains for non existing values
        boolean containsTestNonExisting = (!redLineSB.contains(randomName));
        // Test reverse list
        String expectedReverseList = "";
        for (int i = stationNames.length - 1; i >= 0; i--) {
            expectedReverseList = expectedReverseList + stationNames[i] + "\n";
        }
        boolean reverseListTest = redLineSB.reverseList().equals(expectedReverseList);
        // Reporting strings
        final String PASS = "Pass";
        final String FAIL = "Fail";
        String reportIndexOfTestExisting = (indexOfTestExisting) ? PASS : FAIL;
        String formatIndexOfTestExisting = "\n\nindexOf test for existing values: ......... %s";
        String reportIndexOfTestNonExisting = (indexOfTestNotExisting) ? PASS : FAIL;
        String formatIndexOfTestNonExisting = "\nindexOf test for non existing values: ..... %s";
        String reportIndexOfTestEmpty = (indexOfTestingEmpty) ? PASS : FAIL;
        String formatIndexOfTestEmpty = "\nindexOf test for empty object: ............ %s";
        String reportContaisTestExisting = (containsTestExisting) ? PASS : FAIL;
        String formatContainsTestExisting = "\ncontains test for existing values: ........ %s";
        String reportContainsTestNonExisting = (containsTestNonExisting) ? PASS : FAIL;
        String formatContainsTestNonExisting = "\ncontains test for non existing values: .... %s";
        String reportReverseListTest = (reverseListTest) ? PASS : FAIL;
        String formatReverseListTest = "\nreverseList test: ......................... %s\n\n";
        System.out.printf(formatIndexOfTestExisting, reportIndexOfTestExisting);
        System.out.printf(formatIndexOfTestEmpty, reportIndexOfTestEmpty);
        System.out.printf(formatIndexOfTestNonExisting, reportIndexOfTestNonExisting);
        System.out.printf(formatContainsTestExisting, reportContaisTestExisting);
        System.out.printf(formatContainsTestNonExisting, reportContainsTestNonExisting);
        System.out.printf(formatReverseListTest, reportReverseListTest);
        // ----------- YOU MAY ADD YOUR OWN TESTS BELOW THIS COMMENT LINE ---------------

        redLineSB.contains("Morse"); // true
        redLineSB.contains("Bryn Mawr"); // false
        System.out.println(redLineSB.isEmpty()); // returns false
        
        redLineSB.indexOf("Loyola");  // returns 3
        redLineSB.indexOf("Oak Park"); // returns -1

        System.out.println(redLineSB.reverseList());

        System.out.println(redLineSB.isEmpty()); // returns false

    } // method main
} // class TrainLine
