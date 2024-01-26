package Types;

import Values.Value;
public interface Type {
    Value defaultValue();
    Type deepCopy();
    boolean equals(Type secondType);
}
