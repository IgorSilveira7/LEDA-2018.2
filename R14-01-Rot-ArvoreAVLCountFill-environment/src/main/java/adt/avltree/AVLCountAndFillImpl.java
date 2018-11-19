package adt.avltree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   @Override
   protected void rebalance(BSTNode<T> node) {
      int height = calculateBalance(node);
      if (height > 1) {
         if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
            Util.rightRotation((BSTNode<T>) node.getRight());
            if (this.getRoot() == node) {
               this.root = (BSTNode<T>) node.getRight();
            }
            Util.leftRotation(node);
            this.RLcounter++;
         } else {
            if (this.getRoot() == node) {
               this.root = (BSTNode<T>) node.getRight();
            }
            Util.leftRotation(node);
            this.RRcounter++;
         }
      } else if (height < -1) {
         if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
            Util.leftRotation((BSTNode<T>) node.getLeft());
            if (this.getRoot() == node) {
               this.root = (BSTNode<T>) node.getLeft();
            }
            Util.rightRotation(node);
            this.LRcounter++;
         } else {
            if (this.getRoot() == node) {
               this.root = (BSTNode<T>) node.getLeft();
            }
            Util.rightRotation(node);
            this.LLcounter++;
         }
      }
   }

   @Override
   public void fillWithoutRebalance(T[] array) {
      if (array == null || array.length == 0)
         return;

      Comparable<Integer>[] arrayOrdenado = (Comparable<Integer>[]) this.order();
      List<T> list = new ArrayList<T>(array.length + arrayOrdenado.length);

      Collections.addAll(list, array);

      Collections.addAll(list, (T[]) arrayOrdenado);

      Collections.sort(list);

      this.root = new BSTNode<T>();

      Deque<Integer[]> fila = new LinkedList<Integer[]>();

      Integer[] element = this.getElement(0, list.size() - 1);

      fila.add(element);

      int contador = 0;

      while (contador < list.size()) {

         element = fila.remove();

         insert(list.get(element[1]));

         contador++;

         if (element[0] != element[1]) {
            fila.addLast(this.getElement(element[0], element[1] - 1));
         }
         if (element[1] != element[2]) {
            fila.addLast(this.getElement(element[1] + 1, element[2]));
         }
      }
   }

   private Integer[] getElement(int inicio, int fim) {
      Integer[] array = new Integer[3];
      array[0] = inicio;
      array[1] = inicio + (fim - inicio) / 2;
      array[2] = fim;
      return array;
   }

}
