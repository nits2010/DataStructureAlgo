package Java.Design.WordTimeIterator;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/04/19
 * Description:
 */
public final class WordTimeIterator implements IWordTimeIterator {

    private WordTime next = null;
    private final long timer;

    private final Iterator<WordTime> wordTimeIterator;
    private final Queue<WordTime> priorityQueue;
    private final Map<String, WordTime> wordTimeMap;
    private final long currentTimeStamp;

    public WordTimeIterator(Iterator<WordTime> wordTimeIterator) {
        this.wordTimeIterator = wordTimeIterator;
        this.wordTimeMap = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingLong(WordTime::getTimeStamp));
        this.timer = 10000;
        currentTimeStamp = 10;//System.currentTimeMillis();

    }


    public WordTimeIterator(Iterator<WordTime> wordTimeIterator, long timer) {
        this.wordTimeIterator = wordTimeIterator;
        this.wordTimeMap = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingLong(WordTime::getTimeStamp));
        this.timer = timer;
        currentTimeStamp = 10;//System.currentTimeMillis();
    }


    @Override
    public boolean hasNext() {

        //Check does our queue has data which collide with current time stamp - timer
        WordTime wordTime = findItem();
        if (wordTime != null) {
            next = wordTime;
            return true;
        }

        //if queue don't have data, then push the data in the queue till iterator has data which window till timer frame
        while (wordTimeIterator.hasNext()) {

            WordTime current = wordTimeIterator.next();

            if (current.getTimeStamp() > (currentTimeStamp - timer) || current.getTimeStamp() < (currentTimeStamp + timer)) {
                wordTimeIterator.remove();

                if (!wordTimeMap.containsKey(current.getWord())) {
                    current.setTimeStamp(current.getTimeStamp() + timer);
                    wordTimeMap.put(current.getWord(), current);
                    priorityQueue.offer(current);
                }


            } else
                continue;

        }


        if (priorityQueue.isEmpty())
            return false;
        else {
            next = priorityQueue.poll();
            return true;
        }
    }

    private WordTime findItem() {
        windowQueueForTime();
        return priorityQueue.isEmpty() ? null : priorityQueue.poll();
    }

    private void windowQueueForTime() {
        while (!priorityQueue.isEmpty() && priorityQueue.peek().getTimeStamp() < currentTimeStamp - timer) {
            WordTime wordTime = priorityQueue.poll();
            wordTimeMap.remove(wordTime.getWord());
        }
    }

    @Override
    public WordTime next() {
        if (hasNext()) {
            return next;
        } else throw new NoSuchElementException();

    }

    @Override
    public void remove() {
        if (next == null)
            return;

        WordTime item = wordTimeMap.get(next.getWord());
        if (item != null) {
            priorityQueue.remove(item);
            wordTimeMap.remove(item.getWord());
        }

    }
}
