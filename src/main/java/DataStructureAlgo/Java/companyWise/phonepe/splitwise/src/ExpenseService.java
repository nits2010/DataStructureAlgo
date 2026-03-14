package DataStructureAlgo.Java.companyWise.phonepe.splitwise.src;

import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2026-03-14
 * Question Title: Expense Service
 * Link: TODO: Add Link
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

public class ExpenseService{

    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits) {
        switch (expenseType) {
            case PERCENT:
                for (Split split : splits) {
                    PercentSplit percentSplit = (PercentSplit) split;
                    split.setAmount((amount*percentSplit.getPercent())/100.0);
                }
                return new PercentExpense(amount, paidBy, splits);
            case EQUAL:
                int totalSplits = splits.size();
                double splitAmount = amount/totalSplits;
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                splits.get(0).setAmount(splitAmount + (amount - splitAmount*totalSplits));
                return new EqualExpense(amount, paidBy, splits);
            default:
                return null;
        }
    }
}