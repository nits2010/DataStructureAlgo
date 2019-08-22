package Java;/* package whatever; // don't place package name! */

/**
 * https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
 * <p>
 * Given an array of n integers, find the 3 elements such that a[i] < a[j] < a[k] and i < j < k in 0(n) time. If there are multiple such triplets, then print any one of them.
 * Examples:
 * <p>
 * Input:  arr[] = {12, 11, 10, 5, 6, 2, 30}
 * Output: 5, 6, 30
 * <p>
 * Input:  arr[] = {1, 2, 3, 4}
 * Output: 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4
 * <p>
 * Input:  arr[] = {4, 3, 2, 1}
 * Output: No such triplet
 */
class SortedSubsequenceSize3Exercise1SolutionSet {
    private int i = -1, j = -1, k = -1;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getK() {
        return k;
    }

    public boolean findTriplet3(int input[]) {
        if (input.length <= 2)
            return false;

        return FindSortedSubsequenceOfSize3(input);
    }

    private boolean hasFound() {
        if (i == -1 || j == -1 || k == -1)
            return false;
        return true;
    }

    private boolean FindSortedSubsequenceOfSize3(int input[]) {

        boolean isJFound = false;
        int tempItem = -1;
        int iItem = -1;
        int jItem = -1;

        // let first is tempI
        tempItem = input[0];


        // for rest of the elements
        for (int i = 1; i < input.length; i++) {

            //does current element is for I
            if (input[i] < tempItem) {
                //	System.out.println(k + " " + i );
                tempItem = input[i];
            } else if (input[i] == tempItem)
                continue;
            else if (!isJFound || input[i] > jItem) {
                iItem = tempItem;
                jItem = input[i];
                isJFound = true;
            } else {
                this.i = iItem;
                this.j = jItem;
                this.k = input[i];
                //	System.out.println(k + " " + i );
                break;
            }

        }


        return hasFound();
    }

    public void refresh() {
        i = j = k = -1;
    }

}

/* Name of the class has to be "Main" only if the class is public. */
class SortedSubsequenceSize3Exercise1 {
    public static void main(String[] args) throws java.lang.Exception {
        int arr1[] = {12, 11, 10, 5, 6, 2, 30};
        int arr2[] = {1, 2, 3, 4};
        int arr3[] = {4, 3, 1, 2};
        int arr5[] = {5, 13, 6, 10, 3, 7, 2};
        int arr4[] = {12, 11, 10, 5, 6, 2, 30};
        int arr6[] = {5, 13, 14, 10, 3, 11, 2};
        int arr7[] = {5, 13, 14, 15, 12, 7, 2};
        int arr8[] = {8, 7, 6, 5, 4, 3, 8, 7};

        SortedSubsequenceSize3Exercise1SolutionSet s = new SortedSubsequenceSize3Exercise1SolutionSet();

        if (s.findTriplet3(arr7)) {
            System.out.println("arr7" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr7" + "not found");
        }

        if (s.findTriplet3(arr6)) {
            System.out.println("arr6" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr6" + "not found");
        }

        if (s.findTriplet3(arr8)) {
            System.out.println("arr8" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr8" + "not found");
        }

        if (s.findTriplet3(arr5)) {
            System.out.println("arr5" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr5" + "not found");
        }
        s.refresh();
        if (s.findTriplet3(arr4)) {
            System.out.println("arr4" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr4" + "not found");
        }
        s.refresh();

        if (s.findTriplet3(arr3)) {
            System.out.println("arr3" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr3" + "not found");
        }
        s.refresh();

        if (s.findTriplet3(arr2)) {
            System.out.println("arr2" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr2" + "not found");
        }
        s.refresh();

        if (s.findTriplet3(arr1)) {
            System.out.println("arr1" + "i: " + s.getI() + " j : " + s.getJ() + " k : " + s.getK());
        } else {
            System.out.println("arr1" + "not found");
        }
        s.refresh();


        // your code goes here
    }
}