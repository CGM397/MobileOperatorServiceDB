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
import java.util.Calendar;
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
        //update order
        orderDao.insert(order);

        //update client
        client.setLeftCall(client.getLeftCall() + plan.getMaxCall());
        client.setLeftMessage(client.getLeftMessage() + plan.getMaxMessage());
        client.setLeftLocalData(client.getLeftLocalData() + plan.getMaxLocalData());
        client.setLeftNationalData(client.getLeftNationalData() + plan.getMaxNationalData());

        client.setMaxCall(client.getMaxCall() + plan.getMaxCall());
        client.setMaxMessage(client.getMaxMessage() + plan.getMaxMessage());
        client.setMaxLocalData(client.getMaxLocalData() + plan.getMaxLocalData());
        client.setMaxNationalData(client.getMaxNationalData() + plan.getMaxNationalData());

        client.setBalance(client.getBalance() - plan.getPcost());
        clientDao.update(client);

        //update monthlyBill
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        String year = cal.get(Calendar.YEAR) + "";
        String month = (cal.get(Calendar.MONTH) + 1) + "";
        monthlyBillDao.update(plan.getPcost(), cid, year, month);
        return true;
    }

    public boolean immediatelyUnsubscribePackage(String oid, Date endDate) {
        Order order = orderDao.findOne(oid);
        Package plan = packageDao.find(order.getPid());
        Client client = clientDao.find(order.getCid());

        //update order, add the endDate
        orderDao.update(oid, endDate);

        //update the client info
        int leftCall = client.getLeftCall() - plan.getMaxCall();
        int leftMessage = client.getLeftMessage() - plan.getMaxMessage();
        double leftLocalData = client.getLeftLocalData() - plan.getMaxLocalData();
        double leftNationalData = client.getLeftNationalData() - plan.getMaxNationalData();
        double cost = 0;
        if(leftCall < 0){
            client.setLeftCall(0);
            cost += (-leftCall) * 0.5;
        }else
            client.setLeftCall(leftCall);
        if(leftMessage < 0){
            client.setLeftMessage(0);
            cost += (-leftMessage) * 0.1;
        }else
            client.setLeftMessage(leftMessage);
        if(leftLocalData < 0){
            client.setLeftLocalData(0);
            cost += (-leftLocalData) * 2;
        }else
            client.setLeftLocalData(leftLocalData);
        if(leftNationalData < 0){
            client.setLeftNationalData(0);
            cost += (-leftNationalData) * 5;
        }else
            client.setLeftNationalData(leftNationalData);
        client.setBalance(client.getBalance() + plan.getPcost() - cost);
        client.setMaxCall(client.getMaxCall() - plan.getMaxCall());
        client.setMaxMessage(client.getMaxMessage() - plan.getMaxMessage());
        client.setMaxLocalData(client.getMaxLocalData() - plan.getMaxLocalData());
        client.setMaxNationalData(client.getMaxNationalData() - plan.getMaxNationalData());
        clientDao.update(client);

        //update monthlyBill
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        String year = cal.get(Calendar.YEAR) + "";
        String month = (cal.get(Calendar.MONTH) + 1) + "";
        monthlyBillDao.update(-cost-plan.getPcost(),order.getCid(),year,month);

        return true;
    }

    public boolean unsubscribePackageNextMonth(String oid, Date endDate) {
        return orderDao.update(oid, endDate);
    }

    public double call(String cid, int minutes, String year, String month) {
        Client client = clientDao.find(cid);
        double cost = 0;
        if(client.getLeftCall() < minutes){
            cost = (minutes - client.getLeftCall()) * 0.5;
            client.setLeftCall(0);
            client.setBalance(client.getBalance() - cost);
            monthlyBillDao.update(cost, cid, year, month);
        }else{
            client.setLeftCall(client.getLeftCall() - minutes);
        }
        clientDao.update(client);
        return cost;
    }

    public double dataUse(String cid, double data, String year, String month) {
        Client client = clientDao.find(cid);
        double cost = 0;
        if(client.getLeftNationalData() < data){
            data = data - client.getLeftNationalData();
            client.setLeftNationalData(0);
            if(client.getLeftLocalData() < data){
                cost = (data - client.getLeftLocalData()) * 3;
                client.setLeftLocalData(0);
                client.setBalance(client.getBalance() - cost);
                monthlyBillDao.update(cost, cid, year, month);
            }else{
                client.setLeftLocalData(client.getLeftLocalData() - data);
            }
        }else{
            client.setLeftNationalData(client.getLeftNationalData() - data);
        }
        clientDao.update(client);
        return cost;
    }

    public MonthlyBill getMonthlyBill(String cid, String year, String month) {
        return monthlyBillDao.find(cid, year, month);
    }
}
