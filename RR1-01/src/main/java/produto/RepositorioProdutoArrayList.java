package produto;

import java.util.ArrayList;

/**
 * Classe que representa um repositório de produtos usando ArrayList como
 * estrutura sobrejacente. Alguns métodos (atualizar, remover e procurar) ou
 * executam com sucesso ou retornam um erro. Para o caso desde exercício, o erro
 * será representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 *
 * @author Adalberto
 */
public class RepositorioProdutoArrayList {

	/**
	 * A estrutura onde os produtos sao mantidos. Voce nao precisa se preocupar
	 * por enquanto com o uso de generics em ArrayList.
	 */
	private ArrayList<Produto> produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutoArrayList(int size) {
		super();
		this.produtos = new ArrayList();
	}

	/**
	 * Recebe o codigo do produto e devolve o indice desse produto no array ou
	 * -1 caso ele nao se encontre no array. Esse método é util apenas na
	 * implementacao com arrays por questoes de localizacao. Outras classes que
	 * utilizam outras estruturas internas podem nao precisar desse método.
	 * 
	 * @param codigo
	 * @return
	 */
	private int procurarIndice(int codigo) {
		int retorno = -1;
		int i = 0;
		
		while (i < this.produtos.size() && retorno == -1) {
			if (this.produtos.get(i).getCodigo() == codigo) {
				retorno = i;
			}
			i++;
		}
		return retorno;
	}

	/**
	 * Recebe o codigo e diz se tem produto com esse codigo armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean existe(int codigo) {
		boolean existe = false;
		
		if (this.procurarIndice(codigo) > -1) {
			existe = true;
		}
		return existe;
	}

	/**
	 * Insere um novo produto (sem se preocupar com duplicatas)
	 */
	public void inserir(Produto produto) {
		this.produtos.add(produto);
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(Produto produto) {
		if (this.existe(produto.getCodigo())) {
			int indice = this.procurarIndice(produto.getCodigo());
			this.produtos.set(indice, produto);
		} else {
			throw new RuntimeException("Produto nao existe");
		}
	}

	/**
	 * Remove produto com determinado codigo, se existir, ou entao retorna um
	 * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
	 * array.
	 * 
	 * @param codigo
	 */
	public void remover(int codigo) {
		if (this.existe(codigo)) {
			int indice = this.procurarIndice(codigo);
			this.produtos.remove(indice);
			this.corrigeArrayList(indice);
			this.index--;
		} else {
			throw new RuntimeException("Produto nao existe");
		}
		
	}
	
	private void corrigeArrayList(int i) {
		for (int j = i; j < this.produtos.size(); j++) {
			this.produtos.set(j, this.produtos.get(j + 1));
		}
	}

	/**
	 * Retorna um produto com determinado codigo ou entao um erro, caso o
	 * produto nao esteja armazenado
	 * 
	 * @param codigo
	 * @return
	 */
	public Produto procurar(int codigo) {
		if (this.existe(codigo)) {
			Produto p = this.produtos.get(this.procurarIndice(codigo));
			return p;
		}else {
			// Aqui seria lançada a exceção,
			// mas o teste não espera um erro, e sim null.
			return null;
		}
	}
}
