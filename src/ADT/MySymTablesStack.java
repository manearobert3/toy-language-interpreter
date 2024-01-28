package ADT;

import Controller.ToyLanguageException;
import Values.Value;

public class MySymTablesStack  extends MyStack<MyIDictionary<String, Value>> {
    public MySymTablesStack deepCopy() throws ToyLanguageException {
        MySymTablesStack newStack = new MySymTablesStack();
        MyStack<MyIDictionary<String, Value>> tempStack = new MyStack<>();

        while (!this.stack.empty())
            tempStack.push(this.stack.pop());

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.peek());
            try {
                newStack.push(tempStack.pop().deepCopy());
            } catch (ToyLanguageException e) {
                throw new ToyLanguageException(e.getMessage());
            }
        }

        return newStack;
    }

}