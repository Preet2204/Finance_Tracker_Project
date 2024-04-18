import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class User{
    ArrayList <Accounts> account_list=new ArrayList<>();
    Scanner sca = new Scanner(System.in);
    private String pass=new String();
    private String userName = new String();

    void makeAccount()
    {
        System.out.println("What kind of account do you want to create?");
        System.out.println("1. Savings ");
        System.out.println("2. Checking");
        System.out.println("3. Fixed Deposit");
        System.out.println("4. Credit Card ");
        System.out.println("5. Go Back to Main Menu");
        Accounts a=new Savings();
        boolean UserNotCoop=true;
        while(UserNotCoop)
        {
            UserNotCoop=false;
            switch(sca.nextInt())
            {
                case 1 -> a= new Savings();
                case 2 -> a= new Checking();
                case 3 -> a= new FD();
                case 4 -> a= new Credit_Card();
                case 5 -> {return;}
                default -> {
                    UserNotCoop=true;
                    System.out.println("Enter given choices only");
                }
            };
        }
        account_list.add(a);
        boolean AccNoExists=true;
        String tempAccNo="";
        while(AccNoExists)
        {
            AccNoExists=false;
            System.out.println("Enter Unique Account Number");
            tempAccNo=sca.next();
            for(int i=0;i<account_list.size();i++)
            {
                if(account_list.get(i).account_no.equals(tempAccNo))
                {
                    System.out.println("Account number already refers to another account");
                    AccNoExists=true;
                    break;
                }
            }
        }
        a.account_no=tempAccNo;
        if(a instanceof FD ){ 
            outer: while(true)
            {
                System.out.println("Enter Current Balance");
                double tempBalance=sca.nextDouble();
                if(tempBalance<10000){
                    inner: while(true){
                        System.out.println("You can not create a FD with initial deposit less than 10000. \nIf you want to create an FD having greater balance, enter 0\nElse if you want to go back press 1");
                        switch (sca.nextInt()) {
                            case 0 -> {
                                continue outer;
                            }
                            case 1 -> {
                                this.makeAccount();
                                return;
                            }
                            default ->{
                                System.out.println("enter appropriate choice");
                                continue inner;
                            }
                        }
                    }
                }   
                else{
                    a.balance=tempBalance;
                    System.out.println("You have successfully created an account. You have been redirected to the main menu!");
                    this.makeChoice(Main.printFunctions());
                    break;
                }
            }
        }
        else{
            System.out.println("Enter Current Balance");
            double tempBalance=sca.nextDouble();
            a.balance=tempBalance;
            System.out.println("You have successfully created an account. You have been redirected to the main menu!");
            this.makeChoice(Main.printFunctions());
        }
        
    }

    void AccountFunctions()
    {
        while(true)
        {
            System.out.println("Enter the account number of the account you wish to choose (enter -1 to go back)");
            String tempString=sca.next();
            int i=0;
            if(tempString.equals("-1"))
            {
                makeChoice(1);
                break;
            }
            for(;i<=account_list.size()-1;i++)
            {
                if(tempString.equals(account_list.get(i).account_no)) break;
            }
            if(i<account_list.size())
            {
                account_list.get(i).printAccFunc();
                break;
            }
            else{
                System.out.println("Invaid Account Number.");
                continue;
            }
        }

    }

    void makeChoice(int ch)
    {
        switch(ch)
        {
            case 1 ->{ 
                boolean loop = true;
                while(loop)
                {
                    System.out.println("What would you like to do");
                    System.out.println("1. Add an account");
                    System.out.println("2. Perform actions on already existing account");
                    System.out.println("3. Go Back to Main Menu");
                    loop = false;
                    switch(sca.nextInt())
                    {
                        case 1 -> {
                            makeAccount();
                        }
                        case 2 -> {
                            AccountFunctions();
                        }
                        case 3 -> {return;}
                        default -> {
                            System.out.println("Invalid Number. Enter Again.");
                            loop = true; 
                        }                                       
                    };
                }
            }
            case 2 ->{
                        
            }
        };
    }

    void setUserName(String username) {
        this.userName=username;
    }

    String getUserName()
    {
        return userName;
    }

    void setPass(String password)
    {
        this.pass=password;
    }

    String getPass()
    {
        return pass;
    }



    void makeUser(String username)
    {
        while(true)
        {
            setUserName(username);
            System.out.println("Create Password: ");
            String password = sca.nextLine();
            boolean hasUpper=false;
            boolean hasLower=false;
            boolean hasDigit=false;
            if(password.length()>=5)
            {
                for(int i=0;i<password.length();i++)
                {
                    char ch=password.charAt(i);
                    
                    if(ch >= 'a' && ch <= 'z'){
                        hasLower=true;
                    }
                    else if(ch >= 'A' && ch <= 'Z'){
                        hasUpper=true;
                    }
                    else if(ch >= '0' && ch <= '9'){
                        hasDigit=true;
                    }
                }
                if(hasUpper && hasDigit && hasLower)
                {
                    setPass(password);
                    break;
                }
                else{
                    System.out.println("Invalid Password");
                    System.out.println("Password Must have atleast 1 UpperCase, atleast 1 Lower Case and atleast 1 Digit.");
                    System.out.println("Enter another Password");
                    continue;
                }
            }
            else{
                System.out.println("Invalid Password");
                System.out.println("Password Must have atleast 5 Characters.");
                System.out.println("Enter another Password: ");
                continue;
            }
        }
        return;
    }
}

