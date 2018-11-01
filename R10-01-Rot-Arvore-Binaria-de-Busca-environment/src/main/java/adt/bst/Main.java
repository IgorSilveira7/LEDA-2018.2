package adt.bst;

public class Main {

   public static void main(String[] args) {

      BSTImpl<Integer> bst = new BSTImpl<>();

      bst.insert(6);
      bst.insert(23);
      bst.insert(-34);
      bst.insert(5);
      bst.insert(9);
      bst.insert(2);
      bst.insert(0);
      bst.insert(76);
      bst.insert(12);
      bst.insert(77);
      bst.insert(232);
      bst.insert(-40);
      
      

      bst.remove(-34);

      System.out.println(bst.search(-34));
   }

}
