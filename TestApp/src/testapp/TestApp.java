package testapp;

public class TestApp {
    
    public static void main(String[] args) {
        String data = "1, Nguyen    van a, anv@gmail.com, 098889999, Cau Giay - Ha Noi - Viet Nam";
        
        String[] nameSegment = data.split(",")[1].split(" ");
        
        String properName = "";
        
        for (String s : nameSegment) {
            if (!s.isEmpty()) {
                String firstLetter = s.substring(0, 1).toUpperCase();
                String remainingLetter = s.substring(1);
                
                properName += firstLetter + remainingLetter + " ";
            }
        }
        
        System.out.println(properName);
    }
}
