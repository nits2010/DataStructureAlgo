package Java.Design.WordTimeIterator;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/04/19
 * Description:
 */
public class WordTime implements Cloneable {

    private long timeStamp;
    private String word;
    private int id;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordTime(String word, long timeStamp, int id) {
        this.timeStamp = timeStamp;
        this.word = word;
        this.id = id;
    }


    @Override
    public String toString() {
        return "{" + "id=" + id + "timeStamp=" + timeStamp + ", word='" + word + '}';
    }

    public WordTime clone() {
        return new WordTime(this.word, this.timeStamp, this.id);
    }
}
