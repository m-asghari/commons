package edu.usc.infolab.commons.Queues;

import java.util.ArrayList;
import java.util.HashMap;

public class MinPriorityQueue<T> {
	private HashMap<T, Double> _values;
	private ArrayList<T> _elements;
	private int size;
	
	public MinPriorityQueue(T minElement) {
		_values = new HashMap<T, Double>();
		_elements = new ArrayList<T>();
		_elements.add(minElement);
		_values.put(minElement, 1.0 * Integer.MIN_VALUE);
		size = 0;
	}
	
	private Double Value(int pos) {
		return _values.get(_elements.get(pos));
	}
	
	private int Parent(int pos) {
		return pos / 2;
	}
	
	private int LeftChild(int pos) {
		return (2 * pos);
	}
	
	private int RightChild(int pos) {
		return (2 * pos) + 1;
	}
	
	private boolean IsLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}
	
	private void Swap(int pos1, int pos2) {
		T tmp1;
		T tmp2;
		tmp1 = _elements.get(pos1);
		tmp2 = _elements.get(pos2);
		_elements.remove(pos1);
		_elements.add(pos1, tmp2);
		_elements.remove(pos2);
		_elements.add(pos2, tmp1);
	}
	
	private void MinHeapify(int pos) {
		if (!IsLeaf(pos)) {
			if (Value(pos) > Value(LeftChild(pos)) ||
				Value(pos) > Value(RightChild(pos)) ) {
				if (Value(LeftChild(pos)) < Value(RightChild(pos))) {
					Swap(pos, LeftChild(pos));
					MinHeapify(LeftChild(pos));
				}
				else {
					Swap(pos, RightChild(pos));
					MinHeapify(RightChild(pos));
				}
			}
		}
	}
	
	public Double Value(T element) {
		if (_values.containsKey(element))
			return _values.get(element);
		return 1.0 * Integer.MAX_VALUE;
	}
	
	public boolean IsEmpty() {
		return (size == 0);
	}
	
	public boolean Contains(T element) {
		return _elements.contains(element);
	}
	
	public void Insert(T element, Double value) {
		_values.put(element, value);
		_elements.add(element);
		size++;
		
		int current = size;
		while (Value(current) < Value(Parent(current))) {
			Swap(current, Parent(current));
			current = Parent(current);
		}
	}
	
	public Pair<T, Double> Pop() {
		T popped = _elements.remove(1);
		Double value = _values.remove(popped);
		if (_elements.size() > 1) {
			T last = _elements.remove(_elements.size() - 1);
			_elements.add(1, last);
			MinHeapify(1);
		}
		size--;
		return new Pair<T, Double>(popped, value);
	}
	
	public void Delete(T element) {
		int pos = _elements.indexOf(element);
		Delete(pos);
	}
	
	public void Delete(int pos) {
		T deleted = _elements.remove(pos);
		_values.remove(deleted);
		T last = _elements.remove(_elements.size() - 1);
		_elements.remove(pos);
		_elements.add(pos, last);
		size--;
		MinHeapify(pos);
	}
	
	public void Update(T element, Double value) {
		Delete(element);
		Insert(element, value);		
	}
	
	public void MinHeap() {
		for (int pos = size / 2; pos >= 1; pos--) {
			MinHeapify(pos);
		}
	}
	
	public void Print() {
		for (int i = 1; i < size / 2; i++) {
			System.out.println(String.format("Parent: %s, LeftChild: %s, RightChild: %s", 
					_elements.get(i).toString(), _elements.get(2 * i).toString(), _elements.get(2 * i + 1).toString()));
			System.out.println();
		}
	}
}
