package films;

import customers.Customer;

/**
 * Created by User on 04.04.2017.
 */
public class NewRelease extends Film {

    public NewRelease(String name) {
        super(name);

    }

    @Override
    public void rentFilm(Customer customer, int numberOfDays) {
        if (!isAvailable()) {
            System.out.println("Film '"+getName()+"' is not available!");
        } else {
            setAvailable(false);
            int customerBonusPoints = customer.getBonusPoints();
            setPeriod(numberOfDays);
            setHolder(customer);
            if(customerBonusPoints>24){
                setPrice(0);
                customer.setBonusPoints(customerBonusPoints-25);
            }else {
                setPrice(numberOfDays * PREMIUM_PRICE);
                customer.setBonusPoints(customerBonusPoints + 2);
            }
            addFilmToRentedFilmsList(this);
        }
    }

    @Override
    public void returnFilm(Customer customer,int daysSinceFilmWasRented) {
        if (isAvailable()) {
            System.out.println("'"+getName()+"' is already in stock");
        } else {
            if(customer.getName().equals(getHolder().getName())) {
                setAvailable(true);
                int extraDays = daysSinceFilmWasRented - getPeriod();
                if (extraDays >= 0) {
                    int lateCharge = extraDays * PREMIUM_PRICE;
                    setPrice(lateCharge);
                }
                setPeriod(extraDays);
                addFilmToReturnedFilmsList(this);
            }else{
                System.out.println("You can not return other customers films.");
            }
        }
    }

}
