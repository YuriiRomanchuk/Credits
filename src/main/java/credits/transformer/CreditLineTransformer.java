package credits.transformer;

import credits.model.Bank;
import credits.model.CreditLine;

import javax.servlet.http.HttpServletRequest;

public class CreditLineTransformer implements Transformer {
    @Override
    public CreditLine transformToObject(HttpServletRequest request) {

        CreditLine creditLine = new CreditLine();
        creditLine.setPercent(Integer.parseInt(request.getParameter("percent")));
        creditLine.setMaxSum(Integer.parseInt(request.getParameter("maxSum")));
        creditLine.setEarlyRedemption(request.getParameter("earlyRedemption").equals("on")? true : false);
        creditLine.setIncreaseCreditLine(request.getParameter("increaseCreditLine").equals("on") ? true : false);

        Bank bank = new Bank();
        bank.setId(Integer.parseInt(request.getParameter("bank")));
        bank.setName("1");

        creditLine.setBank(bank);

        return creditLine;
    }
}
