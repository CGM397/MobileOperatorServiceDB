package service;

import entity.MonthlyBill;
import entity.Order;

import java.util.ArrayList;
import java.util.Date;

public interface BusinessManagement {
    public ArrayList<Order> getPackage(String cid);

    public boolean subscribePackage(String oid, String cid, String pid, Date startDate, Date endDate);

    public boolean immediatelyUnsubscribePackage(String oid, Date endDate);

    public boolean unsubscribePackageNextMonth(String oid, Date endDate);

    public double call(String cid, int minutes, String year, String month);

    public double dataUse(String cid, double data, String year, String month);

    public MonthlyBill getMonthlyBill(String cid, String year, String month);
}
