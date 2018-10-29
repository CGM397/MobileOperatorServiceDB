package DAO;

import entity.Order;
import service.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class OrderDaoImpl implements OrderDao{
    public boolean insert(Order order) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "INSERT INTO orders (oid,pid,cid,startDate,endDate) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,order.getOid());
            stmt.setString(2,order.getPid());
            stmt.setString(3,order.getCid());
            stmt.setDate(4,new java.sql.Date(order.getStartDate().getTime()));
            stmt.setDate(5,new java.sql.Date(order.getEndDate().getTime()));
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean update(String oid, Date endDate) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "UPDATE orders SET endDate = ? WHERE oid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1,new java.sql.Date(endDate.getTime()));
            stmt.setString(2,oid);
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public Order findOne(String oid) {
        Order res = new Order();
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "SELECT * FROM orders WHERE oid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,oid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                res = new Order(oid,rs.getString("pid"),
                        rs.getString("cid"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"));
            }
            BaseDao.closeAll(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<Order> find(String cid) {
        ArrayList<Order> res = new ArrayList<Order>();
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "SELECT * FROM orders WHERE cid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cid);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Order oneOrder = new Order(rs.getString("oid"),
                        rs.getString("pid"),
                        rs.getString("cid"),
                        rs.getDate("startDate"),
                        rs.getDate("endDate"));
                res.add(oneOrder);
            }
            BaseDao.closeAll(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
