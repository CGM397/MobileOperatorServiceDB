package entity;

public class Package {

    private String pid;

    private double pcost;

    private int maxCall;

    private int maxMessage;

    public Package() {}

    public Package(String pid, double pcost, int maxCall, int maxMessage, double maxLocalData, double maxNationalData) {
        this.pid = pid;
        this.pcost = pcost;
        this.maxCall = maxCall;
        this.maxMessage = maxMessage;
        this.maxLocalData = maxLocalData;
        this.maxNationalData = maxNationalData;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public double getPcost() {
        return pcost;
    }

    public void setPcost(double pcost) {
        this.pcost = pcost;
    }

    public int getMaxCall() {
        return maxCall;
    }

    public void setMaxCall(int maxCall) {
        this.maxCall = maxCall;
    }

    public int getMaxMessage() {
        return maxMessage;
    }

    public void setMaxMessage(int maxMessage) {
        this.maxMessage = maxMessage;
    }

    public double getMaxLocalData() {
        return maxLocalData;
    }

    public void setMaxLocalData(double maxLocalData) {
        this.maxLocalData = maxLocalData;
    }

    public double getMaxNationalData() {
        return maxNationalData;
    }

    public void setMaxNationalData(double maxNationalData) {
        this.maxNationalData = maxNationalData;
    }

    private double maxLocalData;

    private double maxNationalData;

}
