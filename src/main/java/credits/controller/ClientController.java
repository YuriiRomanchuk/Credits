package credits.controller;

import credits.exception.ModelException;
import credits.model.Client;
import credits.service.BankService;
import credits.service.ClientCreditService;
import credits.service.ClientService;
import credits.view.RedirectViewModel;
import credits.view.View;
import credits.view.ViewModel;

public class ClientController {

    private ClientService clientService;
    private BankService bankService;

    public ClientController(ClientService clientService, BankService bankService) {
        this.clientService = clientService;
        this.bankService = bankService;
    }

    public View showAddClient() {
        return new ViewModel("WEB-INF/jsp/add-client.jsp");
    }

    public View addClient(Client client, ClientCreditService clientCreditService) {
        View view;
        try {
            clientService.addClient(client);
            view = new ViewModel("client-cabinet");
            view.addParameter("client_id", client.getId());
            view.addParameter("listClientCredit", clientCreditService.receiveClientCreditsByClientId(client.getId()));
            view.addParameter("listOfBank", bankService.getBanks());
            /* view.addParameter("client", List<ClientCredit>);*/
        } catch (ModelException e) {
            view = new ViewModel("add-client");
            view.addParameter("Error", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectViewModel(view);
    }

}
