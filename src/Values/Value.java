package Values;
import Types.*;
public interface Value {
    Type getType();
    Value deepCopy();
}
