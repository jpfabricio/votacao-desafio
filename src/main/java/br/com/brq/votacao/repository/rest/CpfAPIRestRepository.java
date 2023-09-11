package br.com.brq.votacao.repository.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "cpfApi", url = "${url.api.cpf}")
public interface CpfAPIRestRepository {

    @PostMapping(value = "/ferramentas_online.php", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String validaCpf(Map<String, ?> body);
}