package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> saida = new BSTNode<T>();
		
		if(element != null) {
			saida = this.search(element, this.getRoot());
		}
		
		return saida;
	}
	
	private BSTNode<T> search(T element, BSTNode<T> node) {
		if(node.isEmpty() || element.equals(node.getData())) {
			return node;
		} else if(this.comparator.compare(element, node.getData()) < 0){
			return this.search(element, (BSTNode<T>) node.getLeft());
		} else {
			return this.search(element, (BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public void insert(T element) {
		if(element != null) {
			this.insert(element, this.getRoot(), (BSTNode<T>) this.getRoot().getParent());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if(node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
			node.setParent(parent);
			
		} else {
			if(this.comparator.compare(element, node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft(), node);
			} else {
				this.insert(element, (BSTNode<T>) node.getRight(), node);
			}
		}
	}

	@Override
	public T[] sort(T[] array) {
		while(!this.getRoot().isEmpty()) {
			this.remove(this.getRoot().getData());
		}
		
		for(T t : array) {
			this.insert(t);
		}
		
		return this.order();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] reverseOrder() {
		List<T> array = new ArrayList<>();
		this.reverseOrder(this.getRoot(), array);
		return  (T[]) array.toArray(new Comparable[this.size()]);
	}
	
	private void reverseOrder(BSTNode<T> node, List<T> array) {
		if(!node.isEmpty()) {
			this.reverseOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			this.reverseOrder((BSTNode<T>) node.getLeft(), array);
			
		}
	}
	

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
