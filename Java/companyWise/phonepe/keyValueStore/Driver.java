package Java.companyWise.phonepe.keyValueStore;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class Driver {

    public static void main(String[] args) {
        test();

        testL();

        testS();


    }


    public static void test() {
        IKeyValueStore<Object, Object> keyValue = new KeyValueStore<>();


        keyValue.put("Nitin", "Gupta");
        keyValue.put(1, 6);
        keyValue.put(3, 9);

        System.out.println(keyValue.get("Nitin"));
        System.out.println(keyValue.get(1));
        System.out.println(keyValue.get(3));

    }

    public static void testL() {

        IKeyValueStore<Object, ICustomList<Object>> keyValueL = new KeyValueStore<>();
        ICustomList<Object> list = new CustomList<>();
        list.add("Gutpa");
        list.add("Test");


        keyValueL.putL("Nitin", list);

        ICustomList<Object> list2 = new CustomList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        keyValueL.putL(1, list2);


        System.out.println(keyValueL.getL("Nitin"));
        System.out.println(keyValueL.getL(1));
    }

    public static void testS() {

        IKeyValueStore<Object, Set<Object>> keyValueS = new KeyValueStore<>();


        Set<Object> set1 = new HashSet<>();
        set1.add("SortDates");
        set1.add("TEST2");
        set1.add("TEST2");
        set1.add("");
        keyValueS.put("Nitin", set1);

        Set<Object> set2 = new HashSet<>();
        set2.add(1);
        set2.add(2);
        set2.add(1);


        keyValueS.putS(1, set2);


        System.out.println(keyValueS.getS("Nitin"));
        System.out.println(keyValueS.getS(1));
    }
}
