package DAO;

import entity.Client;
import service.ClientDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientDaoImpl implements ClientDao{

    public boolean insert(Client client) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "INSERT INTO client (cid,leftCall,leftMessage,leftLocalData," +
                    "leftNationalData,balance) VALUES (?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,client.getCid());
            stmt.setInt(2,client.getLeftCall());
            stmt.setInt(3,client.getLeftMessage());
            stmt.setDouble(4,client.getLeftLocalData());
            stmt.setDouble(5,client.getLeftNationalData());
            stmt.setDouble(6,client.getBalance());
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean update(Client client) {
        boolean res = false;
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "UPDATE client SET leftCall = ?, leftMessage = ?, leftLocalData = ?," +
                    "leftNationalData = ?, balance = ? WHERE cid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,client.getLeftCall());
            stmt.setInt(2,client.getLeftMessage());
            stmt.setDouble(3,client.getLeftLocalData());
            stmt.setDouble(4,client.getLeftNationalData());
            stmt.setDouble(5,client.getBalance());
            stmt.setString(6,client.getCid());
            stmt.executeUpdate();
            res = true;
            conn.close();
            stmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }

    public Client find(String cid) {
        Client res = new Client();
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "SELECT * FROM client WHERE cid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                res = new Client(cid, rs.getInt("leftCall"),
                        rs.getInt("leftMessage"),
                        rs.getDouble("leftLocalData"),
                        rs.getDouble("leftNationalData"),
                        rs.getDouble("balance"));
            }
            BaseDao.closeAll(conn, stmt, rs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
