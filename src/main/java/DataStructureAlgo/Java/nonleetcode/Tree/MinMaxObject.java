package DataStructureAlgo.Java.nonleetcode.Tree;

/**
 * Author: Nitin Gupta
 * Date: 27/12/18
 * Question Title: Min Max Object
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
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