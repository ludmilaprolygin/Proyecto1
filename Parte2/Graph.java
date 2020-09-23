package Parte2;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Handler;


public class Graph {
	protected Map<Integer, Integer> nodos;
	protected Map<String, Arco> arcos;
	protected static Logger logger;
	
	public Graph() {
		this.nodos = new HashMap<Integer, Integer>();
		this.arcos = new HashMap<String, Arco>();
		
		if(logger==null) {
			logger = Logger.getLogger(Graph.class.getName());
			
			Handler handler = new ConsoleHandler();
			handler.setLevel(Level.FINE);
			logger.addHandler(handler);
			
			logger.setLevel(Level.WARNING);
			
			Logger rootLogger = logger.getParent();
			for(Handler h : rootLogger.getHandlers())
				h.setLevel(Level.OFF);
		}
	}
	
	public void addNode(int node) {
		boolean esta = nodos.containsKey(node);
		
		logger.fine("Nodo de valor "+node);
		if(!esta) {
			nodos.put(node, 0);
			logger.info("El nodo se agregó con éxito");
		}	
		else
			logger.warning("El nodo ya existía con anterioridad");
	}
	
	public void addEdge(int node1, int node2) {
		boolean esta1 = nodos.containsKey(node1);
		boolean esta2 = nodos.containsKey(node2);
		
		Arco arco = new Arco(node1, node2);
		String clave = node1+","+node2;
		Arco valor = arcos.put(clave, arco);
		int cant;
		
		logger.fine("Nodos de valores "+node1+ " y "+node2);
		if(esta1&&esta2) {
			if(valor!=null) { //Ya había un arco antes
				arcos.put(clave, valor);
				logger.warning("El arco ya existía con anterioridad");
			}
			else { //No había un arco
				cant = nodos.get(node1);
				nodos.replace(node1, cant+1);
				cant = nodos.get(node2);
				nodos.replace(node2, cant+1);
				logger.info("El arco se agregó con éxito");
			}
		}	
		else
			logger.warning("Al menos un nodo no existe en el grafo");
	}

	public void removeEdge(int node1, int node2) {
		boolean esta1 = nodos.containsKey(node1);
		boolean esta2 = nodos.containsKey(node2);
		
		String clave = node1+","+node2;
		int cant; 
		Arco arco = arcos.remove(clave);
		
		logger.fine("Nodos de valores "+node1+ " y "+node2);
		if(esta1&&esta2) 
			if(arco!=null) { //Había un arco que borrar
				cant = nodos.get(node1);
				nodos.replace(node1, cant-1);
				cant = nodos.get(node2);
				nodos.replace(node2, cant-1);
				logger.info("El arco se eliminó con éxito");
			}
			else
				logger.warning("El arco no existe en el grafo");
		else
			logger.warning("Al menos un nodo no existe en el grafo");
	}
	
	public void removeNode(int node) {
		Integer cant = nodos.get(node);
		int cantArcos, node1, node2;
		Collection<Arco> arcosTotales = arcos.values();
		Arco[] arregloArcos = arcosTotales.toArray(new Arco[arcos.size()]);
		String clave;
	
		logger.fine("Nodo de valor "+node);
		if(cant!=null) { //El nodo existía
			for(int i=0; i<arregloArcos.length && cant!=0; i++) {
				if(arregloArcos[i].getNodo1()==node || arregloArcos[i].getNodo2()==node) {
					cant--;
					node1 = arregloArcos[i].getNodo1();
					node2 = arregloArcos[i].getNodo2();
					clave = node1+","+node2;
					cantArcos = nodos.get(node1);
					nodos.replace(node1, cantArcos-1);
					cantArcos = nodos.get(node2);
					nodos.replace(node2, cantArcos-1);
					arcos.remove(clave);
				}
			}
			nodos.remove(node);
			logger.info("El nodo se eliminó con éxito");
		}
		else
			logger.warning("El nodo no existe en el grafo");
	}
}
