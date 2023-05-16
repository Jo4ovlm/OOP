package TrabalhoM1;
public class Perecível extends Produto {
    private Data validade;

    public Data getValidade() {
        return validade;
    }

    public Perecível(String nome, int id, int qtdEstoque, double preco, int dia, int mes, int ano) {
        super(nome, id, qtdEstoque, preco);
        this.validade = new Data(dia,mes,ano);
    }

    public boolean estaVencido(Data diaHoje){
        return !validade.ehValido(diaHoje);
    }

    public double aplicaDesconto(double desconto){
        return super.getPreco() - (super.getPreco() * (desconto/100));
    }

    @Override
    public String toString(){
        return super.getNome() + "|" + super.getId() + "|" + super.getQtdEstoque()
                + "|" + super.getPreco() + "|" + validade.getDia() + "/" + validade.getMes() + "/"
                + validade.getAno();
    }



}
