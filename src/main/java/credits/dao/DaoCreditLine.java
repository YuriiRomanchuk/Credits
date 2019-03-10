package credits.dao;

import credits.model.CreditLine;

import java.sql.SQLException;
import java.util.List;

public class DaoCreditLine {

    private DataSource dataSource;
    private DaoBank daoBank;

    public DaoCreditLine(DataSource dataSource, DaoBank daoBank) {
        this.dataSource = dataSource;
        this.daoBank = daoBank;
    }

    public List<CreditLine> receiveAllCreditLines() {
        return dataSource.receiveRecords("select percent, earlyRedemption, increaseCreditLine, maxSum, bank_id, id from credit_lines", rs -> {
            CreditLine creditLine = new CreditLine();
            creditLine.setPercent(rs.getInt("percent"));
            creditLine.setEarlyRedemption(rs.getBoolean("earlyRedemption"));
            creditLine.setIncreaseCreditLine(rs.getBoolean("increaseCreditLine"));
            creditLine.setMaxSum(rs.getInt("maxSum"));
            creditLine.setId(rs.getInt("id"));
            creditLine.setBank(daoBank.getBankById(rs.getInt("bank_id")));
            return creditLine;
        }, preparedStatement -> {});
    }


    public void saveCreditLine(CreditLine creditLine) {

        String query = "insert into credit_lines (percent, earlyRedemption, increaseCreditLine, maxSum, bank_id ) values(?, ?, ?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            try {
                ps.setInt(1, creditLine.getPercent());
                ps.setBoolean(2, creditLine.isEarlyRedemption());
                ps.setBoolean(3, creditLine.isIncreaseCreditLine());
                ps.setInt(4, creditLine.getMaxSum());
                ps.setInt(5, creditLine.getBank().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}




