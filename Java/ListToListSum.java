package Java;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 31/03/19
 * Description:
 * https://www.careercup.com/question?id=5767007794888704
 * <p>
 * <p>
 * I/P [8, 3, 2, [5, 6, [9]], 6]
 * <p>
 * O/P 8+3+2+2*(5+6+3*(9))+6 => 95
 */
public class ListToListSum {


    static class Element {
        public int val;
        public List<Element> list;
        public boolean isList;


        public Element() {

        }

        public Element(int val, boolean x) {
            this.val = val;
            this.isList = x;
        }

        @Override
        public String toString() {
            String x = "";

            if (list != null && !list.isEmpty()) {
                x = String.valueOf(val);
                String l = list.toString();
                x = x + l;
            }
            return x;
        }
    }

    public static void main(String args[]) {


        List<Element> list = new ArrayList<>();

        Element inner2 = new Element();
        inner2.isList = false;
        inner2.val = 9;

        Element inner1 = new Element();
        inner1.isList = true;
        inner1.list = new ArrayList<>();
        inner1.list.add(new Element(5, false));
        inner1.list.add(new Element(6, false));

        Element element = new Element();
        element.isList = true;
        element.list = new ArrayList<>();
        element.list.add(inner2);


        inner1.list.add(element);


        Element outer = new Element();
        outer.isList = true;
        outer.list = new ArrayList<>();
        outer.list.add(new Element(8, false));
        outer.list.add(new Element(3, false));
        outer.list.add(new Element(2, false));

        Element element1 = new Element();
        element1.isList = true;
        element1.list = new ArrayList<>();
        element1.list.add(inner1);

        outer.list.add(element1);

        outer.list.add(new Element(6, false));

        System.out.println(outer.list);

    }


}
