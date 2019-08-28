package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-20
 * Description:
 * Union Find algirthm with path compression and ranking
 */
public class UnionFindDisjointSets {

    class Set {

        int rank;
        int id;

    }

    private Set parent[];

    public UnionFindDisjointSets(int n) {
        parent = new Set[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = new Set();
            parent[i].id = i; //parent of it self
            parent[i].rank = 1;
        }
    }

    public int findParent(int i) {
        return find(i);
    }

    public void unionBoth(int i, int j) {
        union(i, j);
    }


    /**
     * find the parent of this set id
     *
     * @param i
     * @return O(logn)
     */
    private int find(int i) {
        if (parent[i].id == i)
            return parent[i].id;

        return parent[i].id = find(parent[i].id); //Path compression
    }

    /**
     * O(logn)
     *
     * @param i
     * @param j
     */
    private void union(int i, int j) {

        int pi = find(i);
        int pj = find(j);

        if (pi == pj)
            return;

        if (parent[pi].rank < parent[pj].rank) {
            parent[pi].id = pj; //make pj as parent of pi, this will make pj size always same as we added one more child only
        } else if (parent[pi].rank > parent[pj].rank)
            parent[pj].id = pi; //make pi as parent of pj, this will make pi size always same as we added one more child only
        else {
            parent[pj].id = pi; //make pi as parent of pj, and increase its rank(size)
            parent[pj].rank++;
        }

    }

}
