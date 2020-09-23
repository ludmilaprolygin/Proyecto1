package Parte2;

public class LoggingPrueba {
	public static void main(String[]args) {
		Graph grafo = new Graph();
		
		grafo.addNode(1);
		grafo.addNode(5);
		grafo.addNode(7);
		grafo.addNode(5);
		
		grafo.addEdge(1, 5);
		grafo.addEdge(5, 7);
		grafo.addEdge(1, 5);
		grafo.addEdge(5, 1);
		grafo.addEdge(3, 7);
		
		grafo.removeNode(1);
		grafo.removeNode(2);
		
		grafo.removeEdge(5, 7);
		grafo.removeEdge(0, 7);
		grafo.removeEdge(7, 5);
	}
}
