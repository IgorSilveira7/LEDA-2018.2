package produto;

public class RepositorioProdutosArray {
	/**
	 * A estrutura (array) onde os produtos sao mantidos.
	 */
	private Produto[] produtos;

	/**
	 * A posicao do ultimo elemento inserido no array de produtos. o valor
	 * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
	 */
	private int index = -1;

	public RepositorioProdutosArray(int size) {
		super();
		this.produtos = new ProdutoNaoPerecivel[size];
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
		
		while ( i < this.produtos.length && retorno == -1) {
			if (this.produtos[i].getCodigo() == codigo) {
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
	public void inserir(ProdutoNaoPerecivel produto) {
		if (this.index < this.produtos.length) {
			this.index++;
			this.produtos[this.index] = produto;
		}
	}

	/**
	 * Atualiza um produto armazenado ou retorna um erro caso o produto nao
	 * esteja no array. Note que, para localizacao, o código do produto será
	 * utilizado.
	 */
	public void atualizar(ProdutoNaoPerecivel produto) {
		if (this.existe(produto.getCodigo())) {
			this.produtos[this.procurarIndice(produto.getCodigo())] = produto;
		} else {
			throw new RuntimeException("Produto nao existe no Array");
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
			this.produtos[indice] = null;
			this.corrigeArray(indice);
			this.index--;
		} else {
			throw new RuntimeException("Produto nao existe no Array");
		}
	}
	
	private void corrigeArray(int i) {
		for (int j = i; j < this.produtos.length; j++) {
			this.produtos[j] = this.produtos[j + 1];
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
			return this.produtos[this.procurarIndice(codigo)];
		} else {
			throw new RuntimeException("Produto nao existe no Array");
		}
	}

}

