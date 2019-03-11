package credits.dao;

import credits.model.CreditLine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DaoCreditLine {

    private DataSource dataSource;
    private DaoBank daoBank;

    public DaoCreditLine(DataSource dataSource, DaoBank daoBank) {
        this.dataSource = dataSource;
        this.daoBank = daoBank;
    }

    public List<CreditLine> receiveAllCreditLines() {
        return dataSource.receiveRecords("select percent, earlyRedemption, increaseCreditLine, maxSum, bank_id, id from credit_lines",
                rs -> CreditLineConverter(rs),
                preparedStatement -> {
                });
    }

    public List<CreditLine> receiveSelectionsCreditLines(Map<String, String> selections) {
        return dataSource.receiveRecords("select percent, earlyRedemption, increaseCreditLine, maxSum, bank_id, id from credit_lines" +
                        " where percent <= ? and earlyRedemption = ? and increaseCreditLine = ? and maxSum <= ?",
                rs -> CreditLineConverter(rs),
                preparedStatement -> {
                    preparedStatement.setInt(1, Integer.valueOf(selections.get("percent")));
                    preparedStatement.setBoolean(2, selections.get("earlyRedemption") == "1" ? true : false);
                    preparedStatement.setBoolean(3, selections.get("increaseCreditLine") == "1" ? true : false);
                    preparedStatement.setInt(4, Integer.valueOf(selections.get("maxSum")));
                });
    }


    public void saveCreditLine(CreditLine creditLine) {

        String query = "insert into credit_lines (percent, earlyRedemption, increaseCreditLine, maxSum, bank_id ) values(?, ?, ?, ?, ?)";

        dataSource.implementWrite(query, ps -> {
            ps.setInt(1, creditLine.getPercent());
            ps.setBoolean(2, creditLine.isEarlyRedemption());
            ps.setBoolean(3, creditLine.isIncreaseCreditLine());
            ps.setInt(4, creditLine.getMaxSum());
            ps.setInt(5, creditLine.getBank().getId());
        }, r -> {
        });
    }


    public CreditLine getClientById(int credit_line_id) {
        return dataSource.receiveFirstRecord("select * from credit_lines where id = ?",
                rs -> CreditLineConverter(rs),
                preparedStatement -> preparedStatement.setInt(1, credit_line_id)).orElse(null);

    }

    private CreditLine CreditLineConverter(ResultSet rs) throws SQLException {
        CreditLine creditLine = new CreditLine();
        creditLine.setPercent(rs.getInt("percent"));
        creditLine.setEarlyRedemption(rs.getBoolean("earlyRedemption"));
        creditLine.setIncreaseCreditLine(rs.getBoolean("increaseCreditLine"));
        creditLine.setMaxSum(rs.getInt("maxSum"));
        creditLine.setId(rs.getInt("id"));
        creditLine.setBank(daoBank.getBankById(rs.getInt("bank_id")));
        return creditLine;
    }


}




