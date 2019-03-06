package credits.service;

import credits.exception.ModelException;
import credits.model.Bank;

import java.util.ArrayList;
import java.util.List;

public class BankService {


    private List<Bank> banks = new ArrayList<>();


    public List<Bank> getBanks() {
        return banks;
    }

    public void addBank(Bank currentBank) throws ModelException {

        try {
            banks.add(currentBank);
        } catch (Exception e) {
            throw new ModelException("Current bank did not save", e);
        }
    }

}
