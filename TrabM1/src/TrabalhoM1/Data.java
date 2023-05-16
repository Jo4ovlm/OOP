package TrabalhoM1;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        if(dia < 1 || dia > 31)
            throw new IllegalArgumentException("Precisa ser entre 1 - 31");
        if (mes < 1 || mes > 12)
            throw new IllegalArgumentException("Precisa ser entre 1 -12");
        if (ano < 2023)
            throw new IllegalArgumentException("Precisa ser maior que 2023");
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }


    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public boolean ehValido(Data dataHoje){
        boolean vencido = true;
        if(dataHoje.getAno() > this.getAno()) {
            vencido = false;
        } else if(dataHoje.getAno() == this.getAno()){
            if(dataHoje.getMes() > this.getMes()) {
                vencido = false;
            }else if(dataHoje.getMes() == this.getMes()){
                if(dataHoje.getDia() > this.getDia())
                    vencido = false;
            }
        }
        return vencido;
    }

    public boolean faltaUm(Data dataHoje){
        if(this.mes == dataHoje.getMes() && this.ano == dataHoje.getAno()){
            if(this.dia - dataHoje.getDia() == 1)
                return true;
        }
        return false;
    }

}
