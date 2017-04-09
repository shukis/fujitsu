package main;

import customers.Customer;
import films.Film;
import inventory.Inventory;


/**
 * Created by User on 04.04.2017.
 */
public class Main {

    public static void main(String args[]) {
        Customer eddie = new Customer("Eddie");
        Customer jack = new Customer("Jack");
        Customer margo = new Customer("Margo");

        eddie.setBonusPoints(60);

        Film matrix = Inventory.addFilm("Matrix","old film");
        Film kingKong = Inventory.addFilm("King Kong","newrelease");
        Film australia = Inventory.addFilm("Australia","regular film");
        Film menInBlack2 = Inventory.addFilm("Men In Black 2","regularfilm");
        Film fightClub = Inventory.addFilm("Fight club","regularfilm");
        Film avatar = Inventory.addFilm("Avatar","newrelease");
        Film terminator = Inventory.addFilm("Terminator","old film");



        matrix.rentFilm(jack,3);
        kingKong.rentFilm(eddie,5);
        australia.rentFilm(eddie,4);
        menInBlack2.rentFilm(margo,1);
        fightClub.rentFilm(jack,6);
        avatar.rentFilm(eddie,5);
        terminator.rentFilm(jack,3);

        Film.makeRentedFilmsReceipt(eddie);
        Film.makeRentedFilmsReceipt(jack);
        Film.makeRentedFilmsReceipt(margo);

        matrix.returnFilm(jack,3);
        kingKong.returnFilm(eddie,2);
        australia.returnFilm(eddie,5);
        menInBlack2.returnFilm(margo,6);


        Film.makeReturnedFilmsReceipt(eddie);
        Film.makeReturnedFilmsReceipt(jack);
        Film.makeReturnedFilmsReceipt(margo);


        Inventory.changeTheTypeOfFilm("Matrix","regular film");

        Inventory.listAllFilms();
        Inventory.listAllFilmsInStock();
        Inventory.listAllRentedFilms();
        System.out.println("Bonus points:");
        System.out.println("Eddie:"+eddie.getBonusPoints());
        System.out.println("Jack:"+jack.getBonusPoints());
        System.out.println("Margo:"+margo.getBonusPoints());


    }
}
