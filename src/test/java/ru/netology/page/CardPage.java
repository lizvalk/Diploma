package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage {
    private final SelenideElement heading = $(byText("Оплата по карте"));
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement nameField = $$("span.input__box").get(3);
    private final SelenideElement cvcCodeField = $("[placeholder='999']");
    private final SelenideElement buyButton = $$("span.button__text").get(2);

    public CardPage() {
        heading.shouldBe(Condition.visible);
    }
}