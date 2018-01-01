package candidates;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jorge on 30/03/17.
 *
 * The Safe class is used as a simulation for a bank safe. It needs to satisfy the following conditions:

 The getItems() method should return the content of the safe. There should be no way to modify the content through it or through the object it returns (except with the reflection).
 The addItem(item) method should add a new item to the safe. If the safe is full, IllegalStateException should be thrown.
 The toString() method should return the safe's description in the format "Safe: numOfItems/capacity". For example, if the capacity of the safe is 2 and one item is added to it, it should return: "Safe: 1/2".
 Fix the bugs!

 */
public class Safe {

private Collection<String> items = new ArrayList<String>();
    private int capacity;

    public Safe(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setItems(Collection<String> it){
        this.items = it;
    }

    public Collection<String> getItems() {

        return this.items;
    }

    public void addItem(String item) {
        if (this.items.size() >= this.capacity){
            throw new IllegalStateException();
        }
        this.items.add(item);
    }

    @Override
    public String toString() {
        return String.format("Safe: %d/%d", items.size(), getCapacity());
    }

    public static void main(String[] args) {
        Safe safe = new Safe(2);
        safe.addItem("item");
        safe.addItem("item");
        System.out.println(safe);
    }
}
