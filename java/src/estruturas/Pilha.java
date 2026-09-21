package estruturas;

public class Pilha<T> {

    private Object[] elementos;
    private int topo;

    public Pilha(int capacidade) {
        elementos = new Object[capacidade];
        topo = -1;
    }

    public void empilhar(T elemento) {

        if (topo == elementos.length - 1) {
            System.out.println("Pilha cheia!");
            return;
        }

        topo++;
        elementos[topo] = elemento;
    }

    @SuppressWarnings("unchecked")
    public T desempilhar() {

        if (estaVazia()) {
            return null;
        }

        T elemento = (T) elementos[topo];

        elementos[topo] = null;
        topo--;

        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T topo() {

        if (estaVazia()) {
            return null;
        }

        return (T) elementos[topo];
    }

    public boolean estaVazia() {
        return topo == -1;
    }
}