package Rental;

public class JackHammer extends Tool {

    /**
     *
     * Builds a JackHammer Instance, Which is an Instance of Tool
     * @param code   A String Representing the Tool Code For the Tool
     *
     */
    public JackHammer(String code) {

        super(code);                                                                               // Call the Parent Class's Constructor and Supply the Tool Code
        try {
            switch(code) {                                                                         // Switch Statement Based off the Tool's Code to Assign Tool Brand
                case "JAKR" :                                                                      // When the Code is JAKR...
                    this.brand = "Ridgid";                                                         // The Tool is Made by Ridgid
                    break;                                                                         // Break out of the Switch Statement
                case "JAKD" :                                                                      // When the Code is JAKD...
                    this.brand = "DeWalt";                                                         // The Tool is Made by DeWalkt
                    break;                                                                         // Break out of the Switch Statement
                default :                                                                          // Default Case for Unexpected Tool Codes
                    throw new UnknownToolCodeException("Unknown tool code: " + code);              // Throw an UnknownToolCodeException
            }
        } catch (UnknownToolCodeException error) {                                                 // Catch UnknownToolCodeException
            error.printStackTrace();                                                               // Print the Stack Trace
        }

        this.type = "Jackhammer";                                                                  // Assign the Type
        this.dailyCharge = 2.99;                                                                   // Assign the Daily Charge
        this.weekDayCharge = true;                                                                 // This Tool has a Week Day Charge
        this.weekendCharge = false;                                                                // This Tool does NOT Have a Weekend Charge
        this.holidayCharge = false;                                                                // This Tool does NOT Have a Holiday Charge

    }

}
