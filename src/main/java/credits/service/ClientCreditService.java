package credits.service;

import credits.data.dao.DaoClientCredit;
import credits.exception.ModelException;
import credits.data.model.ClientCredit;

import java.util.List;
import java.util.Map;

public class ClientCreditService {
    private DaoClientCredit daoClientCredit;

    public ClientCreditService(DaoClientCredit daoClientCredit) {
        this.daoClientCredit = daoClientCredit;
    }

    public void addClientCredit(Map<String, String> selections) throws ModelException {

        try {
            daoClientCredit.saveClientCredit(selections);
        } catch (Exception e) {
            throw new ModelException("Current client credit does not save", e);
        }

    }

    public List<ClientCredit> receiveClientCreditsByClientId(int client_id) {
        return daoClientCredit.receiveClientCreditByClientId(client_id);
    }
}
