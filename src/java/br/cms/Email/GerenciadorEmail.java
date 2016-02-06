package br.cms.Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class GerenciadorEmail {

    public void enviarEmailMensagem(EmailMensagem em) {

        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setSSLOnConnect(true);

            email.setAuthenticator(new DefaultAuthenticator("clubmsystem@gmail.com", "clubsystem4"));

            email.setFrom(em.getDestinatario(), "CMS");
            email.setSubject(em.getTitulo());
            email.addTo(em.getDestinatario(), em.getUsuario());

            StringBuilder builder = new StringBuilder();
            builder.append(em.getMensagem());

            email.setHtmlMsg(builder.toString());

            email.send();
        } catch (EmailException ex) {
            throw new RuntimeException(ex);
        }

    }
}
