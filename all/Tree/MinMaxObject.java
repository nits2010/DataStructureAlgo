package Tree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 27/12/18
 * Description:
 */
public class MinMaxObject<T extends Comparable<? super T>> {

    public T data;

    public MinMaxObject<T> updateData(T newData) {
        this.data = newData;
        return this;
    }
}