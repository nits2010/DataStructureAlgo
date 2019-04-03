package facebook;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 01/04/19
 * Description:
 */
public class ContactCleaner {


    static class Contact {
        String userName;

        Set<String> contacts;

        boolean isVisited = false;

        int index = -1;


        public Contact(String name, Set<String> set, int index) {
            userName = name;
            contacts = new HashSet<>(set);
            this.index = index;
        }

        @Override
        public String toString() {
            return String.valueOf(index);
        }
    }

    public static void main(String args[]) {

        String [][]contacts =  {{"John", "john@gmail.com", "john@fb.com"},
                {"Dan", "dan@gmail.com", "+1234567"},
                {"john123", "5412312", "john123@skype.com"},
                {"john1985", "5412312", "john@fb.com"},
                {"john19856", "john123@skype.com", "john@fb1.com"},
                {"Dan2", "dan123@gmail.com", "+1234567"},{"Dan3", "dan@gmail.com", "+123456712312"},
                {"Sandy", "sandy@gmail.com", "+123456712"},{"sandy4", "sandy@fb.com", "sandy@gmail.com"}};

        List<Contact> contactList = buildContactList(contacts);
        List<Set<Contact>> result = buildContactGraph(contactList);


        System.out.println(result);
    }

    private static List<Set<Contact>> buildContactGraph(List<Contact> contactList) {

        Map<String, List<Contact>> map = buildContactMap(contactList);

        List<Set<Contact>> result = new ArrayList<>();


        for (List<Contact> contacts : map.values()) {
            Set<Contact> uniques = null;
            for (Contact con : contacts) {

                if (!con.isVisited) {
                    uniques = dfs(map, con, new HashSet<>());
                }

            }
            if (uniques != null)
                result.add(uniques);


        }

        return result;


    }

    private static Set<Contact> dfs(Map<String, List<Contact>> map, Contact con, Set<Contact> uniques) {

        if (!con.isVisited) {
            con.isVisited = true;
            uniques.add(con);

            for (String s : con.contacts) {
                List<Contact> contactList = map.get(s);

                for (Contact c : contactList) {
                    if (!c.isVisited) {
                        dfs(map, c, uniques);
                    }
                }
            }

        }


        return uniques;
    }


    private static Map<String, List<Contact>> buildContactMap(List<Contact> contactList) {

        Map<String, List<Contact>> map = new HashMap<>();

        for (Contact cont : contactList) {

            for (String c : cont.contacts) {

                if (map.containsKey(c)) {
                    map.get(c).add(cont);
                } else {
                    List<Contact> list = new ArrayList<>();
                    list.add(cont);
                    map.put(c, list);
                }

            }
        }
        return map;

    }

    private static List<Contact> buildContactList(String[][] contacts) {

        List<Contact> contactList = new LinkedList<>();

        for (int i = 0; i < contacts.length; i++) {
            Set<String> conts = new HashSet<>();
            for (int j = 1; j < contacts[0].length; j++) {
                conts.add(contacts[i][j]);
            }

            contactList.add(new Contact(contacts[i][0], conts, i));

        }

        return contactList;
    }
}
