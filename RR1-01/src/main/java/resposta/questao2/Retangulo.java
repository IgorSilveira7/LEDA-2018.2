package resposta.questao2;

public class Retangulo implements Forma {
	
	private double base;
	private double altura;
	
	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}

	@Override
	public double calculaArea() {
		return this.base * this.altura;
	}

}
