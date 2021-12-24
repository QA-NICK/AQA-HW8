package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField =  $("[data-test-id='login'] input");
    private SelenideElement passwordField =  $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");


    public VerificationPage validLogin (DataGenerator.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passwordField.setValue(authInfo.getPassword());
        loginButton.click();
        return new VerificationPage();


    }
    public void doubleLoginButtonClick() {
        loginButton.click();

    }
    public void checkErrorNotification (){
        errorNotification.shouldBe(Condition.visible);
    }

}