abstract class Accounts
{
    double minBalance;
    double balance;
    double interest_rate;
    String account_no;
    ArrayList<Transaction> transactions = new ArrayList<>();

    Accounts()
    {
        this.interest_rate=0.0;
        this.account_no="";
        this.balance=0.0;
        this.minBalance=0.0;
    }

    void deposit(double amount)
    {
        if(amount>0)
        {
            balance += amount;
            System.out.println("Your deposit has been completed. \nYour current balance is "+getBalance());
            Transaction temp = new Transaction(amount, balance, account_no, false);
            this.transactions.add(temp);
        }
        else
        {
            System.out.println("The entered amount to withdraw must be positive");
        }
        this.printAccFunc();
    }

    void withdraw(double amount){
        if((balance-amount)<0)
        {
            System.out.println("AAP UTNE AMIR NAHI HO");
        }
    }
   
    double getBalance()
    {
        return balance;
    }
    void printAccFunc(){
        System.out.println("The following are the functions you can perform on the selected account");
        System.out.println("1. Deposit money in Account.");
        System.out.println("2. Withdraw money in Account.");
        System.out.println("3. Print Balance of Account.");
        System.out.println("4. Choose another account");
        // System.out.println("5. Go Back to Main Menu");
    }
    protected void common_funcall(int inp){
        switch(inp)
        {
            case 1 -> {
                System.out.println("Enter the amount to be Deposited");
                this.deposit(Main.sca.nextDouble());
            }
            case 2 -> {
                System.out.println("Enter the amount to be Withdrawn");
                this.withdraw(Main.sca.nextDouble());
            }
            case 3 -> {
                System.out.println("Your current Balance is "+getBalance());
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.printAccFunc();
            }
            case 4 -> {
                Main.users.get(Main.index).AccountFunctions();
            }
        }   
    }

    void printTransactions() {
        Transaction.printTop();

        for(int i = 0; i < transactions.size(); i++) {
            this.transactions.get(i).printTrans();
        }

    }
    // abstract void transfer(double amount, String destination_acc_no);
    // abstract void getTransactionHistory();
    // abstract void addTransaction();
    // abstract void calculateInterest();
}

class Savings extends Accounts
{

    double minBalance=2000.0;
    Savings()
    {
        super();
        interest_rate = 3.5;
    }

    void printAccFunc() {
        super.printAccFunc();

        System.out.println("Enter the appropriate choice");
        super.common_funcall(Main.sca.nextInt());

    }
    void withdraw(double amount)
    {
        super.withdraw(amount);
        if((balance-amount)<minBalance)
        {
            System.out.println("Warning! The amount withstanding falls below minimum balance required (Rs.2000), \n If you wish to continue with the withdrawal, you will be charged Rs.500. \n The maximum amount you can withdraw is "+ (balance-minBalance));
            System.out.println("Enter 0 to continue with this withdrawal and 1 to discontinue the withdrawal");
            int choice=Main.sca.nextInt();
            if(choice==0)
            {
                balance-=(amount+500);
                Transaction temp = new Transaction(amount, balance, account_no, true);
                super.transactions.add(temp);
            }
        }else {
            balance -= amount;
            Transaction temp = new Transaction(amount, balance, account_no, true);
            super.transactions.add(temp);
        }
        this.printAccFunc();
        
    }      
    

}      

class Checking extends Accounts      
{      
    Checking()      
    {      
        super();      
        interest_rate = 1;      
    }
    void printAccFunc() {
        super.printAccFunc();

        System.out.println("Enter the appropriate choice");
        super.common_funcall(Main.sca.nextInt());

    }
    void withdraw(double amount)
    {
        if((balance-amount)<0)
        {
            System.out.println("AAP UTNE AMIR NAHI HO");
        }
        else {
            balance -= amount;
            Transaction temp = new Transaction(amount, balance, account_no, true);
            super.transactions.add(temp);
        }
        this.printAccFunc();
        
    }
}

