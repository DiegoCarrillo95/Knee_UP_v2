package knee_up_v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import com.fazecast.jSerialComm.SerialPort;

public class PortaSerial {

	SerialPort chosenPort;
	static OutputStream out;
	boolean portaconectada = false;

	PortaSerial(String portaselecionada) {
		chosenPort = SerialPort.getCommPort(portaselecionada);
		chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

		if (chosenPort.openPort()) {
			chosenPort.setBaudRate(19200);
			portaconectada = true;
			System.out.println("abriu porta");
		}
		else System.out.println("n abriru porta");
	}

	public boolean testarArduino() throws IOException // escreve mensagem específica no arduino e aguarda retorno
														// correto
	{
		String str = new String("knee_up"); // STRING PARA TESTAR O ARDUINO - NÃO MUDAR
		System.out.println(chosenPort.getBaudRate());

		Thread thread = new Thread() // cria thread de escrita pois é mais fácil usar os tempos de espera
		{
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
				for (int i = 0; i < 10; i++) { // mostrou-se necessário um loop com algumas iterações para que a
												// mensagem fosse enviada corretamente
					output.print(str);
					output.flush();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				output.close();
			}

		};
		thread.start();
		try {
			thread.join(); // espera thread de escrita terminar
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    System.out.println("escreveu");

		InputStream in = chosenPort.getInputStream();
		
		int received = in.read();
		System.out.println("recebeu " + received);

		boolean returnvalue = false;

		if (received == 42) {
			returnvalue = true; // CONFIRMA ARDUINO PROGRAMADO CORRETAMENTE
		}
		
		in.close();
		chosenPort.closePort();
		portaconectada = false;
		return returnvalue;
	}

	public Gráfico realizarTeste(int numTeste, String NomePaciente, String DataTeste) throws IOException {

		String str = new String("iniciarteste"); // STRING PARA TESTAR O ARDUINO - NÃO MUDAR
		
		if (chosenPort.openPort()) {
			portaconectada = true;
		}
		
		Thread thread = new Thread() { // cria thread de escrita pois é mais fácil usar os tempos de espera
		
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
				for (int i = 0; i < 10; i++) { // mostrou-se necessário um loop com algumas iterações para que a
												// mensagem fosse enviada corretamente
					output.print(str);
					output.flush();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
				}
				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
				System.out.println("enviou " + str);
				output.close();
			}

		};
		thread.start();
		try {
			thread.join(); // espera thread de escrita terminar
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InputStream in = chosenPort.getInputStream();
		int received = in.read();
		System.out.println("recebeu " + received);

		if (received == 77) {
			System.out.println("leitura confirmada");

		}

		in.close();
		
		Txt txt1 = new Txt();
		txt1.openFile(numTeste, NomePaciente, DataTeste);
		
		
		//criar thread para escutar porta serial:
		Thread threadLeitura = new Thread() {
			@Override public void run() {
				BufferedReader br = new BufferedReader(new InputStreamReader(chosenPort.getInputStream())); //BufferedReader é mais rápido que scanner
				int i = 0;
				int tensaoadc[] = new int[1000];
				int tempomillis[] = new int[1000];
				System.out.println("iniciou leitura");
				while(i<1000) {  
					try{
					tensaoadc[i] = Integer.parseInt(br.readLine());
					tempomillis[i] = Integer.parseInt(br.readLine());		
					//System.out.println(tensaoadc[i]);
					//System.out.println(tempomillis[i]);
					i++;
					} catch(Exception e) {
						System.out.println("erro");
						JOptionPane.showMessageDialog(null,"Erro na medição - reinicie o programa","Knee UP",JOptionPane.ERROR_MESSAGE);
					}					
				}
				
				 System.out.println("terminou leitura");			

				try {
					br.close();
				} catch (IOException e) {
					 System.out.println("erro ao fechar o BufferedReader");		
				}
				
				for(i = 0; i<1000 ;i++){
					txt1.addTempo(tempomillis[i]/1000.0);	
					txt1.addTensao((tensaoadc[i]*5.0)/1023.0);		
				}
				 System.out.println("terminou gravação em arquivo txt");		
			}
		};	
		threadLeitura.start();		
		try {
			threadLeitura.join();
		} catch (InterruptedException e) {
			System.out.println("erro na leitura do arduino");
		}
		
		txt1.closeFile();
		
		Gráfico graf = new Gráfico();
		graf.GeraGrafico(txt1.getNomeArquivo(), numTeste);
		
		System.out.println("terminou geração de gráfico");
		
		return graf;
	}

	public void FecharCom() {
		portaconectada = false;
		chosenPort.closePort();
		// threadAtiva = false;
	}

	public boolean getPortaconectada() {
		return portaconectada;
	}

}
