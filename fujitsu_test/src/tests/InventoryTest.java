package tests;

import customers.Customer;
import films.Film;
import films.RegularFilm;
import inventory.Inventory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by User on 06.04.2017.
 */
public class InventoryTest {


    @Test
    public void testAddFilmNotNull() throws Exception {
        Film americanPie = Inventory.addFilm("American Pie", "regular film");
        assertNotNull(americanPie);
    }

    @Test
    public void testRemoveFilm() throws Exception {

        Film forrestGump = Inventory.addFilm("Forrest Gump", "old film");
        Inventory.removeFilm(forrestGump.getName());
        assertFalse(Inventory.allFilms.contains(forrestGump));

    }


    @Test
    public void testGetFilmByName() throws Exception {
        Film americanPie = Inventory.addFilm("American Pie", "regular film");
        Film currentFilm = Inventory.getFilmByName(americanPie.getName());
        assertEquals(americanPie,currentFilm);
    }

    @Test
    public void testChangeTheTypeOfFilm() throws Exception {
        Film americanPie = Inventory.addFilm("American Pie", "regular film");
        String currentType = Film.getFilmType(americanPie);
        americanPie = Inventory.changeTheTypeOfFilm("American Pie","Old film");
        String newType = Film.getFilmType(americanPie);
        assertNotEquals(currentType,newType);
    }

    @Test
    public void testListAllFilms() throws Exception {
        Inventory.addFilm("King Kong","newrelease");
        Inventory.addFilm("Australia","newrelease");
        Inventory.addFilm("Men In Black 2","newrelease");
        int expectedNumberOfFilms = Inventory.allFilms.size();
        int actualNumberOfFilms = 0;
        for(Film film : Inventory.allFilms){
            actualNumberOfFilms++;
        }
        assertEquals(expectedNumberOfFilms,actualNumberOfFilms);

    }

    @Test
    public void testListAllFilmsInStock() throws Exception {
        Inventory.allFilms.clear();
        Film kingKong = Inventory.addFilm("King Kong","newrelease");
        Inventory.addFilm("Australia","newrelease");
        Inventory.addFilm("Men In Black 2","newrelease");

        kingKong.setAvailable(false);
        int numberOfFilmsInStock = 0;
        for(Film film : Inventory.allFilms){
            if(film.isAvailable()) {
                numberOfFilmsInStock++;
            }
        }
        assertEquals(2,numberOfFilmsInStock);
    }

    @Test
    public void testListAllRentedFilms() throws Exception {
        Inventory.allFilms.clear();
        Film kingKong = Inventory.addFilm("King Kong","newrelease");
        Film australia = Inventory.addFilm("Australia","newrelease");
        Inventory.addFilm("Men In Black 2","newrelease");

        kingKong.setAvailable(false);
        australia.setAvailable(false);
        int numberOfRentedFilms = 0;
        for(Film film : Inventory.allFilms){
            if(!film.isAvailable()) {
                numberOfRentedFilms++;
            }
        }
        assertEquals(2,numberOfRentedFilms);
    }

}