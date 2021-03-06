package tree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
        this.root = new BinaryNode<>();
    }
    
    public BinaryTree(BinaryNode<T> root) {
        this.root = root;
    }

    public BinaryTree(T content) {
        this.root = new BinaryNode<>(content);
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    public void setRoot(BinaryNode<T> root) {
        this.root = root;
    }

    public void setLeft(BinaryTree<T> tree) {
        this.root.setLeft(tree);
    }

    public void setRight(BinaryTree<T> tree) {
        this.root.setRight(tree);
    }

    public BinaryTree<T> getLeft() {
        return this.root.getLeft();
    }

    public BinaryTree<T> getRight() {
        return this.root.getRight();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean isLeaf() {
        return this.root.getLeft() == null && this.root.getRight() == null;
    }

    public int countLeavesRecursive() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.isLeaf()) {
            return 1;
        } else {
            int leavesLeft = 0;
            int leavesRight = 0;
            if (this.root.getLeft() != null) {
                leavesLeft = this.root.getLeft().countLeavesRecursive();
            }
            if (this.root.getRight() != null) {
                leavesRight = this.root.getRight().countLeavesRecursive();
            }
            return leavesLeft + leavesRight;
        }
    }

    public int countLeavesIterative() {
        Stack<BinaryTree<T>> stack = new Stack();
        int count = 0;
        if (this.isEmpty()) {
            return count;
        } else {
            stack.push(this);
            while (!stack.empty()) {
                BinaryTree<T> subtree = stack.pop();
                if (subtree.getRoot().getLeft() != null) {
                    stack.push(subtree.getRoot().getLeft());
                }
                if (subtree.getRoot().getRight() != null) {
                    stack.push(subtree.getRoot().getRight());
                }
                if (subtree.isLeaf()) {
                    count++;
                }
            }
        }
        return count;
    }

    public BinaryNode<T> recursiveSearch(T content, Comparator<T> cmp) {
        if (this.isEmpty()) {
            return null;
        } else {
            if (cmp.compare(this.root.getContent(), content) == 0) {
                return this.root;
            } else {
                BinaryNode<T> tmp = null;
                if (this.root.getLeft() != null) {
                    tmp = this.root.getLeft().recursiveSearch(content, cmp);
                }
                if (tmp == null) {
                    if (this.root.getRight() != null) {
                        return this.root.getRight().recursiveSearch(content, cmp);
                    }
                }
                return tmp;
            }
        }
    }
   
    
    //TAREA
    //Estudiante: Gustavo Guzman
    
    //1. Implemente el m??todo findParent, que dado un nodo de ??rbol binario, retorna el padre 
    //correspondiente. La implementaci??n de su m??todo debe considerar que el nodo ra??z no tiene 
    //un padre.
    
    /*Metodo Recursivo
    Descripcion: Se va a recorrer el arbol binario y se va a comprobar cual de estos nodos es el
    padre de ese nodo. Ademas de que a??adiremos un comparator en los parametros para poder
    comparar sus contenidos y saber si son iguales.
    */
    
    public BinaryTree<T> recursiveFindParent(BinaryNode<T> node, Comparator<T> cmp){
        if(this.isEmpty()){
            return null;
        //Si este nodo es el mismo que el nodo raiz que invoca a este metodo se retorna null
        //, dado que un nodo ra??z no tiene.
        }else if(cmp.compare(this.getRoot().getContent(), node.getContent())==0){
            return null;
        }else{
            if(this.getLeft()!=null){
                //Comparamos los contenido de los nodos de los hijos del arbol que invoco el metodo
                //dado de que si alguno es igual, podemos concluir que el arbol que invoco el metodo
                //es su hijo
                if(cmp.compare(this.getLeft().getRoot().getContent(), node.getContent())==0){
                    return this;
                }
            }if(this.getRight()!=null){
                if(cmp.compare(this.getRight().getRoot().getContent(), node.getContent())==0){
                    return this;
                }
            }
            
                //En caso de que ninguno los dos hijos sea igual al nodo que estamos comparando
                //volveremos a llamar a la funcion en cada hijo y si este da un valor diferente
                //a null retornamos ese valor dado que seria el padre de este nodo
                if(this.getLeft()!=null){
                    if(this.getLeft().recursiveFindParent(node, cmp)!=null){
                        return this.getLeft().recursiveFindParent(node, cmp);
                    }
                }if(this.getRight()!=null){    
                    if(this.getRight().recursiveFindParent(node, cmp)!=null){
                    return this.getRight().recursiveFindParent(node, cmp);
                    }
                }
        }
        //en caso de que ninguno sea su padre 
        return null;
    }
    
    /*Metodo iterativo*/
    
       public BinaryTree<T> iterativeFindParent(BinaryNode<T> node, Comparator<T> cmp){
           Stack<BinaryTree<T>> s = new Stack<>();
           //Si el nodo del arbol al que le vamos a buscar el padre es nulo retornamos null
           if(this.isEmpty() || node==null || cmp==null){
               return null;
           //Si el node que se busca es igual al arbol este retorna null
           }else if(cmp.compare(this.root.getContent(), node.getContent())==0){
               return null;
           }else{
               s.push(this);
               while(!s.isEmpty()){
                   BinaryTree<T> tree = s.pop();
                   //En los hijos se compara si su contenido es igual al contenido del nodo,
                   //si es el caso se retorna el arbol que el cual uno de sus hijos es igual al nodo
                   //como parametro, caso contrario el hijo que no sea igual se lo agrega a la pila
                   if(tree.getLeft()!=null){
                    if(cmp.compare(tree.getLeft().getRoot().getContent(), node.getContent())==0){
                        return tree;
                    }else{
                       s.push(tree.getLeft());
                    }
                   }
                   if(tree.getRight()!=null){
                    if(cmp.compare(tree.getRight().getRoot().getContent(), node.getContent())==0){
                        return tree;
                    }else{
                        s.push(tree.getRight());
                    }
                   }
               }
           }
           return null;
       }
    
    
    //2. Implemente el m??todo countLevels que calcule el n??mero de niveles de ??rbol. Considere 
    //que un ??rbol vac??o tiene 0 niveles, mientras que un ??rbol hoja tiene 1 solo nivel.
       
    /*Metodo recursivo
    Descripcion: En este metodo hago la suposicion de que el nivel 1 de un arbol es su raiz*/
    public int recursiveCountLevels(){
        if (this.isEmpty()) {
            return 0;
        }else if(this.isLeaf()){
            return 1;
        }
        else{
            //Contaremos los niveles del hijo izquierdo como derecho
            int nivelesRight = 0;
            int nivelesLeft = 0;
            //Si los los hijos del arbol que invoca al metodo no son null
            //le sumamos un nivel que es el que estariamos y volvemos a llamar
            //al metodo, pero a sus hijos y que vayan contando sus niveles
            if(this.getLeft()!=null){
                nivelesLeft = nivelesLeft + 1 + this.getLeft().recursiveCountLevels();
            }
            if(this.getRight()!=null){
                nivelesRight = nivelesRight + 1 + this.getRight().recursiveCountLevels();
            }
            //Se escoge el que tiene un nivel mayor ya que serian los niveles del arbol
            //que invoco originalmente al metodo, ademas de que se le suma 1 al retorno
            //ya que tambien se cuenta el nivel de la raiz
            if(nivelesRight>=nivelesLeft){
                return nivelesRight++;
            }else{
                return nivelesLeft++;
            }
        }
    }
    
    /*Metodo iterativo*/
    
    public int iterativeCountLevels(){
        int niveles = 1;
        //Pila que contiene los BInaryTree del nivel actual
        Stack<BinaryTree<T>> s1 = new Stack<>();
        //Pila que contiene los BInaryTree del siguiente nivel
        Stack<BinaryTree<T>> s2 = new Stack<>();
        if(this.isEmpty()){
            return 0;
        }else if(this.isLeaf()){
            return 1;
        }else{
            s1.push(this);
            //Si la segunda pila esta vacia es false y el while acaba
            boolean validacion =true;
            while(validacion){
                niveles++;
                while(!s1.isEmpty()){
                    BinaryTree<T> t = s1.pop();
                    //Si el arbol es una hoja no se hace ninguna validacion con alguno
                    //de sus hijos.
                    if(!t.isLeaf()){
                        //Si el arbol tiene hijo un hijo se lo agrega a la pila 2
                        //para usarlo en el siguiente nivel
                        if(t.getLeft()!=null){
                            s2.push(t.getLeft());
                        }else if(t.getRight()!=null){
                            s2.push(t.getRight());
                        }
                    }
                }
                //Si la pila 2 es vacia quiere decir que no hay mas elemento y 
                //se alcanzo su maximo nivel
                if(s2.isEmpty()){
                    validacion=false;
                }
                //Los elementos de la pila 2 se pasan a la pila uno
                while(!s2.isEmpty()){
                    s1.push(s2.pop());
                }
            }
        }
        return niveles;
    }
    
    //3. Se dice que un ??rbol binario es zurdo si el ??rbol: 1) est?? vac??o, 2) es una hoja, o 3) si sus hijos 
    //son ambos zurdos y tiene a m??s de la mitad de sus descendientes en el hijo izquierdo. 
    //Implementar el m??todo isLefty que indique si un ??rbol binario es zurdo o no
    
    /*Metodo recursivo*/
    
    public boolean recursiveIsLefty(){
        //Si es vacio o es una hoja directamente se dice que es zurdo
        if(this.isEmpty() || this.isLeaf()){
            return true;
        }
        else{
            //Contaremos la cantidad de hijos tanto la izquierda como derecha
            int hijosLeft=0;
            int hijosRight=0;
            //Si un hijo no es nulo se contaran los hijos que tiene
            if(this.getLeft()!=null){
               hijosLeft = this.getLeft().contarhijos();
            }if(this.getRight()!=null){
               hijosRight = this.getRight().contarhijos();
            }
            if(this.getLeft()!=null){
                //Si la cantidad de hijos izquierdo es mayor a la de hijos derecho y
                //si ambos hijos son zurdos, entonces el arbol que llamo al metodo es zurdo
                if(hijosLeft>hijosRight && this.getLeft().recursiveIsLefty() && 
                        this.getRight().recursiveIsLefty() ){
                    return true;
                }
            }
        }
        return false;
    }
    //Metodo de apoyo
    //Descripcion: Este metodo va a contar la cantidad de hijos que tiene el
    //arbol que lo invoca
    public int contarhijos(){
        int cont=0;
        Stack<BinaryTree<T>> sl = new Stack<>();
                sl.push(this.getLeft());
                while(!sl.isEmpty()){
                    BinaryTree<T> tl = sl.pop();
                    if(tl.getLeft()!=null){
                        if(!tl.getLeft().isEmpty()){
                            cont++;
                            sl.push(tl.getLeft());
                        }
                    }
                    if(tl.getRight()!=null){
                        if(!tl.getRight().isEmpty()){
                            cont++;
                            sl.push(tl.getRight());
                        }
                    }
                }
        return cont;
    }
    
    /*Metodo iterativo*/
    
    public boolean iterativeIsLefty(){
        if(this.isEmpty() || this.isLeaf()){
            return true;
        }else{
            Stack<BinaryTree<T>> s = new Stack<>();
            s.push(this);
            while(!s.isEmpty()){
                BinaryTree<T> t = s.pop();
                //Contaremos la cantidad de hijos tanto la izquierda como derecha
                int hijosLeft=0;
                int hijosRight=0;
                //Si un hijo no es nulo se contaran los hijos que tiene
                if(t.getLeft()!=null){
                   hijosLeft = t.getLeft().contarhijos();
                }if(t.getRight()!=null){
                   hijosRight = t.getRight().contarhijos();
                }
                //Si el hijo izquierdo tiene mas descendientes que el derecho
                //se a??ade el hijo izquierdo y derecho a la pila, en el caso deque tenga
                //para despues poder seguir comprabando si es un arbol zurdo
                if(hijosLeft>hijosRight){
                    if(t.getLeft()!=null){
                        s.push(t.getLeft());
                    }if(t.getRight()!=null){
                        s.push(t.getRight());
                    }
                //Siel hijo izquierdo no tiene mas descendientes que el derecho
                //podemos concluir que el arbol no es zurdo
                }else{
                    return false;
                }
            }
        }
        //Si en la iteracion nunca se dio el caso que un arbol no sea zurdo
        //concluimos que si lo es.
        return true;
    }
    
    //4. Implemente el m??todo isIdentical que, dado un segundo ??rbol binario, retorne true o 
    //false indicando si dicho ??rbol es igual al que invoca el m??todo (this).
    
    /*
    Metodo recursivo
    Descripcion: En parametros aparte del arbol al que se va a comparar, a??adimos un Comparator
    Para asi poder comparar su contenido y que este se lo pase como parametro
    */
    public boolean recursiveIsIdentical(BinaryTree<T> tree,Comparator<T> cmp ){
        if(this.isEmpty()){
            return false;
        }
        else{
            //Si el contenido de los arboles no son iguales podemos decir que no son identicos
            if(cmp.compare(this.getRoot().getContent(), tree.getRoot().getContent())!=0){
                return false;
            }
            //Si algun hijo izquierdo entre los dos arboles es nulo no searian iguales
            if(this.getLeft()!=null && tree.getLeft()!=null){
                //Aplicamos el mismo metodo de forma recursiva para que haga las misma validaciones
                //en caso de que en algun momento no sean iguales retorna false
                if(!this.getLeft().recursiveIsIdentical(tree.getLeft(), cmp)){
                    return false;
                }
            }
            else if((this.getLeft()==null && tree.getLeft()!=null) ||
                    (this.getLeft()!=null && tree.getLeft()==null)){
                return false;
            }
            //Si algun hijo izquierdo entre los dos arboles es nulo no searian iguales
            if(this.getRight()!=null && tree.getRight()!=null){
                //Aplicamos el mismo metodo de forma recursiva para que haga las misma validaciones
                //en caso de que en algun momento no sean iguales retorna false
                if(!this.getRight().recursiveIsIdentical(tree.getRight(), cmp)){
                    return false;
                }
            }
            else if((this.getRight()==null && tree.getRight()!=null) ||
                    (this.getRight()!=null && tree.getRight()==null)){
                return false;
            }
        }
        //retorna true si en ningun momento se dio el caso de que no sean iguales
        return true;
        
        
        
    }
    
    /*Metodo iterativo*/
    
    public boolean iterativeIsIdentical(BinaryTree<T> tree,Comparator<T> cmp ){
        //usamos 2 pilas para recorrer a la vez los dos arboles a la vez
        Stack<BinaryTree<T>>  s1 = new Stack<>();
        Stack<BinaryTree<T>>  s2 = new Stack<>();
        if(this.isEmpty()){
            return false;
        }else{
            s1.push(this);
            s2.push(tree);
            while(!(s1.isEmpty() && s2.isEmpty())){
                BinaryTree<T> t1 = s1.pop();
                BinaryTree<T> t2 = s2.pop();
                //comparamos los arboles, si son iguales vemos que tengan los mismos
                //hijos para agregarlos ala pila y asi seguir verificando, si no tienen los
                //mismos hijos directamente se retorna null
                if(cmp.compare(t1.getRoot().getContent(), t2.getRoot().getContent())==0){
                    if(t1.getLeft()!=null && t2.getLeft()!=null){
                        s1.push(t1.getLeft());
                        s2.push(t2.getLeft());
                    }else if((this.getLeft()==null && tree.getLeft()!=null) ||
                        (this.getLeft()!=null && tree.getLeft()==null)){
                            return false;
                    }
                    if(t1.getRight()!=null && t2.getRight()!=null){
                        s1.push(t1.getRight());
                        s2.push(t2.getRight());
                    }else if((this.getRight()==null && tree.getRight()!=null) ||
                        (this.getRight()!=null && tree.getRight()==null)){
                            return false;
                    }
                }else{
                    return false;
                }
                
            }
        }
        //Si en ningun caso se dio que no sean iguales,concluimos que son iguales
        
        return true;
    }
    
    //5. Encontrar el valor m??s grande de cada nivel del ??rbol. El m??todo largestValueOfEachLevel
    //debe imprimir el mayor valor presente en cada nivel de un ??rbol binario cuyos nodos 
    //contienen n??meros enteros.
        
    /*
    Metodo Recursivo:
    */
    public void recursivelargestValueOfEachLevel(Comparator<T> cmp){
        //Solamente el metodo recorre el queue para imprimir sus valores
        //los cuales son el valor m??s grande de cada nivel del ??rbol
        Queue<T> queue = this.largestValuesQueueRecursive(cmp);
        while(!queue.isEmpty()) {
            System.out.print(queue.poll()+" ");
        }
        System.out.println(".");
    }
    
    private Queue<T> largestValuesQueueRecursive(Comparator<T> cmp){
        if(this.isEmpty()) {
            return null;
        }
        Queue<T> q = new ArrayDeque();
        q.offer(this.getRoot().getContent());
        Queue<T> queueLeft = new ArrayDeque();
        Queue<T> queueRight = new ArrayDeque();
        //Si ningun hijos es nulo el Queue de cada hijo sera igual a si mismo
        //que se invoca el metodo de forma recursiva
        if(this.getLeft() != null){
            queueLeft = this.getLeft().largestValuesQueueRecursive(cmp);
        }
        if(this.getRight() != null){
            queueRight = this.getRight().largestValuesQueueRecursive(cmp);
        }
        //se comparan los queue paralelamente, y si si son el mayor en su nivel se 
        //los agrega al queue que se va a retornar
        while(!queueLeft.isEmpty() || !queueRight.isEmpty()){ 
            if(!queueLeft.isEmpty() && !queueRight.isEmpty()){
                if(cmp.compare(queueLeft.peek(), queueRight.peek()) >= 0){
                    q.offer(queueLeft.poll());
                    queueRight.poll();
                }else{
                    q.offer(queueRight.poll());
                    queueLeft.poll();
                }
            }
            else if(!queueLeft.isEmpty()){
                q.offer(queueLeft.poll());
            }else{
                q.offer(queueRight.poll());
            }
        }
        return q;
    }
    
    //Metodo iterativo
    
    public void iteravitelargestValueOfEachLevel(Comparator<T> cmp){
        
        if(this.getRoot() == null) {
            return;
        }
        
        Stack<BinaryNode<T>> s = new Stack();
        s.push(this.getRoot());
        //Mapa del nodo con su nivel
        Map<BinaryNode<T>, Integer> mapa = new HashMap();
        //Mapa del nivel con lista de sus valores
        Map<Integer, List<T>> mapa_valores = new HashMap();
        mapa.put(this.getRoot(), 1);
        //Recorremos la pila con los nodos, para ir a??adiendo a la lista de cada nivel
        //el contenido de ese nodo
        while(!s.isEmpty()){
            BinaryNode<T> padre = s.pop();
            int nivel = mapa.get(padre)+1;
            if(padre.getLeft() != null){
                BinaryNode<T> l_node = padre.getLeft().getRoot();
                s.push(l_node);
                mapa.put(l_node, nivel);
                if(mapa_valores.containsKey(nivel)) mapa_valores.get(nivel).add(l_node.getContent());
                else {
                    List<T> lista = new LinkedList();
                    lista.add(l_node.getContent());
                    mapa_valores.put(nivel, lista);
                }
            }
            if(padre.getRight() != null){
                BinaryNode<T> r_node = padre.getRight().getRoot();
                s.push(r_node);
                mapa.put(r_node, nivel);
                if(mapa_valores.containsKey(nivel)) mapa_valores.get(nivel).add(r_node.getContent());
                else {
                    List<T> lista = new LinkedList();
                    lista.add(r_node.getContent());
                    mapa_valores.put(nivel, lista);
                }
            }
        }
        //recoremos el mapa e imprimos el maximo de la lista por nivel
        System.out.print(this.getRoot().getContent()+" ");
        mapa_valores.keySet().forEach(i -> {
            System.out.print(mapa_valores.get(i).stream().max(cmp).get()+" ");
        });
        System.out.println("");
        
    }
    
    //6. El m??todo countNodesWithOnlyChild debe retornar el n??mero de nodos de un ??rbol que 
    //tienen un solo hijo.
    //Metodo recursivo
    public int recursiveCountNodesWithOnlyChild(){
        int count = 0;
        if(this.isEmpty() || this.isLeaf()){
            return 0;
        }else{
            //Si su hijo izquierdo es nulo y el derecho no, quiere decir que tiene
            //un solo hijo y es el derecho, por ende el count aumenta en uno y ademas
            //se le suma a su hijo derecho llamando a este mismo metodo
            if(this.getLeft()==null && this.getRight()!=null){
                count = count + 1 + this.getRight().recursiveCountNodesWithOnlyChild();
            //Si su hijo derecho es nulo y el izquierdo no, quiere decir que tiene
            //un solo hijo y es el izquierdo, por ende el count aumenta en uno y ademas
            //se le suma a su hijo izquierdo llamando a este mismo metodo
            }else if(this.getLeft()!=null && this.getRight()==null){
                count = count +1 + this.getLeft().recursiveCountNodesWithOnlyChild();
            //Si sus dos hijos no son null quiere decir que si tiene a los dos, por ende
            //solo al count solo se le aumenta a los hijos llamando nuevamente este metodo
            }else if(this.getLeft()!=null && this.getRight()!=null){
                count = count + this.getLeft().recursiveCountNodesWithOnlyChild();
                count = count + this.getRight().recursiveCountNodesWithOnlyChild();
            }
        }
        return count;
    }
    
    /*Metodo iterativo*/
    
    public int iterativeCountNodesWithOnlyChild(){
    Stack<BinaryTree<T>> s  = new Stack<>();
    int cont=0;
    if(this.isEmpty()){
        return 0;
    }else{
        s.push(this);
        //Se recorre la pila
        while(!s.isEmpty()){
            //Se extrae el ultimo elemento de la pila
            BinaryTree<T> t = s.pop();
            //Si su hijo izquierdo es nulo y el derecho no, quiere decir que tiene
            //un solo hijo y es el derecho, por ende se aumenta el contador y al
            //hijo derecho se lo agrega a la pila
            if(t.getLeft()==null && t.getRight()!=null){
                cont++;
                s.push(t.getRight());
            //Si su hijo derecho es nulo y el izquierno no, quiere decir que tiene
            //un solo hijo y es el izquierdo, por ende se aumenta el contador y al
            //hijo izquierdo se lo agrega a la pila
            }else if(t.getLeft()!=null && t.getRight()==null){
                cont++;
                s.push(t.getLeft());
            //Si ninguno es null quiere decir que tiene sus 2 hijos y solamento estos se los
            //agrega a la pila
            }else if(t.getLeft()!=null && t.getRight()!=null){
                s.push(t.getLeft());
                s.push(t.getRight());
            }
        }
    }
    return cont;
    }
    
    //7. El m??todo isHeightBalanced debe retornar si un ??rbol binario est?? balanceado en altura o 
    //no. Un ??rbol vac??o est?? siempre balanceado en altura. Un ??rbol binario no vac??o est?? 
    //balanceado si y solo si:
    
    /*Metodo recursivo*/
    
    public boolean recursiveIsHeightBalanced(){
        if(this.isEmpty()){
            return true;
        }else{
            //Si un hijo no es null se le calcula la altura
            int alturaLeft=0;
            int alturaRight=0;
            if(this.getLeft()!=null){
                alturaLeft = this.getLeft().recursiveCountLevels();
            }if(this.getRight()!=null){
                alturaRight = this.getRight().recursiveCountLevels();
            //Se hacen las validaciones
            //Si La diferencia entre las alturas de sus sub??rboles izquierdo y derecho es menor que 1,
            //Su sub??rbol izquierdo est?? balanceado y  Su sub??rbol derecho est?? balanceado, podemos
            //concluir que esta balanceado
            }if((alturaLeft-alturaRight)<=1){
                boolean validacionLeft;
                if(this.getLeft()!=null){
                    validacionLeft = this.getLeft().recursiveIsHeightBalanced();
                }else{
                    validacionLeft=true;
                }
                boolean validacionRight;
                if(this.getRight()!=null){
                    validacionRight = this.getRight().recursiveIsHeightBalanced();
                }else{
                    validacionRight=true;
                }
                if(validacionLeft==true && validacionRight==true){
                  return true;  
                }
            }
        }
        return true;
    }
    
    /*Metodo iterativo*/
    
    public boolean iterativeisHeightBalanced(){
        if(this.isEmpty()){
            return true;
        }else{
            Stack<BinaryTree<T>> s = new Stack<>();
            s.push(this);
            while(!s.isEmpty()){
                //Calculamos la altura de cada hijo si no es nulo
                BinaryTree<T> t = s.pop();
                int alturaLeft=0;
                int alturaRight=0;
                if(t.getLeft()!=null){
                    alturaLeft = t.getLeft().recursiveCountLevels();
                }if(t.getRight()!=null){
                    alturaRight = t.getRight().recursiveCountLevels();
                //Si la altura del hijo izquiero menos con la del hijo derecho es mayor a 1
                //el arbol no esta balanceado
                }if((alturaLeft-alturaRight)>1){
                    return false;
                //Caso contrario hay que comprobar si sus hijos estan balanceado
                //por ende se los a??ade a la pila
                }else{
                    if(t.getLeft()!=null){
                        s.push(t.getLeft());
                    }if(t.getRight()!=null){
                        s.push(t.getRight());
                    }
                }
            }
        }
        //Si en ningun momento se dice que el arbol no esta nivelado, concluimos que si
        //lo esta
        return true;
    } 
    @Override
    public String toString(){
        return ""+root.getContent();
    }
}
