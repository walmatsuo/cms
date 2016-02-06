package br.cms.Model;

import java.util.Date;

public class Reserva {

    private long id;
    private String title;
    private Date start;
    private Date end;
    private String color;
    private String statusReserva;
    private Espaco espacoReserva;
    private Socio socioReserva;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getColor() {
        if ("a".equals(this.statusReserva)) {
            this.color = "#3CB371";
        } else if ("k".equals(this.statusReserva)) {
            this.color = "#ff8400";
        } else {
            this.color = "#236b8e";
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatusReserva() {
        return statusReserva;
    }

    public void setStatusReserva(String statusReserva) {
        this.statusReserva = statusReserva;
    }

    public Espaco getEspacoReserva() {
        return espacoReserva;
    }

    public void setEspacoReserva(Espaco espacoReserva) {
        this.espacoReserva = espacoReserva;
    }

    public Socio getSocioReserva() {
        return socioReserva;
    }

    public void setSocioReserva(Socio socioReserva) {
        this.socioReserva = socioReserva;
    }
}
