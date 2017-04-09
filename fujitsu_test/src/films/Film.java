package films;

import customers.Customer;
import inventory.Inventory;


import java.util.ArrayList;

/**
 * Created by User on 04.04.2017.
 */
public abstract class Film {


    private Customer holder;
    private final String name;
    private boolean available = true;
    private int period, price;
    public static final int PREMIUM_PRICE = 4;
    public static final int BASIC_PRICE = 3;
    public static ArrayList<Film> rentedFilms = new ArrayList<>();
    public static ArrayList<Film> returnedFilms = new ArrayList<>();


    public Film(String name) {
        this.name = name;
        Inventory.allFilms.add(this);
    }

    public Customer getHolder() {
        return holder;
    }

    public void setHolder(Customer holder) {
        this.holder = holder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract void rentFilm(Customer customer, int numberOfDays);

    public abstract void returnFilm(Customer customer,int daysSinceFilmWasRented);



    public static void addFilmToRentedFilmsList(Film film){
        rentedFilms.add(film);
    }

    public static void makeRentedFilmsReceipt(Customer customer){
        ArrayList<Film> tempRentedFilms = new ArrayList<>();
        int totalPrice = 0;
        boolean paymentWithBonusPoints = false;
        String customerName = customer.getName();
        System.out.println("Customer: " + customerName);
        for(Film film : rentedFilms){
            if(customerName.equals(film.getHolder().getName())) {
                if (film.getPrice() == 0) {
                    System.out.println(film.getName() + " (" + getFilmType(film) + ") " + film.getPeriod() + " days (Paid with 25 Bonus points)");
                    paymentWithBonusPoints = true;
                } else {
                    System.out.println(film.getName() + " (" + getFilmType(film) + ") " + film.getPeriod() + " days " + film.getPrice() + " EUR");
                    totalPrice += film.getPrice();
                }
                tempRentedFilms.add(film);
            }

        }
        rentedFilms.removeAll(tempRentedFilms);
        System.out.println("Total price: "+totalPrice+" EUR");
        if(paymentWithBonusPoints){
            System.out.println("Remaining Bonus points: "+customer.getBonusPoints());
        }
        System.out.println("-----------------------------------------------");
    }

    public static void makeReturnedFilmsReceipt(Customer customer){
        ArrayList<Film> tempReturnedFilms = new ArrayList<>();
        boolean returnedInTime = true;
        int totalPrice = 0;
        String customerName = customer.getName();
        System.out.println("Customer: " + customerName);
        for(Film film : returnedFilms){
            if(customerName.equals(film.getHolder().getName())) {
                if (film.getPeriod() < 1) {
                    System.out.println(film.getName() + " (" + getFilmType(film) + ") returned in time!");

                } else {
                    returnedInTime = false;
                    System.out.println(film.getName() + " (" + getFilmType(film) + ") " + film.getPeriod() + " extra days " + film.getPrice() + " EUR");
                    totalPrice += film.getPrice();
                }
                tempReturnedFilms.add(film);
            }

        }
        returnedFilms.removeAll(tempReturnedFilms);
        if(!returnedInTime) {
            System.out.println("Total late charge: " + totalPrice + " EUR");
        }
        System.out.println("-----------------------------------------------");
    }


    public static String getFilmType(Film film){
        String clazz = film.getClass().toString().substring(film.getClass().toString().lastIndexOf('.')+1);
        if(clazz.equals("NewRelease")) return "New release";
        else if(clazz.equals("RegularFilm")) return "Regular film";
        else if(clazz.equals("OldFilm")) return "Old film";
        return null;
    }

    public static void addFilmToReturnedFilmsList(Film film) {
        rentedFilms.remove(film);
        returnedFilms.add(film);
    }


}
