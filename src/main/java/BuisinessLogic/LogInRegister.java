package BuisinessLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LogInRegister {
    public boolean adminLogIn(String username,String pass){
        return username.equals("admin123") && pass.equals("admin123");
    }
    public boolean employeeLogIn(String username,String pass){
        return username.equals("employee123") && pass.equals("employee123");
    }

    public boolean register(String username, String pass){
        File f=new File("src/main/resources/register.txt");
        int q=1;
        try{
            Scanner a=new Scanner(f);
            while(a.hasNextLine()){
                String e=a.nextLine();
                String[] user=e.split(",");
                if(user[0].equals(username)){
                    q=0;
                    break;
                }
            }
            a.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(q==1){
            try {
                FileWriter myWriter = new FileWriter(f, true);
                myWriter.write(username + "," + pass + "\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean logInCustomer(String username,String pass) throws FileNotFoundException {
        File f=new File("src/main/resources/register.txt");
        Scanner s=new Scanner(f);
        while(s.hasNextLine()){
            String e=s.nextLine();
            String[] user=e.split(",");
            if(user[0].equals(username) && user[1].equals(pass)){
                return true;
            }
        }
        s.close();
        return false;
    }
}
