import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


class User{
    ArrayList <Accounts> account_list=new ArrayList<>();    //will contain all the accounts a user will have
    Scanner sca = new Scanner(System.in);
    private String pass=new String();
    private String userName = new String();

    void makeAccount()
    {
        System.out.println("\nWhat kind of account do you want to create?\n");
        System.out.println("1. Savings ");
        System.out.println("2. Checking");
        System.out.println("3. Fixed Deposit");
        System.out.println("4. Go Back to Main Menu\n");
        Accounts a=new Accounts();
        boolean UserNotCoop=true;           //variable used to check if the user has entered the correct serial number
                                            //we initialise the variable to true as default to enter the while loop
        while(UserNotCoop)                  //while loop to repeat the entering input process till the user enter a digit in the menu
        {
            UserNotCoop=false;              //we assume that the user will cooperate 
            String choice = Main.sca.next();
            Main.sca.nextLine();
            switch(choice.charAt(0))
            {
                case '1' -> a = new Savings();
                case '2' -> a = new Checking();
                case '3' -> a = new FD();
                case '4' -> {return;}
                default -> {
                    UserNotCoop=true;        //only when he doesnt we update the variable to true
                    System.out.println("Enter given choices only");
                }
            };
        }
        String tempAccNo="";
        UserNotCoop = true;                 //now the variable is used to check if the entered account number is valid
        while(UserNotCoop)                  //while loop will iterate till the entered account number is valid
        {
            UserNotCoop = false;            //we assume that the user will enter a valid account number  
            System.out.println("Enter Unique Account Number (Only 7 Digits)");
            System.out.println("+++++++");
            tempAccNo=Main.sca.next();

            if(tempAccNo.length() == 7) {

                for(int i = 0; i < 7; i++) {        //checking if all entered characters are numbers 
                    if(tempAccNo.charAt(i) > '9' || tempAccNo.charAt(i) < '0') {
                        System.out.println("Account Number should only have Numbers");
                        UserNotCoop = true;         //now it holds that the user has not entered valid acc no (i.e the while loop will re-iterate)
                        break;                      //exits the for loop as soon it encounters one non digit 
                    }

                }

                for(int i=0;i<account_list.size() && !UserNotCoop;i++)  // && !UserNotCoop is present to avoid execution of this loop if the user hasnt entered a valid account number 
                                                                        //only when user cooperates will this loop execute 
                {
                    if(account_list.get(i).account_no.equals(tempAccNo)) //this checks if there already exists a account with same reference number
                    {
                        System.out.println("Account number already refers to another account\n");
                        UserNotCoop = true;                              //user doesnt enter valid acc no, loop iterates
                        break;
                    }
                }
            }else {
                System.out.println("\nInvalid Account No.");
                System.out.println("Account No. should only have 7 Digits\n");
                UserNotCoop = true;                                     //user doesnt enter valid acc no, loop iterates
            }
        }
        
        a.account_no=tempAccNo;

        if(a instanceof FD ){           //checks if the object a is of type FD (since FD has a initial deposit criteria)
            outer: while(true)          //outer while iterates till balance entered isnt greater than 10,000
            {
                System.out.println("Enter Current Balance");
                double tempBalance=Main.sca.nextDouble();
                if(tempBalance<10000){

                    System.out.println("You can not create a FD with initial deposit less than 10000."); 

                    inner: while(true){             //inner while is for takig the proper input from the given choices 
                        System.out.println("If you want to create an FD having greater balance, enter 0\nElse if you want to go back press 1");
                        String choiceString = Main.sca.next();
                        Main.sca.nextLine();
                        switch (choiceString.charAt(0)) {
                            case '0' -> {
                                continue outer;     //takes the balance again for the FD account
                            }
                            case '1' -> {
                                this.makeAccount(); //takes user back to making new Account 
                                return;
                            }
                            default ->{
                                System.out.println("enter appropriate choice");
                                continue inner;
                            }
                        }
                    }
                }   
                else{       //balance is sufficient to create an FD account
                    a.balance=tempBalance;
                    System.out.println("enter the maturity period in years you want to choose for your FD account");
                    a.tenure=Main.sca.nextInt();
                    a.interest_rate=(3.5 + a.balance/10000000 + a.tenure/4);
                    System.out.println("The interest category applicable for your tenure and deposit is "+a.interest_rate+"%");
                    a.last_date = LocalDate.now();
                    account_list.add(a);
                    System.out.println("You have successfully created an account. You have been redirected to the main menu!");
                    break;
                }
            }
        }
        else{                                               //if the account is of any type other than FD (no minimum initial deposit is required)
            System.out.println("Enter Current Balance");
            double tempBalance=Main.sca.nextDouble();
            a.balance=tempBalance;
            a.last_date = LocalDate.now();
            account_list.add(a);
            System.out.println("You have successfully created an account. You have been redirected to the main menu!");
            
        }
        
    }

