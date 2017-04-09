package tests;

import films.Film;
import inventory.Inventory;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User on 09.04.2017.
 */
public class FilmTest {
    @Test
    public void testAddFilmToRentedFilmsList() throws Exception {
        Film.rentedFilms.clear();
        Film matrix = Inventory.addFilm("Matrix", "regular film");
        Film avatar = Inventory.addFilm("Avatar", "New Release");
        Film.addFilmToRentedFilmsList(matrix);
        Film.addFilmToRentedFilmsList(avatar);
        assertEquals(2,Film.rentedFilms.size());
    }

    @Test
    public void testGetFilmType() throws Exception {
        Film matrix = Inventory.addFilm("Matrix", "regular film");
        String result = Film.getFilmType(matrix);
        assertEquals("Regular film", result);
    }

    @Test
    public void testAddFilmToReturnedFilmsList() throws Exception {
        Film.returnedFilms.clear();
        Film matrix = Inventory.addFilm("Matrix", "regular film");
        Film avatar = Inventory.addFilm("Avatar", "New Release");
        Film.addFilmToReturnedFilmsList(matrix);
        Film.addFilmToReturnedFilmsList(avatar);
        assertEquals(2,Film.returnedFilms.size());
    }

}