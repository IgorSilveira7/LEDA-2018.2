package vetor;

public class TestarVetor {

	public static void main(String[] args) {
		Vetor<Aluno> v = new Vetor<Aluno>(10);
		Aluno a = new Aluno("a1", 9.0);
		Aluno b = new Aluno("a2", 8.0);
		Aluno c = new Aluno("a3", 7.0);
		Aluno d = new Aluno("a4", 10.0);
		Aluno e = new Aluno("a5", 6.0);
		
		v.setComparadorMaximo(new Maximo());
		v.setComparadorMinimo(new Minimo());
		
		
		v.inserir(a);
		v.inserir(b);
		v.inserir(c);
		v.inserir(d);
		v.inserir(e);
		
	
		System.out.println(v.maximo().getNome());
		System.out.println(v.minimo().getNome());
	}
}
