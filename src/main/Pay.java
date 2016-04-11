package main;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pojo.Credit;

/**
 * Created by YH on 2016/3/19.
 *     功能：根据当前持有信用卡信息，计算套取信用卡后的还款计划
 *     入参：信用卡信息：List<Map<String,Object>>
 *     套取额度：int 套取日：Date 每月还款：int 还款日：Date 出参：还款计划
 *
 */
public class Pay {
    public static void main(String[] args){
        List<Credit> cardList = new ArrayList<Credit>();
        Credit card0 = new Credit("招商信用卡",20000,27,9,5000,3000);
        Credit card1 = new Credit("民生信用卡",10000,14,24,2500,300);
        cardList.add(card0);
        cardList.add(card1);
        GoPay(cardList,18000,"2016-03-19");
    }
    public static void  GoPay(List<Credit> cardList, int payMoney, String payDate){
        try{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = dateFormat.parse(payDate);
        // 对 calendar 设置为 date 所定的日期
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d);
        //1、执行借款
        //遍历cardList,按照距还款日由久至近的顺序依次扣减，扣减时存留1000
        for(Credit card:cardList){
            //获取每张卡的距离还款日的日期
            int i=0;
            /*
            if(card.getCheckDate()>=cal1.get(Calendar.DAY_OF_MONTH)){
                i=card.getCheckDate()-cal1.get(Calendar.DAY_OF_MONTH);
            }else{
                i=cal1.getActualMaximum(Calendar.DAY_OF_MONTH)-cal1.get(Calendar.DAY_OF_MONTH)+card.getCheckDate();
            }

            if(card.getReturnDate()>=card.getCheckDate()){
                i=i+card.getReturnDate()-card.getCheckDate();
            }else{
                i=i+cal1.getActualMaximum(Calendar.DAY_OF_MONTH)-card.getCheckDate()+card.getReturnDate();
            }*/
            if(card.getCheckDate()<cal1.get(Calendar.DAY_OF_MONTH)){
                i++;
            }
            if(card.getReturnDate()<card.getCheckDate()){
                i++;
            }

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.MONTH, +i);
            cal2.set(Calendar.DATE,card.getReturnDate());
            i = daysBetween(cal1,cal2);
            System.out.println(card.getCardName()+"："+payDate+"支付，距离还款日"+i+"天");
        }


        //2、执行循环，从借款日开始每天循环卡信息，检测是否出账单、该还款等，直至把款全部还清
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int daysBetween(Calendar cal1,Calendar cal2){
        long time1 = cal1.getTimeInMillis();
        long time2 = cal2.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }


}
