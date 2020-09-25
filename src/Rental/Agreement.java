package Rental;

import java.lang.Integer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.NumberFormat;

public class Agreement {
    /**
     *
     * These Variables Should All be Populated Using Information Supplied from the Tool Object
     *
     */
    public String toolCode;
    public String toolType;
    public String toolBrand;
    public double dailyCharge;
    public boolean weekendCharge;
    public boolean holidayCharge;
    /**
     *
     * The Variables Are Populated Inside the Agreement
     *
     */
    public int rentalDays;
    public LocalDate checkoutDate;
    public Integer discountPercentAsInt;
    /**
     *
     * These Variables Are Calculated After the Necessary Variables are Assigned
     *
     */
    public LocalDate dueDate;
    public int chargeDays;
    public double discountPercentAsDouble;
    public double subTotalAsDouble;
    public double discountedAmountAsDouble;
    public double totalAsDouble;

    /**
     *
     * Class Specific Variables
     *
     */
    private final NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
    private final NumberFormat percentFormat = NumberFormat.getPercentInstance();


    /**
     *
     * Builds an Agreement Instance. Generated Every Time a User Checkout
     * @param toolInstance     An Instance of the Tool Class, the Tool Being Rented
     * @param rentalDays       An int Representing the Number of Days a Tool Will be Rented
     * @param checkoutDate     A Date Representing When a Tool was Checked Out
     * @param discountPercent  A String Representing the
     *
     */
    public Agreement(Tool toolInstance,
                     int rentalDays,
                     LocalDate checkoutDate,
                     int discountPercent) {

        try {
            if (rentalDays < 1) {                                                                  // If rentalDays is Less Than 1...
                throw new RentalDayOutOfBoundsException("Rental days must be at 1 or greater");    // Throw RentalDayOutOfBoundsException
            }
            if (discountPercent > 100 || discountPercent < 0) {                                    // If discountPercent is Greanter Than 100 AND Less Than 0...
                throw new PercentageOutOfBoundsException("Discount percent must be between 0 - 100");
                                                                                                   // Throw PercentageOutOfBoundsException
            }
        } catch (RentalDayOutOfBoundsException error) {                                            // Catch RentalDayOutOfBoundsException
            error.printStackTrace();                                                               // Print the Stack Trace
        } catch (PercentageOutOfBoundsException error) {                                           // Catch RentalDayOutOfBoundsException
            error.printStackTrace();                                                               // Print the Stack Trace
        }

        this.toolCode = toolInstance.code;                                                         // Assign the Tool Code to This Agreement
        this.toolType = toolInstance.type;                                                         // Assign the Tool Type to This Agreement
        this.toolBrand = toolInstance.brand;                                                       // Assign the Tool Brand to This Agreement
        this.dailyCharge = toolInstance.dailyCharge;                                               // Assign the Daily Charge to This Agreement
        this.weekendCharge = toolInstance.weekendCharge;                                           // Assign If the Tool has Weekend Charges to This Agreement
        this.holidayCharge = toolInstance.holidayCharge;                                           // Assign if the Tool has Holiday Charges to This Agreement

        this.rentalDays = rentalDays;                                                              // Assign the Rental Days to This Agreement
        this.checkoutDate = checkoutDate;                                                          // Assign the Checkout Date to This Agreement
        this.discountPercentAsInt = discountPercent;                                               // Assign the Discount Percent As an Integer to This Agreement

        this.dueDate = checkoutDate.plusDays(rentalDays);                                          // Calculate the Due Date for this Agreement
        this.chargeDays = calculateChargeDays();                                                   // Calculate the Number of Days The User Will be Charged for
        this.discountPercentAsDouble = this.discountPercentAsInt / 100.00;                         // Calculate the Discount Percent as a Double
        this.subTotalAsDouble = ((double) this.chargeDays) * (this.dailyCharge);                   // Calculate the Sub Total
        this.discountedAmountAsDouble = this.subTotalAsDouble * this.discountPercentAsDouble;      // Calculate the Discounted Amount as a Double
        this.totalAsDouble = this.subTotalAsDouble - this.discountedAmountAsDouble;                // Calculate the Total as a Double
        this.totalAsDouble = ((this.totalAsDouble == this.discountedAmountAsDouble) ?              // Edge Case for .X9 Values due to Rounding...
                                (this.totalAsDouble - 0.01) : this.totalAsDouble);

    }

