package resposta.questao2;

public class Triangulo implements Forma {
	
	private double altura;
	private double base;
	
	public Triangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	
	@Override
	public double calculaArea() {
		return (this.base * this.altura) / 2;
	}
	
	

}
