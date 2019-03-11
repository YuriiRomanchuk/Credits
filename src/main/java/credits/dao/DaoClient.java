package credits.dao;

import credits.model.Client;

public class DaoClient {

    private DataSource dataSource;

    public DaoClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveClient(Client client) {

        Client oldClient = getClientByName(client.getName());

        if (oldClient == null) {

            String query = "insert into clients (name) values(?)";

            dataSource.implementWrite(query, ps -> {
                ps.setString(1, client.getName());

            }, r -> client.setId(r.getInt(1)));
        } else {
            client.setId(oldClient.getId());
        }
    }


    public Client getClientById(int client_id) {

        return dataSource.receiveFirstRecord("select * from clients where id = ?",
                receiveClientConverter(),
                preparedStatement -> preparedStatement.setInt(1, client_id)).orElse(null);

    }

    public Client getClientByName(String name) {
        return dataSource.receiveFirstRecord("select * from clients where name = ?",
                receiveClientConverter(),
                preparedStatement -> preparedStatement.setString(1, name)).orElse(null);

    }

    private DataSource.SqlFunction<Client> receiveClientConverter() {
        return rs -> {
            Client client = new Client();
            client.setName(rs.getString("name"));
            client.setId(rs.getInt("id"));
            return client;
        };
    }
}
