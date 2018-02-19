package knee_up_v2;



public class Principal {

	private static Cadastro cadastro;
	private static Paciente paciente;
	private static IniciarTeste iniciarteste;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		cadastro = new Cadastro();
		paciente = new Paciente();
		iniciarteste = new IniciarTeste();
		
		cadastro.MostrarJanelaCadastro();
		
		Thread verifica_cadastro = new Thread() {    //thread que verifica se a janela de cadastro foi completa
	        public void run() {
	                while (!cadastro.getDadosInseridos())
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	            }
	    };
	    
	    verifica_cadastro.start();
	    verifica_cadastro.join();
		//System.out.println("3"); 
		//Carrega dados inseridos na janela Cadastro no objeto da classe "Paciente"
		//Dados digitados como Nome do Paciente, Data do Teste, Data Cirurgia, etc..
		paciente.setNomePaciente(cadastro.getNomePaciente());
		paciente.setCodigo(cadastro.getCodigo());
		paciente.setDataCirurgia(cadastro.getDataCirurgia());
		paciente.setDataTeste(cadastro.getDataTeste());
		paciente.setMedicoResponsavel(cadastro.getMedicoResponsavel());
		paciente.setCidade(cadastro.getCidade());
		//Fecha Jnanela Cadastro da classe (JCadastro)
		//cadastro.FecharJanelaConectar();
			
		iniciarteste.setNomePaciente(paciente.getNomePaciente());
		iniciarteste.setNomeMedico(paciente.getMedicoResponsavel());
		iniciarteste.setDataTeste(paciente.getDataTeste());
		iniciarteste.setDataCirurgia(paciente.getDataCirurgia());
		iniciarteste.setCodigo(paciente.getCodigo());
	
	//Chama Janela da classe Iniciar Teste
		iniciarteste.MostrarJanelaIniciarTeste();
		
		Thread verifica_inicio = new Thread() {    //thread que verifica se teste foi completo
	        public void run() {
	                while (!iniciarteste.getClicouBotaoIniciarTeste())
	                    try {
	                        Thread.sleep(100);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	               // System.out.println("Working now");
	            }
	    };
	    
	    verifica_inicio.start();
	    verifica_inicio.join();
	    
	    System.out.println("fim");
		
	}

}
