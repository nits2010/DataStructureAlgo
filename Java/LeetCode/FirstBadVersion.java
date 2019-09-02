package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-02
 * Description: 278. First Bad Version
 * <p>
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Example:
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * <p>
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 */
public class FirstBadVersion {

    public static void main(String[] args) {
        test(5, 4, 4);
        test(5, 2, 2);
        test(5, 8, -1);
    }

    private static void test(int n, int badVersion, int expected) {
        FirstBadVersionBinarySearch versionControl = new FirstBadVersionBinarySearch(badVersion);
        System.out.println("Expected :" + expected);
        System.out.println(" Obtained : " + versionControl.firstBadVersion(n));


    }


}

class VersionControl {
    final int firstBadVersion;

    public VersionControl(int firstBadVersion) {
        this.firstBadVersion = firstBadVersion;
    }

    boolean isBadVersion(int version) {
        if (version < firstBadVersion)
            return false;
        if (version >= firstBadVersion)
            return true;
        return true;
    }
}

class FirstBadVersionBinarySearch extends VersionControl {

    public FirstBadVersionBinarySearch(int firstBadVersion) {
        super(firstBadVersion);
    }

    public int firstBadVersion(int n) {
        int s = 1, e = n;

        while (s < e) {
            int mid = s + (e - s) / 2;

            if (isBadVersion(mid)) {

                e = mid;
            } else
                s = mid + 1;

        }
        return s;
    }

}
