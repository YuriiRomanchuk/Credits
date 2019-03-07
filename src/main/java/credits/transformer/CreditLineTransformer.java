package credits.transformer;

import credits.model.CreditLine;

import javax.servlet.http.HttpServletRequest;

public class CreditLineTransformer implements Transformer {
    @Override
    public CreditLine transformToObject(HttpServletRequest request) {

       /* bankService.getBanks();

        CreditLine creditLine = new CreditLine();
        creditLine.setRegistrationNumber(Integer.parseInt(request.getParameter("registrationNumber")));
        creditLine.setName(request.getParameter("name"));*/

        return null;
    }
}