    void AccountFunctions()
    {
        while(true)
        {
            System.out.println("Enter the account number of the account you wish to choose (enter -1 to go back)");
            System.out.println("+++++++");
            String tempString=Main.sca.next();
            int i=0;                    //to store the index of the account the user wants to perform functions on
            if(tempString.equals("-1"))
            {
                makeChoice('1');
                break;                  //break is required to exit the while loop, once the user makes another choice and that execution is completed, code returns back to this line
            }
            for(;i<=account_list.size()-1;i++)  //searching the account number entered 
            {
                if(tempString.equals(account_list.get(i).account_no)) break;    
            }
            if(i<account_list.size())   //after the loop iterates completely, if the acc no was found, index of that account in the arraylist must be smaller than the list size
            {
                account_list.get(i).printAccFunc();
                break;  
            }
            else{
                System.out.println("Account Number Doesnt Exist.");  
                continue;
            }
        }

    }

    //For user to make a choice given in the MAIN MENU only 
    void makeChoice(char ch)
    {
        switch(ch)
        {
            case '1' ->{                  //to MANAGE ACCOUNTS 
                boolean loop = true;
                inner: while(loop)     // To reiterate the choices if user enters wrong number
                {
                    System.out.println("\nWhat would you like to do\n");                        
                    System.out.println("1. Add an account");
                    System.out.println("2. Perform actions on already existing account");
                    System.out.println("3. Go Back to Main Menu\n");
                    loop = false;           //we assume that the user will choose the correct serial number 
                    String choice = Main.sca.next();
                    Main.sca.nextLine();
                    switch(choice.charAt(0))
                    {
                        case '1' -> {
                            makeAccount();
                            break inner;
                        }
                        case '2' -> {
                            AccountFunctions();
                            break inner;
                        }
                        case '3' -> {return;}     //directly returning back will always go back to main method because wherever makeChoice is called, the method always goes back to main method 
                        default -> {
                            System.out.println("Invalid Number. Enter Again.");
                            loop = true; 
                        }                                       
                    };
                }
            }
            case '2' ->{
                boolean loop = true;
                while(loop) {                                                           //while loop to iterate till the user enter the correct serial number 
                    System.out.println("What would you like to do.");
                    System.out.println("1. Print Transaction of All Accounts");
                    System.out.println("2. Print Transaction of a Specific Account");
                    System.out.println("3. Go Back to Main Menu");
                    System.out.println("Enter Appropriate digit");
                    loop = false;                                                       //we assume the user enter correct serial number
                    String choice = Main.sca.next();
                    Main.sca.nextLine(); 
                    switch (choice.charAt(0)) {
                        case '1' -> {
                            System.out.println("----------------------------------------------------------------------------------------------------");
                            for(int i = 0; i < account_list.size(); i++) {
                                System.out.println("Account No: " + account_list.get(i).account_no);
                                System.out.println("Account Type: " + account_list.get(i).getClass().getName());
                                System.out.println("----------------------------------------------------------------------------------------------------");
                                account_list.get(i).printTransactions();
                                System.out.println("----------------------------------------------------------------------------------------------------");
                                System.out.println("----------------------------------------------------------------------------------------------------");
                            }
                        }
                        case '2' -> {
                            boolean accNoExists = true;
                            while(accNoExists)
                            {
                                accNoExists = false;
                                System.out.println("Enter Account No. of the Account you want to print Transaction of.");
                                String tempAcc_no = Main.sca.next();
                                int i = 0;
                                for(; i < account_list.size(); i++) {
                                    if(account_list.get(i).account_no.equals(tempAcc_no)) {
                                        account_list.get(i).printTransactions();
                                        break;
                                    }
                                }

                                if(i == account_list.size()) {
                                    System.out.println("Invalid Account No.");
                                    System.out.println("Account No. Doesn't Exist.");
                                    accNoExists = true;
                                }
                            }
                        }
                        case '3' -> {return;}
                        default -> {
                            System.out.println("Invalid Input. Please try Again.");
                            loop = true;
                        }
                    }
                }
            }
            case '3' -> {return;}
            default ->{
                System.out.println("Invalid Choice. Please Try Again.");
                return;
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
        while(true)                                         //loop to set new password every time theuser fails to follow all the constraints
        {
            setUserName(username);
            System.out.println("\nCreate Password: ");
            String password = Main.sca.nextLine();
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
                    else if(ch > '0' && ch <= '9'){
                        hasDigit=true;
                    }
                }
                if(hasUpper && hasDigit && hasLower)
                {
                    setPass(password);
                    break;
                }
                else{
                    System.out.println("\nInvalid Password");
                    System.out.println("Password Must have atleast 1 UpperCase, atleast 1 Lower Case and atleast 1 Digit.");
                    System.out.println("Enter another Password\n");
                    continue;
                }
            }
            else{
                System.out.println("\nInvalid Password");
                System.out.println("Password Must have atleast 5 Characters.");
                System.out.println("Enter another Password: ");
                continue;
            }
        }
        // return; 
    }
}

