package credits.transformer;

import credits.data.model.Bank;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class BankTransformer implements Transformer {

    @Override
    public Bank transformToObject(HttpServletRequest request) {

        Bank bank = new Bank();
        bank.setRegistrationNumber(Integer.parseInt(request.getParameter("registrationNumber")));
        try {
            bank.setName(new String(request.getParameter("name").getBytes("ISO-8859-1"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return bank;
    }
}
