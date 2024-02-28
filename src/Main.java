import java.util.Scanner;

class User{
    private String pass=new String();
    
    void choice(boolean signup){
        if(signup){
            System.out.println("Create Password : ");
            makeUser();
        }
        else{
            
        }
            
    }

    boolean checkPass(){

    }

    void makeUser(){
        Scanner sca = new Scanner();
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
        User one= new User();
        Scanner s=new Scanner(System.in);

        System.out.println("Do you want to SIGN IN or SIGN UP\nEnter 0 for sign in\nEnter 1 for sing up");
        int signup=s.nextInt();
        if(signup==1)
        one.choice(true);
        else
        one.choice(false);
      
    }
}