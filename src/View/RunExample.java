package View;
import Controller.Controller;
import Controller.ToyLanguageException;

import java.io.IOException;
import java.util.Scanner;

public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute()  {
        try{
            System.out.println("You want to display steps? 1.Yes, 2.No");
            Scanner scanner = new Scanner(System.in);
            boolean flag = false;

                int choiceFlag = scanner.nextInt();


                switch (choiceFlag) {
                    case 1:
                        flag = true;
                        break;
                    case 2:
                        flag = false;
                        break;
                    default: {
                        System.out.println("Not a good flag option");
                    }
                }
                ctr.setFlagOpt(flag);
                ctr.allStep(); }
        catch (ToyLanguageException | IOException e) {System.out.println(e.getMessage());} //here you must treat the exceptions that can not be solved in the controller
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}