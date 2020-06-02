import java.io.*;
import java.util.*;
import java.security.*;
public class User
{
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinHash[];
    private ArrayList<Accounts> accounts;
    
    public User(String firstName,String lastName,String pin,Bank theBank)
    {
     this.firstName=firstName;
     this.lastName=lastName;
     try{
     MessageDigest md=MessageDigest.getInstance("MD5");
     this.pinHash=md.digest(pin.getBytes());
     }
     catch(NoSuchAlgorithmException e)
     {
         System.out.println("No Such Directory Exists");
     }
     this.uuid=theBank.getNewUserUUID();
     
     accounts=new ArrayList<Accounts>();
     
     System.out.println("New user "+firstName+" "+lastName+" with User ID:"+uuid+" created");
     System.out.println("You have 1 Savings Account");
    }
    
    public void addAccount(Accounts account)
    {
        this.accounts.add(account);
    }
    
    public String getUserId()
    {
    return this.uuid;
    }
    
    public boolean validatePin(String pin)
    {
        try{
        MessageDigest md=MessageDigest.getInstance("MD5");
        return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinHash);
        }
       catch(NoSuchAlgorithmException e){
           System.out.println("Exception Found");
        }
        return true;
       }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    public void summary()
    {
        System.out.println("Your account summary:");
        for(int i=0;i<accounts.size();i++)
        {
            accounts.get(i).summaryLine();
        }
    }
    public void showAccs()
    {
        for(Accounts a:accounts)
        System.out.println(a.getname());
    }
    public int accNum()
    {
        return this.accounts.size();
    }
    public void printAccHistory(int a)
    {
        accounts.get(a).printTransactions();
    }
    public Accounts getAcc(int a)
    {
        return this.accounts.get(a);
    }
}