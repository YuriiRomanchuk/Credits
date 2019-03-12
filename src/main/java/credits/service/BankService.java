package credits.service;

import credits.data.dao.DaoBank;
import credits.exception.ModelException;
import credits.data.model.Bank;

import java.util.List;

public class BankService {

    private DaoBank daoBank;

    public BankService(DaoBank daoBank) {
        this.daoBank = daoBank;
    }

    public List<Bank> getBanks() {
        return daoBank.receiveAllBanks();
    }

    public void addBank(Bank currentBank) throws ModelException {
        try {
            daoBank.saveBank(currentBank);
        } catch (Exception e) {
            throw new ModelException("Current bank does not save", e);
        }
    }

}
