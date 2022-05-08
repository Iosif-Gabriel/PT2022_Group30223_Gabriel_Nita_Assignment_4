package BuisinessLogic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private static int one=1;
    private final  int ID;
    private int customerID;
    private final Date orderDate;
    private final SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Order(int customerID) {
        this.ID=one;
        one++;
        this.customerID = customerID;
        this.orderDate = new Date();
    }

    public int getID() {
        return ID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public String toString(){
        return "Order ID: "+this.getID()+", "+date.format(orderDate)+", client ID: "+this.getCustomerID();
    }
}
