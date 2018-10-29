package entity;

public class Client {

    private String cid;

    private int leftCall;

    private int maxCall;

    private int leftMessage;

    private int maxMessage;

    private double leftLocalData;

    private double maxLocalData;

    private double leftNationalData;

    private double maxNationalData;

    private double balance;

    public Client() {}

    public Client(String cid, int leftCall, int maxCall, int leftMessage, int maxMessage,
                  double leftLocalData, double maxLocalData, double leftNationalData,
                  double maxNationalData, double balance) {
        this.cid = cid;
        this.leftCall = leftCall;
        this.maxCall = maxCall;
        this.leftMessage = leftMessage;
        this.maxMessage = maxMessage;
        this.leftLocalData = leftLocalData;
        this.maxLocalData = maxLocalData;
        this.leftNationalData = leftNationalData;
        this.maxNationalData = maxNationalData;
        this.balance = balance;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getLeftCall() {
        return leftCall;
    }

    public void setLeftCall(int leftCall) {
        this.leftCall = leftCall;
    }

    public int getMaxCall() {
        return maxCall;
    }

    public void setMaxCall(int maxCall) {
        this.maxCall = maxCall;
    }

    public int getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(int leftMessage) {
        this.leftMessage = leftMessage;
    }

    public int getMaxMessage() {
        return maxMessage;
    }

    public void setMaxMessage(int maxMessage) {
        this.maxMessage = maxMessage;
    }

    public double getLeftLocalData() {
        return leftLocalData;
    }

    public void setLeftLocalData(double leftLocalData) {
        this.leftLocalData = leftLocalData;
    }

    public double getMaxLocalData() {
        return maxLocalData;
    }

    public void setMaxLocalData(double maxLocalData) {
        this.maxLocalData = maxLocalData;
    }

    public double getLeftNationalData() {
        return leftNationalData;
    }

    public void setLeftNationalData(double leftNationalData) {
        this.leftNationalData = leftNationalData;
    }

    public double getMaxNationalData() {
        return maxNationalData;
    }

    public void setMaxNationalData(double maxNationalData) {
        this.maxNationalData = maxNationalData;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
