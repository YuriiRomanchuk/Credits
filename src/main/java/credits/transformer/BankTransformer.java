package credits.transformer;

import credits.model.Bank;

import javax.servlet.http.HttpServletRequest;

public class BankTransformer implements Transformer {

    @Override
    public Bank transformToObject(HttpServletRequest request) {

        Bank bank = new Bank();
        bank.setRegistrationNumber(Integer.parseInt(request.getParameter("registrationNumber")));
        bank.setName(request.getParameter("name"));

        return bank;
    }
}