class FD extends Accounts
{
    
    
    public FD()
    {
        super();

    }
    void printAccFunc() {
        
    }
    void withdraw(double amount) {
        super.withdraw(amount);
    }
}

class Credit_Card extends Accounts
{


    void printAccFunc() {
        
    }
    void withdraw(double amount) {}
}

class Transaction {
    String time;
    double amount;
    double balance;
    String accountno;
    boolean withtrue;

    Transaction(double amount, double balance, String accountno, boolean withtrue) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        this.time = now.format(dtf);

        this.amount = amount;
        this.balance = balance;
        this.accountno = accountno;
        this.withtrue = withtrue;
    }

    static void printTop() {
        // System.out.println("\t Time \t     Account No. \t Amount \t With/Dep \t Balance");
        System.out.printf("%12s \t  %17s  %6s \t %5s \t%9s", "Time", "Account No.", "Amount", "With/Dep", "Balance");
        System.out.println();
    }

    void printTrans() {
        // if(withtrue)System.out.println(time + "\t   " + accountno + "\t" + amount + "\t\t Withdraw \t" + balance);
        // else System.out.println(time + "\t   " + accountno + "\t  " + amount + "\t\t Deposit \t" + balance);
        if(withtrue) System.out.format("%20s \t %7s %10s \t %7s \t%9s", time, accountno, Double.toString(amount), "Withdraw", Double.toString(balance));
        else System.out.format("%20s \t %7s %10s \t %7s \t%9s", time, accountno, Double.toString(amount), "Deposit", Double.toString(balance));
        System.out.println();
    }

}

public class Main
{   
    static ArrayList<User> users=new ArrayList<>();         //ArrayList for all users.
    static int index;
    static Scanner sca = new Scanner(System.in);
    static void signup()
    {
        while(true){
            boolean userExists=false;

            System.out.println("Enter User Name: ");

            String username=sca.nextLine();
            for(int i = 0;i<users.size();i++)
            {
                
                if(users.get(i).getUserName()==username)
                {
                    userExists=true;
                    break;
                }
            }

            if(userExists)
            {
                System.out.println("UserName already exists, enter a new username!");
                continue;
            }
            else{
                users.add(new User());
                users.get(users.size()-1).makeUser(username);
                users.get(users.size()-1).makeChoice(printFunctions());
                index = users.size() - 1;
                break;
            }
        }
        return;
    }

    static void signin()
    {
        while(true)
        {
            System.out.println("Enter User name: ");
            String signuser = sca.nextLine();
            boolean isUser = false;
            int i;
            for(i = 0; i < users.size(); i++) {
                if(signuser == users.get(i).getUserName()) {
                    isUser = true;
                    break;
                }
            }

            String signpass;
            if(isUser) {
                System.out.println("Enter Password: ");
                signpass = sca.nextLine();

                if(signpass == users.get(i).getPass()) {
                    System.out.println("Login Succesfull");
                    users.get(i).makeChoice(printFunctions());
                    index = i;
                    break;
                }else {
                    System.out.println("Wrong Password");
                    System.out.println("Try Again");
                    continue;
                }
            }
            else
            {
                System.out.println("entered username doesnt exist. Please try again");
                continue;
            }
        }
        return;
    }

    static void inupChoice() {
        System.out.println("Press 0 to Sign Up or 1 to Sign In!");
        int signchoice=sca.nextInt();
        sca.nextLine();
        if(signchoice == 0) signup();
        else if(signchoice == 1) signin();
    }

    public static void main(String[] args) {

        System.out.print("Welcome. ");
        
        inupChoice();

        while(true) {
            int choice = printFunctions();
            if(choice == 4) {
                System.out.println("Exiting the Program....");
                break;
            }
            users.get(index).makeChoice(choice);
        }
        
    }

    static int printFunctions()
    { 
        System.out.println("Welcome");
        System.out.println("The following functionalities are available for your use:");
        System.out.println("1. Manage Accounts");
        System.out.println("2. Track Transactions");
        // System.out.println("2. Budgeting");
        // System.out.println("3. Debt Management");
        System.out.println("3. Log Out");
        System.out.println("4. Exit");
        System.out.println("Enter the appropriate Digit to proceed further");
        
        int ch = sca.nextInt();
        return ch;
    }
}
