package com.web.library.batchlibrary.service;

import com.web.library.batchlibrary.model.Emprunt;
import com.web.library.batchlibrary.proxy.FeignProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MailService {

    private static Logger logger = LoggerFactory.getLogger(ScheduledTaskLauncher.class);

    @Value("$spring.mail.username")
    private String username;


    private JavaMailSender javaMailSender;
//    private SimpleMailMessage message;
    private FeignProxy feignProxy;

    @Autowired
    public MailService(JavaMailSender javaMailSender, FeignProxy feignProxy) {
        this.javaMailSender = javaMailSender;
//        this.message = message;
        this.feignProxy = feignProxy;
    }


    public void sendMailReturnBook(String accessToken){

        List<Emprunt> loans = new ArrayList<>();

        List<Emprunt> empruntList = feignProxy.getEmpruntExpiredLoanDate(accessToken);

        for (Emprunt emprunts : empruntList) {
            Emprunt emprunt = new Emprunt();
            emprunt.setCopy(emprunts.getCopy());
            emprunt.setCustomer(emprunts.getCustomer());
            emprunt.setEmpruntDate(emprunts.getEmpruntDate());
            emprunt.setReturnDate(emprunts.getReturnDate());
            emprunt.setExtended(emprunts.getExtended());
            emprunt.setId(emprunts.getId());
            loans.add(emprunt);
        }

        for (Emprunt loan : loans){
            sendMessage(loan.getCustomer().getEmail(), loan.getCustomer().getFirstName(), loan.getCustomer().getLastName(),
                    loan.getCopy().getBook().getTitle(), formatDateToMail(loan.getReturnDate()));
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
        SimpleMailMessage mailMessage = new SimpleMailMessage(mailRecoveryModel());
        String text = String.format(Objects.requireNonNull(mailMessage.getText()),argFirst, argLast, argTitle, date);
        mailMessage.setTo(argTo);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
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

    private SimpleMailMessage mailRecoveryModel(){
        SimpleMailMessage writer = new SimpleMailMessage();
        writer.setTo("%s");
        writer.setFrom(username);
        writer.setSubject("Fin de période de prêt - Bibliothèque d'OC-City");
        writer.setText("Bonjour, %s %s" +
                "\n\nLa date de retour de votre prêt du livre \"%s\" était le : %s" +
                "\nIl est possible de prolonger le prêt du livre de 4 semaines, si ce n'est pas déjà fait." +
                "\nDans le cas contraire, pensez à ramener le livre au plus vite" +
                "\n\n\nBibliothèque d'OC-City" +
                "\n\n\n\nCeci est un message automatique, ne pas répondre à ce mail.");

        return writer;
    }
}
