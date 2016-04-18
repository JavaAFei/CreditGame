package main;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pojo.Credit;

/**
 * Created by YH on 2016/3/19.
 * 功能：根据当前持有信用卡信息，计算套取信用卡后的还款计划
 * 入参：信用卡信息：List<Map<String,Object>>
 * 套取额度：int 套取日：Date 每月还款：int 还款日：Date 出参：还款计划
 * 流程：
 * 1、初始化当前的信用卡信息，设定额度、账单日、还款日、总欠款、已出账单
 * 2、设定计划借款金额、借款日期，开始执行借款
 * 3、借款：
 * 3.1 将所有信用卡按照还款日期最晚至最近做好顺序排列。
 * 3.2 按照排列顺序，依次执行借款，借款时每张卡剩余1000的额度。
 * 4、还款：
 * 4.1 每天执行循环，检测当前信用卡是否已出账单、是否需要还款。
 * 4.2 若需要还款，根据需还款金额、还款日期调用借款方法，得出还款方法。
 * 4.3 每月发工资日给最近需要还款的信用卡还款，直到还款全部完成，还款结束。
 */
public class Pay {

    //初始化信息
    static Credit card0 = new Credit("招商信用卡-郭", 20000, 27, 9, 5000, 3000);
    static Credit card1 = new Credit("民生信用卡-郭", 10000, 14, 24, 2500, 300);
    static Credit card2 = new Credit("中信信用卡-郭", 10000, 6, 17, 2500, 300);
    static Credit card3 = new Credit("交通信用卡-江", 10000, 30, 5, 2500, 300);
    static Credit card4 = new Credit("招商信用卡-江", 10000, 8, 20, 2500, 300);
    static List<Credit> cardList = new ArrayList<Credit>();
    static int wageDay = 13;
    static int wagePrice = 5000;
    static int wagePrices = 0;


    public static void main(String[] args) {

        cardList.add(card0);
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);

        Play(cardList, 18000, "2016-04-18");
    }

    public static void Play(List<Credit> cardList, int payMoney, String payDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = dateFormat.parse(payDate);
            Calendar cal1 = Calendar.getInstance();// 对 calendar 设置为 date 所定的日期
            cal1.setTime(d);


            //1、执行借款
            //将所有信用卡按照还款日期最晚至最近做好顺序排列。
            cardList = sortCardList(cardList, cal1);
            GoPay(payMoney, cardList);

            //2、执行循环，从借款日开始每天循环卡信息，检测是否出账单、该还款等，直至把款全部还清
            GoReturn(cardList, cal1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void GoReturn(List<Credit> cardList, Calendar cal1) {
        System.out.println("开始计算还款计划：");
        for (int i = 0; i < 365; i++) {
            cal1.add(Calendar.DATE, 1);
            String s = (new SimpleDateFormat("yyyy-MM-dd")).format(cal1.getTime());
            System.out.println(s);

            //工资日
            if (wageDay == cal1.get(Calendar.DAY_OF_MONTH)) {
                wagePrices += wagePrice;
                System.out.println("工资日，工资卡金额：" + wagePrices);
            }

            for (Credit card : cardList) {
                if (card.getCheckDate() == cal1.get(Calendar.DAY_OF_MONTH)) {
                    card.setCheckPrice(card.getOwingPrice());
                    System.out.println(card.getCardName() + "账单日，已出账单为：" + card.checkPrice);
                }
                if (card.getReturnDate() - 3 == cal1.get(Calendar.DAY_OF_MONTH)) {
                    System.out.println(card.getCardName() + "还款日(提前三天)，还款计划如下：");
                    cardList = sortCardList(cardList, cal1);
                    GoReturn(card, cardList);
                    card.setCheckPrice(0);
                    System.out.println(card.getCardName() + "还款日(提前三天)，已还款完成");
                }
            }

        }
    }

    private static void GoPay(int payMoney, List<Credit> cardList) {
        for (Credit card : cardList) {
            int i = card.getCardPrice() - card.getOwingPrice() - 1000;
            if (i < 0) {
                System.out.println(card.getCardName() + "未借款");
                continue;
            } else if (i >= payMoney) {
                card.setOwingPrice(card.getOwingPrice() + payMoney);
                System.out.println(card.getCardName() + "借款：" + payMoney + ",借款完成");
                return;
            } else {
                card.setOwingPrice(card.getOwingPrice() + i);
                payMoney = payMoney - i;
                System.out.println(card.getCardName() + "借款：" + i + ",剩余需要借" + payMoney);
            }
        }
    }

    private static void GoReturn(Credit card2, List<Credit> cardList) {
        //工资卡借款
        if (wagePrice <= 0) {
            System.out.println("工资卡未借款");
        } else if (wagePrice >= card2.getCheckPrice()) {
            wagePrice = wagePrice-card2.getCheckPrice();
            System.out.println("工资卡借款：" + card2.getCheckPrice() + ",借款完成");
            card2.setCheckPrice(0);
            return;
        } else {
            wagePrice=0;
            card2.setCheckPrice(card2.getCheckPrice() - wagePrice);
            System.out.println( "工资卡借款：" + wagePrice + ",剩余需要借" + card2.getCheckPrice());
        }

        for (Credit card : cardList) {
            int i = card.getCardPrice() - card.getOwingPrice() - 1000;
            if (i < 0) {
                System.out.println(card.getCardName() + "未借款");
                continue;
            } else if (i >= card2.getCheckPrice()) {
                card.setOwingPrice(card.getOwingPrice() + card2.getCheckPrice());
                card2.setCheckPrice(0);
                System.out.println(card.getCardName() + "借款：" + card2.getCheckPrice() + ",借款完成");
                return;
            } else {
                card.setOwingPrice(card.getOwingPrice() + i);
                card2.setCheckPrice(card2.getCheckPrice() - i);
                System.out.println(card.getCardName() + "借款：" + i + ",剩余需要借" + card2.getCheckPrice());
            }
        }
    }

    private static List<Credit> sortCardList(List<Credit> cardList, Calendar cal1) {
        for (Credit card : cardList) {
            //获取每张卡的距离还款日的日期
            int i = 0;
            if (card.getCheckDate() < cal1.get(Calendar.DAY_OF_MONTH)) {
                i++;
            }
            if (card.getReturnDate() < card.getCheckDate()) {
                i++;
            }
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.MONTH, +i);
            cal2.set(Calendar.DATE, card.getReturnDate());
            i = daysBetween(cal1, cal2);
         //   System.out.println(card.getCardName() + "距离还款日" + i + "天");
            card.setReturnNum(i);
        }
        for (int i = 0; i < cardList.size(); i++) {
            for (int j = i + 1; j < cardList.size(); j++) {
                if (cardList.get(i).getReturnNum() < cardList.get(j).getReturnNum()) {
                    Credit card = cardList.get(i);
                    cardList.set(i, cardList.get(j));
                    cardList.set(j, card);
                }
            }
        }
     //   System.out.println("排列后的顺序：");
        for (Credit card : cardList) {
      //      System.out.println(card.getCardName() + "距离还款日" + card.getReturnNum() + "天");
        }
        return cardList;
    }

    public static int daysBetween(Calendar cal1, Calendar cal2) {
        long time1 = cal1.getTimeInMillis();
        long time2 = cal2.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


}
