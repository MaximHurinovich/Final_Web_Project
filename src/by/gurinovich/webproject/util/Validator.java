package by.gurinovich.webproject.util;

public class Validator {
    public static final String USERNAME_REGEX = "^^\\S([^\\ ]){1,25}$";
    public static final String PASSWORD_REGEX = "^(\\S){8,25}$";
    public static final String EMAIL_REGEX = "^(\\S){1,25}@(\\w){2,8}.(\\w){2,6}$";
    public static final String CARD_REGEX = "^\\d{4}$";
    public static final String NAME_REGEX = "^([A-Z][a-z]{0,15})|([А-Я][а-я]{0,15})$";

    public boolean checkString(String str, String regEx){
        return str.matches(regEx);
    }
}
