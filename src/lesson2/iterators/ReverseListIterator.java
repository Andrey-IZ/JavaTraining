package lesson2.iterators;

import java.util.*;

public class ReverseListIterator<T> implements Iterator<T>, Iterable<T> {
    private final List<T> list;
    private int position;

    public ReverseListIterator(List<T> list) {
        this.list = list;
        this.position = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public T next() {
        if (position < 0) {
            throw new NoSuchElementException();
        }
        return list.get(position--);
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
