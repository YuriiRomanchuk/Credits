package credits.model;

public class CreditLine {

    private Bank bank;
    private int percent;
    private boolean earlyRedemption;
    private boolean increaseCreditLine;
    private int maxSum;
    private int id;

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CreditLine() {
    }

    public CreditLine(Bank bank, int percent, int id) {
        this.bank = bank;
        this.percent = percent;
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public int getPercent() {
        return percent;
    }

    public int getId() {
        return id;
    }

    public boolean isEarlyRedemption() {
        return earlyRedemption;
    }

    public boolean isIncreaseCreditLine() {
        return increaseCreditLine;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setEarlyRedemption(boolean earlyRedemption) {
        this.earlyRedemption = earlyRedemption;
    }

    public void setIncreaseCreditLine(boolean increaseCreditLine) {
        this.increaseCreditLine = increaseCreditLine;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }
}
