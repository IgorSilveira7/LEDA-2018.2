package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(this.isFull()) {
			throw new StackOverflowException();
		} else {
			if(element != null) {
				this.top.insertFirst(element);
			}
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		T result; 
		
		if(this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			result = ((SingleLinkedListImpl<T>)this.top).getHead().getData();
			this.top.removeFirst();
		}
		return result;
	}

	@Override
	public T top() {
		return ((SingleLinkedListImpl<T>)this.top).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return (this.top.size() == this.size);
	}

}
