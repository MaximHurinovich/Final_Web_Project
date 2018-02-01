package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.BetsDAO;
import by.gurinovich.webproject.entity.Odd;

import java.sql.SQLException;
import java.util.ArrayList;

public class OddLogic {
    private static BetsDAO dao = new BetsDAO();
    public static ArrayList<Odd> getActiveOdds(String username){
        ArrayList<Odd> odds = null;
        try {
            odds = dao.getOdds(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd: odds) {
                if(odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }

    public static ArrayList<Odd> getPassiveOdds(String username){
        ArrayList<Odd> odds = null;
        try {
            odds = dao.getOdds(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Odd> out = new ArrayList<>();
        if (odds != null) {
            for (Odd odd: odds) {
                if(!odd.isActive())
                    out.add(odd);
            }
        }
        return out;
    }
}