    /**
     *
     * Calculates the Number of Days that the User Will Be Charged For Renting the Item
     * @return  An Integer Representing the Number of Days the User Will be Charged
     *
     */
    private int calculateChargeDays() {

        int chargeDays = this.rentalDays;                                                          // Set the Charge Days to the Rental Days

        if (!this.weekendCharge) {                                                                 // If This Agreement Doesn't Charge on Weekends...
            chargeDays -= calculateWeekendsToNotChargeFor();                                       // Charge n Less Days for This Agreement
        }

        if (!this.holidayCharge) {                                                                 // If This Agreement Doesn't Charge on Holidays...
            chargeDays -= calculateHolidaysToNotChargeFor();                                       // Charge n Less Days for This Agreement
        }

        return chargeDays;                                                                         // Return the Number of Days the User Should be Charged

    }

    /**
     *
     * Calculates the Number of Days to Not Charge If a Tool Does Not Charge on Weekends
     * @return  An Integer Representing the Number of Days a User Will Not be Charged
     *
     */
    private int calculateWeekendsToNotChargeFor() {

        LocalDate bufferDate = this.checkoutDate;                                                  // Create a Dummy Date and Set it Equal to the Checkout Date
        int daysToNotCharge = 0;                                                                   // Create a Running Total of Days to Not Charge the User For

        while (!bufferDate.equals(this.dueDate)) {                                                 // While the Dummy Date is Not the Due Date...

            if ((bufferDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) ||                            // If the Dummy Date is Sunday OR
                    bufferDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {                        // If the Dummy Date is Saturday...
                daysToNotCharge++;                                                                 // Increase the Number of Days to Not Charge by 1
            }

            bufferDate = bufferDate.plusDays(1);                                                   // Increase the Dummy Date by 1. Very Important

        }

        return daysToNotCharge;                                                                    // Return the Number of Days the User Should NOT be Charged

    }

    /**
     *
     * Calculates the Number of Days to Not Charge if a Tool Does Not Charge on Holidays
     * @return  An Integer Representing the Number of Days a User Will not be Charged
     *
     */
    private int calculateHolidaysToNotChargeFor() {

        int daysToNotCharge = 0;                                                                   // Create a Running Total of Days to Not Charge the User For
        int currentYear = this.checkoutDate.getYear();                                             // Get the Starting Year
        LocalDate independenceDay = calculateIndependenceDay(currentYear);                         // Calculate Independence Day This Year
        LocalDate laborDay = calculateLaborDay(currentYear);                                       // Calculate Labor Day This Year

        if ((independenceDay.isAfter(this.checkoutDate)) &&                                        // If Independence Day is After the Checkout Date AND
                (independenceDay.isBefore(this.dueDate))) {                                        // If Independence Day is Before the Due Date...
            daysToNotCharge++;                                                                     // Increase the Number of Days to Not Charge by 1
        }

        if ((laborDay.isAfter(this.checkoutDate)) &&                                               // If Labor Day is After the Checkout Date AND
                (laborDay.isBefore(this.dueDate))) {                                               // If Labor Day is Before the Due Date...
            daysToNotCharge++;                                                                     // Increase the Number of Days to Not Charge by 1
        }

        return daysToNotCharge;                                                                    // Return the Number of Days the User Should NOT be Charged

    }

    /**
     *
     * Calculates When Labor Day is on a Given Year
     * @param currentYear  An int Representing the Given Year
     * @return  A LocalDate Representing Labor Day in a Given Year
     *
     */
    private LocalDate calculateLaborDay(int currentYear) {

        LocalDate octoberFirst = LocalDate.of(currentYear, 9, 1);                                  // Calculate the First Day of October...
        LocalDate laborDay = octoberFirst;                                                         // Set Labor Day to October First

        for (int i = 0; i < 6; i++) {                                                              // Loop Through a Full Week...

            if (octoberFirst.plusDays(i).getDayOfWeek().equals(DayOfWeek.MONDAY)) {                // If October First + i is a Monday...

                i = 6;                                                                             // Set i = 6 to Escape the Loop
                laborDay = octoberFirst.plusDays(i);                                               // Set laborDay to octoberFirst.plusDays(i)

            }

        }

        return laborDay;                                                                           // Return the Date of Labor Day for the Year

    }

    /**
     *
     * Calculates When Independence Day is on a Given Year
     * @param currentYear  An int Representing the Given Year
     * @return  A LocalDate Representing Independence Day in a Given Year
     *
     */
    private LocalDate calculateIndependenceDay(int currentYear) {

        LocalDate independenceDay = LocalDate.of(currentYear, 7, 4);                               // Calculate the 4th of July...

        if (independenceDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {                           // If the 4th is a Saturday...
            return independenceDay.minusDays(1);                                                   // Return the 3rd of July
        } else if (independenceDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {                      // Else If the 4th is a Sunday...
            return independenceDay.plusDays(1);                                                    // Return the 5th of July
        } else {                                                                                   // Else the 4th is Not a Saturday or a Sunday...
            return independenceDay;                                                                // Return the 4th of July
        }

    }

    /**
     *
     * Returns the Total in a Formatted String
     * @return  A Formatted String Containing the Total for the Agreement
     *
     */
    public String getTotal() {

        return defaultFormat.format(this.totalAsDouble);                                           // Return the Total in $XXX,XXX.XX Format

    }

    /**
     *
     * Returns the Total for the Agreement
     * @return  A Formatted String Containing the Sub Total for the Agreement
     *
     */
    public String getSubTotal() {

        return defaultFormat.format(this.subTotalAsDouble);                                        // Return the Sub Total in $XXX,XXX.XX Format

    }

    /**
     *
     * Returns the Discount Amount for the Agreement
     * @return  A Formatted String Containing the Discounted Amount for the Agreement
     *
     */
    public String getDiscountedAmount() {

        return defaultFormat.format(this.discountedAmountAsDouble);                                // Return the Discounted Amount in $XXX,XXX.XX Format

    }

    /**
     *
     * Returns the Checkout Date for the Agreement
     * @return  A Formatted String Containing the Checkout Date for the Agreement
     *
     */
    public String getCheckoutDate() {

        return this.checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));                  // Returns the Checkout Date in mm/dd/yy Format

    }

    /**
     *
     * Returns the Due Date for the Agreement
     * @return  A Formatted String Containing the Due Date for the Agreement
     *
     */
    public String getDueDate() {

        return this.dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy"));                       // Returns the Due Date in mm/dd/yy Format

    }

    /**
     *
     * Returns the Discount Percent for the Agreement
     * @return  A Formatted String Containing the Discount Percent for the Agreement
     *
     */
    public String getDiscountPercent() {

        return percentFormat.format(this.discountPercentAsDouble);                                 // Returns the Discount Percent as X%

    }
}

class PercentageOutOfBoundsException extends Exception {

    public PercentageOutOfBoundsException(String message) {

        super(message);                                                                            // Call the Parent's Constructor and Supply the Message

    }

}

class RentalDayOutOfBoundsException extends Exception {

    public RentalDayOutOfBoundsException(String message) {

        super(message);                                                                            // Call the Parent's Constructor and Supply the Message

    }

}