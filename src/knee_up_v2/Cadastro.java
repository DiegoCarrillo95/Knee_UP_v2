package knee_up_v2;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class Cadastro {

	
	/**
	 * 
	 */
	/**
	 * 
	 */
	private JFrame window;
	private JPanel contentPane;
	private JTextField MedicoResponsavel;
	private JTextField DataTeste;
	private JTextField DataCirurgia;
	private JTextField Codigo;
	private JTextField NomePaciente;
	//private static Cadastro frame;
	
	private boolean DadosInseridos; 
	private JTextField Cidade;
	
	
	
	
	
	
	
	public void FecharJanelaConectar () throws InterruptedException
	{	
		DadosInseridos = true;
		window.setVisible(false);
		window.dispose();	
	}
	
	public void MostrarJanelaCadastro ()	{ 
		window.setTitle("Knee UP");
		window.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	public Cadastro() {
		window = new JFrame();
		DadosInseridos = false;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel label_5 = new JLabel("Formul\u00E1rio");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_5.setBounds(150, 11, 136, 29);
		contentPane.add(label_5);
		JLabel label = new JLabel("Nome Paciente");
		label.setBounds(12, 54, 128, 14);
		contentPane.add(label);
		JLabel label_1 = new JLabel("C\u00F3digo");
		label_1.setBounds(10, 85, 128, 14);
		contentPane.add(label_1);
		JLabel label_2 = new JLabel("Data Cirurgia");
		label_2.setBounds(10, 118, 128, 14);
		contentPane.add(label_2);
		JLabel label_3 = new JLabel("Data Teste");
		label_3.setBounds(10, 155, 128, 14);
		contentPane.add(label_3);
		JLabel label_4 = new JLabel("Medico Responsavel");
		label_4.setBounds(12, 229, 128, 14);
		contentPane.add(label_4);
		
		//******************************
		//Variaveis que o usuario digita
		
		MedicoResponsavel = new JTextField();
		MedicoResponsavel.setColumns(10);
		MedicoResponsavel.setBounds(148, 226, 232, 20);
		contentPane.add(MedicoResponsavel);
		
		
		/*Pega a data do sistema e joga em DataTeste*/
		System.currentTimeMillis();  
		Date date = new Date(System.currentTimeMillis());
		String dataString = new SimpleDateFormat("dd/MM/yyyy").format(date);
		String data[] = dataString.split("\\/");
		
		DataTeste = new JTextField( data[0] + " /" + data[1] + " /" + data[2]);
		DataTeste.setBounds(148, 152, 86, 20);
		contentPane.add(DataTeste);
		DataTeste.setColumns(10);
		
		DataCirurgia = new JTextField();
		DataCirurgia.setColumns(10);
		DataCirurgia.setBounds(148, 115, 86, 20);
		contentPane.add(DataCirurgia);
		
		Codigo = new JTextField();
		Codigo.setColumns(10);
		Codigo.setBounds(150, 82, 136, 20);
		contentPane.add(Codigo);
		
		NomePaciente = new JTextField();
		NomePaciente.setColumns(10);
		NomePaciente.setBounds(150, 51, 230, 20);
		contentPane.add(NomePaciente);
		
		
		
		//Variaveis que o usuario digita
		//******************************

		JButton BotaoOK = new JButton("OK");
	
		BotaoOK.setBounds(197, 257, 89, 23);
		contentPane.add(BotaoOK);
		
		JLabel lblLocalidade = new JLabel("Cidade");
		lblLocalidade.setBounds(12, 187, 75, 14);
		contentPane.add(lblLocalidade);
		
		Cidade = new JTextField();
		Cidade.setText("Curitiba");
		Cidade.setBounds(150, 183, 152, 20);
		contentPane.add(Cidade);
		Cidade.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("* dd /mm /yyyy");
		lblNewLabel.setBounds(256, 155, 89, 14);
		contentPane.add(lblNewLabel);
		
		BotaoOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				//Fecha Janela "Conectar"
				try {FecharJanelaConectar();}
				catch (InterruptedException e) {e.printStackTrace();}
			
				
			}
		});
		
		window.add(contentPane);
	}
	
	public String getCidade() {
		
		String cidade = Cidade.getText();
		return cidade;
	}

	
	public String getMedicoResponsavel() {
		
		String medicoResponsavel = MedicoResponsavel.getText();
		return medicoResponsavel;
	}

	public String getDataTeste() {
		
		String dataTeste = DataTeste.getText();
		return dataTeste;

	}

	public String  getDataCirurgia() {
		
		String dataCirurgia = DataCirurgia.getText();
		return dataCirurgia;
		
	}

	public String getCodigo() {
		
		String codigo = Codigo.getText();
		return codigo;
		
	}

	
	public String getNomePaciente() {		
		
		String nomepaciente = NomePaciente.getText();
		return nomepaciente;
		
	}

	public boolean getDadosInseridos()
	{
		return this.DadosInseridos;
	}
	
}
