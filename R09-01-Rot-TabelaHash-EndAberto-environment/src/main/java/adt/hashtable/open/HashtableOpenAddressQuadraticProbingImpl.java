package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(this.search(element) == null && element != null) {
			int i = 0;
			while (i < this.table.length) {
				int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
				if (this.table[aux] == null || this.table[aux].equals(new DELETED())) {
					this.table[aux] = element;
					this.elements++;
					return;
				} else {
					this.COLLISIONS++;
					i++;
				}
			}

			throw new HashtableOverflowException();
			
		}

	}

	@Override
	public void remove(T element) {
		int i = 0;

		while (i < this.table.length) {
			int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);

			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				this.table[aux] = new DELETED();
				this.elements--;
				break;
			} else {
				i++;
			}
		}
	}

	@Override
	public T search(T element) {
		int i = 0;
		T result = null;

		while (i < this.table.length) {
			int aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);

			if (this.table[aux] == null) {
				break;
			} else if (element.equals(this.table[aux])) {
				result = (T) this.table[aux];
			}

			i++;
		}

		return result;
	}

	@Override
	public int indexOf(T element) {
		int i = 0;
		int result = -1;
		int aux;

		do {
			aux = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, i);
			if (this.table[aux].equals(element)) {
				result = aux;
				break;
			} else {
				i++;
			}

		} while (this.table[aux] == null || i < this.table.length);

		return result;
	}
}
