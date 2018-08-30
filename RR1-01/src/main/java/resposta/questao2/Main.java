package resposta.questao2;

public class Main {

	public static void main(String[] args) {
		
		//Quadrado
		Forma q = new Quadrado(2);
		System.out.println(q.calculaArea()); // 4
		
		//Retangulo
		Forma r = new Retangulo(2, 2.5);
		System.out.println(r.calculaArea()); // 5
		
		//Triangulo
		Forma t = new Triangulo(3,6);
		System.out.println(t.calculaArea()); // 9
		
		//Circulo
		Forma c = new Circulo(6);
		System.out.println(c.calculaArea()); // 113,09...
		
		

	}

}
