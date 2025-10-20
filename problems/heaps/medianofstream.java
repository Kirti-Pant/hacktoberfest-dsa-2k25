import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    /*
     * Problem: Median of Data Stream
     *
     * Description:
     * Given a stream of integers, design a data structure that supports the following operations efficiently:
     * 1. addNum(int num): Add a number from the data stream to the data structure.
     * 2. findMedian(): Return the median of all numbers added so far.
     *
     * The median is defined as:
     * - The middle element of the sorted list if the number of elements is odd.
     * - The average of the two middle elements if the number of elements is even.
     * 
     * Approach:
     * Uses two heaps (priority queues):
     * - maxHeap stores the smaller half of the numbers (in reverse order for max heap behavior).
     * - minHeap stores the larger half of the numbers.
     *
     * The heaps are balanced so their size difference is at most one.
     * The median is either the top of maxHeap or the average of tops of both heaps.
     * 
     * Time Complexity:
     * - addNum: O(log n) due to heap insertion and possible rebalancing.
     * - findMedian: O(1) for retrieving median.
     */
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.offer(maxHeap.poll());
        }
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        int[] stream = {5, 15, 1, 3};

        for (int num : stream) {
            mf.addNum(num);
            System.out.println("Added " + num + ", current median is " + mf.findMedian());
        }
    }
}