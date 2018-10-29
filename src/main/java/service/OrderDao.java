package service;

import entity.Order;

import java.util.ArrayList;
import java.util.Date;

public interface OrderDao {
    public boolean insert(Order order);

    public boolean update(String oid, Date endDate);

    public Order findOne(String oid);

    public ArrayList<Order> find(String cid);
}
