import java.io.*;
public class ATM
{
    static Bank theBank;
    public static void main(String args[])throws IOException
    {
     BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("\u000C");
     theBank=new Bank("Bank of India");
     System.out.println("\t\tWelcome to Bank of India");
     System.out.println("How many new users you want to add");
     int users=Integer.parseInt(in.readLine());
     for(int j=0;j<users;j++)
     {
         addUser();
     }
    
    User curUser;
    curUser=promptMenu(theBank);
    printMenu(curUser);
    int ch=0;
    while(ch!=3)
    {
        ch=Menu();
        switch(ch)
        {
         case 1:
         curUser=promptMenu(theBank);
         printMenu(curUser);
         break;
         case 2:
         addUser();
         break;
        }
    }
    System.out.println("\n\n####################################################\n############Thanks for visiting our Bank.###########\n####################################################");
    }
    public static User promptMenu(Bank theBank)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        String UserId;
        String pin="";
        User authUser;
        do
        {
            
            System.out.println("\n\nWelcome to "+theBank.getName());
            System.out.println("Enter UserId:");
            UserId=in.readLine();
            System.out.println("Enter Pin:");
            pin=in.readLine();
            authUser=theBank.userLogin(UserId,pin);
            if(authUser==null)
            System.out.println("Invalid User ID or pin.Try again!!!");
            else
            System.out.println("Welcome "+authUser.getFirstName());
        }
        while(authUser==null);
        return authUser;
    }
    
    public static void printMenu(User curUser)throws IOException
    {
       curUser.summary();
       BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
       int choice;
       do
       {
           System.out.println("What would you like to do?");
       System.out.println("\n1.Transaction history\n2.Deposit\n3.Withdraw\n4.Transfer\n5.Add Account\n6.Log out");
       choice=Integer.parseInt(in.readLine());
       if(choice<1||choice>5)
       {
           System.out.println("Invalid choice!!Please enter again\n\n");
           continue;
        }
       switch(choice)
       {
           case 1:
           showTransactions(curUser);
           break;
           case 2:
           deposit(curUser);
           break;
           case 3:
           withdraw(curUser);
           break;
           case 4:
           transfer(curUser);
           break;
           case 5:
           addAcc(curUser);
       }
       }
       while(choice!=6);
    }
    public static void showTransactions(User curUser)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your accounts:");
        curUser.showAccs();
        int ch;
        do{
         System.out.println("Enter the Account number whose transaction history you want");
         ch=Integer.parseInt(in.readLine());
         if(ch<1||ch>curUser.accNum())
         System.out.println("Invalid Choice!!!!Pleasy enter a valid number");
        }
        while(!(ch>0&&ch<=curUser.accNum()));
        curUser.printAccHistory(ch-1);
    }
    public static void deposit(User curUser)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your accounts:");
        curUser.showAccs();
        int ch;
        do{
         System.out.println("Enter the Account number in which you want to deposit money");
         ch=Integer.parseInt(in.readLine());
         if(ch<1||ch>curUser.accNum())
         System.out.println("Invalid Choice!!!!Pleasy enter a valid number");
        }
        while(!(ch>0&&ch<=curUser.accNum()));
        int dep;
        String memo;
        do
        {
        System.out.print("Enter the amount of money you want to deposit:$");
        dep=Integer.parseInt(in.readLine());
        if(dep<0)
        System.out.println("Please enter a positive amount");
        }
        while(dep<0);
        System.out.println("Enter memo");
        memo=in.readLine();
        Accounts inAcc=curUser.getAcc(ch-1);
        Transactions depo=new Transactions(dep,inAcc,memo);
        inAcc.addTransaction(depo);
    }
    
    public static void withdraw(User curUser)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your accounts:");
        curUser.showAccs();
        int ch;
        do{
         System.out.println("Enter the Account number from which you want to withdraw money");
         ch=Integer.parseInt(in.readLine());
         if(ch<1||ch>curUser.accNum())
         System.out.println("Invalid Choice!!!!Pleasy enter a valid number");
        }
        while(!(ch>0&&ch<=curUser.accNum()));
        int dep;
        String memo;
        do
        {
        System.out.print("Enter the amount of money you want to withdraw:$");
        dep=Integer.parseInt(in.readLine());
        if(dep<0)
        System.out.println("Please enter a positive amount");
        if(dep>curUser.getAcc(ch-1).getBalance())
        System.out.println("Not enough balance availaible!!!Please enter a lesser amount");
        }
        while(dep<0||dep>curUser.getAcc(ch-1).getBalance());
        System.out.println("Enter memo");
        memo=in.readLine();
        Accounts inAcc=curUser.getAcc(ch-1);
        dep=dep*(-1);
        Transactions depo=new Transactions(dep,inAcc,memo);
        inAcc.addTransaction(depo);
    }
    public static void transfer(User curUser)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Your accounts:");
        curUser.showAccs();
        int from,to;
        do{
         System.out.println("Enter the Account number from which you want to transfer money");
         from=Integer.parseInt(in.readLine());
         if(from<1||from>curUser.accNum())
         System.out.println("Invalid Choice!!!!Pleasy enter a valid number");
        }
        while(!(from>0&&from<=curUser.accNum()));
        do{
         System.out.println("Enter the Account number to which you want to transfer money");
         to=Integer.parseInt(in.readLine());
         if(to<1||to>curUser.accNum())
         System.out.println("Invalid Choice!!!!Pleasy enter a valid number");
         if(from==to)
         System.out.println("Can't transfer money to same account!!!Select some other account");
        }
        while(!(to>0&&to<=curUser.accNum())||from==to);
        int dep;
        String memo;
        do
        {
        System.out.print("Enter the amount of money you want to transfer:$");
        dep=Integer.parseInt(in.readLine());
        if(dep<0)
        System.out.println("Please enter a positive amount");
        if(dep>curUser.getAcc(from-1).getBalance())
        System.out.println("Not enough balance availaible!!!Please enter a lesser amount");
        }
        while(dep<0||dep>curUser.getAcc(from-1).getBalance());
        System.out.println("Enter memo");
        memo=in.readLine();
        Accounts toAcc=curUser.getAcc(to-1);
        Transactions withdraw=new Transactions(dep,toAcc,memo);
        toAcc.addTransaction(withdraw);
        Accounts fromAcc=curUser.getAcc(from-1);
        dep=dep*(-1);
        Transactions depo=new Transactions(dep,fromAcc,memo);
        fromAcc.addTransaction(depo);
    }
    public static int Menu()throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n\n\n1.Login as existing User\n2.Add a new user\n3.Leave the Bank");
        int ch=Integer.parseInt(in.readLine());
        while(ch!=1&&ch!=2&&ch!=3)
        {
            System.out.println("Invalid Choice!!!!Please Enter 1 or 2");
            ch=Integer.parseInt(in.readLine());
        }
        return ch;
    }
    private static void addUser()throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Enter user first name");
     String firstName=in.readLine();
     System.out.println("Enter user last name");
     String lastName=in.readLine();
     System.out.println("Enter 4 digit Pin to set");
     String pin=in.readLine();
     System.out.println("\u000C");
     User aUser=theBank.addUser(firstName,lastName,pin);
     System.out.println("How many more accounts you want to have.(0 for none)");
     int i=Integer.parseInt(in.readLine());
     for(int k=0;k<i;k++)
     {
        addAcc(aUser);
          }
        
    }
    public static void addAcc(User aUser)throws IOException
    {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
         int ch;
     System.out.println("Choose the Account type.\n1 for Savings\n2 for Checkings\n\nEnter your choice.");
     do
         {
             ch=Integer.parseInt(in.readLine());
             if(ch!=1&&ch!=2)
             System.out.println("Invalid Choice!!!Please enter 1 or 2");
         }
            while(ch!=1&&ch!=2);
     Accounts anAcc=null;
     switch(ch)
     {
         case 1:
         anAcc=new Accounts("Savings",aUser,theBank);
         break;
         case 2:
         anAcc=new Accounts("Checkings",aUser,theBank);
         break;
     }
    }
}
