package serviceImpl;

import DAO.ClientDaoImpl;
import DAO.MonthlyBillDaoImpl;
import DAO.OrderDaoImpl;
import DAO.PackageDaoImpl;
import entity.Client;
import entity.MonthlyBill;
import entity.Order;
import entity.Package;
import service.*;

import java.util.ArrayList;
import java.util.Date;

public class BusinessManagementImpl implements BusinessManagement {

    private ClientDao clientDao = new ClientDaoImpl();
    private MonthlyBillDao monthlyBillDao = new MonthlyBillDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private PackageDao packageDao = new PackageDaoImpl();

    public ArrayList<Order> getPackage(String cid) {
        return orderDao.find(cid);
    }

    public boolean subscribePackage(String oid, String cid, String pid, Date startDate, Date endDate) {
        Order order = new Order(oid, pid, cid, startDate, endDate);
        Package plan = packageDao.find(pid);
        Client client = clientDao.find(cid);
        if(plan.getPcost() > client.getBalance())
            return false;
        orderDao.insert(order);
        client.setLeftCall(client.getLeftCall() + plan.getMaxCall());
        client.setLeftMessage(client.getLeftMessage() + plan.getMaxMessage());
        client.setLeftLocalData(client.getLeftLocalData() + plan.getMaxLocalData());
        client.setLeftNationalData(client.getLeftNationalData() + plan.getMaxNationalData());
        client.setBalance(client.getBalance() - plan.getPcost());
        clientDao.update(client);
        return true;
    }

    public boolean immediatelyUnsubscribePackage(String oid, Date endDate) {

        return orderDao.update(oid, endDate);
    }

    public boolean unsubscribePackageNextMonth(String oid, Date endDate) {
        return orderDao.update(oid, endDate);
    }

    public void call(String cid, int minutes, String year, String month) {
        Client client = clientDao.find(cid);
        if(client.getLeftCall() < minutes){
            client.setLeftCall(0);
            double cost = (minutes - client.getLeftCall()) * 0.5;
            monthlyBillDao.update(cost, cid, year, month);
        }else{
            client.setLeftCall(client.getLeftCall() - minutes);
        }
        clientDao.update(client);
    }

    public void dataUse(String cid, double data, String year, String month) {
        Client client = clientDao.find(cid);
        if(client.getLeftNationalData() < data){
            data = data - client.getLeftNationalData();
            client.setLeftNationalData(0);
            if(client.getLeftLocalData() < data){
                client.setLeftLocalData(0);
                double cost = (data - client.getLeftLocalData()) * 3;
                monthlyBillDao.update(cost, cid, year, month);
            }else{
                client.setLeftLocalData(client.getLeftLocalData() - data);
            }
        }else{
            client.setLeftNationalData(client.getLeftNationalData() -data);
        }
    }

    public MonthlyBill getMonthlyBill(String cid, String year, String month) {
        return monthlyBillDao.find(cid, year, month);
    }
}
