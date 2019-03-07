package credits.service;

import credits.exception.ModelException;
import credits.model.CreditLine;

import java.util.ArrayList;
import java.util.List;

public class CreditLineService {

    private List<CreditLine> creditLines = new ArrayList<>();

    public List<CreditLine> getCreditLines() {
        return creditLines;
    }

    public void addCreditLine(CreditLine creditLine) throws ModelException {
        try {
            creditLines.add(creditLine);
        } catch (Exception e) {
            throw new ModelException("Current credit line does not save", e);
        }
    }

}
