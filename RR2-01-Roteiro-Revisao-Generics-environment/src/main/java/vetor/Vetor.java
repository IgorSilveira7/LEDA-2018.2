package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator comparadorMaximo;
	private Comparator comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = 0;
	}

	public void setComparadorMaximo(Comparator comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T o) {
		if (this.indice < this.tamanho) {
			this.arrayInterno[this.indice] = o;
			this.indice++;
		}
	}

	// Remove um objeto do vetor
	public T remover(T o) {
		T retorno = null;
		int i = 0;
		boolean achou = false;
		while(i < this.arrayInterno.length && !achou) {
			if (this.arrayInterno[i].equals(o)) {
				achou = true;
				retorno = o;
				this.arrayInterno[i] = this.arrayInterno[this.indice - 1];
				this.arrayInterno[this.indice - 1] = null;
				this.indice--;
			}
		}
		return retorno;
		
	}

	// Procura um elemento no vetor
	public T procurar(T o) {
		T retorno = null;
		int i = 0;
		boolean achou = false;
		while(i < this.arrayInterno.length && !achou) {
			if (this.arrayInterno[i].equals(o)) {
				achou = true;
				retorno = o;
			}
		}
		return retorno;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		boolean retorno = false;
		if (this.indice == 0) {
			retorno = true;
		}
		
		return retorno;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		boolean retorno = false;
		if (this.indice == this.tamanho) {
			retorno = true;
		}
		
		return retorno;
	}

}
