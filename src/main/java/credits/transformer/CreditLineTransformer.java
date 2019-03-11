package credits.transformer;

import credits.model.Bank;
import credits.model.CreditLine;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CreditLineTransformer implements Transformer {
    @Override
    public CreditLine transformToObject(HttpServletRequest request) {

        CreditLine creditLine = new CreditLine();
        creditLine.setPercent(Integer.parseInt(request.getParameter("percent")));
        creditLine.setMaxSum(Integer.parseInt(request.getParameter("maxSum")));
        creditLine.setEarlyRedemption(request.getParameter("earlyRedemption") == null ? false : true);
        creditLine.setIncreaseCreditLine(request.getParameter("increaseCreditLine") == null ? false : true);

        Bank bank = new Bank();
        bank.setId(Integer.parseInt(request.getParameter("bank")));
        bank.setName("1");

        creditLine.setBank(bank);

        return creditLine;
    }

    public Map<String, String> transformToMap(HttpServletRequest request) {

        Map<String, String> selections = new HashMap<>();

        selections.put("percent", request.getParameter("percent"));
        selections.put("maxSum", request.getParameter("maxSum"));
        selections.put("earlyRedemption", request.getParameter("earlyRedemption") == null ? "0" : "1");
        selections.put("increaseCreditLine", request.getParameter("earlyRedemption") == null ? "0" : "1");
      /*  selections.put("bank_id", request.getParameter("bank").equals("Choose...") ? "" : request.getParameter("bank"));*/
        selections.put("client_id", request.getParameter("client_id"));

        return selections;
    }

}
