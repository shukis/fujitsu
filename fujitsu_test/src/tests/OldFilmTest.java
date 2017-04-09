package tests;

import customers.Customer;
import films.Film;
import inventory.Inventory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 09.04.2017.
 */
public class OldFilmTest {
    @Test
    public void testRentFilmCorrectBonusPointsCounter() throws Exception {
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        for(int i = 0; i<50; i++){
            matrix.rentFilm(mike,2);
            matrix.returnFilm(mike, 2);
        }
        assertEquals(50,mike.getBonusPoints());
    }
    @Test
    public void testRentFilmCorrectListAdder() throws Exception{
        Film.rentedFilms.clear();
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        Film americanPie = Inventory.addFilm("American Pie", "old film");
        matrix.rentFilm(mike, 2);
        americanPie.rentFilm(mike, 2);
        assertEquals(2,Film.rentedFilms.size());

    }
    @Test
    public void testRentFilmCorrectFilmHolderSetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 2);
        String customerName = mike.getName();
        assertEquals(customerName,matrix.getHolder().getName());
    }
    @Test
    public void testRentFilmCorrectFilmAvailabilitySetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 2);
        assertFalse(matrix.isAvailable());
    }
    @Test
    public void testRentFilmCorrectPeriodSetter() throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 2);
        assertEquals(2,matrix.getPeriod());
    }

    @Test
    public void testRentFilmCorrectPriceSetter()throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 10);
        int expectedPrice = 18;
        assertEquals(expectedPrice,matrix.getPrice());
    }



    @Test
    public void testReturnFilmCorrectHolder() throws Exception {
        Film.returnedFilms.clear();
        Customer mike = new Customer("Mike");
        Customer jane = new Customer("Jane");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 10);
        matrix.returnFilm(jane,10);
        assertFalse(matrix.isAvailable());
    }

    @Test
    public void testReturnFilmCorrectExtraPriceCounter()throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 5);
        matrix.returnFilm(mike, 8);
        int expectedPrice = 9;
        assertEquals(expectedPrice, matrix.getPrice());
    }
    @Test
    public void testReturnFilmCorrectFilmAvailabilitySetter()throws Exception{
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        matrix.rentFilm(mike, 5);
        matrix.returnFilm(mike, 5);
        assertTrue(matrix.isAvailable());
    }
    @Test
    public void testReturnFilmCorrectListAdder()throws Exception{
        Film.returnedFilms.clear();
        Customer mike = new Customer("Mike");
        Film matrix = Inventory.addFilm("Matrix", "old film");
        Film americanPie = Inventory.addFilm("American Pie", "old film");
        matrix.rentFilm(mike, 5);
        americanPie.rentFilm(mike, 5);
        matrix.returnFilm(mike, 5);
        americanPie.returnFilm(mike, 5);
        assertEquals(2, Film.returnedFilms.size());
    }

}