package credits.dao;

import credits.model.Bank;

import java.sql.SQLException;
import java.util.function.Consumer;

public class DaoBank {

    private DataSource dataSource;
    private Consumer<Bank> bankConverter;


    public DaoBank(DataSource dataSource) {
        this.dataSource = dataSource;
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


 /*
    private void initializeCreateBank() {

        bankConverter = bc -> {

        }


    }*/
}
