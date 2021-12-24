package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.UserGenerator;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
UserGenerator mySql = new UserGenerator();

@AfterAll
    static void clean(){
    UserGenerator.cleanDB();
}
@SneakyThrows
@Test
    void shouldLogin(){
    open("http://localhost:9999/");
    var loginPage = new LoginPage();
    var autoInfo = DataGenerator.getAuthInfo();
    var verificationPage = loginPage.validLogin(autoInfo);
    var verificationCode = UserGenerator.getVerificationCode("vasya");
    var dashboardPage = verificationPage.validVerify();

}
@SneakyThrows
    @Test
    void  shouldBlockStatusIfThreeWrongPass(){
    open("http://localhost:9999/");
    var loginPage = new LoginPage();
    var autoInfo = DataGenerator.getOtherAuthInfo();
    loginPage.validLogin(autoInfo);
    loginPage.checkErrorNotification();
    loginPage.doubleLoginButtonClick();
    loginPage.checkErrorNotification();
    loginPage.doubleLoginButtonClick();
    loginPage.checkErrorNotification();
    var sqlStatus = mySql.getStatus(autoInfo.getLogin());
    assertEquals("blocked", sqlStatus);





}


}
