package DataStructureAlgo.Java.companyWise.phonepe.splitwise.src;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Group
 * Link: https://leetcode.com/problems/group/
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

public class Group {

    private final String groupId;
    private final Set<User> group;

    private final Map<String, User> groupUsers;

    private Map<String, Map<String, Double> > balanceSheet;

    public Group() {
        group = new HashSet<>();
        groupId = String.valueOf(UUID.randomUUID());
        balanceSheet = new HashMap<>();
        groupUsers = new HashMap<>();
    }

    public Map<String, User> getGroupUsers() {
        return groupUsers;
    }

    public Set<User> getGroup() {
        return group;
    }

    public void addUser(User user){
        group.add(user);
        groupUsers.put(user.getId(), user);
    }

    public String getGroupId() {
        return groupId;
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return balanceSheet;
    }
}
