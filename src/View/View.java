package View;

import ADT.*;
import Controller.Controller;
import Expressions.ArithExp;
import Expressions.Expression;
import Expressions.VarExp;
import Repository.IRepository;
import Repository.Repository;
import Controller.ToyLanguageException;
import Stmts.CompStmt;
import Stmts.IStmt;
import Stmts.PrintStmt;
import Stmts.VarDeclStmt;
import Types.BoolType;
import Types.IntType;
import Values.BoolValue;
import Values.IntValue;
import Expressions.ValueExp;
import Values.StringValue;
import Values.Value;
import Stmts.IfStmt;
import Stmts.AssignStmt;
import Stmts.closeReadFile;
import Stmts.OpenRFile;
import Stmts.ReadFile;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class View {

    IStmt example1 = new CompStmt(new VarDeclStmt("v",new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),new VarDeclStmt("v",new IntType()))); //new PrintStmt(new
                  //  VarExp("v"))));

    IStmt example2 = new CompStmt( new VarDeclStmt("a",new IntType()),
            new CompStmt(new VarDeclStmt("b",new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                            ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                            new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                    IntValue(1)))), new PrintStmt(new VarExp("b"))))));

    IStmt example3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                    IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                    VarExp("v"))))));
    public void menu() {
        while (true) {
            System.out.println("flag:1. true, 2.false");

            Scanner scanner = new Scanner(System.in);
            boolean flag = false;

            try {
                int choiceFlag = scanner.nextInt();


                switch (choiceFlag) {
                    case 1:
                        flag = true;
                        break;
                    case 2:
                        flag = false;
                        break;
                    default:
                        System.out.println("Not a good flag option");
                }
                System.out.println("0. End of execution");
                System.out.println("1. Run Example1: " + example1);

                System.out.println("2. Run Example2: " + example2);
                System.out.println("3. Run Example3: " + example3);
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        System.exit(0);
                    case 1:
                        //runExample(example1, flag);
                        break;
                    case 2:
                        //runExample(example2, flag);
                        break;
                    case 3:
                       // runExample(example3, flag);
                        break;
                    default:
                        System.out.println("Invalid example number.");
                }
            }catch (InputMismatchException e){
                System.out.println(e);
            }
        }

    }

//    public static void main(String[] arg){
//        //View view =  new View();
//        //view.menu();
//        String varf;
//        varf="test.in";
//        String varc="varc";
//        Expression varcExp = new VarExp(varc);
//        Expression fileNameExp = new ValueExp(new StringValue(varf));
//        new OpenRFile(fileNameExp);
//
//        new ReadFile(fileNameExp,varc);
//        System.out.println(varc);
//        new ReadFile(fileNameExp,varc);
//        System.out.println(varc);
//        new closeReadFile(fileNameExp);
//
//    }

//    public void runExample(IStmt example,boolean flagC) throws IOException, ToyLanguageException {
//        MyIStack<IStmt> stk = new MyStack<IStmt>();
//        MyIDictionary<String, Value> symtbl =
//                new MyDictionary<String,Value>();
//        MyIList<Value> out = new MyList<Value>();
//       // PrgState crtPrgState = new PrgState(stk,symtbl,out, example);
////        IRepository repo = new Repository("repo.txt");
//        //repo.add(crtPrgState);
//        Controller controller = new Controller(repo,flagC);
//        try {
//            controller.allStep();
//
//        }catch(ToyLanguageException e){
//            System.out.println(e.getMessage());
//        }
//    }
}
