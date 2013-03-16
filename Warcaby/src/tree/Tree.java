package tree;

public class Tree<T> {
	private Node<T> root; //korzen drzewa
	
	//Konstruktory
	public Tree(){
		root=null;
	}
	
	public Tree(Node<T> r){
		root=r;
	}

	//Przchodzenie po drzewie - trawersowanie
	public void preOrder(Node<T> n){
		//root - lewe skrajne poddrzewo - prawe skrajne poddrzewo
		System.out.println(n.getData()+" ");
		Node<T> tmp = n.getMaxLeftChild();
		while(tmp!=null){
			preOrder(tmp);
			tmp=tmp.getSiblingElement();
		}
	}
	
	
	
	public static void main(String[] args){
		Node<String> n0 = new Node<String>(null,"A"); //korzen
		
		Node<String> n1 = new Node<String>(null,"B"); //
		Node<String> n2 = new Node<String>(null,"C"); //
		
		Node<String> n3 = new Node<String>(null,"B1"); //
		Node<String> n4 = new Node<String>(null,"B2"); //
		Node<String> n5 = new Node<String>(null,"B3"); //
		
		n0.addChild(n1);
		n0.addChild(n2);
		
		n1.addChild(n3);
		n1.addChild(n4);
		n1.addChild(n5);
		
		
		Tree<String> tree = new Tree<String>(n0);
		tree.preOrder(n0); //od korzenia
	}

}

