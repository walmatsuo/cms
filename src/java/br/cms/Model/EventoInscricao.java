package br.cms.Model;

public class EventoInscricao {

    private long idEventoInscricao;
    private Evento evento;
    private Socio socio;
    private String statusEventoInscricao;

    public long getIdEventoInscricao() {
        return idEventoInscricao;
    }

    public void setIdEventoInscricao(long idEventoInscricao) {
        this.idEventoInscricao = idEventoInscricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public String getStatusEventoInscricao() {
        return statusEventoInscricao;
    }

    public void setStatusEventoInscricao(String statusEventoInscricao) {
        this.statusEventoInscricao = statusEventoInscricao;
    }

}
