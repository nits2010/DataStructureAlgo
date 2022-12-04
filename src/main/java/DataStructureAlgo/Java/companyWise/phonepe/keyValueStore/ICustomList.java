package DataStructureAlgo.Java.companyWise.phonepe.keyValueStore;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-12
 * Description:
 */
public interface ICustomList<T> {


    int size();

    boolean isEmpty();

    void linkFirst(T e);
    void add(T e);

}
