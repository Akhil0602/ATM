import java.io.*;
import java.util.*;

public class Transactions
{
    private double amount;
    private Date timeStamp;
    private String memo;
    private Accounts inAccount;
    
    public Transactions(double amount,Accounts inAcc)
    {
    this.amount=amount;
    inAccount=inAcc;
    timeStamp=new Date();
    }
    public Transactions(double amount,Accounts inAcc,String memo)
    {
        this(amount,inAcc);
        this.memo=memo;
    }
    public double amount()
    {
        return amount;
    }
    public void details()
    {
        System.out.println(timeStamp+":"+amount+":"+memo);
    }
}