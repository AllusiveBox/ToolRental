package Rental;

public class Chainsaw extends Tool {

    /**
     *
     * Builds a Chainsaw Instance, Which is an Instance of Tool
     * @param code   A String Representing the Tool Code For the Tool
     *
     */
    public Chainsaw(String code) {

        super(code);
        try {
            switch(code) {                                                                         // Switch Statement Based off the Tool's Code to Assign Tool Brand
                case "CHNS" :                                                                      // When the Code is CHNS...
                    this.brand = "Stihl";                                                          // The Tool is Made by Stihl
                    break;                                                                         // Break out of the Switch Statement
                default :                                                                          // Default Case for Unexpected Tool Codes
                    throw new UnknownToolCodeException("Unknown tool code: " + code);              // Throw an UnknownToolCodeException
            }
        } catch (UnknownToolCodeException error) {                                                 // Catch UnknownToolCodeException
            error.printStackTrace();                                                               // Print the Stack Trace
        }
        this.type = "Chainsaw";                                                                    // Assign the Type
        this.dailyCharge = 1.49;                                                                   // Assign the Daily Charge
        this.weekDayCharge = true;                                                                 // This Tool has a Week Day Charge
        this.weekendCharge = false;                                                                // This Tool does NOT have a Weekend Charge
        this.holidayCharge = true;                                                                 // This Tool has a Holiday Charge

    }

}
