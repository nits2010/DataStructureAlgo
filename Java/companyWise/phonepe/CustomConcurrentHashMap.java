package Java.companyWise.phonepe;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class CustomConcurrentHashMap<Key, Value> implements ICustomMap<Key, Value>, Serializable {


    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int DEFAULT_CONCURRENCY_LEVEL = 16;
    private static final float LOAD_FACTOR = 0.75f;


//    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    @Override
    public int size() {

        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public Value put(Key key, Value value) {
        return null;
    }
}
