package View;

import ADT.*;
import Expressions.*;
import Stmts.*;
import Types.BoolType;
import Types.IntType;
import Types.RefType;
import Types.StringType;
import Values.BoolValue;
import Values.IntValue;
import Controller.Controller;
import Repository.IRepository;
import Repository.Repository;
import Values.StringValue;
import Values.Value;
import View.TextMenu;
import Controller.ToyLanguageException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class Interpreter {
    private static List<IStmt> listOfExamples;
    private static List<Controller> listOfControllers;

    public static List<IStmt> returnListOfExamples() {
        return listOfExamples;
    }

    public static List<Controller> returnListOfControllers() {
        return listOfControllers;

    }

    public static void main(String[] args) {
        String lastExampleRun = null;
        //
//            IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new VarDeclStmt("v", new IntType()))); //new PrintStmt(new
//            //  VarExp("v"))));
//            listOfExamples.add(ex1);
//            lastExampleRun ="ex1:"+ex1.toString();
//            ex1.typecheck(new MyDictionary<>());
//            PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1, new MyDictionary<>(), new MyHeap());
//            IRepository repo1 = new Repository(prg1, "log1.txt");
//            Controller ctr1 = new Controller(repo1, true);
//
//            IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                    new CompStmt(new VarDeclStmt("b", new IntType()),
//                            new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
//                                    ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                                    new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
//                                            IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//            lastExampleRun = "ex2:"+ex2.toString();
//            ex2.typecheck(new MyDictionary<>());
//            PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2, new MyDictionary<>(), new MyHeap());
//            IRepository repo2 = new Repository(prg2, "log2.txt");
//            Controller ctr2 = new Controller(repo2, true);
//
//
//            IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
//                    new CompStmt(new VarDeclStmt("v", new IntType()),
//                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
//                                            IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
//                                            VarExp("v"))))));
//            lastExampleRun = "ex3:"+ex3.toString();
//            ex3.typecheck(new MyDictionary<>());
//            PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3, new MyDictionary<>(), new MyHeap());
//            IRepository repo3 = new Repository(prg3, "log3.txt");
//            Controller ctr3 = new Controller(repo3, true);
//
//            IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
//                    new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.txt"))),
//                            new CompStmt(new OpenRFile(new VarExp("varf")),
//                                    new CompStmt(new VarDeclStmt("varc", new IntType()),
//                                            new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
//                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
//                                                            new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
//                                                                    new CompStmt(new PrintStmt(new VarExp("varc")),
//                                                                            new closeReadFile(new VarExp("varf"))))))))));
//            lastExampleRun = "ex4:"+ex4.toString();
//            ex4.typecheck(new MyDictionary<>());
//            PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4, new MyDictionary<>(), new MyHeap());
//            IRepository repo4 = new Repository(prg4, "log4.txt");
//            Controller ctr4 = new Controller(repo4, true);
//
//
//            IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
//                    new CompStmt(new VarDeclStmt("b", new IntType()),
//                            new CompStmt(new VarDeclStmt("c", new IntType()),
//                                    new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
//                                            new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))),
//                                                    new CompStmt(new IfStmt(new RelationalExp("<=", new VarExp("a"), new VarExp("b")),
//                                                            new AssignStmt("c", new VarExp("a")), new AssignStmt("c", new VarExp("b"))),
//                                                            new PrintStmt(new VarExp("c"))))))));
//            lastExampleRun = "ex5:"+ex5.toString();
//            ex5.typecheck(new MyDictionary<>());
//            PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex5, new MyDictionary<>(), new MyHeap());
//            IRepository repo5 = new Repository(prg5, "log5.txt");
//            Controller ctr5 = new Controller(repo5, true);
//
//            IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
//                            new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
//                                    new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
//                                    new PrintStmt(new VarExp("v")))));
//
//            lastExampleRun = "ex6:"+ex6.toString();
//            ex6.typecheck(new MyDictionary<>());
//            PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex6, new MyDictionary<>(), new MyHeap());
//            IRepository repo6 = new Repository(prg6, "log6.txt");
//            Controller ctr6 = new Controller(repo6, true);
//
//
//            IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
//                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
//                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
//                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
//                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
//
//            lastExampleRun = "ex7:"+ex7.toString();
//            ex7.typecheck(new MyDictionary<>());
//            PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex7, new MyDictionary<>(), new MyHeap());
//            IRepository repo7 = new Repository(prg7, "log7.txt");
//            Controller ctr7 = new Controller(repo7, true);
//
//            IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
//                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
//                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
//                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
//                                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
//                                                    new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
//            lastExampleRun = "ex8:"+ex8.toString();
//            ex8.typecheck(new MyDictionary<>());
//            PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex8, new MyDictionary<>(), new MyHeap());
//            IRepository repo8 = new Repository(prg8, "log8.txt");
//            Controller ctr8 = new Controller(repo8, true);
//
//            IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
//                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
//                            new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
//                                    new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
//                                            new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
//            lastExampleRun = "ex9:"+ex9.toString();
//            ex9.typecheck(new MyDictionary<>());
//            PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex9, new MyDictionary<>(), new MyHeap());
//            IRepository repo9 = new Repository(prg9, "log9.txt");
//            Controller ctr9 = new Controller(repo9, true);
//
//            IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
//                    new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
//                            new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
//                                    new CompStmt(new NewHeapStmt("a", new VarExp("v")),
//                                            new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
//                                                    new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
//            lastExampleRun = "ex10:"+ex10.toString();
//            ex10.typecheck(new MyDictionary<>());
//            PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex10, new MyDictionary<>(), new MyHeap());
//            IRepository repo10 = new Repository(prg10, "log10.txt");
//            Controller ctr10 = new Controller(repo10, true);
//
//
////        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
////                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
////                                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
////                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("v")))))));
////
////        PrgState prg10 = new PrgState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex10,new MyDictionary<>(),new MyHeap());
////        IRepository repo10 = new Repository(prg10, "log10.txt");
////        Controller ctr10 = new Controller(repo10,true);
//            IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                    new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
//                            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
//                                    new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
//                                            new CompStmt(new forkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
//                                                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
//                                                            new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
//                                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
//            lastExampleRun = "ex11:"+ex11.toString();
//            ex11.typecheck(new MyDictionary<>());
//            PrgState prg11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex11, new MyDictionary<>(), new MyHeap());
//            IRepository repo11 = new Repository(prg11, "log11.txt");
//            Controller ctr11 = new Controller(repo11, true);
//            IStmt ex12 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
//                    new CompStmt(new VarDeclStmt("count", new IntType()), new WhileStmt(new RelationalExp("<", new VarExp("count"), new ValueExp(new IntValue(10))),
//                            new CompStmt(new forkStmt(new forkStmt(new CompStmt(new NewHeapStmt("a", new VarExp("count")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))), new CompStmt(new AssignStmt("count", new ArithExp('+', new VarExp("count"), new ValueExp(new IntValue(1)))), new CompStmt(new NopStmt(), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
//
//            lastExampleRun = "ex12:"+ex12.toString();
//            ex12.typecheck(new MyDictionary<>());
//            PrgState prg12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex12, new MyDictionary<>(), new MyHeap());
//            IRepository repo12 = new Repository(prg12, "log12.txt");
//            Controller ctr12 = new Controller(repo12, true);
//            TextMenu menu = new TextMenu();
//            menu.addCommand(new ExitCommand("0", "exit"));
//            menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
//            menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
//            menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
//            menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
//            menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
//            menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
//            menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
//            menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
//            menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
//            menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
//            menu.addCommand(new RunExample("11", ex11.toString(), ctr11));
//            menu.addCommand(new RunExample("12", ex12.toString(), ctr12));
//
//
//            menu.show();
//        } catch (ToyLanguageException e) {
//            System.out.println(e.getMessage());
//            System.out.println(lastExampleRun);
//        }
        MyIProcedureTable procedureTable = new MyProcedureTable();
        IStmt f1 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ArithExp('+', new VarExp("a"), new VarExp("b"))),
                        new PrintStmt(new VarExp("v"))
                )
        );
        IStmt f2 = new CompStmt(
                new VarDeclStmt("v", new IntType()),
                new CompStmt(
                        new AssignStmt("v", new ArithExp('*', new VarExp("a"), new VarExp("b"))),
                        new PrintStmt(new VarExp("v"))
                )
        );

        procedureTable.put("sum", new Pair<>(Arrays.asList("a", "b"), f1));
        procedureTable.put("product", new Pair<>(Arrays.asList("a", "b"), f2));
        try {
            IStmt ex12 =
                    new CompStmt(
                            new VarDeclStmt("v", new IntType()),
                            new CompStmt(
                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                    new CompStmt(
                                            new VarDeclStmt("w", new IntType()),
                                            new CompStmt(
                                                    new AssignStmt("w", new ValueExp(new IntValue(5))),
                                                    new CompStmt(
                                                            new FuncCallStmt(
                                                                    "sum",
                                                                    Arrays.asList(
                                                                            new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))),
                                                                            new VarExp("w")
                                                                    )
                                                            ),
                                                            new CompStmt(
                                                                    new PrintStmt(new VarExp("v")),
                                                                    new CompStmt(
                                                                            new FuncCallStmt(
                                                                                    "product",
                                                                                    Arrays.asList(
                                                                                            new VarExp("v"),
                                                                                            new VarExp("w")
                                                                                    )
                                                                            ),
                                                                            new forkStmt(
                                                                                    new FuncCallStmt(
                                                                                            "sum",
                                                                                            Arrays.asList(
                                                                                                    new VarExp("v"),
                                                                                                    new VarExp("w")
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    );
            lastExampleRun = "ex12:" + ex12.toString();
            ex12.typecheck(new MyDictionary<>());
            PrgState prg12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex12, new MyDictionary<>(), new MyHeap(),procedureTable);
            IRepository repo12 = new Repository(prg12, "log12.txt");
            Controller ctr12 = new Controller(repo12, true);
            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1", ex12.toString(), ctr12));
            menu.show();
        } catch (ToyLanguageException e) {
            throw new RuntimeException(e);
        }
    }
}