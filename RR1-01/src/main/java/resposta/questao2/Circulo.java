package resposta.questao2;

public class Circulo implements Forma{
	
	private double raio;
	
	public Circulo(double raio) {
		this.raio = raio;
	}

	@Override
	public double calculaArea() {
		return Math.PI * Math.pow(raio, 2);
	}

}
