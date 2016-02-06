package br.cms.Email;

public class EmailMensagem {

    private String destinatario;
    private String mensagem;
    private String titulo;
    private String usuario;

    public EmailMensagem(String destinatario, String titulo, String mensagem, String usuario) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.titulo = titulo;
        this.usuario = usuario;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    

}
