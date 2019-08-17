package Java.companyWise.phonepe.keyValueStore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
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
