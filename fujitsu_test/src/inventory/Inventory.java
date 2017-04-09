package inventory;


import films.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import static films.Film.getFilmType;

/**
 * Created by User on 05.04.2017.
 */
public class Inventory {

    public static ArrayList<Film> allFilms = new ArrayList<>();


    public static Film addFilm(String name, String type){
        type = type.replaceAll(" ","");
        if(type.trim().equalsIgnoreCase("newrelease")){
            return new NewRelease(name);
        }else if(type.trim().equalsIgnoreCase("regularfilm")){
            return new RegularFilm(name);
        }else if(type.trim().equalsIgnoreCase("oldfilm")){
            return new OldFilm(name);
        }
        System.out.println("Incorrect type of a film!");
        return null;
    }
    public static void removeFilm(String name) {
        Film film = getFilmByName(name);
        if (film != null) {
            allFilms.remove(film);
        }

    }

    public static Film getFilmByName(String name){
        try {
            Predicate<Film> predicate = c -> c.getName().equalsIgnoreCase(name);
            Film film = allFilms.stream().filter(predicate).findFirst().get();
            return film;

        }catch (NoSuchElementException e){
            System.out.println("Such film does not exist!");
        }
        return null;
    }

    public static Film changeTheTypeOfFilm(String name, String newType){
        Film film = getFilmByName(name);
        if(film.isAvailable()) {
            removeFilm(name);
            return addFilm(name, newType);
        }
        System.out.println("Film is not available at the moment!");
        return null;
    }
    public static void listAllFilms(){
        System.out.println("All films:");
        for(Film film : allFilms){
            System.out.println(film.getName()+ " ("+ getFilmType(film)+")");
        }
        System.out.println("------------");
    }

    public static void listAllFilmsInStock(){
        System.out.println("Films in stock:");
        for(Film film : allFilms){
            if(film.isAvailable()){
                System.out.println(film.getName()+ " ("+ getFilmType(film)+")");
            }
        }
        System.out.println("------------");
    }
    public static void listAllRentedFilms(){
        System.out.println("Rented films:");
        for(Film film : allFilms){
            if(!film.isAvailable()){
                System.out.println(film.getName()+ " ("+ getFilmType(film)+")" + ". Holder: "+film.getHolder().getName());
            }
        }
        System.out.println("------------");
    }


}
