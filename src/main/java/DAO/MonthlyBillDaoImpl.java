package DAO;

import entity.MonthlyBill;
import service.MonthlyBillDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class MonthlyBillDaoImpl implements MonthlyBillDao{
    public boolean insert(MonthlyBill bill) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "INSERT INTO monthlyBill (cid, billYear,billMonth, cost) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,bill.getCid());
            stmt.setString(2,bill.getBillYear());
            stmt.setString(3,bill.getBillMonth());
            stmt.setDouble(4,bill.getCost());
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean update(double cost, String cid, String billYear, String billMonth) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "UPDATE monthlyBill SET cost = cost + ? WHERE cid = ? AND billYear = ? " +
                    "AND billMonth = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1,cost);
            stmt.setString(2,cid);
            stmt.setString(3,billYear);
            stmt.setString(4,billMonth);
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public MonthlyBill find(String cid, String billYear, String billMonth) {
        MonthlyBill res = new MonthlyBill();
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "SELECT cost FROM monthlyBill WHERE cid = ? AND billYear = ? AND billMonth = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cid);
            stmt.setString(2,billYear);
            stmt.setString(3,billYear);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                res = new MonthlyBill(cid, billYear, billMonth, rs.getDouble("cost"));
            }
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
