package tk.joaodjunior.pdf.model;

import java.util.List;

public class Livro {
	
	private Integer id;
	private String nome;
	private String downloadUrl;
	private List<Pagina> paginas;
	private List<Paragrafo> paragrafos;
	
	public Livro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Livro(Integer id, String nome, String downloadUrl, List<Pagina> paginas, List<Paragrafo> paragrafos) {
		super();
		this.id = id;
		this.nome = nome;
		this.downloadUrl = downloadUrl;
		this.paginas = paginas;
		this.paragrafos = paragrafos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public List<Paragrafo> getParagrafos() {
		return paragrafos;
	}

	public void setParagrafos(List<Paragrafo> paragrafos) {
		this.paragrafos = paragrafos;
	}
	
	

}
