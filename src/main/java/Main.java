
import View.Controller;
import View.LogInRegisterView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        LogInRegisterView logInView=new LogInRegisterView();
        logInView.setVisible(true);
        Controller controller=new Controller(logInView);

    }
}
