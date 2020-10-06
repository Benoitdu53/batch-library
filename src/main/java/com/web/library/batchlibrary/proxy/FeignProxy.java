package com.web.library.batchlibrary.proxy;

import com.web.library.batchlibrary.model.Emprunt;
import com.web.library.batchlibrary.model.LoginBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "library", url = "localhost:8181")
public interface FeignProxy {

// Méthodes utilisées pour le batch

    // Récupère le token "Authorization"
    @PostMapping("authenticate")
    ResponseEntity<?> validationAuthentication(@RequestBody LoginBean loginBean);

    // Récupération des prêts expirés
    @GetMapping(value="empruntDelay")
    List<Emprunt> getEmpruntExpiredLoanDate(@RequestHeader("Authorization") String accessToken);
}
