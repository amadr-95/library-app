package com.biblioteca.util;

import com.biblioteca.model.entidades.Usuario;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Properties;

public class EmailSender {

    private final static String EMAIL = "sabido.carrero.amador@iescamas.es";
    private final static String PASSWORD = "iescamas2DAW";
    private final static String ASUNTO = "Penalización Biblioteca";
    private final static String CUERPO =
            "Tienes libros pendientes de devolver, por favor, devuélvelos lo antes posible. Gracias.";

    public static void enviarCorreos(List<Usuario> destinatarios, HttpServletRequest request) {
        // Configurar las propiedades del servidor de correo
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        // Crear la sesión de correo
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            // Crear un mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            for (Usuario destinatario : destinatarios) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario.getEmail()));
            }

            message.setSubject(ASUNTO);
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(CUERPO, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            // Enviar el mensaje de correo
            Transport.send(message);

            request.setAttribute("exito", "Emails enviados correctamente");
        } catch (MessagingException e) {
            request.setAttribute("error", "No se pudieron enviar los emails");
        }

    }
}
