package Parte1;
public class Producto {
	private String nombre;
	private float valor;
	
	
	public Producto(String nombre) {
		this.nombre = nombre;
		this.valor = 0;
	}
	
	public Producto(String nombre, int valor) {
		this.nombre = nombre;
		if (valor > 0){
			this.valor = valor;
		}else{
			this.valor = 0;
		}
	}
	
	public String getNombre() {	
		return this.nombre;
	}
	
	public float getValor() {	
		return this.valor;
	}
	
	public void setValor(float valor) {
		if (valor > 0){
			this.valor = valor;
		}else {
			this.valor = 0;
		}
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}