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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
public class initialiseExamples {
    private static List<IStmt> listOfExamples=new ArrayList<>();
    private static List<Controller> listOfControllers=new ArrayList<>();
    private static List<String> listOfErrors= new ArrayList<>();
    private static boolean initialized=false;
    public static List<IStmt> returnListOfExamples(){
        return listOfExamples;
    }
    public static List<Controller> returnListOfControllers(){
        return listOfControllers;

    }
    public static boolean initializedBool(){
        return initialized;
    }
    public static List<String> returnListOfErrors(){
        return listOfErrors;
    }
    public static void start() throws ToyLanguageException{
        if(initialized==false) {
            String lastExampleRun = null;
            try {
                initialized = true;
                IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v")))); //new PrintStmt(new
                //  VarExp("v"))));



                lastExampleRun = "ex1:" + ex1.toString();
                ex1.typecheck(new MyDictionary<>());
                PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex1, new MyDictionary<>(), new MyHeap());
                IRepository repo1 = new Repository(prg1, "log1.txt");
                Controller ctr1 = new Controller(repo1, true);
                listOfExamples.add(ex1);
                listOfControllers.add(ctr1);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                        ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                        new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                                IntValue(1)))), new PrintStmt(new VarExp("b"))))));
                lastExampleRun = "ex2:" + ex2.toString();
                ex2.typecheck(new MyDictionary<>());
                PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex2, new MyDictionary<>(), new MyHeap());
                IRepository repo2 = new Repository(prg2, "log2.txt");
                Controller ctr2 = new Controller(repo2, true);
                listOfExamples.add(ex2);
                listOfControllers.add(ctr2);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                        new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                                IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                                VarExp("v"))))));
                lastExampleRun = "ex3:" + ex3.toString();
                ex3.typecheck(new MyDictionary<>());
                PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex3, new MyDictionary<>(), new MyHeap());
                IRepository repo3 = new Repository(prg3, "log3.txt");
                Controller ctr3 = new Controller(repo3, true);
                listOfExamples.add(ex3);
                listOfControllers.add(ctr3);
            } catch (ToyLanguageException e) {

                listOfErrors.add(e.getMessage());
                System.out.println(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.txt"))),
                                new CompStmt(new OpenRFile(new VarExp("varf")),
                                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                                new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                                new closeReadFile(new VarExp("varf"))))))))));
                lastExampleRun = "ex4:" + ex4.toString();
                ex4.typecheck(new MyDictionary<>());
                PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex4, new MyDictionary<>(), new MyHeap());
                IRepository repo4 = new Repository(prg4, "log4.txt");
                Controller ctr4 = new Controller(repo4, true);
                listOfExamples.add(ex4);
                listOfControllers.add(ctr4);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {

                IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new VarDeclStmt("c", new IntType()),
                                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(2))),
                                                new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(3))),
                                                        new CompStmt(new IfStmt(new RelationalExp("<=", new VarExp("a"), new VarExp("b")),
                                                                new AssignStmt("c", new VarExp("a")), new AssignStmt("c", new VarExp("b"))),
                                                                new PrintStmt(new VarExp("c"))))))));
                lastExampleRun = "ex5:" + ex5.toString();
                ex5.typecheck(new MyDictionary<>());
                PrgState prg5 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex5, new MyDictionary<>(), new MyHeap());
                IRepository repo5 = new Repository(prg5, "log5.txt");
                Controller ctr5 = new Controller(repo5, true);
                listOfExamples.add(ex5);
                listOfControllers.add(ctr5);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                                new CompStmt(new WhileStmt(new RelationalExp(">", new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                        new PrintStmt(new VarExp("v")))));

                lastExampleRun = "ex6:" + ex6.toString();
                ex6.typecheck(new MyDictionary<>());
                PrgState prg6 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex6, new MyDictionary<>(), new MyHeap());
                IRepository repo6 = new Repository(prg6, "log6.txt");
                Controller ctr6 = new Controller(repo6, true);
                listOfExamples.add(ex6);
                listOfControllers.add(ctr6);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {

                IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

                lastExampleRun = "ex7:" + ex7.toString();
                ex7.typecheck(new MyDictionary<>());
                PrgState prg7 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex7, new MyDictionary<>(), new MyHeap());
                IRepository repo7 = new Repository(prg7, "log7.txt");
                Controller ctr7 = new Controller(repo7, true);
                listOfExamples.add(ex7);
                listOfControllers.add(ctr7);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                        new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
                lastExampleRun = "ex8:" + ex8.toString();
                ex8.typecheck(new MyDictionary<>());
                PrgState prg8 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex8, new MyDictionary<>(), new MyHeap());
                IRepository repo8 = new Repository(prg8, "log8.txt");
                Controller ctr8 = new Controller(repo8, true);
                listOfExamples.add(ex8);
                listOfControllers.add(ctr8);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                        new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                                new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));
                lastExampleRun = "ex9:" + ex9.toString();
                ex9.typecheck(new MyDictionary<>());
                PrgState prg9 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex9, new MyDictionary<>(), new MyHeap());
                IRepository repo9 = new Repository(prg9, "log9.txt");
                Controller ctr9 = new Controller(repo9, true);
                listOfExamples.add(ex9);
                listOfControllers.add(ctr9);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                        new CompStmt(new NewHeapStmt("a", new VarExp("v")),
                                                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
                                                        new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));
                lastExampleRun = "ex10:" + ex10.toString();
                ex10.typecheck(new MyDictionary<>());
                PrgState prg10 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex10, new MyDictionary<>(), new MyHeap());
                IRepository repo10 = new Repository(prg10, "log10.txt");
                Controller ctr10 = new Controller(repo10, true);
                listOfExamples.add(ex10);
                listOfControllers.add(ctr10);

            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
