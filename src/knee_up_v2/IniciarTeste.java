package knee_up_v2;

import javax.swing.ImageIcon;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JProgressBar;

//import java.io.*;


//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import com.fazecast.jSerialComm.SerialPort;
import com.itextpdf.text.DocumentException;

public class IniciarTeste  {
	
	private JFrame frame;
	private String [] portas = new String[10];
	//private boolean janelaAbertaFechada = true; 
	private boolean desconectado = true; 
	private JComboBox<String> ComboBoxDePortas ;
	private PortaSerial portaserial;
	private JButton button_1 ;
	//private  JConectar JanelaConectar = new JConectar();
	private ImageIcon FiguraStatus_OFF; 
	//private ImageIcon FiguraStatus_ON; 
	private JLabel FiguraStatus ;
	private JPanel contentPane;
	//private  IniciarTeste frame;
	private JLabel StatusConexão;
	private JLabel LabelNomePaciente;
	private JLabel LabelCodigo;
	private JLabel LabelDataCirurgia ;
	private JLabel LabelDataTeste ;
	private JLabel LabelMedico ;
	
	private String NomePaciente;
	private String Codigo;
	private String DataCirurgia ;
	private String DataTeste ;
	private String NomeMedico;
	
	private boolean BotaoIniciarTeste = false;
	
	
	public void setBotaoIniciarTeste(boolean botao) {
		BotaoIniciarTeste = botao;
	}
	
	public void MostrarJanelaIniciarTeste()	{
		
		LabelNomePaciente.setText(NomePaciente); 
		LabelCodigo.setText(Codigo);   //Seta codigo
		LabelDataCirurgia.setText(DataCirurgia);   //Seta data da cirurgia
		LabelDataTeste.setText(DataTeste);   //Seta data do teste
		LabelMedico.setText(NomeMedico);   //Seta nome do medico
		
		frame.setVisible(true);
		frame.setTitle("Knee UP");
					
			
	}
	
	public void FecharJanelaIniciarTeste () throws InterruptedException
	{frame.dispose();	}
	
