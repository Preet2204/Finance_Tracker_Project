import java.util.*;

class User{
    Scanner sca = new Scanner(System.in);
    private String pass=new String();
    private String userName = new String();
    
    void makeChoice(int ch)
    {
        
    }

    void setUserName(String username){
        this.userName=username;
    }
    String getUserName()
    {
        return userName;
    }
    void setPass(String password){
        this.pass=password;
    }
    String getPass()
    {
        return pass;
    }

    void makeUser(){
        while(true) {
            System.out.println("Enter User Name: ");
            setUserName(sca.nextLine());
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
                    System.out.println("Enter another Password: ");
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
    }
}


public class Main {   
    static ArrayList<User> users=new ArrayList<>();
    static Scanner sca = new Scanner(System.in);
    static int ind = 0;
    static void signup(ArrayList<User> use) {
        boolean userExists=false;
        for(int i=0;i<use.size();i++)
        {
            System.out.println("Enter User Name: ");
            String username=sca.nextLine();
            if(use.get(i).getUserName()==username)
            {
                userExists=true;
                break;
            }
        }
        if(userExists){
            System.out.println("UserName already exists, enter a new username!");
            signup(use);
        }
        use.add(new User());
        use.get(ind++).makeUser();
    }

    static void signin(ArrayList<User> use) {
        System.out.println("Enter User name: ");
        String signuser = sca.nextLine();
        boolean isUser = false;
        int i;
        for(i = 0; i < ind; i++) {
            if(signuser == use.get(i).getUserName()) {
                isUser = true;
                break;
            }
        }

        String signpass;
        if(isUser) {
            System.out.println("Enter Password: ");
            signpass = sca.nextLine();
            
            if(signpass == use.get(i).getPass()) {
                System.out.println("Login Succesfull");
                printFunctions();
            }else {
                System.out.println("Wrong Password");
                System.out.println("Try Again");
                signin(use);
            }
        }
    }
    public static void main(String[] args) {
        
        System.out.println("Welcome. Press 0 to Sign Up or 1 to Sign In!");
        int signchoice=sca.nextInt();
        if(signchoice == 0) signup(users);
        else if(signchoice==1) {
            System.out.println("enter username");
            String username=new String();    
        }
        
    }

    static void printFunctions()
    { 
        System.out.println("Welcome");
        System.out.println("The following functionalities are available for your use:");
        System.out.println("1. Accounts and balances");
        System.out.println("2. Tracking your Transactions");
        System.out.println("3. Budgeting");
        System.out.println("4. Debt Management");
        System.out.println("Enter the appropriate Digit to proceed further");
        int ch= sca.nextInt();
    }
}
