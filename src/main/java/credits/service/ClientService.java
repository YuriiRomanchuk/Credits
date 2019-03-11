package credits.service;

import credits.dao.DaoClient;
import credits.exception.ModelException;
import credits.model.Client;

public class ClientService {

    private DaoClient daoClient;

    public ClientService(DaoClient daoClient) {
        this.daoClient = daoClient;
    }

    public void addClient(Client client) throws ModelException {

        try {
            daoClient.saveClient(client);
        } catch (Exception e) {
            throw new ModelException("Current client does not save", e);
        }

    }

}
