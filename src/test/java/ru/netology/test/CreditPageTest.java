package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class CreditPageTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }
}
