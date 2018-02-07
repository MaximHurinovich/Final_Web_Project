package test.by.gurinovich.webproject;

import by.gurinovich.webproject.entity.User;
import by.gurinovich.webproject.util.Validator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidatorTest {
    private User user;
    private Validator validator;
    @BeforeClass
    public void init(){
        user = new User("zaerer", "afafafaf8822", "Maxik", "Kochanov", "0001", "kochanov1199@mail.ru", 228.24);
        validator = new Validator();
    }

    @AfterClass
    public void destroy(){
        user = null;
    }

    @Test
    public void nameTest(){
        boolean firstname = validator.checkString(user.getFirstName(), Validator.NAME_REGEX);
        boolean secondname = validator.checkString(user.getSecondName(), Validator.NAME_REGEX);
        Assert.assertTrue(firstname&&secondname);
    }

    @Test
    public void emailTest(){
        boolean email = validator.checkString(user.getEmail(), Validator.EMAIL_REGEX);
        Assert.assertTrue(email);
    }

    @Test
    public void usernameTest(){
        boolean username = validator.checkString(user.getUsername(), Validator.USERNAME_REGEX);
        Assert.assertTrue(username);
    }

    @Test
    public void cardTest(){
        boolean cardNumber = validator.checkString(user.getCardNumber(), Validator.CARD_REGEX);
        Assert.assertTrue(cardNumber);
    }

    @Test
    public void passwordTest(){
        boolean password = validator.checkString(user.getPassword(), Validator.PASSWORD_REGEX);
        Assert.assertTrue(password);
    }
}
