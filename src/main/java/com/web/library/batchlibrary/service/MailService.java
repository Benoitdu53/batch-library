package com.web.library.batchlibrary.service;

import com.web.library.batchlibrary.model.Emprunt;
import com.web.library.batchlibrary.proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    private JavaMailSender javaMailSender;
    private SimpleMailMessage message;
    private FeignProxy feignProxy;

    @Autowired
    public MailService(JavaMailSender javaMailSender, SimpleMailMessage message, FeignProxy feignProxy) {
        this.javaMailSender = javaMailSender;
        this.message = message;
        this.feignProxy = feignProxy;
    }


    public void sendMailReturnBook(String accessToken){

        List<Emprunt> empruntList = feignProxy.getEmpruntExpiredLoanDate(accessToken);

        for (Emprunt emprunt : empruntList){
            sendMessage(emprunt.getCustomer().getEmail(), emprunt.getCustomer().getFirstName(), emprunt.getCustomer().getLastName(),
                    emprunt.getCopy().getBook().getTitle(), formatDateToMail(emprunt.getReturnDate()));
        }
    }

    /**
     * Cette méthode envoie le message pré configurer
     * @param argTo
     * @param argFirst
     * @param argLast
     * @param argTitle
     * @param date
     */
    private void sendMessage(String argTo, String argFirst, String argLast, String argTitle, String date){
        SimpleMailMessage mailMessage = new SimpleMailMessage(message);
        String text = String.format(Objects.requireNonNull(mailMessage.getText()),argFirst, argLast, argTitle, date);
        message.setTo(argTo);
        message.setText(text);
        javaMailSender.send(message);
    }

    private void sendSimpleMessage(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    /**
     * Méthode qui formatte la date de retour prévue
     * @param date
     * @return
     */
    private String formatDateToMail(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(date);
    }
}
