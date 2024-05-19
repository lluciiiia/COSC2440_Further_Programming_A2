package com.team2.a2.FacadeImpl;

/**
 * @author <Team 2>
 */

import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Account;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Repository.AccountRepository;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.InsuranceCardRepository;
import com.team2.a2.Repository.LogRepository;

public class InsuranceCardFacadeImpl implements InsuranceCardFacade {

    CustomerRepository customerRepository;
    InsuranceCardRepository insuranceCardRepository;
    LogRepository logRepository;
    AccountRepository accountRepository;

    public InsuranceCardFacadeImpl() {
        this.customerRepository = new CustomerRepository();
        this.insuranceCardRepository = new InsuranceCardRepository();
        this.logRepository = new LogRepository();
        this.accountRepository = new AccountRepository();
    }

    @Override
    public InsuranceCard getInsuranceCardByCustomerID(int customerID) {
        return insuranceCardRepository.getInsuranceCardByCustomerId(customerID);
    }

    @Override
    public void deleteInsuranceCardById(int id, int userAccountId) throws Exception {
        Account userAccount = accountRepository.getAccountById(userAccountId);
        if (userAccount == null) throw new Exception("Current user's account doesn't exist");

        InsuranceCard insuranceCard = insuranceCardRepository.getInsuranceCardById(id);
        if (insuranceCard == null) throw new Exception("Insurance card doesn't exist");;

        insuranceCardRepository.deleteInsuranceCardById(id);

        logRepository.createLog(userAccountId, "Deleted an insurance card with id " + id);
    }

    @Override
    public InsuranceCard getInsuranceCardById(int id) {
        return insuranceCardRepository.getInsuranceCardById(id);
    }
}
