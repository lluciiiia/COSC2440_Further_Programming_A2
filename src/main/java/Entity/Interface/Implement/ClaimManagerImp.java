package Entity.Interface.Implement;

import Entity.InsuranceObject.Claim;
import Entity.Interface.ProcessManager.ClaimMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClaimManagerImp implements ClaimMap {
    private Map<String, Claim> claimMap;
    public ClaimManagerImp() {
        claimMap = new HashMap<>();
    }

    @Override
    public void addClaim(Claim claim) {
        if (!claimMap.containsKey(claim.getClaimID())) {
            claimMap.put(claim.getClaimID(), claim);
        } else {
            System.out.println("Claim already exists!");
        }
    }

    @Override
    public void removeClaim(Claim claim) {
        if (claimMap.containsKey(claim.getClaimID())) {
            claimMap.remove(claim.getClaimID());
        } else {
            System.out.println("Claim does not exist!");
        }
    }

    @Override
    public Claim getOne(Claim claim) {
        return claimMap.get(claim.getClaimID());
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claimMap.values());
    }
}
