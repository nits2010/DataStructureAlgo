package Java.Design.MultiLevelCache;

import java.util.Objects;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/04/19
 * Description:
 */
public class Driver {


    static class MyCacheKey extends CacheKey {

        String key;

        public MyCacheKey(int levelId, String key) {
            super(levelId);
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyCacheKey that = (MyCacheKey) o;
            return Objects.equals(key, that.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    static class MyCacheValue<T> {
        T value;

        public MyCacheValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String args[]) {

        IMultiLevelCache<MyCacheKey, MyCacheValue<String>> multiLevelCache = new MultiLevelCache<>(3, 50);


        multiLevelCache.add(new MyCacheKey(29, "Key1"), new MyCacheValue<>("Key1Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key1")));
        multiLevelCache.add(new MyCacheKey(80, "Key2"), new MyCacheValue<>("Key2Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key2")));
        multiLevelCache.add(new MyCacheKey(900, "Key3"), new MyCacheValue<>("Ke31Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key3")));
        multiLevelCache.add(new MyCacheKey(290, "Key4"), new MyCacheValue<>("Key4Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key4")));
        multiLevelCache.add(new MyCacheKey(12, "Key5"), new MyCacheValue<>("Key5Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key5")));
        multiLevelCache.add(new MyCacheKey(9, "Key6"), new MyCacheValue<>("Key6Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key6")));
        multiLevelCache.add(new MyCacheKey(121, "Key7"), new MyCacheValue<>("Key7Value"));
        System.out.println(multiLevelCache.get(new MyCacheKey(20, "Key7")));


        System.out.println(multiLevelCache.remove(new MyCacheKey(20, "Key1")));

        multiLevelCache.show();

    }
}
