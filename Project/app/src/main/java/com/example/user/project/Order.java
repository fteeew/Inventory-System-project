package com.example.user.project;

public class Order {
    private String OrderDate;
    private String OrederDueDate;
    private int ID;
    private int VoidInd;
    public Order()
    {

    }
    public Order( String OrderDate,String OrederDueDate,int ID,int VoidInd)
    {
        this.OrderDate=OrderDate;
        this.OrederDueDate=OrederDueDate;
        this.ID=ID;
        this.VoidInd=VoidInd;

    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getOrederDueDate() {
        return OrederDueDate;
    }

    public void setOrederDueDate(String orederDueDate) {
        OrederDueDate = orederDueDate;
    }


    public int getVoidInd() {
        return VoidInd;
    }

    public void setVoidInd(int voidInd) {
        VoidInd = voidInd;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
