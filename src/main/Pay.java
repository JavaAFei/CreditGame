package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pojo.Credit;


/**
 * 
 * @author YH
 * 
 *         功能：根据当前持有信用卡信息，计算套取信用卡后的还款计划 
 *         入参：信用卡信息：List<Map<String,Object>>
 *         套取额度：int 套取日：Date 每月还款：int 还款日：Date 出参：还款计划
 * 
 */

public class Pay {
	public static void main(String[] args) {
		 System.out.println("Hello World!");
	        Calendar cal = Calendar.getInstance();
	        int day=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        System.out.println(day);

	        cal.getTimeInMillis();


	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try{
	            Date d = dateFormat.parse("2016-04-05");
	            Calendar cal1 = Calendar.getInstance();
		        cal1.setTime(d);

		       long  l =cal1.getTimeInMillis();
		       long  l2 =cal.getTimeInMillis();
	        }catch (Exception e){
	        }
	          // 对 calendar 设置为 date 所定的日期
	     
	}

	public void Pay(List<Credit> cardList, int payMoney,
			Date payDay, int returnMoney, Date returnDay) {
		//1、执行借款
		Calendar c = Calendar.getInstance();
		//2、执行循环，从借款日开始每天循环卡信息，检测是否出账单、该还款等，直至把款全部还清
		
		
	}
}
