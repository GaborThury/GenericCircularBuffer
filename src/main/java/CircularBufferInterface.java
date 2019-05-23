import java.util.Comparator;
import java.util.List;

public interface CircularBufferInterface <T> {
    void put(T t);
    T get();
    Object[] toObjectArray();
    T[] toArray();
    List<T> asList();
    void addAll(List<? extends T> toAdd);
    void sort(Comparator<? super T> comparator);
    boolean isEmpty();
}
