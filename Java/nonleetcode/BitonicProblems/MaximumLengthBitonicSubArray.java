package Java.nonleetcode.BitonicProblems;/* package whatever; // don't place package name! */

import Java.helpers.GenericPrinter;

//MaximumLengthBitonicSubarray
class MaximumLengthBitonicSubArrayHelper {
    private int i;
    private int j;
    private int length = 0;
    private int input[];

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getLength() {
        return length;
    }

    public void setInput(int in[]) {
        this.input = in;
    }

    public boolean MaximumLengthBitonicSubArrayHelper(int input[]) {
        if (input.length == 0)
            return false;

        setInput(input);
        return calculateMaximumLengthBitonicSubarray();
    }

    public void print() {
        GenericPrinter.print(input);
    }

    //false if no subarray exist otherwise true
    private boolean calculateMaximumLengthBitonicSubarray() {

        boolean found = false; // does any BSA found

        boolean directionChange = false; //does direction numberOfWays increase to decrease

        int countOfChange = 0;

        int i = 0;
        directionChange = false;
        int start = i;
        i++;
        for (; i < input.length; i++) {
            if (countOfChange != 0 && countOfChange % 2 == 0) {
                if (this.length < (i - 2 - start + 1)) {
                    this.i = start;
                    this.j = i - 2;
                    this.length = this.j - this.i + 1;
                }
                start = i - 2;
                countOfChange = 0;
            }

            if (input[i] > input[i - 1]) {
                if (directionChange == true) {
                    countOfChange++;
                }
                directionChange = false;
            }
            if (input[i] < input[i - 1]) {
                if (directionChange == false) {
                    countOfChange++;
                }
                directionChange = true;
            }


        }

        if (directionChange == true) {
            if (this.length < (i - 1 - start + 1)) {
                this.i = start;
                this.j = i - 1;
                this.length = this.j - this.i + 1;
            }
        }
        return directionChange;
    }

    public void refersh() {
        i = -1;
        j = -1;
        length = 0;
    }
}

class MaximumLengthBitonicSubArray {
    public static void main(String[] args) {
        MaximumLengthBitonicSubArrayHelper item = new MaximumLengthBitonicSubArrayHelper();
        int input[] = {12, 4, 78, 90, 45, 23, 78, 122, 136, 24, 22, 27, 29, 34, 85, 65, 12, 10, 1};
        int input2[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int input3[] = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1};
        int input4[] = {1, 1, 1, 1, 1, 1, 1, 1};
        int input5[] = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        print(item, input);
        item.refersh();

        print(item, input2);
        item.refersh();

        print(item, input3);
        item.refersh();

        print(item, input4);
        item.refersh();

        print(item, input5);
    }

    private static void print(MaximumLengthBitonicSubArrayHelper item, int[] input) {
        if (item.MaximumLengthBitonicSubArrayHelper(input)) {
            item.print();
            System.out.println(" start : " + item.getI() + " end : " + item.getJ() + " length: " + item.getLength());
        } else {
            item.print();
            System.out.println("Not found");
        }
    }
}