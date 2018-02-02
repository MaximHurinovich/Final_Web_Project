package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.RacesDAO;
import by.gurinovich.webproject.dao.UsersDAO;

import java.sql.SQLException;

public class AdminLogic {

    public boolean deleteRace(int id){
        RacesDAO dao = new RacesDAO();
        try {
            return dao.deleteRace(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean banUser(String username){
        UsersDAO dao = new UsersDAO();
        try {
            return dao.banUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean makeAdmin(String username){
        UsersDAO dao = new UsersDAO();
        try{
            return dao.makeAdmin(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean makeBookmaker(String username){
        UsersDAO dao = new UsersDAO();
        try{
            return dao.makeBookmaker(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