class Accounts
{
    //declaring the neccessary variables required
    int tenure;
    double minBalance;
    double balance;
    double interest_rate;
    String account_no;
    LocalDate last_date;
    ArrayList<Transaction> transactions = new ArrayList<>();

    Accounts()
    {
        this.interest_rate = 0.0;
        this.account_no = "";
        this.balance = 0.0;
        this.minBalance = 0.0;
    }

    void deposit(double amount)
    {
        if(amount>0)
        {
            calculateInterest();
            balance += amount;
            System.out.println("\nYour deposit has been completed. \nYour current balance is "+getBalance());
            Transaction temp = new Transaction(amount, balance, account_no, false);                 //adding the transaction to the transaction list 
            this.transactions.add(temp);
        }
        else
        {
            System.out.println("\nThe entered amount to withdraw must be positive\n");
        }
        this.printAccFunc();                                                       //returning back to the functions that can be performed on the given account
    }

    void withdraw(double amount){
        
        if((balance-amount)<0)
        {
            System.out.println("\nAAP UTNE AMIR NAHI HO\n");
        }
        else {
            calculateInterest();
            balance -= amount;
            System.out.println("\nYour withdrawal is successfully completed");
            Transaction temp = new Transaction(amount, balance, account_no, true);      //adding transaction to the transaction list 
            transactions.add(temp);
        }
        this.printAccFunc();
    }
   
    double getBalance()
    {
        return balance;
    }

    void printAccFunc(){
        System.out.println("\nThe following are the functions you can perform on the selected account\n");
        System.out.println("1. Deposit money in Account.");
        System.out.println("2. Withdraw money in Account.");
        System.out.println("3. Print Balance of Account.");
        System.out.println("4. Choose another account");
        System.out.println("5. Go Back to Main Menu");
    }

    protected void common_funcall(char inp){             //has switch to execute appropriate function as per the methods in printAccFunc 
        switch(inp)
        {
            case '1' -> {
                System.out.println("\nEnter the amount to be Deposited");
                this.deposit(Main.sca.nextDouble());    //calls the deposit function of the account object created 
            }
            case '2' -> {
                System.out.println("\nEnter the amount to be Withdrawn");
                this.withdraw(Main.sca.nextDouble());   //calls the withdraw function of the account object created 
            }
            case '3' -> {
                System.out.println("\nYour current Balance is "+getBalance()); 
                try{                                    //adds a delay of 3 seconds after printing the balance of the account 
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.printAccFunc();                    //will return back to functionalities of the chosen ACCOUNT  
            }
            case '4' -> {
                Main.users.get(Main.index).AccountFunctions();  //goes to the functions of the chosen USER
            }
            case '5' -> {
                return;                                 //this will terminate all the recursive function calls and return the code to main 
            }
        }   
    }

    void printTransactions() {
        Transaction.printTop();
        System.out.println("----------------------------------------------------------------------------------------------------");
        for(int i = 0; i < transactions.size(); i++) {
            this.transactions.get(i).printTrans();
        }

    }

    protected void calculateInterest() {
        LocalDate now_date = LocalDate.now();
        long daysdiff = ChronoUnit.DAYS.between(last_date, now_date);
        double now_balan = balance;
        double diff;
        
        for(int i = 0; i < daysdiff; i++) {
            now_balan = balance;
            balance += interest_rate*(balance)/100;
            diff = balance - now_balan;
            Transaction temp = new Transaction(diff, balance, account_no, false);
            transactions.add(temp);
        }

        last_date = now_date;
    }
}

class Savings extends Accounts
{

