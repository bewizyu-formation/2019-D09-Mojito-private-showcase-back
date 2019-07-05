package fr.formation.util;

import java.util.regex.*;
import fr.formation.artist.Artist;
import fr.formation.userCommun.UserCommun;

public class Checks {

    public static boolean checkEmail(String email){
        if(email == null){
            return false;
        }
        return Pattern.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",email);
    }

    public static boolean checkPassword(String password){
        if(password == null){
            return false;
        }

        return Pattern.matches(".*[0-9].*", password) && Pattern.matches(".*[a-z].*", password)
                && Pattern.matches(".*[A-Z].*", password) && password.length() >= 8;
    }

    public static boolean checkWithPassword(Artist artist){
        if (artist == null){
            return false;
        }
        return !((artist.getImage() == null ^ artist.getImageType() == null) || !(checkEmail(artist.getEmail())) || !(checkPassword(artist.getPassword()))
                || artist.getDescription() == null || artist.getAdress() == null || artist.getName() == null || artist.getUsername() == null || artist.getCity() == null);
    }

    public static boolean checkWithPassword(UserCommun user){
        return !(!(checkEmail(user.getEmail())) || !(checkPassword(user.getPassword()))
                 || user.getAdress() == null || user.getCity() == null || user.getUsername() == null);
    }

    public static boolean check(Artist artist){
        if (artist == null){
            return false;
        }
        return !((artist.getImage() == null ^ artist.getImageType() == null) || !(checkEmail(artist.getEmail()))
                || artist.getDescription() == null || artist.getAdress() == null || artist.getName() == null
                || artist.getUsername() == null ||  artist.getCity() == null);
    }

    public static boolean check(UserCommun user){
        return !(!(checkEmail(user.getEmail())) || user.getAdress() == null
                || user.getCity() == null || user.getUsername() == null);
    }
}
