package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Question Title: Custom Value
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

public class CustomValue<T> implements Serializable {

    private T value;

    public CustomValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomValue<?> that = (CustomValue<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


}
