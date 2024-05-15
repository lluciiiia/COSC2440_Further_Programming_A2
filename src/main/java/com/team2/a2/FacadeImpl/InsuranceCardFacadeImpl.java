package com.team2.a2.FacadeImpl;

import com.team2.a2.Facade.InsuranceCardFacade;
import com.team2.a2.Model.InsuranceObject.InsuranceCard;
import com.team2.a2.Model.User.Customer.Customer;
import com.team2.a2.Repository.CustomerRepository;
import com.team2.a2.Repository.InsuranceCardRepository;
import com.team2.a2.Request.InsertInsuranceCardRequest;

public class InsuranceCardFacadeImpl implements InsuranceCardFacade {

    CustomerRepository customerRepository;
    InsuranceCardRepository insuranceCardRepository;

    public InsuranceCardFacadeImpl() {
        this.customerRepository = new CustomerRepository();
        this.insuranceCardRepository = new InsuranceCardRepository();
    }

    @Override
    public void createInsuranceCard(InsertInsuranceCardRequest request) {
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        if (customer == null) return;

        InsuranceCard card = insuranceCardRepository.getInsuranceCard(request.getCardNumber(), request.getExpiryDate());
        if (card != null) return;

        InsuranceCard customerCard = insuranceCardRepository.getInsuranceCardByCustomerId(request.getCustomerId());
        if (customerCard != null) return;

        insuranceCardRepository.createInsuranceCard(request);
     }

    @Override
    public InsuranceCard getInsuranceCardByCustomerId(int customerId) {
        return insuranceCardRepository.getInsuranceCardByCustomerId(customerId);
    }
}
