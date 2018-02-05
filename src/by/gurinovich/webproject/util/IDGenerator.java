package by.gurinovich.webproject.util;

public class IDGenerator {
    private static int id = 100;
    public static int generateID(){
        return ++id;
    }
}
