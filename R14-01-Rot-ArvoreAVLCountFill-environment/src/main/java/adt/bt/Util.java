package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> pivot = node.getRight();

		if (node.getParent() != null) {
			if (node.getParent().getLeft().equals(node))
				node.getParent().setLeft(pivot);
			else
				node.getParent().setRight(pivot);
		}

		pivot.setParent(node.getParent());
		node.setParent(pivot);
		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent(node);
		pivot.setLeft(node);
		return (BSTNode<T>) pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BTNode<T> pivot = node.getLeft();

		if (node.getParent() != null) {
			if (node.getParent().getLeft().equals(node))
				node.getParent().setLeft(pivot);
			else
				node.getParent().setRight(pivot);
		}

		pivot.setParent(node.getParent());
		node.setParent(pivot);
		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);
		pivot.setRight(node);

		return (BSTNode<T>) pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}

	// Trocar

	public static <T extends Comparable<T>> void swap(T[] array, int index1, int index2) {
		T aux = array[index1];
		array[index1] = array[index2];
		array[index2] = aux;
	}
}
