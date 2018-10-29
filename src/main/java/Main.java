import entity.MonthlyBill;
import entity.Order;
import service.BusinessManagement;
import serviceImpl.BusinessManagementImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        BusinessManagement businessManagement = new BusinessManagementImpl();
        /**
         * 方法：对某个用户进行套餐的查询(包括历史记录)操作
         * 返回值：该用户的所有订单(包括已结束的和未结束的)
         * 注意：结束日期在用户开始订购套餐时，取默认值‘1970-01-01’，
         *      在用户退订套餐时，取退订日期，
         *      所以当套餐结束日期是‘1970-01-01’时，就说明此订单还未结束。
         */
        long a1 = System.currentTimeMillis();
        ArrayList<Order> orders = businessManagement.getPackage("cid-003");
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a1)/1000f+" 秒");
        for(Order order : orders){
            System.out.println("订单编号: " + order.getOid() + "; 套餐编号: " + order.getPid() +
            "; 客户编号: " + order.getCid() + "; 起始日期: " + order.getStartDate() +
                    "; 结束日期(如果是1970-01-01就说明此订单还未结束): " + order.getEndDate());
        }

        /**
         * 方法：对某个用户进行套餐的订购操作
         * 返回值：是否订购成功
         * 检查方法：查看该用户的余额、月账单、订单等变动即可验证
         */
        String start = "2018-10-29";    //由于java.util.Date和java.sql.Date之间的转换，所以输入时间要往后加一天
        String end1 = "1970-01-02";      //所以实际上的startDate = 2018-10-28, endDate = 1970-01-01
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date endDate1 = new Date();
        try{
            startDate = simpleDateFormat.parse(start);
            endDate1 = simpleDateFormat.parse(end1);
        }catch (Exception e){
            e.printStackTrace();
        }
        long a2 = System.currentTimeMillis();
        boolean res1 = businessManagement.subscribePackage("oid-005","cid-004","pid-001",startDate,endDate1);
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a2)/1000f+" 秒");
        if(res1)
            System.out.println("订购成功！");
        else
            System.out.println("订购失败！");

        /**
         * 方法：对某个用户进行套餐的立即退订操作
         * 具体实现方法：将套餐费用退还给用户，并将用户已使用的套餐内流量、短信、通话时间等按照
         *               基本收费标准从用户的余额中扣除，并将此费用加入月账单，账单中的套餐费用
         *               扣除，最后将订单中的endDate填上
         * 返回值：是否退订成功
         * 检查方法：查看该用户的余额、月账单、订单等变动即可验证
         * 注意：1.建议此方法和上一个方法分开运行(运行一个时注释掉另一个)，因为方法中针对的是同一个人，
         *       同时运行的话不易检查验证方法正确性。
         *       2.此方法应在上一个方法运行之后再运行，因为此方法中退订的订单是上个方法中订购的订单
         */
        Date endDate2 = new Date();
        String end2 = "2018-10-30";     //比实际时间晚一天，理由同上
        try{
            endDate2 = simpleDateFormat.parse(end2);
        }catch (Exception e){
            e.printStackTrace();
        }
        long a3 = System.currentTimeMillis();
        boolean res2 = businessManagement.immediatelyUnsubscribePackage("oid-005",endDate2);
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a3)/1000f+" 秒");
        if(res2)
            System.out.println("立即退订成功！");
        else
            System.out.println("立即退订失败！");

        /**
         * 方法：对某个用户进行套餐的退订(次月生效)操作
         * 具体实现方法：将对应订单的endDate填上次月的第一天即可，因为client表中的流量、短信、
         *               通话时间会在每个月的第一天重新导入，需要检测还在生效的订单，所以将
         *               endDate设置在次月第一天，这样不会扣套餐费用，也不会给与用户该套餐的优惠。
         * 返回值：是否退订成功
         * 检查方法：查看oid对应的order中的endDate即可验证
         */
        Date endDate3 = new Date();
        String end3 = "2018-11-02";     //比实际时间晚一天，理由同上
        try{
            endDate3 = simpleDateFormat.parse(end3);
        }catch (Exception e){
            e.printStackTrace();
        }
        long a4 = System.currentTimeMillis();
        boolean res3 = businessManagement.unsubscribePackageNextMonth("oid-001",endDate3);
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a4)/1000f+" 秒");
        if(res3)
            System.out.println("退订成功(次月生效)！");
        else
            System.out.println("退订失败！");

        /**
         * 方法：某个用户在通话情况下的资费生成
         * 具体实现方法：将用户剩余套餐通话时间和本次通话时间比较，如果未超过剩余优惠时间，则
         *               扣除本次通话时间，否则扣除全部剩余优惠时间以抵消部分通话时间，剩余未
         *               抵消的时间以基本收费方式从余额中扣除，并加入本月账单
         * 检查方法：查看对应client的剩余优惠通话时间、余额和本月账单即可验证
         * 注意：改变通话时长可以验证优惠时间不足时的情况
         */

        long a5 = System.currentTimeMillis();
        double callCost = businessManagement.call("cid-002",101,"2018","10");
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a5)/1000f+" 秒");
        System.out.println("通话结束！费用: " + callCost + " 元");

        /**
         * 方法：某个用户在使用流量情况下的资费生成
         * 具体实现方法：先将用户剩余的全国流量和本次使用的流量比较，足则扣，不足则扣完再将
         *               剩余的本次使用的流量和本地流量比较，足则扣，不足则扣完再将剩余的本
         *               次使用的流量按照3元/M的收费标准进行收费
         * 检查方法：查看该用户的流量剩余、余额和本月账单即可验证
         */

        long a6 = System.currentTimeMillis();
        double dataUseCost = businessManagement.dataUse("cid-003",4097,"2018","10");
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a6)/1000f+" 秒");
        System.out.println("使用流量结束！费用: " + dataUseCost + " 元");

        /**
         * 方法：某个用户月账单的生成
         * 账单定义：包含每个月订单的费用，额外的流量、短信、通话时间所产生的费用
         * 返回值：此用户指定月份的账单(仅有总消费费用)。
         */
        long a7 = System.currentTimeMillis();
        MonthlyBill bill = businessManagement.getMonthlyBill("cid-001","2018","10");
        System.out.println("执行耗时 : "+(System.currentTimeMillis()-a7)/1000f+" 秒");
        System.out.println("客户编号: " + bill.getCid() + "; 账单年份: " + bill.getBillYear() +
                "; 账单月份: " + bill.getBillMonth() + "; 账单总费用: " + bill.getCost());
    }
}
