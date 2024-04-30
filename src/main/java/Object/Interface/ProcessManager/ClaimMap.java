package Object.Interface.ProcessManager;

import Object.InsuranceObject.Claim;

import java.util.List;

public interface ClaimMap {
    void addClaim(Claim claim);
    void removeClaim(Claim claim);
    Claim getOne(Claim claim);
    List<Claim> getAll();
}
