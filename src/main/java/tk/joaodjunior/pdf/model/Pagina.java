package tk.joaodjunior.pdf.model;

import java.util.List;

public class Pagina {
	
	private int numeroPagina;
	private Integer paragrafos;
	
	public Pagina() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pagina(int numeroPagina, Integer paragrafos) {
		super();
		this.numeroPagina = numeroPagina;
		this.paragrafos = paragrafos;
	}
	public int getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public Integer getParagrafos() {
		return paragrafos;
	}
	public void setParagrafos(Integer paragrafos) {
		this.paragrafos = paragrafos;
	}
	
	

}
