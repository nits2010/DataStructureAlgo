package Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta
 * Date: 27/12/18
 * Description:
 */
public class MinMaxObject<T extends Comparable<? super T>> {

    public T data;

    public MinMaxObject() {
    }

    public MinMaxObject(T data) {
        this.data = data;
    }

    public MinMaxObject<T> updateData(T newData) {
        this.data = newData;
        return this;
    }
}