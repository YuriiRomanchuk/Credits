package credits.dao;

import credits.reader.ResourceFileReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

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

        ResourceFileReader resourceFileReader = new ResourceFileReader();
        List<String> queries = resourceFileReader.parseFileStrings(resourceFileReader.receiveFileStrings("sql/initializeTable.sql"), ";");

        for (String query : queries) {
            implementWrite(query, p -> {
            });
        }
    }

    private void testConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void implementWrite(String query, SqlConsumer<PreparedStatement> parameters) {

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

    public <T> List<T> receiveRecords(String query, SqlFunction<T> converter, SqlConsumer<PreparedStatement> parameters) {

        List<T> objects = new ArrayList<>();

        try (
                Connection connection = receiveConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            parameters.accept(preparedStatement);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    objects.add(converter.apply(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return objects;
    }

    public <T> Optional<T> receiveFirstRecord(String sql, SqlFunction<T> converter, SqlConsumer<PreparedStatement> parameters) {
        return receiveRecords(sql, converter, parameters).stream().findFirst();
    }

    interface SqlFunction<T> extends Function<ResultSet, T> {
        @Override
        default T apply(ResultSet resultSet) {
            try {
                return safeApply(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        T safeApply(ResultSet resultSet) throws SQLException;
    }

    interface SqlConsumer<T> extends Consumer<T> {
        @Override
        default void accept(T preparedStatement) {
            try {
                safeApply(preparedStatement);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        void safeApply(T preparedStatement) throws SQLException;
    }

}
