package batch.proxy;

import batch.model.Emprunt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface FeignProxy {

// Méthodes utilisées pour le batch

    // Récupération des prêts expirés
    @GetMapping(value="empruntDelay")
    List<Emprunt> getEmpruntExpiredLoanDate();
}
