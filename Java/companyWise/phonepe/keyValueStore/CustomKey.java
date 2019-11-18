package Java.companyWise.phonepe.keyValueStore;

import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public class CustomKey<T> implements Serializable {

    private long id;
    private T key;

    public CustomKey(T key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomKey customKey = (CustomKey) o;
        return id == customKey.id &&
                Objects.equals(key, customKey.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, key);
    }
}
