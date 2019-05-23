import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class CircularBufferImpl<T> implements CircularBufferInterface<T> {

    private T[] buffer;
    private int headIndex;
    private int tailIndex;

    @SuppressWarnings("unchecked")
    public CircularBufferImpl(int size) {
        buffer = (T[])new Object[size];
    }

    @Override
    public void put(T t) {
        if (!isEmpty() && (headIndex == tailIndex)) throw new RuntimeException("The buffer is full!");
        buffer[headIndex] = t;
        headIndex = increase(headIndex);
    }

    @Override
    public T get() {
        if (isEmpty() && (headIndex == tailIndex)) throw new RuntimeException("The buffer is empty!");
        T value = buffer[tailIndex];
        buffer[tailIndex] = null;
        tailIndex = increase(tailIndex);
        return value;
    }

    @Override
    public Object[] toObjectArray() {
        List<Object> objects = new ArrayList<>();
        while (!isEmpty()) {
            objects.add(get());
        }
        return objects.toArray();
    }

    @Override
    public T[] toArray() {
        return toArray(asList());
    }

    @Override
    public List<T> asList() {
        List<T> elements = new ArrayList<>();
        while (!isEmpty()) {
            elements.add(get());
        }
        return elements;
    }

    @Override
    public void addAll(List<? extends T> toAdd) throws RuntimeException {
        toAdd.forEach(this::put);
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        List<T> elements = asList();
        elements.sort(comparator);
        elements.forEach(this::put);
    }

    @Override
    public boolean isEmpty() {
        for (T t : buffer) {
            if (t != null) return false;
        }
        return true;
    }

    private int increase(int intToIncrease) {
        return (intToIncrease == buffer.length - 1) ? 0 : intToIncrease + 1;
    }

    @SuppressWarnings("unchecked")
    private T[] toArray(Collection<T> c, T[] a) {
        return c.size()>a.length ?
                c.toArray((T[]) Array.newInstance(a.getClass().getComponentType(), c.size())) :
                c.toArray(a);
    }

    @SuppressWarnings("unchecked")
    private T[] toArray(Collection<T> c, Class klass) {
        return toArray(c, (T[])Array.newInstance(klass, c.size()));
    }

    private T[] toArray(Collection<T> c) {
        return toArray(c, c.iterator().next().getClass());
    }
}
