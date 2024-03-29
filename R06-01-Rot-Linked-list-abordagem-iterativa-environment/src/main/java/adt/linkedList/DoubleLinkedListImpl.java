package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}
	
	@Override
	public T search(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.head;
		DoubleLinkedListNode<T> auxLast = (DoubleLinkedListNode<T>) this.last;
		
		while(!(auxHead.equals(auxLast)) && !(auxHead.getNext().equals(auxLast)) 
				&& !(auxHead.getData().equals(element)) && !(auxLast.getData().equals(element))) {
			
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}
		
		T result = null;
		
		if(auxHead.getData().equals(element)) {
			result = auxHead.getData();
		}
		
		if(auxLast.getData().equals(element)) {
			result = auxLast.getData();
		}
		
		return result;
	}
	
	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, null, null);
		newLast.setNext(new DoubleLinkedListNode<>());
		newLast.setPrevious(this.last);
		this.last.setNext(newLast);
		
		if(this.last.isNIL()) {
			this.head = newLast;
		}
		
		this.last = newLast;
		
	}
	
	@Override
	public void remove(T element) {
		if(this.head.getData().equals(element)) {
			this.removeFirst();
		} else {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) this.head;
			while(!aux.isNIL() && !aux.getData().equals(element)) {
				aux = (DoubleLinkedListNode<T>) aux.getNext();
			}
			if(!aux.isNIL()) {
				aux.getPrevious().setNext(aux.getNext());
				((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, null, null);
		newHead.setNext(this.head);
		newHead.setPrevious(new DoubleLinkedListNode<>());
		((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);
		
		if(this.head.isNIL()) {
			this.last = newHead;
		}
		
		this.head = newHead;
	}

	@Override
	public void removeFirst() {
		if(!this.head.isNIL()) {
			this.head = this.head.getNext();
			if(this.head.isNIL()) {
				this.last = (DoubleLinkedListNode<T>) this.head;
			}
		}
		
		((DoubleLinkedListNode<T>) this.head).setPrevious(new DoubleLinkedListNode<>());
	}

	@Override
	public void removeLast() {
		if(!this.last.isNIL()) {
			this.last = this.last.getPrevious();
			if(this.last.isNIL()) {
				this.head = this.last;
			}
		}
		
		this.last.setNext(new DoubleLinkedListNode<>());
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
