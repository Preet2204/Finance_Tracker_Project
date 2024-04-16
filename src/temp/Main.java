import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date; 
public class Main {    
    public static void main(String[] args) {    
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String temp = dtf.format(now);
        System.out.println(temp); 
    }    
}