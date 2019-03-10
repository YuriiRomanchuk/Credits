package credits.dao;

import credits.model.Bank;

import java.sql.SQLException;
import java.util.List;

public class DaoBank {

    private DataSource dataSource;
    private DataSource.SqlFunction<Bank> bankConverter;


    public DaoBank(DataSource dataSource) {
        this.dataSource = dataSource;
        receiveConverter();
    }

    public void saveBank(Bank bank) {

        String query = "insert into banks (name, registrationNumber) values(?, ?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setString(1, bank.getName());
                ps.setInt(2, bank.getRegistrationNumber());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public List<Bank> receiveAllBanks() {
        return dataSource.receiveRecords("select name, registrationNumber, id from banks",
                bankConverter,
                preparedStatement -> {
                });
    }

    public Bank getBankById(int bank_id) {
        return dataSource.receiveFirstRecord("select * from banks where id = ?",
                bankConverter,
                preparedStatement -> preparedStatement.setInt(1, bank_id)).orElse(null);

    }

    private void receiveConverter() {
        bankConverter = rs -> {
            Bank bank = new Bank();
            bank.setName(rs.getString("name"));
            bank.setRegistrationNumber(rs.getInt("registrationNumber"));
            bank.setId(rs.getInt("id"));
            return bank;
        };

    }

}
