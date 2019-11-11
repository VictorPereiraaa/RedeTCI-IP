package networkEcho;

public class Info {

	public static final String sisNome = "Network Echo III"; //nome do sistema
	public static final String sisVersao = "1.00"; //vers�o do sistema

	public static final int porta = 5000; //porta para iniciar a conex�o
	public static final int delay = 1000; //delay para atendimento dos clientes
	public static final int tamMax = 2;  //tamanho m�ximo da matriz gerada

	public static final String getNomeVersao() {
		return (Info.sisNome + " - " + Info.sisVersao);
	}

}
