package tk.joaodjunior.pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import tk.joaodjunior.pdf.model.Livro;
import tk.joaodjunior.pdf.model.Pagina;
import tk.joaodjunior.pdf.model.Paragrafo;

public class PdfToJson {

	public static void main(String[] args) {

		Livro livro = new Livro();
		Pagina pagina = null;
		List<Pagina> paginas = new ArrayList<Pagina>();
		List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();
		try {
			// Create PdfReader instance.
			PdfReader pdfReader = new PdfReader(
					"C:\\tmp\\DIREITO DO TRABALHO\\LEITE, Carlos Henrique Bezerra. Curso de Direito Processual do Trabalho (2015).pdf");
			livro.setNome("Curso de Direito Processual do Trabalho (2015)");
			livro.setDownloadUrl("https://jdjscm.s3-us-west-2.amazonaws.com/teste/LEITE%2C+Carlos+Henrique+Bezerra.+Curso+de+Direito+Processual+do+Trabalho+(2015).pdf");
			// Get the number of pages in pdf.
			int pages = pdfReader.getNumberOfPages();

			// Iterate the pdf through pages.
			for (int i = 74; i <= pages; i++) {
				pagina = new Pagina();
				pagina.setNumeroPagina(i);
				if(i == 341)
					System.out.println("Aqui");
				// Extract the page content using PdfTextExtractor.
				String pageContent = PdfTextExtractor.getTextFromPage(pdfReader, i);
				
				List<Paragrafo> tempParagrafos = parseToParagraphs(pageContent, i);
				paragrafos.addAll(tempParagrafos);
				if(tempParagrafos.size() > 0)
					pagina.setParagrafos(tempParagrafos.size());
				paginas.add(pagina);
				// Print the page content on console.
				System.out.println("Content on Page " + i + ": " + pageContent);
			}
			livro.setPaginas(paginas);
			livro.setParagrafos(paragrafos);
			parseToLivroJson(livro);

			// Close the PdfReader.
			pdfReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static List<Paragrafo> parseToParagraphs(String texts, int paginaNumero) {
		List<Paragrafo> paragrafos = new ArrayList<Paragrafo>();
		Paragrafo paragrafo = null;
		String[] strings = texts.split("\\r?\\n");
		String iterador = "";
		int i = 0;
		for(String p : strings) {
			String regra = p.isEmpty() ? "" : String.valueOf(p.toCharArray()[p.length()-1]);
			if(regra.equals(".") || regra.equals(":") || regra.equals("?") || regra.equals("!")) {
				paragrafo = new Paragrafo();
				paragrafo.setConteudo(iterador.isEmpty() ? p + "\\r?\\n" : iterador + p);
				paragrafo.setPaginaNumero(paginaNumero);
				paragrafos.add(paragrafo);
				iterador = "";
			} else if(regra.equals("-")) {
				for(char l : p.toCharArray()) {
					String val = String.valueOf(l).equals("-") ? "" : String.valueOf(l);
					iterador += val;
				}
			} else if(!iterador.isEmpty() && (strings.length - 1) == i) {
				paragrafo = new Paragrafo();
				paragrafo.setConteudo(iterador);
				paragrafo.setPaginaNumero(paginaNumero);
				paragrafos.add(paragrafo);
			} else {
				iterador += p + "\\r?\\n";
			}
			
			i++;
		}
		return paragrafos;
	}

	public static void parseToLivroJson(Livro livro) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(new File("c:\\APPS\\"+livro.getNome()+".json"), livro);
	}
	
	public static void parseToParagrafJson(List<Paragrafo> paragrafos) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(new File("c:\\APPS\\"+"Paragrafos"+".json"), paragrafos);
	}

}
