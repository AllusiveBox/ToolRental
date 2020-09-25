package Rental;

public class Ladder extends Tool {

    /**
     *
     * Builds a Ladder Instance, Which is an Instance of Tool
     * @param code   A String Representing the Tool Code For the Tool
     *
     */
    public Ladder(String code) {

        super(code);
        try {
            switch(code) {                                                                         // Switch Statement Based off the Tool's Code to Assign Tool Brand
                case "LADW" :                                                                      // When the Code is CHNS...
                    this.brand = "Werner";                                                         // The Tool is Made by Stihl
                    break;                                                                         // Break out of the Switch Statement
                default :                                                                          // Default Case for Unexpected Tool Codes
                    throw new UnknownToolCodeException("Unknown tool code: " + code);              // Throw an UnknownToolCodeException
            }
        } catch (UnknownToolCodeException error) {                                                 // Catch UnknownToolCodeException
            error.printStackTrace();                                                               // Print the Stack Trace
        }
        this.type = "Ladder";                                                                      // Assign the Type
        this.dailyCharge = 1.99;                                                                   // Assign the Daily Charge
        this.weekDayCharge = true;                                                                 // This Tool Has a Week Day Charge
        this.weekendCharge = true;                                                                 // This Tool has a Weekend Charge
        this.holidayCharge = true;                                                                 // This Tool has a Holiday Charge

    }

}
