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
    public void createInsuranceCard(InsertInsuranceCardRequest request) throws Exception {
        Customer customer = customerRepository.getCustomerById(request.getCustomerId());
        if (customer == null) throw new Exception("Customer doesn't exist");;

        InsuranceCard card = insuranceCardRepository.getInsuranceCard(request.getCardNumber(), request.getExpiryDate());
        if (card != null) throw new Exception("Insurance card is being used. Try a different card");;

        insuranceCardRepository.createInsuranceCard(request);
     }

    @Override
    public InsuranceCard getInsuranceCardByCustomerID(int customerID) {
        return insuranceCardRepository.getInsuranceCardByCustomerId(customerID);
    }

    @Override
    public void deleteInsuranceCardById(int id) throws Exception {
        InsuranceCard insuranceCard = insuranceCardRepository.getInsuranceCardById(id);
        if (insuranceCard == null) throw new Exception("Insurance card doesn't exist");;

        insuranceCardRepository.deleteInsuranceCardById(id);
    }

    @Override
    public InsuranceCard getInsuranceCardById(int id) {
        return insuranceCardRepository.getInsuranceCardById(id);
    }
}
