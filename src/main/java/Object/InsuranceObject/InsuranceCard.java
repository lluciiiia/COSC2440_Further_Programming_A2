package Object.InsuranceObject;

import java.util.Date;

public class InsuranceCard {
    private String cardNumber;
    private Date expiryDate;

    //default constructor
    public InsuranceCard() {
        this.cardNumber = "default";
        this.expiryDate = new Date();
    }

    //initializer
    public InsuranceCard(String cardNumber, Date expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    //getter function
    public String getCardNumber() {
        return cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

}
