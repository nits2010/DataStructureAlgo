package Java.companyWise.phonepe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-12
 * Description:
 */
public class SolutionPhonePe {

    public static void main(String[] args) {
        IKeyValueStore<Object, Object> keyValue = new KeyValueStore<>();


        keyValue.put("Nitin", "Gupta");
        keyValue.put(1, 6);

        System.out.println(keyValue.get("Nitin"));
        System.out.println(keyValue.get(1));


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


        IKeyValueStore<Object, Set<Object>> keyValueS = new KeyValueStore<>();


        Set<Object> set1 = new HashSet<>();
        set1.add("TEST");
        set1.add("TEST2");
        keyValueS.put("Nitin", set1);

        Set<Object> set2 = new HashSet<>();
        set2.add(1);
        set2.add(2);
        set2.add(1);


        keyValueS.putS(1, set2);


        System.out.println(keyValueS.get("Nitin"));
        System.out.println(keyValueS.get(1));


    }
}
