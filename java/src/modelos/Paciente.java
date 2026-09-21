package modelos;

public class Paciente {

    private int id;
    private String nome;
    private int idade;
    private Prioridade prioridade;

    public Paciente(int id, String nome, int idade, Prioridade prioridade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Nome: " + nome
                + " | Idade: " + idade
                + " | Prioridade: " + prioridade;
    }
}