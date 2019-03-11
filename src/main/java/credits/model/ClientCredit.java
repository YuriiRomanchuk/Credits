package credits.model;

public class ClientCredit {

    private Client client;
    private CreditLine creditLine;

    public Client getClient() {
        return client;
    }

    public CreditLine getCreditLine() {
        return creditLine;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCreditLine(CreditLine creditLine) {
        this.creditLine = creditLine;
    }
}
