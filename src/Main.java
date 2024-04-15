import java.util.*;

class User{
    ArrayList <Accounts> account_list=new ArrayList<>();
    Scanner sca = new Scanner(System.in);
    private String pass=new String();
    private String userName = new String();

    void makeAccount(){
        System.out.println("What kind of account do you want to create?");
        System.out.println("1. Savings ");
        System.out.println("2. Checking");
        System.out.println("3. Fixed Deposit");
        System.out.println("4. Credit Card ");
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
                default -> {
                    UserNotCoop=true;
                    System.out.println("enter given choices only");
                }
            };
        }
        account_list.add(a);
        boolean AccNoExists=true;
        String tempAccNo="";
        while(AccNoExists)
        {
            AccNoExists=false;
            System.out.println("enter unique account number");
            tempAccNo=sca.next();
            for(int i=0;i<account_list.size();i++)
            {
                if(account_list.get(i).account_no==tempAccNo)
                {
                    System.out.println("Account number already refers to another account");
                    AccNoExists=true;
                    break;
                }
            }
        }
        a.account_no=tempAccNo;
        System.out.println("enter current balance");
        a.balance=sca.nextDouble();
        System.out.println("You have successfully created an account. You have been redirected to the main menu!");
    }

    void AccountFunctions()
    {
        while(true)
        {
            System.out.println("Enter the account number of the account you wish to choose");
            String tempString=sca.next();
            int i=0
            for(;i<=account_list.size()-1;i++)
            {
                if(tempString==account_list.get(i).account_no)
                break;
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

    void makeChoice(int ch) {
        switch(ch)
        {
            case 1 ->{ 
                        System.out.println("What would you like to do");
                        System.out.println("1. Add an account");
                        System.out.println("2. Perform actions on already existing account");
                        switch(sca.nextInt())
                        {
                            case 1 -> makeAccount();
                            case 2 -> {
                                AccountFunctions();
                            }
                                    
                        };
                    }
        };
    }



    void setUserName(String username) {
        this.userName=username;
    }

    String getUserName() {
        return userName;

    }

    void setPass(String password) {
        this.pass=password;
    }

    String getPass() {
        return pass;
    }



    void makeUser(String username){
        while(true) {
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
                    
                    if(ch>='a' && ch<='z'){
                        hasLower=true;
                    }
                    else if(ch>='A' && ch<='Z'){
                        hasUpper=true;
                    }
                    else if(ch>='0' && ch<='9'){
                        hasDigit=true;
                    }
                }
                if(hasUpper && hasDigit && hasLower) {
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

abstract class Accounts{
    double balance=0.0;
    double interest;
    String account_no;

    void deposit(double amount)
    {
        balance+=amount;
    }
    void withdraw(double amount)
    {
        balance-=amount;
    }
    double getBalance()
    {
        return balance;
    }
    abstract void printAccFunc();
    // abstract void transfer(double amount, String destination_acc_no);
    // abstract void getTransactionHistory();
    // abstract void addTransaction();
    // abstract void calculateInterest();
}

class Savings extends Accounts{
    
    void printAccFunc() {
        
    }

}

class Checking extends Accounts{

}

class FD extends Accounts{

}

class Credit_Card extends Accounts{

}

public class Main {   
    static ArrayList<User> users=new ArrayList<>();         //ArrayList for all users.
    static Scanner sca = new Scanner(System.in);
    static void signup() {
        boolean userExists=false;
        
        System.out.println("Enter User Name: ");
    
        String username=sca.nextLine();
        for(int i=0;i<users.size();i++)
        {
            
            if(users.get(i).getUserName()==username)
            {
                userExists=true;
                break;
            }
        }

        if(userExists){
            System.out.println("UserName already exists, enter a new username!");
            signup();
        }
        else{
            users.add(new User());
            users.get(users.size()-1).makeUser(username);
            users.get(users.size()-1).makeChoice(printFunctions());
        }
        return;
    }

    static void signin(){
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
            }else {
                System.out.println("Wrong Password");
                System.out.println("Try Again");
                signin();
            }
        }
        else
        {
            System.out.println("entered username doesnt exist. Please try again");
            signin();
        }
        return;
    }
    public static void main(String[] args) {

        System.out.println("Welcome. Press 0 to Sign Up or 1 to Sign In!");
        int signchoice=sca.nextInt();
        sca.nextLine();
        if(signchoice == 0) signup();
        else if(signchoice==1) signin();
        printFunctions();
        
    }

    static int printFunctions()
    { 
        System.out.println("Welcome");
        System.out.println("The following functionalities are available for your use:");
        System.out.println("1. Manage Accounts");
        System.out.println("2. Tracking your Transactions");
        System.out.println("3. Budgeting");
        System.out.println("4. Debt Management");
        System.out.println("5. Log Out");
        System.out.println("6. Exit");
        System.out.println("Enter the appropriate Digit to proceed further");
        int ch= sca.nextInt();
        return ch;
    }
}
