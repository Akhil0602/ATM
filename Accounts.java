import java.io.*;
import java.util.*;

public class Accounts
{
    private String name;
    //private double balance;
    private String uuid;
    private User holder;
    private ArrayList<Transactions> transactions;
    
    public Accounts(String Name,User holder,Bank theBank)
    {
        this.name=Name;
        this.holder=holder;
        this.uuid=theBank.getNewAccountUUID();
        transactions=new ArrayList<Transactions>();
        holder.addAccount(this);
        theBank.addAccount(this);
    }
    public String getAccountId()
    {
        return this.uuid;
    }
     
    public void summaryLine()
    {
        double balance=getBalance();
        if(balance>=0)
         System.out.printf("User ID:"+uuid+":Balance:$"+balance+":Type:"+name+"\n");
        else
         System.out.printf(uuid+":("+balance+"):"+name+"\n");
    }
    public double getBalance()
    {
        double balance=0;
        for(Transactions t:transactions)
        balance+=t.amount();
        return balance;
    }
    public String getname()
    {
        return this.name;
    }
    public void printTransactions()
    {
        for(Transactions t:transactions)
        t.details();
    }
    public void addTransaction(Transactions trans)
    {
        this.transactions.add(trans);
    }
}