    Savings()
    {
        interest_rate = 3.5;
        minBalance=2000.0;
    }

    void printAccFunc() {
        super.printAccFunc();

        while(true) {
            System.out.println("\nEnter the appropriate choice\n");
            String choice = Main.sca.next();
            Main.sca.nextLine();
            if(choice.charAt(0) > '0' && choice.charAt(0) <= '5'){
                super.common_funcall(choice.charAt(0));               //takes input from the static scanner object in Main class
                break;
            } else {
                System.out.println("Enter Appropriate input.\n");
            }
        }
    }

    void withdraw(double amount)
    {
        if((balance-amount)<0)
        {
            System.out.println("AAP UTNE AMIR NAHI HO");
        }
        else if((balance-amount)<minBalance)                    //checking if balance withstanding after withdrawal is more than the minimum balance required in a savings account 
        {
            System.out.println("Warning! The amount withstanding falls below minimum balance required (Rs.2000), \n If you wish to continue with the withdrawal, you will be charged Rs.500. \n The maximum amount you can withdraw is "+ (balance-minBalance));
            System.out.println("Enter 0 to continue with this withdrawal and 1 to discontinue the withdrawal\n");
            int choice=Main.sca.nextInt();
            if(choice==0)
            {
                calculateInterest();
                balance-=(amount+500);
                Transaction temp = new Transaction(amount, balance, account_no, true);
                super.transactions.add(temp);
            }
        }else {
            calculateInterest();
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

        while(true) {
            System.out.println("Enter the appropriate choice\n");
            String choice = Main.sca.next();
            Main.sca.nextLine();
            if(choice.charAt(0) > '0' && choice.charAt(0) <= '5'){
                super.common_funcall(choice.charAt(0));               
                break;
            } else {
                System.out.println("\nEnter Appropriate input.\n");
            }
        }

    }
}

class FD extends Accounts
{
    // int tenure;   
    
    public FD()
    {
        super();
    }
    void printAccFunc() {
        super.printAccFunc();
        
        while(true) {
            System.out.println("Enter the appropriate choice\n");
            String choice = Main.sca.next();
            Main.sca.nextLine();
            if(choice.charAt(0) > '0' && choice.charAt(0) <= '5'){
                common_funcall(choice.charAt(0));               //calling the overriden function common_funcall
                break;
            } else {
                System.out.println("\nEnter Appropriate input.");
            }
        }
    }

    @Override
    public void common_funcall(char inp){             //has switch to execute appropriate function as per the methods in printAccFunc 
        switch(inp)
        {
            case '1' -> {   //deposition in fd is not allowed
                System.out.println("This is a Fixed Deposit account, You can not deposit any amount other than the initial deposit mentioned in the contract");
                this.printAccFunc();
            }
            case '2' -> {
                System.out.println("Enter 0 to break the Fixed deposit and withdraw full amount (subject to reduction in interest rates for the contract)");
                System.out.println("Enter 1 to Withdraw partial amount (subject to penalty [5% of the withdrawn amount])");
                System.out.println("enter anything else to go back");

                String choice = Main.sca.next();
                Main.sca.nextLine();
                switch (choice.charAt(0)) {
                    case '0' -> {
                        System.out.println("We are deleting this account,\nplease enter 0 to confirm and 1 to think again");
                        if(Main.sca.nextInt()==0)
                        {
                            this.withdraw(this.balance);
                            for(int i=0;i<Main.users.get(Main.index).account_list.size();i++)                   //searching for the index of the account in the list of accounts of the present user 
                            {
                                if(Main.users.get(Main.index).account_list.get(i).account_no.equals(this.account_no)) 
                                {
                                    Main.users.get(Main.index).account_list.remove(i);                         //removes the account from the list     
                                    break;
                                }
                                
                            }
                           
                        }
                        else{
                            common_funcall('2');                //redirecting the user to the previous page 
                        }
                    }
                    case '1' ->
                    {
                        System.out.println("enter the amount you want to withdraw");
                        int tempwithdrawAmt=Main.sca.nextInt();
                        this.withdraw(1.05*tempwithdrawAmt);
                    }
                    default ->{
                        this.printAccFunc();
                    }
                }
            }
            case '3' -> {
                System.out.println("Your current Balance is "+getBalance()); 
                try{                                    //adds a delay of 3 seconds after printing the balance of the account 
                    Thread.sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.printAccFunc();                    //will return back to functionalities of the chosen ACCOUNT  
            }
            case '4' -> {
                Main.users.get(Main.index).AccountFunctions();  //goes to the functions of the chosen USER
            }
            case '5' -> {
                return;                                 //this will terminate all the recursive function calls and return the code to main 
            }
        }   
    }
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
        System.out.printf("%12s \t  %17s  %6s \t %5s \t%9s", "Time", "Account No.", "Amount", "With/Dep", "Balance");
        System.out.println();
    }

