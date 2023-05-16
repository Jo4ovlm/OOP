package TrabalhoM1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Produto> naoPereciveis = new ArrayList<Produto>();
        ArrayList<Perecível> pereciveis = new ArrayList<Perecível>();
        int dia = 0, mes = 0, ano = 0;

        while(!validaData(dia, mes, ano)) {
            System.out.println("Data de hoje: ");

            System.out.println("Dia: ");
            dia = input.nextInt();
            System.out.println("Mes: ");
            mes = input.nextInt();
            System.out.println("Ano: ");
            ano = input.nextInt();
        }
        Data dataHoje = new Data(dia,mes,ano);
        menu(naoPereciveis,pereciveis,dataHoje);


    }

    public static void menu(ArrayList<Produto> naoPereciveis, ArrayList<Perecível> pereciveis, Data dataHoje){
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        while(opcao != 4) {
            System.out.printf("1 - Incluir Produto %n" +
                    "2 - Vender Produto%n3 - Relatorio do estoque%n4 - Sair%nOpçao:");
        opcao = input.nextInt();
        if(opcao == 1)
            incluiProduto(naoPereciveis, pereciveis);
        if(opcao == 2)
            vendeProduto(naoPereciveis, pereciveis, dataHoje);
        if(opcao == 3)
            relatorio(naoPereciveis, pereciveis);
        }

    }

    public static void incluiProduto(ArrayList<Produto> naoPereciveis, ArrayList<Perecível> pereciveis){
        Scanner input = new Scanner(System.in);
        int opcao = 0;
        while(opcao != 1 && opcao != 2) {
            System.out.println("O produto é: 1 - Perecivel / 2 - Nao Perecivel");
            opcao = input.nextInt();
            if (opcao == 1)
                incluiPerecivel(pereciveis);
            if (opcao == 2)
                incluiNaoPerecivel(naoPereciveis);
        }
    }

    public static void incluiPerecivel(ArrayList<Perecível> pereciveis){
        Scanner input = new Scanner(System.in);
        System.out.println("Nome do produto: ");
        String nome = input.nextLine();
        boolean unico = false;
        int id = 0;
        while(!unico) {
            System.out.println("Id do produto: ");
            id = input.nextInt();
            unico = true;
            for (int i=0; i < pereciveis.size(); i++){
                if(id == pereciveis.get(i).getId()){
                    System.out.println("O id ja foi usado. Informe outro");
                    unico = false;
                    break;
                }
            }
        }
        int qtd = -1;
        double preco = -1;

        while(qtd < 0 || preco < 0) {
            System.out.println("Quantidade em estoque: ");
            qtd = input.nextInt();
            System.out.println("Preco: ");
            preco = input.nextDouble();
        }

        int dia = 0, mes = 0, ano = 0;

        while(!validaData(dia, mes, ano)) {
            System.out.println("Data de validade: ");

            System.out.println("Dia: ");
            dia = input.nextInt();
            System.out.println("Mes: ");
            mes = input.nextInt();
            System.out.println("Ano: ");
            ano = input.nextInt();
        }

        Perecível novoProduto = new Perecível(nome,id,qtd,preco,dia,mes,ano);
        pereciveis.add(novoProduto);

    }

    public static void incluiNaoPerecivel(ArrayList<Produto> naoPereciveis){
        Scanner input = new Scanner(System.in);
        System.out.println("Nome do produto: ");
        String nome = input.nextLine();
        boolean unico = false;
        int id = 0;
        while(!unico) {
            System.out.println("Id do produto: ");
            id = input.nextInt();
            unico = true;
            for (int i=0; i < naoPereciveis.size(); i++){
                if(id == naoPereciveis.get(i).getId()){
                    System.out.println("O id ja foi usado. Informe outro");
                    unico = false;
                    break;
                }
            }
        }
        int qtd = -1;
        double preco = -1;

        while(qtd < 0 || preco < 0) {
            System.out.println("Quantidade em estoque: ");
            qtd = input.nextInt();
            System.out.println("Preco: ");
            preco = input.nextDouble();
        }

        Produto novoProduto = new Produto(nome,id,qtd,preco);
        naoPereciveis.add(novoProduto);
    }

    public static void vendeProduto(ArrayList<Produto> naoPereciveis, ArrayList<Perecível> pereciveis, Data dataHoje){
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o Id do produto: ");
        int id = input.nextInt();
        int tipo = 0;
        while(tipo != 1 && tipo != 2) {
        System.out.println("Informe o tipo do produto(1-Perecivel/2-Nao Perecivel): ");
        tipo = input.nextInt();
            if (tipo == 1)
                vendePerecivel(pereciveis, id, dataHoje);
            if (tipo == 2)
                vendeNaoPerecivel(naoPereciveis, id);
        }
    }

    public static void vendePerecivel(ArrayList<Perecível> pereciveis, int id, Data dataHoje){
        Scanner input = new Scanner(System.in);
        int index = 0;
        boolean achou = false;
        for(int i = 0; i < pereciveis.size(); i++){
            if(pereciveis.get(i).getId() == id) {
                achou = true;
                index = i;
                break;
            }
        }

        if(!achou){
            System.out.println("Compra cancelada! Produto não encontrado");
            return;
        }

        double novoPreco = pereciveis.get(index).getPreco();
        System.out.println("Quantidade a ser vendida: ");
        int qtd = input.nextInt();
        if(qtd > pereciveis.get(index).getQtdEstoque()){
            System.out.println("Compra cancelada! Sem estoque disponivel");
            return;
        }
        if(pereciveis.get(index).estaVencido(dataHoje)){
            System.out.println("Compra cancelada! Produto vencido");
            return;
        }

        if(pereciveis.get(index).getValidade().faltaUm(dataHoje)){
            System.out.println("Qual o percentual de desconto?");
            double desconto = input.nextDouble();
            novoPreco = pereciveis.get(index).aplicaDesconto(desconto);
        }

        System.out.println("Produto vendido: " + pereciveis.get(index).getNome() + " Quantidade vendida: " + qtd + " Preco Unitario: R$" +  novoPreco +  " Preco total: R$" +
                             qtd*novoPreco) ;
        char sn = ' ';
        while((sn != 's' || sn != 'S') && (sn != 'n' || sn != 'N' )) {
            System.out.println("Confirmar a venda: S / N: ");
            sn = input.next().charAt(0);
            if (sn == 'n' || sn == 'N') {
                System.out.println("Compra cancelada!");
                return;
            } else if (sn == 's' || sn == 'S') {
                if(pereciveis.get(index).estaVencido(dataHoje)){
                    System.out.println("Compra cancelada! Produto Vencido");
                    return;
                }else {
                    pereciveis.get(index).setQtdEstoque(pereciveis.get(index).getQtdEstoque() - qtd);
                    System.out.println("Compra Confirmada!");
                    return;
                }
            }
        }
    }

    public static void vendeNaoPerecivel(ArrayList<Produto> naoPereciveis, int id){
        Scanner input = new Scanner(System.in);
        int i = 0;
        boolean achou = false;
        for( i = 0; i < naoPereciveis.size(); i++){
            if(naoPereciveis.get(i).getId() == id) {
                achou = true;
                break;
            }
        }

        if(!achou){
            System.out.println("Compra cancelada! Produto não encontrado");
            return;
        }

        System.out.println("Quantidade a ser vendida: ");
        int qtd = input.nextInt();

        if(qtd > naoPereciveis.get(i).getQtdEstoque()){
            System.out.println("Compra cancelada! Sem estoque disponivel");
            return;
        }


        System.out.println("Produto vendido: " + naoPereciveis.get(i).getNome() + "  Quantidade vendida: " + qtd  +" Preco Unitario: R$" + naoPereciveis.get(i).getPreco() + " Preco total: R$" +
                 qtd*naoPereciveis.get(i).getPreco());

        input.nextLine();
        char sn = ' ';
        while((sn != 's' || sn != 'S') && (sn != 'n' || sn != 'N' )) {
            System.out.println("Confirmar a venda: S / N: ");
            sn = input.next().charAt(0);
            if (sn == 'n' || sn == 'N') {
                System.out.println("Compra cancelada!");
                return;
            } else if (sn == 's' || sn == 'S') {
                naoPereciveis.get(i).setQtdEstoque(naoPereciveis.get(i).getQtdEstoque() - qtd);
                System.out.println("Compra Confirmada!");
                return;
            }
        }
    }

    public static void relatorio(ArrayList<Produto> naoPereciveis, ArrayList<Perecível> pereciveis){
        System.out.println("Produtos Pereciveis: ");
        System.out.println("Nome|id|qtd|preço|validade");
        for(int i=0; i < pereciveis.size(); i++){
            System.out.println(pereciveis.get(i).toString());
        }
        System.out.println("-----------------------------");
        System.out.println("Produtos Nao Pereciveis: ");
        System.out.println("Nome|id|qtd|preço");
        for(int i=0; i < naoPereciveis.size(); i++){
            System.out.println(naoPereciveis.get(i).toString());
        }
        System.out.println(" ");
    }

    public static boolean validaData(int dia, int mes, int ano){
        if(dia < 1 || dia > 31)
            return false;
        if (mes < 1 || mes > 12)
            return false;
        if (ano < 2023)
            return false;
        return true;
    }

}