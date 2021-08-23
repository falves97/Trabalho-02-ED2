package Estructures;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private final List<T> elements;

    public Stack() {
        elements = new ArrayList<>();
    }

    public void push(T data) {
        if (data != null) {
            elements.add(data);
        }
    }

    public T pop() {
        int last = elements.size() - 1;
        T data = elements.get(last);

        elements.remove(last);
        return data;
    }

    public T top() {
        int last = elements.size() - 1;
        T data = elements.get(last);

        return data;
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        return "Stack{" +
                "elements=" + elements +
                ", size=" + elements.size() +
                '}';
    }
}