    void printTrans() {
        if(withtrue) System.out.format("%20s \t %7s %10s \t %7s \t%9s", time, accountno, Double.toString(amount), "Withdraw", Double.toString(balance));
        else System.out.format("%20s \t %7s %10s \t %7s \t%9s", time, accountno, Double.toString(amount), "Deposit", Double.toString(balance));
        System.out.println();
    }
}

public class Main
{   
    static ArrayList<User> users=new ArrayList<>();         //A dynamic list for all users.
    static int index;
    static Scanner sca = new Scanner(System.in);

    static void signup()
    {
        while(true){                                        //while loop iterates till the user enters a previously non existing username                                      
            boolean userExists=false;

            System.out.println("\nEnter User Name: ");

            String username=sca.nextLine();
            for(int i = 0;i<users.size();i++)               //searches in the arraylist if any user has a similar username
            {
                if(users.get(i).getUserName().equals(username)){
                    userExists=true;
                    break;                                  
                }
            }

            if(userExists)
            {
                System.out.println("\nUserName already exists, enter a new username!\n");
                continue;
            }
            else{
                users.add(new User());
                users.get(users.size()-1).makeUser(username);
                index = users.size() - 1;                   //variable used for all classes to know the current user who is accessing the finance tracker
                break;                                     //breaking while loop, since user did enter a unique username 
            }
        }
        return;
    }

    static void signin()
    {
        while(true)
        {
            System.out.println("Enter User name: ");
            String signuser = sca.next();
            sca.nextLine();
            
            boolean isUser = false;
            int i;                                                  //to store the value of the user index for which the user wants to sign in 
            for(i = 0; i < users.size(); i++) {                     //searching for the user who has the input username
                if(signuser.equals(users.get(i).getUserName())) {
                    isUser = true;                                  
                    break;
                }
            }

            String signpass;
            if(isUser) {
                System.out.println("Enter Password: ");
                signpass = sca.nextLine();

                if(signpass.equals(users.get(i).getPass())) {              //checking if the password entered is correct 
                    System.out.println("Login Succesfull");
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
                System.out.println("Entered Username doesnt exist. Please try again");
                continue;
            }
        }
        return;
    }

    static boolean inupChoice() {                                           //This function is made boolean to check if the User wants to exit the program.
        while(true)                                                         //while loop to iterate indefinitely till the user enter the correct input
        {
            System.out.println("\nPress 0 to Sign Up or 1 to Sign In!(Press -1 to Exit)\n");
            String signchoice = sca.next();
            sca.nextLine();

            if(signchoice.equals("-1")) {
                return true;
            }
            
            
            if(signchoice.length() == 1){
                if(signchoice.charAt(0) == '0') signup();
                else if(signchoice.charAt(0) == '1') signin();
                else{
                    System.out.println("\nWrong Input, Enter Again\n");
                    continue;
                }
            }else {
                System.out.println("\nEnter only one input\n");
                continue;
            }
            break;
        }
        return false;        
    }

    public static void main(String[] args) {

        System.out.println("\nWelcome. ");
        
        if(inupChoice())                                               //this method is called to take sign in or sign up from user 
        {
            System.out.println("Exiting the Program....");
            return;
        }

        while(true) {                                               //this while loop continues to print functions till the user chooses to exit the program
            char choice = printFunctions();                         //all functions in the whole code will finally return here
            if(choice == '3') {                                     //if the user chooses to log out , they will be redirected to inup function
                if(inupChoice()) choice = '4';
            }

            if(choice == '4') {                                     //if user chooses to exit the program, we simply get out of the while loop :) 
                System.out.println("Exiting the Program....");      
                break;
            }
            users.get(index).makeChoice(choice);                    
        }
        
    }

    static char printFunctions()
    { 
        System.out.println("\nMain Menu\n");
        System.out.println("The following functionalities are available for your use:\n");
        System.out.println("1. Manage Accounts");
        System.out.println("2. Track Transactions");
        System.out.println("3. Log Out");
        System.out.println("4. Exit");
        System.out.println("Enter the appropriate Digit to proceed further\n");
        
        String choice = sca.next();
        sca.nextLine();
        return choice.charAt(0);
    }
}