//        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
//                new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(20))),
//                                        new CompStmt(new NewHeapStmt("v", new ValueExp(new IntValue(30))),
//                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("v")))))));
//
//        PrgState prg10 = new PrgState(new MyStack<>(),new MyDictionary<>(),new MyList<>(),ex10,new MyDictionary<>(),new MyHeap());
//        IRepository repo10 = new Repository(prg10, "log10.txt");
//        Controller ctr10 = new Controller(repo10,true);
                IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                        new CompStmt(new NewHeapStmt("a", new ValueExp(new IntValue(22))),
                                                new CompStmt(new forkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));
                lastExampleRun = "ex11:" + ex11.toString();
                ex11.typecheck(new MyDictionary<>());
                PrgState prg11 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex11, new MyDictionary<>(), new MyHeap());
                IRepository repo11 = new Repository(prg11, "log11.txt");
                Controller ctr11 = new Controller(repo11, true);
                listOfExamples.add(ex11);
                listOfControllers.add(ctr11);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
            try {
                IStmt ex12 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("count", new IntType()), new WhileStmt(new RelationalExp("<", new VarExp("count"), new ValueExp(new IntValue(10))),
                                new CompStmt(new forkStmt(new forkStmt(new CompStmt(new NewHeapStmt("a", new VarExp("count")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))), new CompStmt(new AssignStmt("count", new ArithExp('+', new VarExp("count"), new ValueExp(new IntValue(1)))), new CompStmt(new NopStmt(), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

                lastExampleRun = "ex12:" + ex12.toString();
                ex12.typecheck(new MyDictionary<>());
                PrgState prg12 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex12, new MyDictionary<>(), new MyHeap());
                IRepository repo12 = new Repository(prg12, "log12.txt");
                Controller ctr12 = new Controller(repo12, true);
                listOfExamples.add(ex12);
                listOfControllers.add(ctr12);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }
               // TextMenu menu = new TextMenu();


            try {
                IStmt ex13 = new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(
                        new AssignStmt("v", new ValueExp(new IntValue(10))),
                        new CompStmt(new forkStmt(
                                        new CompStmt(
                                                new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))),
                                                new CompStmt(
                                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))),
                                                        new PrintStmt(new VarExp("v"))
                                                )
                                        )
                                ),
                                new CompStmt(
                                        new SleepStmt(10),
                                        new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))
                                )
                        ))
                );
                lastExampleRun = "ex13:" + ex13.toString();
                ex13.typecheck(new MyDictionary<>());
                PrgState prg13 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), ex13, new MyDictionary<>(), new MyHeap());
                IRepository repo13 = new Repository(prg13, "log13.txt");
                Controller ctr13 = new Controller(repo13, true);
                listOfExamples.add(ex13);
                listOfControllers.add(ctr13);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
                listOfErrors.add(e.getMessage());
                System.out.println(lastExampleRun);
            }

            IStmt example15 = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(

                    new AssignStmt("v", new ValueExp(new IntValue(0))),
                    new CompStmt(
                            new WhileStmt(
                                    new RelationalExp("<", new VarExp("v"), new ValueExp(new IntValue(3))),
                                    new CompStmt(
                                            new forkStmt(
                                                    new CompStmt(
                                                            new PrintStmt(new VarExp("v")),
                                                            new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                                                    )
                                            ),
                                            new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))
                                    )
                            ),
                            new CompStmt(
                                    new SleepStmt(5),
                                    new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))
                            )
                    ))
            );

            try {
                example15.typecheck(new MyDictionary<>());
                PrgState prg15 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), example15, new MyDictionary<>(), new MyHeap());
                IRepository repo15 = new Repository(prg15, "log15.txt");
                Controller ctr15 = new Controller(repo15, true);
                listOfExamples.add(example15);
                listOfControllers.add(ctr15);
            } catch (ToyLanguageException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    }

