package br.cms.Model;

public class CategoriaSocio {
    
    private long idCategoriaSocio;
    private String nomeCategoriaSocio;
    private double valorTitulo;
    private double valorMensalidade;
    private int lotacaoMinima;
    private int lotacaoMaxima;
    private String statusCategoria;

    public long getIdCategoriaSocio() {
        return idCategoriaSocio;
    }

    public void setIdCategoriaSocio(long idCategoriaSocio) {
        this.idCategoriaSocio = idCategoriaSocio;
    }

    public String getNomeCategoriaSocio() {
        return nomeCategoriaSocio;
    }

    public void setNomeCategoriaSocio(String nomeCategoriaSocio) {
        this.nomeCategoriaSocio = nomeCategoriaSocio;
    }

    public double getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(double valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public int getLotacaoMinima() {
        return lotacaoMinima;
    }

    public void setLotacaoMinima(int lotacaoMinima) {
        this.lotacaoMinima = lotacaoMinima;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public void setLotacaoMaxima(int lotacaoMaxima) {
        this.lotacaoMaxima = lotacaoMaxima;
    }

    public String getStatusCategoria() {
        return statusCategoria;
    }

    public void setStatusCategoria(String statusCategoria) {
        this.statusCategoria = statusCategoria;
    }
}
