package resposta.questao2;

public class Quadrado implements Forma{
	
	private double lado;
	
	public Quadrado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calculaArea() {
		return this.lado * this.lado;
	}

}