	/**
	 * Create the frame.
	 */
	public IniciarTeste() {
		
	
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 400, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//COLOCA IMAGEM CONECÇÃO NA JANELA
		ImageIcon icon = new ImageIcon("C:\\Documents and Settings\\Administrador\\Desktop\\workspace\\IC_Joelho_v2\\Arquivos_Imagens\\Logo_Conectar.GIF");
		JLabel label_0 = new JLabel(icon);
		label_0.setHorizontalAlignment(SwingConstants.CENTER);
		label_0.setBounds(93, 11, 205, 99);
		contentPane.add(label_0);
		
		//CARREGA IMAGENS DE STATUS (ON/OFF)
		FiguraStatus_OFF = new ImageIcon("C:\\Documents and Settings\\Administrador\\Desktop\\workspace\\IC_Joelho_v2\\Arquivos_Imagens\\Status_OFF.JPG");
	//	FiguraStatus_ON = new ImageIcon("C:\\Documents and Settings\\Administrador\\Desktop\\workspace\\IC_Joelho_v2\\Arquivos_Imagens\\Status_ON.JPG");
		
		//COLOCA IMAGEM STATUS_OFF NA JANELA
		FiguraStatus = new JLabel(FiguraStatus_OFF);
		FiguraStatus.setHorizontalAlignment(SwingConstants.CENTER);
		FiguraStatus.setBounds(155, 122, 14, 14);
		contentPane.add(FiguraStatus);
		
		
		
		JLabel label = new JLabel("Nome Paciente");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Arial", Font.BOLD, 12));
		label.setBackground(new Color(46, 139, 87));
		label.setBounds(20, 280, 128, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setForeground(new Color(0, 0, 128));
		label_1.setFont(new Font("Arial", Font.BOLD, 12));
		label_1.setBackground(new Color(46, 139, 87));
		label_1.setBounds(20, 310, 128, 23);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Data Cirurgia");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(new Color(0, 0, 128));
		label_2.setFont(new Font("Arial", Font.BOLD, 12));
		label_2.setBackground(new Color(46, 139, 87));
		label_2.setBounds(20, 345, 128, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Data Teste");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(new Color(0, 0, 128));
		label_3.setFont(new Font("Arial", Font.BOLD, 12));
		label_3.setBackground(new Color(46, 139, 87));
		label_3.setBounds(20, 375, 128, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Medico Responsavel");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setForeground(new Color(0, 0, 128));
		label_4.setFont(new Font("Arial", Font.BOLD, 12));
		label_4.setBackground(new Color(46, 139, 87));
		label_4.setBounds(10, 406, 138, 14);
		contentPane.add(label_4);
		
		JButton btnIniciarTeste = new JButton("Iniciar Teste");
		btnIniciarTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//BOTÃO INICIAR TESTE
				
				if(desconectado) {
					JOptionPane.showMessageDialog(null,
							"Para iniciar o teste, conecte-se antes a uma porta serial!",
							"Knee UP",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					BotaoIniciarTeste = true;
					try {
						FecharJanelaIniciarTeste();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						realizarTeste();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnIniciarTeste.setBounds(151, 432, 118, 23);
		contentPane.add(btnIniciarTeste);
		
		LabelNomePaciente = new JLabel("New label");
		LabelNomePaciente.setBounds(156, 278, 236, 20);
		contentPane.add(LabelNomePaciente);
		
		
		LabelCodigo = new JLabel("New label");
		LabelCodigo.setBounds(158, 312, 136, 20);
		contentPane.add(LabelCodigo);
		
		LabelDataCirurgia = new JLabel("New label");
		LabelDataCirurgia.setBounds(158, 343, 120, 20);
		contentPane.add(LabelDataCirurgia);
		
		LabelDataTeste = new JLabel("New label");
		LabelDataTeste.setBounds(158, 373, 120, 20);
		contentPane.add(LabelDataTeste);
		
	    LabelMedico = new JLabel("New label");
		LabelMedico.setBounds(168, 407, 224, 14);
		contentPane.add(LabelMedico);	
		
		JLabel lblStatusConeco = new JLabel("Status da conexão");
		lblStatusConeco.setBounds(20, 122, 150, 14);
		contentPane.add(lblStatusConeco);
		
		StatusConexão = new JLabel("DESCONECTADO");
		StatusConexão.setBounds(179, 122, 138, 14);
		contentPane.add(StatusConexão);
		
		JButton button = new JButton("Procurar Portas ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//*********************************
				//Botão de PROCURAR portar SERIAIS
				
				ComboBoxDePortas.removeAllItems();
				
				//Obtem as portas existentes no sistema e coloca na string portas
				
				SerialPort[] portNames = SerialPort.getCommPorts();

				//Imprime as portas encontradas no ComboBox
				for (int i = 0 ; i < portNames.length ; i ++ ) {
					ComboBoxDePortas.addItem(portNames[i].getSystemPortName());	
					button_1.setEnabled(true);
				}
				if(portNames.length==0) {
					ComboBoxDePortas.addItem("Nenhuma Porta Encontrada");
				}
			
				//*********************************
				//Botão de PROCURAR portar SERIAIS
				
			}
		});
		
		button.setBounds(10, 171, 132, 23);
		contentPane.add(button);
		
		
		ComboBoxDePortas = new JComboBox<String>();
		ComboBoxDePortas.setBounds(169, 172, 182, 20);
		contentPane.add(ComboBoxDePortas);
		
		
		button_1 = new JButton("Conectar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//***********************************************************
				//Botão de CONECTAR software com a porta SERIAIL selecionada

				if (desconectado==true)
				{ // CONECTA A PORTA
					if(ComboBoxDePortas.getSelectedItem().equals("Nenhuma Porta Encontrada"))
					{
						JOptionPane.showMessageDialog(null,"É necessário definir uma porta!","Knee UP",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						//Coloca a porta serial seleciona em Srting[0];
						portas[0]=(String) ComboBoxDePortas.getSelectedItem();
						//Conecta a porta serial definida por portas[0] com Baud-Rate de 19200
						portaserial = new PortaSerial(portas[0]);
						if(portaserial.getPortaconectada()) 
						{
							try {
								if(portaserial.testarArduino()) { //TESTA SE ESTÁ CONECTADO A UM ARDUINO COM PROGRAMAÇÃO CORRETA
									button_1.setText("Desconectar"); //muda texto do botão
									//Imprime no label a palavra conectado + a porta q foi selecionada
									StatusConexão.setText("CONECTADO - " + ComboBoxDePortas.getSelectedItem());
									desconectado = false; //muda variavel de controle do loop
									//muda a imagem de status para verde
									//FiguraStatus.setIcon(FiguraStatus_ON);
								}
								else
								{
									JOptionPane.showMessageDialog(null,"A porta selecionada não responde corretamente: Verifique se ela está conectada a um Arduino rodando o software correto!",
											"Knee UP",JOptionPane.INFORMATION_MESSAGE);
									portaserial.FecharCom();
									portaserial = null;
									ComboBoxDePortas.removeItem(ComboBoxDePortas.getSelectedItem());
								}
							} catch (HeadlessException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println("DEU RUIM");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Não foi possível abrir a porta selecionada: verifique se ela está conectada a um Arduino rodando o software correto!",
								"Knee UP",JOptionPane.INFORMATION_MESSAGE);
							portaserial.FecharCom();
							portaserial = null;
							ComboBoxDePortas.removeItem(ComboBoxDePortas.getSelectedItem());
							} 
					}
				}
				//Chama medotos de Configurações para abrir porta
			/*	sc.HabilitarEscrita();
				sc.ObterIdDaPorta();
				sc.AbrirPorta();	*/			

				else
				{   //DESCONECTA A PORTA
					portaserial.FecharCom();
					button_1.setText("Conectar");
					desconectado = true;
					StatusConexão.setText("DESCONECTADO");
					//FiguraStatus.setIcon(FiguraStatus_OFF);
				}
			
				//***********************************************************
				//Botão de CONECTAR software com a porta SERIAIL selecionada
				 
			}
		});
		button_1.setBounds(135, 223, 117, 23);
		contentPane.add(button_1);
		button_1.setEnabled(false);
		
		frame.add(contentPane,BorderLayout.CENTER);
	
	}


	public void realizarTeste() throws IOException
	{
		JOptionPane.showMessageDialog(null,"Será Iniciada uma série de 3 Testes","Knee UP",JOptionPane.INFORMATION_MESSAGE); 
		
		JOptionPane.showMessageDialog(null,"TESTE 1 - Para iniciar pressione OK","Knee UP",JOptionPane.INFORMATION_MESSAGE);
		
		Grafico graf1 = portaserial.realizarTeste(1,NomePaciente,DataTeste);
		
		JOptionPane.showMessageDialog(null,"TESTE 2 - Para iniciar pressione OK","Knee UP",JOptionPane.INFORMATION_MESSAGE);
		
		Grafico graf2 = portaserial.realizarTeste(2,NomePaciente,DataTeste);
		
		JOptionPane.showMessageDialog(null,"TESTE 3 - Para iniciar pressione OK","Knee UP",JOptionPane.INFORMATION_MESSAGE);
		
		Grafico graf3 = portaserial.realizarTeste(3,NomePaciente,DataTeste);
		
		Pdf relatorio = new Pdf();
		try {
				
			// Recebe String com horário atual no formato Hora Minuto Segundo
			DateFormat df = new SimpleDateFormat("HHmmss");
			Date today = Calendar.getInstance().getTime();
			String now = df.format(today);  
			
			String nomeArquivo = String.format(NomePaciente.replaceAll("\\s","") + "-" + DataTeste.replaceAll("\\s","").replaceAll("/","") + now);
			relatorio.createPdf(nomeArquivo + ".pdf");
			System.out.println("pdf criado");
			relatorio.setDadosPaciente(NomePaciente, Codigo, DataCirurgia, DataTeste, NomeMedico);
			System.out.println("pdf escrito");
			relatorio.setGraficos(graf1.getChart(), graf2.getChart(), graf3.getChart());
			System.out.println("pdf graficos");
			relatorio.closePdf();
			System.out.println("pdf fechado");
		} catch (DocumentException e) {
			System.out.println("erro no pdf");
		}
		
		JOptionPane.showMessageDialog(null,"Testes Terminados. Arquivo PDF criado!","Knee UP",JOptionPane.INFORMATION_MESSAGE);
		graf1.closeWindow();
		graf2.closeWindow();
		graf3.closeWindow();
	}
	
	
	public  void setNomePaciente(String labelNomePaciente) {
		NomePaciente = labelNomePaciente;
	
	}

	public  void setCodigo(String labelCodigo) {
		Codigo = labelCodigo;
	}

	public  void setDataCirurgia(String labelDataCirurgia) {
		DataCirurgia = labelDataCirurgia;
	}

	public  void setDataTeste(String labelDataTeste) {
		DataTeste = labelDataTeste;
	}

	public  void setNomeMedico(String labelNomeMedico) {
		NomeMedico = labelNomeMedico;
	}
	
	public  boolean getClicouBotaoIniciarTeste() {
		return BotaoIniciarTeste ;
	}
	
	
	
}//FIM DA CLASSE 
