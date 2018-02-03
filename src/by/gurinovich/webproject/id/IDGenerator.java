package by.gurinovich.webproject.id;

public class IDGenerator {
    private static int id = 100;
    public static int generateID(){
        return ++id;
    }
}
