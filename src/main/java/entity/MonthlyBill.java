package entity;

public class MonthlyBill {

    private String cid;

    private String billYear;

    private String billMonth;

    private double cost;

    public MonthlyBill() {}

    public MonthlyBill(String cid, String billYear, String billMonth, double cost) {
        this.cid = cid;
        this.billYear = billYear;
        this.billMonth = billMonth;
        this.cost = cost;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBillYear() {
        return billYear;
    }

    public void setBillYear(String billYear) {
        this.billYear = billYear;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
