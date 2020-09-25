package Rental;

public class Tool {

    public String code;                                                                            // String Representing the Tool Code
    public String brand;                                                                           // String Representing the Brand of Tool
    public String type;                                                                            // String Representing the Type of Tool
    public double dailyCharge;                                                                     // Double Representing the Daily Fee for Renting the Tool
    public boolean weekDayCharge;                                                                  // Boolean Determines if there is Weekday Charge or Not
    public boolean weekendCharge;                                                                  // Boolean Determines if there is Weekend Charge or Not
    public boolean holidayCharge;                                                                  // Boolean Determines if there is Holiday Charge or Not

    /**
     *
     * Builds a Tool Instance
     * @param code   A String Representing the Tool Code For the Tool
     *
     */
    public Tool(String code) {

        this.code = code;                                                                          // Set the Tool Code Equal to the Code Supplied

    }

}

class UnknownToolCodeException extends Exception {

    public UnknownToolCodeException(String message) {

        super(message);                                                                            // Call the Parent's Constructor and Supply the Message

    }

}