import java.util.ArrayList;
import java.util.Scanner;

// class Users {
//     ArrayList<User> user = new ArrayList<>();

//     Users() {
//         Scanner sca = new Scanner(System.in);
//         User temp = new User();
//         System.out.println("Enter User Name: "); 
//         String name = sca.nextLine();                 // Taking User name as input
//         temp.userName = name;
//         System.out.println("Create Password: ");  
//         temp.makeUser();
//         user.add(temp);
//     }
// }

class User{

    private String pass=new String();
    private String userName = new String();
    
    void makeChoice(int ch)
    {
        
    }

    void setUserName(String username){
        userName=username;
    }
    String getUserName()
    {
        return userName;
    }

    void makeUser(){
        Scanner sca = new Scanner(System.in);
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


public class Main {   
    static User one = new User();
    static Scanner sca = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome. Please Sign Up to continue!");       
        System.out.println("Enter User Name: ");
        one.setUserName(sca.nextLine());
        System.out.println("Create Password: ");
        one.makeUser();                             // Calling makeUser method.
        printFunctions();
        
        }

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




    }
}