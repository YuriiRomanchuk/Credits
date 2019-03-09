package credits.model;

public class CreditLine {

    private Bank bank;
    private int percent;
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
}
