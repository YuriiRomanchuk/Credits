package credits.dao;

import credits.model.ClientCredit;

import java.util.List;
import java.util.Map;

public class DaoClientCredit {

    private DataSource dataSource;
    private DaoClient daoClient;
    private DaoCreditLine daoCreditLine;

    public DaoClientCredit(DataSource dataSource, DaoClient daoClient, DaoCreditLine daoCreditLine) {
        this.dataSource = dataSource;
        this.daoClient = daoClient;
        this.daoCreditLine = daoCreditLine;
    }

    public void saveClientCredit(Map<String, String> selections) {

        String query = "insert into client_credits(client_id , credit_line_id) values(?, ?)";

        dataSource.implementWrite(query, ps -> {
            ps.setInt(1, Integer.valueOf(selections.get("client_id")));
            ps.setInt(2, Integer.valueOf(selections.get("credit_line_id")));
        }, r -> {
        });
    }

    public List<ClientCredit> receiveClientCreditByClientId(int client_id) {

        return dataSource.receiveRecords("select * from client_credits where client_id = ?",
                rs -> {
                    ClientCredit clientCredit = new ClientCredit();
                    clientCredit.setClient(daoClient.getClientById(rs.getInt("client_id")));
                    clientCredit.setCreditLine(daoCreditLine.getClientById(rs.getInt("credit_line_id")));
                    return clientCredit;
                },
                preparedStatement -> preparedStatement.setInt(1, client_id));
    }

}
