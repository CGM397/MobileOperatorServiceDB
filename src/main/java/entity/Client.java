package entity;

public class Client {

    private String cid;

    private int leftCall;

    private int leftMessage;

    private double leftLocalData;

    private double leftNationalData;

    private double balance;

    public Client() {}

    public Client(String cid, int maxCall, int leftMessage,
                  double leftLocalData, double leftNationalData, double balance){
        this.cid = cid;
        this.leftCall = maxCall;
        this.leftMessage = leftMessage;
        this.leftLocalData = leftLocalData;
        this.leftNationalData = leftNationalData;
        this.balance =balance;
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

    public int getLeftMessage() {
        return leftMessage;
    }

    public void setLeftMessage(int leftMessage) {
        this.leftMessage = leftMessage;
    }

    public double getLeftLocalData() {
        return leftLocalData;
    }

    public void setLeftLocalData(double leftLocalData) {
        this.leftLocalData = leftLocalData;
    }

    public double getLeftNationalData() {
        return leftNationalData;
    }

    public void setLeftNationalData(double leftNationalData) {
        this.leftNationalData = leftNationalData;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
