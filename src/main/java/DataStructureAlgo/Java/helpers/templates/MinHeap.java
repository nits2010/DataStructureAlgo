package DataStructureAlgo.Java.helpers.templates;

/**
 * Author: Nitin Gupta
 * Date: 8/31/2024
 * Question Category: [easy | medium | hard ]
 * Description: Max heap construction in O(n)
 */
public class MinHeap {

    int[] heap;
    int size;

    public MinHeap(int[] elements) {
        heap = elements;
        size = heap.length;

        buildHeap((size - 1) / 2); //last element parent
    }

    private void buildHeap(int index) {
        for (int i = index; i >= 0; i--)
            heapify(i);
    }

    private void heapify(int index) {
        if (index < 0 || index >= size)
            return;


        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < size && heap[left] < heap[smallest])
            smallest = left;

        if (right < size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest != index) {
            //swap
            int temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;

            heapify(smallest);
        }

    }

    public int peek() {
        if (heap != null && size > 0)
            return heap[0];
        return Integer.MIN_VALUE;
    }

    public int poll() {

        if (heap != null && size > 0) {
            int min = heap[0];
            heap[0] = heap[size - 1];

            heapify(0);
            size--;//decrease heap size
            return min;
        }
        return Integer.MIN_VALUE;
    }

    public void offer(int element) {

        if (heap != null && size >= 0) {
            size++; //increase heap size
            int i = size - 1;

            //find its position to be placed
            while (i > 0 && element < heap[(i - 1) / 2]) {
                heap[i] = heap[(i - 1) / 2];
                i = (i - 1) / 2;

            }
            heap[i] = element;

        }

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}