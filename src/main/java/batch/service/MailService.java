package batch.service;

import batch.model.Emprunt;
import batch.proxy.FeignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SimpleMailMessage message;

    @Autowired
    private FeignProxy feignProxy;


    public void sendMailReturnBook(){

        List<Emprunt> empruntList = feignProxy.getEmpruntExpiredLoanDate();

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
        SimpleMailMessage message = new SimpleMailMessage();
        String text = String.format(Objects.requireNonNull(message.getText()),argFirst, argLast, argTitle, date);
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
        // TODO gérer le nouveau pattern ("dd MMM YYYY") avec Date

        return null;
    }
}
