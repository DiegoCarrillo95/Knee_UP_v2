package knee_up_v2;

public class Paciente {

	/**
	 * @param args
	 */
	
	
	private String nomePaciente;
	private String codigo;
	private String MedicoResponsavel;
	private String dataTeste;
	private String dataCirurgia;
	private String cidade;
	
	public String getCidade() {
		return cidade;
	}
	
	public String getNomePaciente() {
		return nomePaciente;
	}


	public String getCodigo() {
		return codigo;
	}


	public String getMedicoResponsavel() {
		return MedicoResponsavel;
	}


	public String getDataTeste() {
		return dataTeste;
	}


	public String getDataCirurgia() {
		return dataCirurgia;
	}


	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public void setMedicoResponsavel(String medicoResponsavel) {
		this.MedicoResponsavel = medicoResponsavel;
	}


	public void setDataTeste(String dataTeste) {
		this.dataTeste = dataTeste;
	}


	public void setDataCirurgia(String dataCirurgia) {
		this.dataCirurgia = dataCirurgia;
	}


	public void setCidade (String stringCidade) {
		this.cidade = stringCidade;
	}
	

	public Paciente (){}
	
	

}
