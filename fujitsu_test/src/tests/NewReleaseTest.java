package tests;

import customers.Customer;
import films.Film;
import inventory.Inventory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static sun.misc.Version.print;

/**
 * Created by User on 04.04.2017.
 */
public class NewReleaseTest {
    @Test
    public void testRentFilmCorrectBonusPointsCounter() throws Exception {
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        for(int i = 0; i<15; i++){
            split.rentFilm(mike,2);
            split.returnFilm(mike, 2);
        }
        assertEquals(3,mike.getBonusPoints());
    }
    @Test
    public void testRentFilmCorrectListAdder() throws Exception{
        Film.rentedFilms.clear();
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        Film kong = Inventory.addFilm("Kong", "new release");
        split.rentFilm(mike, 2);
        kong.rentFilm(mike, 2);
        assertEquals(2,Film.rentedFilms.size());

    }
    @Test
    public void testRentFilmCorrectFilmHolderSetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 2);
        String customerName = mike.getName();
        assertEquals(customerName,split.getHolder().getName());
    }
    @Test
    public void testRentFilmCorrectFilmAvailabilitySetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 2);
        assertFalse(split.isAvailable());
    }
    @Test
    public void testRentFilmCorrectPeriodSetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 2);
        assertEquals(2,split.getPeriod());
    }

    @Test
    public void testRentFilmCorrectPriceSetter()throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 10);
        int expectedPrice = 40;
        assertEquals(expectedPrice,split.getPrice());
    }



    @Test
    public void testReturnFilmCorrectHolder() throws Exception {
        Film.returnedFilms.clear();
        Customer mike = new Customer("Mike");
        Customer jane = new Customer("Jane");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 10);
        split.returnFilm(jane,10);
        assertFalse(split.isAvailable());
    }

    @Test
    public void testReturnFilmCorrectExtraPriceCounter()throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 5);
        split.returnFilm(mike, 8);
        int expectedPrice = 12;
        assertEquals(expectedPrice, split.getPrice());
    }
    @Test
    public void testReturnFilmCorrectFilmAvailabilitySetter()throws Exception{
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        split.rentFilm(mike, 5);
        split.returnFilm(mike, 5);
        assertTrue(split.isAvailable());
    }
    @Test
    public void testReturnFilmCorrectListAdder()throws Exception{
        Film.returnedFilms.clear();
        Customer mike = new Customer("Mike");
        Film split = Inventory.addFilm("Split", "new release");
        Film kong = Inventory.addFilm("Kong", "new release");
        split.rentFilm(mike, 5);
        kong.rentFilm(mike, 5);
        split.returnFilm(mike, 5);
        kong.returnFilm(mike, 5);
        assertEquals(2, Film.returnedFilms.size());
    }
}