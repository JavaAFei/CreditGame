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
 *         ���ܣ����ݵ�ǰ�������ÿ���Ϣ��������ȡ���ÿ���Ļ���ƻ� 
 *         ��Σ����ÿ���Ϣ��List<Map<String,Object>>
 *         ��ȡ��ȣ�int ��ȡ�գ�Date ÿ�»��int �����գ�Date ���Σ�����ƻ�
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
	          // �� calendar ����Ϊ date ����������
	     
	}

	public void Pay(List<Credit> cardList, int payMoney,
			Date payDay, int returnMoney, Date returnDay) {
		//1��ִ�н��
		Calendar c = Calendar.getInstance();
		//2��ִ��ѭ�����ӽ���տ�ʼÿ��ѭ������Ϣ������Ƿ���˵����û���ȣ�ֱ���ѿ�ȫ������
		
		
	}
}
