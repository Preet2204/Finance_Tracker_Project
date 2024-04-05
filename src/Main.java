mmakeUser();
//         user.add(temp);
//     }
// }

class User{

    protected String pass=new String();
    protected String userName = new String();

    void makeChoice1(int ch)
    {
        switch (ch) {
            case 1:
                
                break;
        
            default:
                break;
        }
    }

    User(String usernaam) {
        userName=usernaam;
    }

    String getUserName() {
        return userName;
    }

    void makeUser(){
        Scanner sca = new Scanner(System.in);
        String password = sca.nextLine();
        boolean hasUpper=false;
        boolean hasLower=false;
        boolean hasDigit=false;
        if(password.length()>=5)                            //password Strength Checker (Length >= 5, Atleast one uppercase and lowercase)
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
            if(hasUpper && hasDigit && hasLower)
            pass=password;
            else{
                System.out.println("Invalid Password");
                System.out.println("Password Must have atleast 1 UpperCase, atleast 1 Lower Case and atleast 1 Digit.");
                System.out.println("Enter another Password: ");
                this.makeUser();
            }
        }
        else{
            System.out.println("Invalid Password");
            System.out.println("Password Must have atleast 5 Characters.");
            System.out.println("Enter another Password: ");
            this.makeUser();
        }
    }
}

class Account extends User{
    Scanner sca = new Scanner(System.in);

    double balance;
    float interestRate;

    Account(String usernaam) {
        super(usernaam);
    }

    void makeAccount() {
        System.out.println("The following Accounts are available for your use: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Checkings Account");
        System.out.println("Enter the appropriate Digit to proceed further");
        int ch = sca.nextInt();
        switch (ch) {
            case 1:
                
                break;
            case 2:

                break;
            default:
                break;
        }
    }

    
}

class Savings extends Account{

    Savings(String usernaam) {
        super(usernaam);
    }
    
}

public class Main {   
    static User one;
    static Scanner sca = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome. Please Sign Up to continue!");       
        System.out.println("Enter User Name: ");
        one = new User(sca.nextLine());
        System.out.println("Create Password: ");
        one.makeUser();                             // Calling makeUser method.
        printFunctions();
        
    }

    //Savings obj = new Savings();
    //obj.balance;

    static void printFunctions()
    { 
        System.out.println("Welcome" + one.getUserName());
        System.out.println("The following functionalities are available for your use:");
        System.out.println("1. Accounts and balances");
        System.out.println("2. Tracking your Transactions");
        System.out.println("3. Budgeting");
        System.out.println("4. Debt Management");
        System.out.println("Enter the appropriate Digit to proceed further");
        int ch= sca.nextInt();
        one.makeChoice1(ch);
    }
}