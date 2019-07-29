package Java.companyWise.facebook;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 01/04/19
 * Description: https://www.careercup.com/question?id=5630954857562112
 * Given a list of Contacts, where each contact consists of a contact ID and a list of email IDs.
 * Output a unique list of contacts by removing duplicates.
 * Two contacts are considered to be the same, if they share at least one email ID.
 * <p>
 * https://www.geeksforgeeks.org/find-same-contacts-in-a-list-of-contacts/
 * <p>
 * <p>
 * Given a list of contacts containing username, email and phone number in any order. Identify the same contacts (i.e., same person having many different contacts) and output the same contacts together.
 * Notes:
 * 1) A contact can store its three fields in any order, i.e., phone number can appear before username or username can appear before phone number.
 * <p>
 * 2) Two contacts are same if they have either same username or email or phone number.
 * <p>
 * Example:
 * <p>
 * Input: contact[] =
 * { {"Gaurav", "gaurav@gmail.com", "gaurav@gfgQA.com"},
 * { "Lucky", "lucky@gmail.com", "+1234567"},
 * { "gaurav123", "+5412312", "gaurav123@skype.com"}.
 * { "gaurav1993", "+5412312", "gaurav@gfgQA.com"}
 * }
 * Output:
 * 0 2 3
 * 1
 * contact[2] is same as contact[3] because they both have same
 * contact number.
 * contact[0] is same as contact[3] because they both have same
 * e-mail address.
 * Therefore, contact[0] and contact[2] are also same.
 */
public class ContactCleaner {


    public static void main(String args[]) {

        String[][] contacts = {{"John", "john@gmail.com", "john@fb.com"},
                {"Dan", "dan@gmail.com", "+1234567"},
                {"john123", "5412312", "john123@skype.com"},
                {"john1985", "5412312", "john@fb.com"},
                {"john19856", "john123@skype.com", "john@fb1.com"},
                {"Dan2", "dan123@gmail.com", "+1234567"}, {"Dan3", "dan@gmail.com", "+123456712312"},
                {"Sandy", "sandy@gmail.com", "+123456712"}, {"sandy4", "sandy@fb.com", "sandy@gmail.com"}};

        List<Set<Contact>> result = ContactCleaner1.clean(contacts);

        System.out.println(result);
    }

}


class Contact {
    String userName;

    Set<String> contacts;

    boolean isVisited = false;

    int index;


    public Contact(String name, Set<String> set, int index) {
        userName = name;
        contacts = new HashSet<>(set);
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return index == contact.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }
}


class ContactCleaner1 {


    public static List<Set<Contact>> clean(String[][] contacts) {

        List<Contact> contactList = buildContactList(contacts);
        List<Set<Contact>> result = clean(contactList);
        return result;
    }

    /**
     * Build contact list contains user name vs list of contact this user has
     *
     * @param contacts
     * @return
     */
    private static List<Contact> buildContactList(String[][] contacts) {
        List<Contact> response = new ArrayList<>(contacts.length);

        for (int i = 0; i < contacts.length; i++) {

            Set<String> set = new HashSet<>();
            String username = null;
            for (int j = 0; j < contacts[0].length; j++) {

                if (isUserName(contacts[i][j])) {
                    username = contacts[i][j];
                    continue;
                }
                set.add(contacts[i][j]);

            }
            response.add(new Contact(username, set, i));
        }

        return response;
    }

    /**
     * check given string is simple string (username)
     *
     * @param s
     * @return
     */
    private static boolean isUserName(String s) {

        if (s.contains("@"))
            return false;
        if (s.contains("+"))
            return false;
        if (s.matches(".*[0-9].*"))
            return false;

        return true;
    }

    private static List<Set<Contact>> clean(List<Contact> contactList) {

        Map<String, List<Contact>> map = buildContactGraphAdjList(contactList);

        List<Set<Contact>> result = new ArrayList<>();


        for (List<Contact> contacts : map.values()) {
            Set<Contact> uniques = new HashSet<>();
            for (Contact con : contacts) {

                if (!con.isVisited) {
                    dfs(map, con, uniques);
                }

            }
            if (!uniques.isEmpty())
                result.add(uniques);


        }

        return result;


    }

    private static void dfs(Map<String, List<Contact>> map, Contact con, Set<Contact> uniques) {

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


    }

    /**
     * with given contact list, build graph from each node may have any contact
     *
     * @param contactList
     * @return
     */
    private static Map<String, List<Contact>> buildContactGraphAdjList(List<Contact> contactList) {

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


}