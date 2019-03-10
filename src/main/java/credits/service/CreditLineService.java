package credits.service;

import credits.dao.DaoCreditLine;
import credits.exception.ModelException;
import credits.model.CreditLine;

import java.util.List;

public class CreditLineService {

    private DaoCreditLine daoCreditLine;

    public CreditLineService(DaoCreditLine daoCreditLine) {
        this.daoCreditLine = daoCreditLine;
    }

    public List<CreditLine> getCreditLines() {
        return daoCreditLine.receiveAllCreditLines();
    }

    public void addCreditLine(CreditLine creditLine) throws ModelException {
        try {
            daoCreditLine.saveCreditLine(creditLine);
        } catch (Exception e) {
            throw new ModelException("Current credit line does not save", e);
        }
    }

}
