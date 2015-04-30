package edu.usc.infolab.commons.Queues;

public class Test {

	public static void main(String[] args) {
		MinPriorityQueue<Integer> queue = new MinPriorityQueue<Integer>(-1);
		queue.Insert(10, 10.0);
		queue.Insert(5, 5.0);
		queue.Insert(6, 6.0);
		queue.Insert(8, 8.0);
		queue.Insert(4, 4.0);
		queue.Insert(9, 9.0);
		queue.Insert(11, 11.0);
		queue.Insert(13, 13.0);
		queue.Insert(2, 2.0);
		queue.Insert(1, 1.0);
		queue.Insert(7, 7.0);
		queue.Print();
	}

}
