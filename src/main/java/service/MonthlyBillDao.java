package service;

import entity.MonthlyBill;

import java.util.Date;

public interface MonthlyBillDao {
    public boolean insert(MonthlyBill bill);

    public boolean update(double cost, String cid, String billYear, String billMonth);

    public MonthlyBill find(String cid, String billYear, String billMonth);
}
