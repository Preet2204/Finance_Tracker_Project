import java.util.ArrayList;
import java.util.Scanner;

class Users {
    ArrayList<User> user = new ArrayList<>();

    Users() {
        Scanner sca = new Scanner(System.in);
        User temp = new User();
        System.out.println("Enter User Name: "); 
        String name = sca.nextLine();                 // Taking User name as input
        temp.userName = name;
        System.out.println("Create Password: ");  
        temp.makeUser();
        user.add(temp);
    }

}

class User{
    private String pass=new String();
    String userName = new String();
    // check 
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
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Welcome. Press '0' to log in and '1' for Sign Up");
        int x = s.nextInt();
        // String pass=s.nextLine();

        if(x == 1){
            Users one;
            one = new Users();
            // one.makeUser();                             // Calling makeUser method.
            // boolean isUser=one.checkPass(pass);
        }


    }
}