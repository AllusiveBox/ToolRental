import Rental.*;
import java.time.*;

public class MyTools {

    public static void main(String[] args) {

        JackHammer rentedItem = new JackHammer("JAKR");
        LocalDate testDate = LocalDate.of(2020, 7, 2);
        int rentalDays = 4;
        int discountPercentInt = 101;

        Agreement userAgreement = new Agreement(rentedItem, rentalDays, testDate, discountPercentInt);

        System.out.println(userAgreement.dueDate);
        System.out.println(userAgreement.chargeDays);
        System.out.println(userAgreement.getDiscountPercent());
        System.out.println(userAgreement.getSubTotal());
        System.out.println(userAgreement.getDiscountedAmount());
        System.out.println(userAgreement.getTotal());

        System.out.println(userAgreement.totalAsDouble);
        System.out.println(userAgreement.discountedAmountAsDouble);

    }

}
