package Object.User.Customer;

import Object.InsuranceObject.Bank;
import Object.InsuranceObject.Claim;
import Object.InsuranceObject.InsuranceCard;

import java.util.List;

public class Dependent extends Customer {

    //default constructor
    public Dependent() {
        super();
    }

    //initializer
    public Dependent(String username, String password, String customerID, String customerName, String homeAddress,
                     String phoneNumber, String email, List<Claim> claims, InsuranceCard insuranceCard, Bank bank) {
        super(username,password, customerID, customerName, homeAddress, phoneNumber,
                email, claims, insuranceCard, bank);
    }
}