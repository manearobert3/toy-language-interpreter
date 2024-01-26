package ADT;

import Controller.StackIsEmptyException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{

    Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    @Override
    public T pop() throws StackIsEmptyException {
        if (this.stack.isEmpty())
            throw new StackIsEmptyException("The Stack is empty");
        return this.stack.pop();
    }

    @Override
    public void push(T e) {
        this.stack.push(e);
    }

    @Override
    public MyStack<T> deepCopy() {
        MyStack<T> newStack = new MyStack<>();


        Stack<T> newInternalStack = new Stack<>();
        newInternalStack.addAll(this.stack);

        newStack.setStack(newInternalStack);

        return newStack;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return  list;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack=" + stack +
                '}';
    }

    public Stack<T> getStack() {
        return stack;
    }

    public void setStack(Stack<T> stack) {
        this.stack = stack;
    }
}
