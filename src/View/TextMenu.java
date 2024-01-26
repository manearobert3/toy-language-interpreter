package View;

import ADT.MyDictionary;
import ADT.MyIDictionary;
import Controller.ToyLanguageException;
import View.Command;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextMenu {
        private MyIDictionary<String, Command> commands;
        public TextMenu(){ commands=new MyDictionary<>(); }
        public void addCommand(Command c){ commands.put(c.getKey(),c);}
        private void printMenu(){
            for(Command com : commands.getMap().values()){
                String line=String.format("%4s : %s", com.getKey(), com.getDescription());
                System.out.println(line);
            }
        }
        public void show() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                printMenu();
                System.out.printf("Input the option:");
                String key = scanner.nextLine();
                try {
                    Command com = commands.lookUp(key);

                    if (com == null) {
                        System.out.println("Invalid Option");
                        continue;
                    }
                    com.execute();
                } catch (ToyLanguageException exc) {
                    System.out.println(exc);

                }
            }

        }
}
