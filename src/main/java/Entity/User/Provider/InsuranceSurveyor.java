package Entity.User.Provider;

public class InsuranceSurveyor extends Provider{

    //default constructor
    public InsuranceSurveyor(){
        super();
    }

    //initializer
    public InsuranceSurveyor(String username, String password, String companyName, String companyAddress,
                             String providerPhone, String providerEmail, String providerID, String providerName){
        super(username, password, companyName, companyAddress, providerPhone, providerEmail, providerID, providerName);
    }
}
