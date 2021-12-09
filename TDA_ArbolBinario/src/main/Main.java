package main;

import java.util.Comparator;
import tree.BinaryNode;
import tree.BinaryTree;



public class Main {

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree(0);
        tree.setLeft(new BinaryTree(1));
        tree.setRight(new BinaryTree(2));
        tree.getLeft().setLeft(new BinaryTree(3));
        tree.getLeft().setRight(new BinaryTree(4));
        tree.getRight().setLeft(new BinaryTree(5));
        tree.getRight().setRight(new BinaryTree(6));
        tree.getRight().getRight().setRight(new BinaryTree(7));
        
        BinaryTree<Integer> tree2 = new BinaryTree(0);
        tree2.setLeft(new BinaryTree(1));
        tree2.setRight(new BinaryTree(2));
        tree2.getLeft().setLeft(new BinaryTree(3));
        tree2.getLeft().setRight(new BinaryTree(4));
        tree2.getRight().setRight(new BinaryTree(5));

        
        /*Pregunta 1*/
        System.out.println("Pregunta 1");
        BinaryNode<Integer> bn= new BinaryNode<>(5);
        //Metodo recursivo
        System.out.println("CountLevels de forma recursiva: "+tree.recursiveFindParent(bn,(n1,n2)->{return n1-n2;}));
        //Metodo iterativo
        System.out.println("CountLevels de forma iterativa: "+tree.iterativeFindParent(bn,(n1,n2)->{return n1-n2;}));       
        /*Pregunta 2*/
        System.out.println("Pregunta 2");
        //Metodo recursivo
        System.out.println("CountLevels de forma recursiva: "+tree.recursiveCountLevels());
        //Metodo iterativo
        System.out.println("CountLevels de forma iterativa: "+tree.iterativeCountLevels());
        /*Pregunta 3*/
        System.out.println("Pregunta 3");
        //Metodo recursivo
        System.out.println("IsLefty de forma recursiva: "+tree.recursiveIsLefty());
        //Metodo iterativo
        System.out.println("IsLefty de forma iterativa: "+tree.iterativeIsLefty());
        /*Pregunta 4*/
        System.out.println("Pregunta 4");
        //Metodo recursivo
        System.out.println("IsIdentical de forma recursiva: "+tree.recursiveIsIdentical(tree,(n1,n2)->{return n1-n2;}));
        //Metodo iterativo
        System.out.println("IsIdentical de forma iterativo: "+tree.iterativeIsIdentical(tree,(n1,n2)->{return n1-n2;}));      
        /*Pregunta 5*/
        System.out.println("Pregunta 5");
        //Metodo recursivo
        System.out.print("LargestValueOfEachLevel de forma recursiva: ");
        tree.recursivelargestValueOfEachLevel((n1,n2)->{return n1-n2;});
        //Metodo iterativo
        System.out.print("LargestValueOfEachLevel de forma iterativa: ");
        tree.iteravitelargestValueOfEachLevel((n1,n2)->{return n1-n2;});
        /*Pregunta 6*/
        System.out.println("Pregunta 6");
        //Metodo recursivo
        System.out.println("CountNodesWithOnlyChild de forma recursiva: "+tree.recursiveCountNodesWithOnlyChild());
        //Metodo iterativo
        System.out.println("CountNodesWithOnlyChild de forma iterativa: "+tree.iterativeCountNodesWithOnlyChild());
        /*Pregunta 7*/
        System.out.println("Pregunta 7");
        //Metodo recursivo
        System.out.println("IsHeightBalanced de forma recursiva: "+ tree.recursiveIsHeightBalanced());
        //Metodo iterativo
        System.out.println("IsHeightBalanced de forma iterativa: "+ tree.iterativeisHeightBalanced());
     
        
                
    }     

}
