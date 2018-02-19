package knee_up_v2;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.JFreeChart;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Pdf {

	Document document;
	PdfWriter writer;

	public void createPdf(String filename) throws FileNotFoundException, DocumentException {
		document = new Document();
		writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
	}
	
	public void closePdf() {
		document.close();
		writer.close();
	}

	public void setDadosPaciente(String NomePaciente, String Codigo, String DataCirurgia, String DataTeste,
			String NomeMedico) throws DocumentException {

		document.add(new Paragraph("Relatório Knee-up"));
		document.add(new Paragraph("Nome do Paciente: " + NomePaciente));
		document.add(new Paragraph("Código: " + Codigo));
		document.add(new Paragraph("Data da Cirurgia: " + DataCirurgia));
		document.add(new Paragraph("Data do Teste: " + DataTeste));
		document.add(new Paragraph("Médico Responsável: " + NomeMedico));
	}
	
	public void setGraficos(JFreeChart chart1, JFreeChart chart2, JFreeChart chart3) throws IOException, DocumentException {
		BufferedImage bufferedImage1 = chart1.createBufferedImage(550, 500);
        Image image1 = Image.getInstance(writer, bufferedImage1, 1.0f);
        document.add(image1);
        
        BufferedImage bufferedImage2 = chart2.createBufferedImage(550, 500);
        Image image2 = Image.getInstance(writer, bufferedImage2, 1.0f);
        document.add(image2);
        
        BufferedImage bufferedImage3 = chart3.createBufferedImage(550, 500);
        Image image3 = Image.getInstance(writer, bufferedImage3, 1.0f);
        document.add(image3);
	}
}
