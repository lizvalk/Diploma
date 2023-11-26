package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private final SelenideElement heading = $(byText("Кредит по данным карты"));
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement cardholderField = $$("[class='input__control']").get(3);
    private final SelenideElement cvcCodeField = $("[placeholder='999']");
    private final SelenideElement buyButton = $$("span.button__text").get(2);

    public CreditPage() {
        heading.shouldBe(Condition.visible);
    }
//    public void fillingCardData () {
//        cardNumberField.setValue();
//        monthField.setValue();
//        yearField.setValue();
//        cardholderField.setValue();
//        cvcCodeField.setValue();
//        buyButton.click();
//    }

}
