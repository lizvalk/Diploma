package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPageTest {
    HomePage homePage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        homePage = open("http://localhost:8080/", HomePage.class);
    }
    @AfterEach
    void tearDown() {
        SQLHelper.cleanDB();
    }
    @Test
    void shouldSuccessfulWhenDataValidForCardApproved() { //все поля валидные карта 1
        var cardInfo = DataHelper.getApprovedCardInfo();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.shouldSuccessfulPayment();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
    @Test
    void shouldRefusalPurchaseUsingCardWithDECLINEDStatus() { //все поля валидные карта 2
        var cardInfo = DataHelper.getDeclinedCardInfo();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.shouldDeclinedTransaction();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }
    @Test
    void shouldSuccessfulIfCardholderDataWithHyphen() { //все поля валидные. Поле Владелец через дефис
        var cardInfo = DataHelper.getCardholderHyphenated();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.shouldSuccessfulPayment();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
    @Test
    void shouldErrorWhenCardNumberEmpty() { //поле Номер карты пустое - неверный формат
        var cardInfo = DataHelper.getEmptyCardNumber();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenCardNumberWithSymbolsOrLetters() { //поле Номер карты заполненное символами/буквами - неверный формат
        var cardInfo = DataHelper.getCardNumberGetFilledLettersOrSymbols();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenCardNumberWithLessThan16Digits() { //поле Номер карты заполненное 15ю символами - неверный формат
        var cardInfo = DataHelper.getNumberCard15Symbols();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenMonthEmpty() { //поле месяц пустое - неверный формат
        var cardInfo = DataHelper.getEmptyMonth();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenMonthWithSymbolsOrLetters() { //поле месяц заполненное символами/буквами - неверный формат
        var cardInfo = DataHelper.getMonthGetFilledLettersOrSymbols();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenMonthWithOneDigit() { //поле месяц заполненное одной цифрой - неверный формат
        var cardInfo = DataHelper.getMonthFilledOneDigit();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldPaymentCardInvalidMonthOver12() { //поле месяц заполненное цифрой 13
        var cardInfo = DataHelper.getCardMonthOver12();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.cardExpirationDateIncorrect();
    }
    @Test
    void shouldPaymentCardInvalidWhen00Month () { //поле месяц заполненное цифрой 00
        var cardInfo = DataHelper.getMonthFilled00();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.cardExpirationDateIncorrect();
    }
    @Test
    void shouldPaymentCardInvalidWhenMonthPreviousToThisYear() { //поле месяц заполненное
        var cardInfo = DataHelper.getCardMonthPreviousToThisYear();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.cardExpirationDateIncorrect();
    }
    @Test
    void shouldErrorWhenYearEmpty() {//поле год пустое
        var cardInfo = DataHelper.getEmptyYear();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldCardExpiredIfDataFromPreviousYear() { //в поле год данные пред года
        var cardInfo = DataHelper.getExpiredCard();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.expiredNotification();
    }
    @Test
    void shouldCardExpirationIncorrectIfData5YearsMoreThenCurrentOne() { //в поле год данные на 5 лет больше текущ
        var cardInfo = DataHelper.getCurrentYearPlus5Years();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.cardExpirationDateIncorrect();
    }
    @Test
    void shouldErrorIfInYearOnlyOneDigit() { //в поле год 1 цифра
        var cardInfo = DataHelper.getOneDigitInYear();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorIfCardholderEmpty() { //пустое поле владелец
        var cardInfo = DataHelper.getEmptyCardholder();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.requiredField();
    }
    @Test
    void shouldErrorIfInCardholderFieldWithNumbers() { //в поле Владелец введены цифры
        var cardInfo = DataHelper.getNumbersInCardholder();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorIfCyrillicInCardholderField() { //Данные о владельце карты указаны неверно: имя и фамилия на кириллице
        var cardInfo = DataHelper.getDataInCyrillic();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorIfInCardholderWithSymbols() { //Данные о владельце карты указаны неверно: введены символы
        var cardInfo = DataHelper.getDataInSymbols();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldErrorWhenCVCEmpty() {//пустое поле CVC
        var cardInfo = DataHelper.getEmptyCVC();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }
    @Test
    void shouldPaymentCardInvalidCvc2Symbols() { //Невалидный код CVC: ввод менее 3 цифр
        var cardInfo = DataHelper.get2SymbolsInCVC();
        var cardPage = homePage.goToCardPage();
        cardPage.fillingCardData(cardInfo);
        cardPage.wrongFormat();
    }


}
