package adt.linkedList;


public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return (this.head.isNIL());
	}

	@Override
	public int size() {
		Integer result = 0;
		
		if(!isEmpty()) {
			SingleLinkedListNode<T> node = this.head;
			
			while(!node.isNIL()) {
				result++;
				node = node.getNext();
			}
		}
		
		return result;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = this.head;
		
		while(!node.isNIL() && !node.getData().equals(element)) {
			node = node.getNext();
		}
		
		return node.getData();
	}

	@Override
	public void insert(T element) {
		if(this.head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.head);
			this.head = newHead;
			return;
		}
		
		SingleLinkedListNode<T> node = this.head;
		
		while(!node.getNext().isNIL()) {
			node = node.getNext();
		}
		
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, node.getNext());
		node.setNext(newNode);
	}

	@Override
	public void remove(T element) {
		if(head.getData().equals(element)) {
			this.head = this.head.getNext();
		} else {
			SingleLinkedListNode<T> node = this.head;
			
			while(!node.getNext().getData().equals(element)) {
				node = node.getNext();
			}
			
			node.setNext(node.getNext().getNext());
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		
		SingleLinkedListNode<T> aux = this.head;
		
		int i = 0;
		
		while(!aux.isNIL()) {
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
