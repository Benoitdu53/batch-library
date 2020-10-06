package batch.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class BatchConfiguration {

    @Value("$spring.mail.username")
    private String username;

    @Bean
    public SimpleMailMessage mailRecoveryModel(){
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
