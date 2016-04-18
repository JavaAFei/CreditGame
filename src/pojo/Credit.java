package pojo;

import java.util.Date;

/**
 * Created by YH on 2016/3/19.
 */
public class Credit {
    //信用卡名称
    public String cardName;
    //信用卡额度
    public int cardPrice;
    //还款日
    public int returnDate;
    //账单日
    public int checkDate;
    //总欠款
    public int owingPrice;
    //已出账单
    public int checkPrice;

    public int returnNum;

    public Credit() {

    }

    public Credit(String cardName, int cardPrice, int returnDate, int checkDate, int owingPrice, int checkPrice) {
        this.cardName = cardName;
        this.cardPrice = cardPrice;
        this.returnDate = returnDate;
        this.checkDate = checkDate;
        this.owingPrice = owingPrice;
        this.checkPrice = checkPrice;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public void setCheckDate(int checkDate) {
        this.checkDate = checkDate;
    }

    public void setOwingPrice(int owingPrice) {
        this.owingPrice = owingPrice;
    }

    public void setCheckPrice(int checkPrice) {
        this.checkPrice = checkPrice;
    }

    public int getCheckDate() {
        return checkDate;
    }

    public String getCardName() {
        return cardName;
    }

    public int getOwingPrice() {
        return owingPrice;
    }

    public int getReturnDate() {
        return returnDate;

    }

    public int getCardPrice() {
        return cardPrice;
    }

    public int getCheckPrice() {
        return checkPrice;
    }

    public int getReturnNum() {
        return returnNum;
    }

    public void setReturnNum(int returnNum) {
        this.returnNum = returnNum;
    }
}
