package ru.netology.data;

import lombok.Value;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class DataGenerator {

    public static AuthInfo getAuthInfo() {
        return  new AuthInfo("vasya","qwerty123");}

    public static AuthInfo getOtherAuthInfo () {
        return new AuthInfo("petya","123qwery");}





    @Value
    public static class AuthInfo {
        private String login;
        private String password;

    }

    @Value
       public static class VerificationCode{
        private String code;
    }

    @Value
    public static class CardInfo{
        private String cardNumber;
    }



}

