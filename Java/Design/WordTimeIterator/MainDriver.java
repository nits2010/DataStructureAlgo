package Java.Design.WordTimeIterator;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/04/19
 * Description:
 * https://www.careercup.com/question?id=5750719415058432
 * TODO: Need to think and correct it
 */
public class MainDriver {
    public static void main(String args[]) {


        WordTime abc = new WordTime("abc", 10, 1);
        WordTime xyz = new WordTime("xyz", 10, 2);
        WordTime pqr = new WordTime("pqr", 11, 3);
        WordTime mno = new WordTime("mno", 12, 4);
        WordTime wy = new WordTime("wy", 13, 5);
        WordTime pq = new WordTime("pq", 14, 6);


        WordTime abc1 = new WordTime("abc", abc.getTimeStamp() + 10, 7);
        WordTime xyz1 = new WordTime("xyz", xyz.getTimeStamp() + 10, 8);
        WordTime pqr1 = new WordTime("pqr", pqr.getTimeStamp() + 10, 9);
        WordTime mno1 = new WordTime("mno", mno.getTimeStamp() + 10, 10);
        WordTime wy1 = new WordTime("wy", wy.getTimeStamp() + 10, 11);
        WordTime pq1 = new WordTime("pq", pq.getTimeStamp() + 10, 12);


        WordTime abc11 = new WordTime("abc", abc.getTimeStamp() - 10, 13);
        WordTime xyz11 = new WordTime("xyz", xyz.getTimeStamp() - 10, 14);
        WordTime pqr11 = new WordTime("pqr", pqr.getTimeStamp() - 10, 15);
        WordTime mno11 = new WordTime("mno", mno.getTimeStamp() - 10, 16);
        WordTime wy11 = new WordTime("wy", wy.getTimeStamp() - 10, 17);
        WordTime pq11 = new WordTime("pq", pq.getTimeStamp() - 10, 18);


        WordTime abc2 = new WordTime("abc", abc.getTimeStamp() + 1, 19);
        WordTime xyz2 = new WordTime("xyz", xyz.getTimeStamp() + 1, 20);
        WordTime pqr2 = new WordTime("pqr", pqr.getTimeStamp() + 1, 21);
        WordTime mno2 = new WordTime("mno", mno.getTimeStamp() + 1, 22);
        WordTime wy2 = new WordTime("wy", wy.getTimeStamp() + 1, 23);
        WordTime pq2 = new WordTime("pq", pq.getTimeStamp() + 1, 24);


        List<WordTime> wordTimes = new ArrayList<>(Arrays.asList(pq11, abc11, xyz11, wy11, mno11, pqr11, abc, xyz, pqr, mno, wy, pq, abc1, xyz1, pqr1, mno1, wy1, pq1, abc2, xyz2, pqr2, mno2, wy2, pq2));


        IWordTimeIterator wordTimeIterator = new WordTimeIterator(wordTimes.iterator(), 10);


        while (wordTimeIterator.hasNext()) {
            WordTime next = wordTimeIterator.next();
            System.out.println(next);
        }
    }
}
