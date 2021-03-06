package credits.service;

import credits.data.dao.DaoCreditLine;
import credits.exception.ModelException;
import credits.data.model.CreditLine;

import java.util.List;
import java.util.Map;

public class CreditLineService {

    private DaoCreditLine daoCreditLine;

    public CreditLineService(DaoCreditLine daoCreditLine) {
        this.daoCreditLine = daoCreditLine;
    }

    public List<CreditLine> getCreditLines() {
        return daoCreditLine.receiveAllCreditLines();
    }

    public List<CreditLine> getCreditLines(Map<String, String> selections) {
        return daoCreditLine.receiveSelectionsCreditLines(selections);
    }

    public void addCreditLine(CreditLine creditLine) throws ModelException {
        try {
            daoCreditLine.saveCreditLine(creditLine);
        } catch (Exception e) {
            throw new ModelException("Current credit line does not save", e);
        }
    }

}
