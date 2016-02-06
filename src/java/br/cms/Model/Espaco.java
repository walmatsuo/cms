package br.cms.Model;

public class Espaco {

    private long idEspaco;
    private String nomeEspaco;
    private int abertura;
    private int fechamento;
    private String descricaoEspaco;
    private String statusEspaco;

    public long getIdEspaco() {
        return idEspaco;
    }

    public void setIdEspaco(long idEspaco) {
        this.idEspaco = idEspaco;
    }

    public String getNomeEspaco() {
        return nomeEspaco;
    }

    public void setNomeEspaco(String nomeEspaco) {
        this.nomeEspaco = nomeEspaco;
    }

    public int getAbertura() {
        return abertura;
    }

    public void setAbertura(int abertura) {
        this.abertura = abertura;
    }

    public int getFechamento() {
        return fechamento;
    }

    public void setFechamento(int fechamento) {
        this.fechamento = fechamento;
    }

    public String getDescricaoEspaco() {
        return descricaoEspaco;
    }

    public void setDescricaoEspaco(String descricaoEspaco) {
        this.descricaoEspaco = descricaoEspaco;
    }

    public String getStatusEspaco() {
        return statusEspaco;
    }

    public void setStatusEspaco(String statusEspaco) {
        this.statusEspaco = statusEspaco;
    }
}
