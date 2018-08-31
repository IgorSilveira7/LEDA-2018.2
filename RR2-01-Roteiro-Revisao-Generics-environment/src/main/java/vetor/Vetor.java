package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T extends Comparable<T>> {

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
		this.arrayInterno = (T[]) new Comparable[tamanho];
		this.tamanho = tamanho;
		this.indice = 0;
	}

	public void setComparadorMaximo(Comparator<T> comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator<T> comparadorMinimo) {
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
			i++;
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
			i++;
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
	
	public T maximo() {
		T maximo = this.arrayInterno[0];
		int i = 0;
		
		while(i < this.indice) {
			if (this.comparadorMaximo.compare(maximo, this.arrayInterno[i]) < 0) {
				maximo = this.arrayInterno[i];
			}
			i++;
		}
		
		return maximo;
	}
	
	public T minimo() {
		T minimo = this.arrayInterno[0];
	    int i = 0;
		
		while(i < this.indice) {
			if (this.comparadorMinimo.compare(minimo, this.arrayInterno[i]) < 0) {
				minimo = this.arrayInterno[i];
			}
			i++;
		}
		
		return minimo;
	}
	
	

}
