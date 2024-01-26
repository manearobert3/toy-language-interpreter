package View.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HeapTableView {
    private final SimpleIntegerProperty first;
    private final SimpleStringProperty second;

    public HeapTableView(int first, String second) {
        this.first = new SimpleIntegerProperty(first);
        this.second = new SimpleStringProperty(second);
    }

    public int getFirst() {
        return first.get() ;
    }

    public String getValue() {
        return second.get();
    }
}
