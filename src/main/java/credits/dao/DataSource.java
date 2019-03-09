package credits.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.function.Consumer;

public class DataSource {

    private final String url;
    private final String userName;
    private final String password;

    public DataSource() {

        testConnection();

        url = "jdbc:postgresql://localhost/postgres";
        userName = "decorator";
        password = "p3214567";

        InitializeDataBase();
    }

    public Connection receiveConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    private void InitializeDataBase() {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            File file = new File("sql/initializeTable.sql");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void testConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void implementWrite(String query, Consumer<PreparedStatement> parameters) {

        try (
                Connection connection = receiveConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            parameters.accept(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
