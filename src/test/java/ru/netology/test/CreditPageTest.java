package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import ru.netology.page.HomePage;

import static com.codeborne.selenide.Selenide.open;

public class CreditPageTest {
    HomePage homePage;
    @BeforeEach
    void setUp() {
        homePage = open("http://localhost:8080/", HomePage.class);
    }
}
