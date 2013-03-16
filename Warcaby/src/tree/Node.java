package tree;

import java.util.LinkedList;

/*
 * Wezel drzewa
 * Parametryzowany typem
 * */

public class Node<T> {
	private T data; // dane
	private Node<T> parent; // rodzic
	private LinkedList<Node<T>> children; // dzieci

	// Konstruktory
	public Node() {
		// Pusty - brak rodzica i pusta lista dzieci
		parent = null;
		children = new LinkedList<Node<T>>();
	}

	public Node(Node<T> par) {
		// Z podanym rodzicem
		this.parent = par;
		children = new LinkedList<Node<T>>();
	}

	public Node(Node<T> par, T data) {
		// z podanym rodzicem i danymi
		this.parent = par;
		children = new LinkedList<Node<T>>();
		this.data = data;
	}

	// GET AND SET

	public Node<T> getParent() {
		return parent;
	}

	public T getData() {
		return data;
	}

	public void setParent(Node<T> par) {
		parent = par;
	}

	public void setData(T d) {
		data = d;
	}

	// NODE INFO
	public int getDegree() {
		// zwraca liczbe potomków wezla - czyli stopien wezla
		return children.size();
	}

	public boolean isLeaf() {
		// okresla czy wezel jest lisciem - (jest gdy nie zawiera potomkow)
		return children.isEmpty();
	}

	// NODE OPERATION
	public void addChild(T data) {
		Node<T> ch = new Node<T>(this, data);// utworzenie nowego wezla z danymi
		children.add(ch);// dodanie go jako potomka
	}
	
	public void addChild(Node<T> ch){
		//dodanie juz utworzonego wezla jako dziecka
		ch.setParent(this);
		children.add(ch);
	}

	public Node<T> getChild(int poz) {
		// zwrca wybranego potomka
		return children.get(poz);
	}

	public LinkedList<Node<T>> getAllChildren() {
		return children;
	}

	public Node<T> removeChild(int poz) {
		return children.remove(poz);
	}

	public void removeAllChildren() {
		// usuwa wszystkich potomkow - tworzy z wezla lisc
		children.clear();
	}

	public Node<T> getMaxLeftChild() {
		if (children.isEmpty())
			return null;
		// zwraca pierwsze nasze dziecko na liscie (najbardziej po lewej
		// stronie)
		return children.get(0);
	}

	public Node<T> getSiblingElement() {
		// zwraca nasz element siostrzany -pierwszy po naszej prawej stronie

		if (parent != null) {
			LinkedList<Node<T>> parChildren = parent.getAllChildren(); // dzeci
																		// naszego
																		// rodzica

			int myPos = parChildren.indexOf(this); // nasza pozycja na liscie
													// dzieci naszego rodzica

			if (myPos < parChildren.size() - 1) {// jezeli nie jestesmy ostatni
				return parChildren.get(myPos + 1); // zwroc naszego najblizszego
													// brata po prawej
			}

		}

		return null;
	}

}
