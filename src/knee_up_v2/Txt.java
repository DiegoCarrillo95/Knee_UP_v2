package knee_up_v2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Txt {
	
	private Formatter x;
	private Scanner sc;
	private String nomeArquivo;
	
	public void openFile(int numFile,String NomePaciente, String DataTeste) {
		try {
			NomePaciente = NomePaciente.replaceAll("\\s",""); //Remove espaços das strings
			DataTeste = DataTeste.replaceAll("\\s","");		
			DataTeste = DataTeste.replaceAll("/",""); //Remove barras das datas	
			
			// Recebe String com horário atual no formato Hora Minuto Segundo
			DateFormat df = new SimpleDateFormat("HHmmss");
			Date today = Calendar.getInstance().getTime();
			String now = df.format(today);  
			
			nomeArquivo = String.format("teste"+ numFile + "-"+ NomePaciente + "-" + DataTeste + now + ".csv");
			x = new Formatter(nomeArquivo);	
			System.out.println("abriu arquibo");
		}
		catch(Exception e) {
			System.out.println("Erro ao abrir/criar o arquivo txt");
		}
	}
	
	public void addTensao(double data) {
		String s = String.format(Locale.US, "%.3f", data);
		x.format(s + "%n");
	}
	
	public void addTempo(double data) {
		String s = String.format(Locale.US, "%.3f", data);
		x.format(s + ",");
	}
	
	public void closeFile() {
		x.close();
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nome) {
		nomeArquivo = nome;
	}
	
	float[] readTensao() {
		
		float[] ret = new float[1000];
		int i = 0;
		String str;
		
		try {
			sc = new Scanner(new File(nomeArquivo));
			System.out.println("Abriu arquivo " + nomeArquivo + " para leitura de tensão");
		} catch (FileNotFoundException e) {
			System.out.println("Não abriu arquivo");
		}
			
		while(sc.hasNext()) {
			str = sc.next();
			str = str.substring(str.indexOf(",")+1,str.length());
			ret[i] = Float.parseFloat(str);
			i++;
		}
		return ret;
		
	}
	
	float[] readTempo() {
		
		float[] ret = new float[1000];
		int i = 0;
		String str;
		
		try {
			sc = new Scanner(new File(nomeArquivo));
			System.out.println("Abriu arquivo " + nomeArquivo + " para leitura de tempo");
		} catch (FileNotFoundException e) {
			System.out.println("Não abriu arquivo");
		}
			
		while(sc.hasNext()) {
			str = sc.next();
			str = str.substring(0,str.indexOf(","));
			ret[i] = Float.parseFloat(str);
			i++;
		}
		return ret;
		
	}
	
}
