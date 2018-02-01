package by.gurinovich.webproject.logic;

import by.gurinovich.webproject.dao.ResultsDAO;
import by.gurinovich.webproject.entity.Race;

import java.sql.SQLException;
import java.util.ArrayList;

public class ResultsLogic {
    public static ArrayList<Race> results(){
        ResultsDAO dao = new ResultsDAO();
        ArrayList<Race> results = null;
        try {
            results= dao.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
