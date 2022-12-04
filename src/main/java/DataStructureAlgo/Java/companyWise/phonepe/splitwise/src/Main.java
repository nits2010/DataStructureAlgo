package DataStructureAlgo.Java.companyWise.phonepe.splitwise.src;

import java.util.*;

// Main class should be named 'Solution'
class Solution {
    public static void main(String[] args) {
        SplitExpenseManager expenseManager = new SplitExpenseManager();

        expenseManager.addUser(new User("u1", "User1", "g@g.com", "1234567890"));
        expenseManager.addUser(new User("u2", "User2", "s@g.com", "1234567891"));
        expenseManager.addUser(new User("u3", "User3", "h@g.com", "1234567892"));
        expenseManager.addUser(new User("u4", "User4", "m@g.com", "1234567893"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "GROUP":
                    Group userGroup = new Group();
                    for (String userId : commands){
                        if(expenseManager.getUserMap().containsKey(userId)) {
                            userGroup.addUser(expenseManager.getUserMap().get(userId));
                            userGroup.getBalanceSheet().put(userId, new HashMap<>());
                        }
                    }
                    expenseManager.getGroups().put(userGroup.getGroupId(), userGroup);
                    System.out.println("Your group id: "+userGroup.getGroupId());
                    break;
                case "SHOW_GROUP_BALANCE":
                    if (commands.length == 1) {
                        System.out.println("Group id needed");

                    }else {
                        String id = commands[1];
                        Group group = expenseManager.getGroups().get(id);
                        expenseManager.showBalances(group);
                    }
                    break;

                case "SHOW":
                    if (commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    String groupId = scanner.nextLine();
                    List<Split> splits = new ArrayList<>();

                    switch (expenseType) {
                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.getUserMap().get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, groupId);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new PercentSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, groupId);
                            break;
                    }
                    break;
            }
        }
    }

}





