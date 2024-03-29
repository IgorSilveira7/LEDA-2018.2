package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()) {
			throw new QueueOverflowException();
		} else if(this.isEmpty()) {
			tail = 0;
			head = 0;
		} else {
			tail = (tail + 1) % array.length;
		}
		
		array[tail] = element;
		elements++;
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T result;
		
		if(this.isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			result = array[head];
			head = (head + 1) % array.length;
			elements--;
		}
		
		return result;
	}

	@Override
	public T head() {
		T result = null;
		
		if(!this.isEmpty()) {
			result = array[head];
		}
		
		return result;
	}

	@Override
	public boolean isEmpty() {
		boolean result = false;
		
		if(elements == 0) {
			result = true;
		}
		
		return result;
	}

	@Override
	public boolean isFull() {
		boolean result = false;
		
		if(elements == array.length) {
			result = true;
		}
		
		return result;
	}

}
