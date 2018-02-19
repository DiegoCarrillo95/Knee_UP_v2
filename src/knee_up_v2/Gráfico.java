package knee_up_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFrame;

public class Gráfico {

	private String codigo;
	private int arquivoParaLaudoOuImagem = 1; // 0 - Imagem, 1 - LaudoPrimeiraPagina, 2 - LaudoSegundaPagina
	private String TituloGrafico;
	private Txt arquivo;
	private JFreeChart chart;
	private ChartFrame frame;
	
	public void GeraGrafico(String nomeArquivo, int numeroTeste) {

		arquivo = new Txt();
		arquivo.setNomeArquivo(nomeArquivo);
		float[] dataTensao = arquivo.readTensao();
		float[] dataTempo = arquivo.readTempo();
		

		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries xySeries = new XYSeries("Teste " + numeroTeste);
		XYSeries marker = new XYSeries("marcador");
		float contador = 0;

		for (contador = 0; contador != 1000; contador++) {
			xySeries.add(dataTempo[(int) contador], dataTensao[(int) contador]);
			marker.add(1,contador/200);  // 1 segundo é quando acende o LED no arduino
		}
		
		
		dataset.addSeries(xySeries);
		dataset.addSeries(marker);

		TituloGrafico = "Gráfico teste " + numeroTeste;

		chart = ChartFactory.createXYLineChart(TituloGrafico, "Tempos (s)", "Tensão (V)", dataset,
				PlotOrientation.VERTICAL, true, false, false);
		

		frame = new ChartFrame(TituloGrafico, chart);
		frame.setVisible(true);
		frame.setSize(800, 650);

		/*// salvar o grafico como imagem .jpg
		FileOutputStream out = null;

		try {
			out = new FileOutputStream(new File(nomeArquivo + ".jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			ChartUtilities.writeChartAsPNG(out, chart, 777, 440);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
	
	void closeWindow() {
		frame.dispose();
	}
	JFreeChart getChart() {
		return chart;
		
	}
}
