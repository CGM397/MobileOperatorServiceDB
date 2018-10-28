package entity;

import java.util.Date;

public class Order {

    private String oid;

    private String pid;

    private String cid;

    private Date startDate;

    private Date endDate;

    public Order() {}

    public Order(String oid, String pid, String cid, Date startDate, Date endDate) {
        this.oid = oid;
        this.pid = pid;
        this.cid = cid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
