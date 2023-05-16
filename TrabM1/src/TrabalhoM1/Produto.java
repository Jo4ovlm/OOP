package TrabalhoM1;

public class Produto {
    private String nome;
    private int id;
    private int qtdEstoque;
    private double preco;

    public Produto(String nome, int id, int qtdEstoque, double preco) {
        this.nome = nome;
        this.id = id;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
    }
    public Produto() {
        this.nome = "";
        this.id = -1;
        this.qtdEstoque = 0;
        this.preco = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return getNome() + "|" + getId() + "|" + getQtdEstoque()
                + "|" + getPreco();
    }
}
