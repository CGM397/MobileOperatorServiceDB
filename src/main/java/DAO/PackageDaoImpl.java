package DAO;

import entity.Package;
import service.PackageDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PackageDaoImpl implements PackageDao{
    public Package find(String pid) {
        Package res = new Package();
        try{
            Connection conn = BaseDao.getConnection();
            String sql = "SELECT * FROM package WHERE pid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pid);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                res = new Package(pid, rs.getDouble("pcost"),
                        rs.getInt("maxCall"),rs.getInt("maxMessage"),
                        rs.getDouble("maxLocalData"),rs.getDouble("maxNationalData"));
            }
            BaseDao.closeAll(conn,stmt,rs);
        }catch (Exception e){
            e.printStackTrace();
        }

        return res;
    }
}
