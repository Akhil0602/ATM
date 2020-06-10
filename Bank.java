import java.io.*;
import java.util.*;

public class Bank
{
    private String name;
    private ArrayList<User> users;
    private ArrayList<Accounts> accounts;
    
    public Bank(String name)
    {
    this.name=name;
    users=new ArrayList<User>();
    accounts=new ArrayList<Accounts>();
    }
    public String getNewUserUUID()
    {
        int len=9;
        String uuid="";
        Random rn=new Random();
        boolean unique=false;
        do{
            for(int i=0;i<len;i++)
            uuid+=((Integer)rn.nextInt(10)).toString();
            unique=true;
            for(User u:this.users)
            if(uuid.equals(u.getUserId()))
            {
                unique=false;
                break;
            }
        }
        while(!unique);
        
        return uuid;
    }
    public String getNewAccountUUID()
    {
        int len=9;
        String uuid="";
        Random rn=new Random();
        boolean unique=false;
        do{
            for(int i=0;i<len;i++)
            uuid+=((Integer)rn.nextInt(10)).toString();
            unique=true;
            for(Accounts u:this.accounts)
            if(uuid.equals(u.getAccountId()))
            {
                unique=false;
                break;
            }
        }
        while(!unique);
        
        return uuid;
    }
    public void addAccount(Accounts account)
    {
        this.accounts.add(account);
    }
    
    public User addUser(String firstName,String lastName,String pin)
    {
        User newUser=new User(firstName,lastName,pin,this);
        this.users.add(newUser);
        Accounts newAccount=new Accounts("Savings",newUser,this);
        
        return newUser;
    }
    
    public User userLogin(String UserId,String pin)
    {
        for(User u:this.users)
        {
            if(u.getUserId().equals(UserId)&&u.validatePin(pin))
            {
             System.out.println("\u000C");
             return u;
            }
        }
        System.out.println("\u000C");
        return null;
    }
    public String getName()
    {
    return this.name;
    }
}
