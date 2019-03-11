package credits.controller;

import credits.exception.ModelException;
import credits.service.BankService;
import credits.service.ClientCreditService;
import credits.view.RedirectViewModel;
import credits.view.View;
import credits.view.ViewModel;

import java.util.Map;

public class ClientCreditController {

    private ClientCreditService clientCreditService;
    private BankService bankService;

    public ClientCreditController(ClientCreditService clientCreditService, BankService bankService) {
        this.clientCreditService = clientCreditService;
        this.bankService = bankService;
    }

    public View showClientCabinet() {
        return new ViewModel("WEB-INF/jsp/client-cabinet.jsp");
    }

    public View addClientCredit(Map<String, String> selections) {
        View view;
        try {
            if (selections.get("credit_line_id") != null) {
                clientCreditService.addClientCredit(selections);
            }
            view = new ViewModel("client-cabinet");
            view.addParameter("client_id", selections.get("client_id"));
            view.addParameter("listClientCredit", clientCreditService.receiveClientCreditsByClientId(Integer.valueOf(selections.get("client_id"))));
            view.addParameter("listOfBank", bankService.getBanks());
        } catch (ModelException e) {
            e.printStackTrace();
            view = new ViewModel("choose-credit-line");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }

        return new RedirectViewModel(view);
    }

}
