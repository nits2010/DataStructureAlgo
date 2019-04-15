package Java.Design.MultiLevelCache;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/04/19
 * Description:
 */
public class CacheKey {

    private final int levelId;

    public CacheKey(int levelId) {
        this.levelId = levelId;
    }

    public int getLevelId() {
        return levelId;
    }

}
