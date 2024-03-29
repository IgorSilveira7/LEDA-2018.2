package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if(this.root.isEmpty()) {
			return -1;
		}
		return this.height(this.getRoot());
	}
	
	protected int height(BSTNode<T> node) {
		if(node.isEmpty()) {
			return -1;
		}
		
		return Math.max(this.height((BSTNode<T>) node.getRight()), this.height((BSTNode<T>) node.getLeft())) + 1;
		
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
		} else if(element.compareTo(node.getData()) < 0){
			return this.search(element, (BSTNode<T>) node.getLeft());
		} else {
			return this.search(element, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.getRoot());
		}
	}

	@SuppressWarnings("unchecked")
	protected BSTNode<T> insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
		} else if (element.compareTo(node.getData()) < 0) {
			node = this.insert(element, (BSTNode<T>) node.getLeft());
		} else {
			node = this.insert(element, (BSTNode<T>) node.getRight());
		}
		return node;
	}

	@Override
	public BSTNode<T> maximum() {
		if(this.getRoot().isEmpty()) {
			return null;
		}
		return this.maximum(this.getRoot());
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {		
		while(!node.getRight().isEmpty()) {
			node = (BSTNode<T>) node.getRight();
		}
		
		return node;
	}


	@Override
	public BSTNode<T> minimum() {
		if(this.getRoot().isEmpty()) {
			return null;
		}
		return this.minimum(this.getRoot());
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		while(!node.getLeft().isEmpty()) {
			node = (BSTNode<T>) node.getLeft();
		}
		
		return node;
	}


	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if(node.isEmpty()) {
			return null;
		} else if(!node.getRight().isEmpty()) {
			return this.minimum((BSTNode<T>) node.getRight());
		} else if(node.getParent().getLeft().equals(node)) {
			return (BSTNode<T>) node.getParent();
		}
		
		while(!node.isEmpty() && this.verifyRight(node)) {
			node = (BSTNode<T>) node.getParent();
		}
		
		return (BSTNode<T>) node.getParent();
		
	}
	
	private boolean verifyRight(BSTNode<T> node) {
		if(node.getParent() == null || node.isEmpty()) {
			return false;
		}
		
		return node.getParent().getRight().equals(node);
	}
	

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if(node.isEmpty()) {
			return null;
		} else if(!node.getLeft().isEmpty()) {
			return this.maximum((BSTNode<T>) node.getLeft());
		} else if(node.getParent().getRight().equals(node)) {
			return (BSTNode<T>) node.getParent();
		}
		
		while(!node.isEmpty() && this.verifyLeft(node)) {
			node = (BSTNode<T>) node.getParent();
		}
		
		return (BSTNode<T>) node.getParent();
	}
	
	private boolean verifyLeft(BSTNode<T> node) {
		if(node.getParent() == null || node.isEmpty()) {
			return false;
		}
		
		return node.getParent().getLeft().equals(node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		remove(node);
	}

	protected BSTNode<T> remove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (hasOneChild(node)) {
				if (node != root) {
					if (node.getParent().getLeft() == node) {
						if (node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					} else {
						if (node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						}
					}
				} else {
					if (root.getLeft().isEmpty())
						root = (BSTNode<T>) root.getRight();
					else {
						root = (BSTNode<T>) root.getLeft();
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				node.setData(sucessor.getData());
				remove(sucessor);
			}
		}
		return node;
	}
	
	protected boolean hasOneChild(BSTNode<T> node) {
		return ((!node.getRight().isEmpty() && node.getLeft().isEmpty()) || (node.getRight().isEmpty() && !node.getLeft().isEmpty()));
	}


	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		List<T> array = new ArrayList<>();
		this.preOrder(this.getRoot(), array);
		return  (T[]) array.toArray(new Comparable[this.size()]);
		
	}
	
	private void preOrder(BSTNode<T> node, List<T> array) {
		if(!node.isEmpty()) {
			array.add(node.getData());
			this.preOrder((BSTNode<T>) node.getLeft(), array);
			this.preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		List<T> array = new ArrayList<>();
		this.order(this.getRoot(), array);
		return  (T[]) array.toArray(new Comparable[this.size()]);
	}
	
	private void order(BSTNode<T> node, List<T> array) {
		if(!node.isEmpty()) {
			this.order((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			this.order((BSTNode<T>) node.getRight(), array);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		List<T> array = new ArrayList<>();
		this.postOrder(this.getRoot(), array);
		return  (T[]) array.toArray(new Comparable[this.size()]);
	}
	
	private void postOrder(BSTNode<T> node, List<T> array) {
		if(!node.isEmpty()) {
			this.postOrder((BSTNode<T>) node.getLeft(), array);
			this.postOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
