package knee_up_v2;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFrame;

public class Grafico {

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
	}
	
	void closeWindow() {
		frame.dispose();
	}
	JFreeChart getChart() {
		return chart;
		
	}
}
