package DataStructureAlgo.Java.companyWise.phonepe.splitwise.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitExpenseManager{

    private final List<Expense> expenses;
    private final Map<String, User> userMap;
    private final Map<String, Map<String, Double> > balanceSheet;

    private final Map<String, Group> groups;


    public Map<String, Group> getGroups() {
        return groups;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Map<String, Map<String, Double>> getBalanceSheet() {
        return balanceSheet;
    }

    public SplitExpenseManager() {
        expenses = new ArrayList<>();
        userMap = new HashMap<>();
        balanceSheet = new HashMap<>();
        groups = new HashMap<>();
    }

    final public void addUser(User user) {
        userMap.put(user.getId(), user);
        balanceSheet.put(user.getId(), new HashMap<>());
    }

    final public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits, String groupId) {
        final Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits);
        expenses.add(expense);

        final Group group = this.groups.get(groupId);
        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();

            Map<String, Double> balances = balanceSheet.get(paidBy);

            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }

            balances.put(paidTo, balances.get(paidTo) + split.getAmount());


            balances = balanceSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());

            if(group!=null)
                addToGroup(group, paidTo, paidBy, split);

        }
    }
    public void addToGroup( Group group, String paidTo, String paidBy, Split split){
        if(!paidBy.equals(paidTo) && group.getGroupUsers().containsKey(paidTo) && group.getGroupUsers().containsKey(paidBy)) {
            addToBalanceSheet(paidTo, paidBy, split, group.getBalanceSheet());
        }
    }

    public void addToBalanceSheet(String paidTo, String paidBy, Split split, Map<String, Map<String, Double> > balanceSheet){
        Map<String, Double> balances = balanceSheet.get(paidBy);

        if (!balances.containsKey(paidTo)) {
            balances.put(paidTo, 0.0);
        }

        balances.put(paidTo, balances.get(paidTo) + split.getAmount());


        balances = balanceSheet.get(paidTo);
        if (!balances.containsKey(paidBy)) {
            balances.put(paidBy, 0.0);
        }
        balances.put(paidBy, balances.get(paidBy) - split.getAmount());
    }



    public void showBalance(String userId) {
        boolean isEmpty = true;
        for (Map.Entry<String, Double> userBalance : balanceSheet.get(userId).entrySet()) {
            if (userBalance.getValue() != 0) {
                isEmpty = false;
                printBalance(userId, userBalance.getKey(), userBalance.getValue());
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances() {
        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : balanceSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    public void showBalances(Group group) {
        if(group==null)
            System.out.println("Group don't exist");

        boolean isEmpty = true;
        for (Map.Entry<String, Map<String, Double>> allBalances : group.getBalanceSheet().entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                    isEmpty = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }

        if (isEmpty) {
            System.out.println("No balances");
        }
    }

    private void printBalance(String user1, String user2, double amount) {
        String user1Name = userMap.get(user1).getName();
        String user2Name = userMap.get(user2).getName();
        if (amount < 0) {
            System.out.println(user1Name + " owes " + user2Name + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2Name + " owes " + user1Name + ": " + Math.abs(amount));
        }
    }

}


