package Rental;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

class AgreementTest {

    @Test
    public void TestAgreementCalculatesCorrect() throws Exception {

        Ladder rentedItem = new Ladder("LADW");
        LocalDate testDate = LocalDate.of(2020, 9, 24);
        int rentalDays = 5;
        int discountPercentInt = 10;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "09/29/20";
        int chargeDays = 5;
        String discountPercent = "10%";
        String subTotal = "$9.95";
        String discountedAmount = "$0.99";
        String total = "$8.96";

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Test
    public void TestAgreementInvalidDiscountPercent() throws Exception {

        JackHammer rentedItem = new JackHammer("JAKR");
        LocalDate testDate = LocalDate.of(2015, 9, 3);
        int rentalDays = 5;
        int discountPercent = 101;

        exception.expect(PercentageOutOfBoundsException.class);
        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercent);

    }

    @Test
    public void TestAgreementWithLadder() {

        Ladder rentedItem = new Ladder("LADW");
        LocalDate testDate = LocalDate.of(2020, 7,2);
        int rentalDays = 3;
        int discountPercentInt = 10;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "07/05/20";
        int chargeDays = 3;
        String discountPercent = "10%";
        String subTotal = "$5.97";
        String discountedAmount = "$0.60";
        String total = "$5.37";

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Test
    public void TestAgreementWithChainSaw() {

        Chainsaw rentedItem = new Chainsaw("CHNS");
        LocalDate testDate = LocalDate.of(2015, 7,2);
        int rentalDays = 5;
        int discountPercentInt = 25;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "07/07/15";
        int chargeDays = 3;
        String discountPercent = "25%";
        String subTotal = "$4.47";
        String discountedAmount = "$1.12";
        String total = "$3.35";

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Test
    public void TestAgreementWithJackHammerOnWeekends() {

        JackHammer rentedItem = new JackHammer("JAKD");
        LocalDate testDate = LocalDate.of(2015,9,3);
        int rentalDays = 6;
        int discountPercentInt = 0;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "09/09/15";
        int chargeDays = 4;
        String discountPercent = "0%";
        String subTotal = "$11.96";
        String discountedAmount = "$0.00";
        String total = subTotal;

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Test
    public void TestAgreementWithJackHammerOverHoliday() {

        JackHammer rentedItem = new JackHammer("JAKR");
        LocalDate testDate = LocalDate.of(2015, 7,2);
        int rentalDays = 9;
        int discountPercentInt = 0;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "07/11/15";
        int chargeDays = 6;
        String discountPercent = "0%";
        String subTotal = "$17.94";
        String discountedAmount = "$0.00";
        String total = subTotal;

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Test
    public void TestAgreementWithJackHammerOverHolidayAndWeekend() {

        JackHammer rentedItem = new JackHammer("JAKR");
        LocalDate testDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 4;
        int discountPercentInt = 50;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        String dueDate = "07/06/20";
        int chargeDays = 1;
        String discountPercent = "50%";
        String subTotal = "$2.99";
        String discountedAmount = "$1.50";
        String total = "$1.49";

        assertEquals(dueDate, userAgreement.getDueDate());
        assertEquals(chargeDays, userAgreement.chargeDays);
        assertEquals(discountPercent, userAgreement.getDiscountPercent());
        assertEquals(subTotal, userAgreement.getSubTotal());
        assertEquals(discountedAmount, userAgreement.getDiscountedAmount());
        assertEquals(total, userAgreement.getTotal());

    }

    @Test
    public void TestAgreementWithInvalidRentDay() throws Exception {

        JackHammer rentedItem = new JackHammer("JAKD");
        LocalDate testDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 0;
        int discountPercent = 5;

        exception.expect(PercentageOutOfBoundsException.class);
        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercent);

    }

    @Test
    public void TestToolBrandWithInvalidToolCode() {

        exception.expect(UnknownToolCodeException.class);
        Ladder rentedItem = new Ladder("ZAD");

    }

}