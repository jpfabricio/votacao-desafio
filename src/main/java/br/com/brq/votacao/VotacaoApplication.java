package br.com.brq.votacao;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@EnableKafka
@EnableFeignClients
@SpringBootApplication
public class VotacaoApplication {

	private static Logger logger = LoggerFactory.getLogger(VotacaoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

	@KafkaListener(topics = "resultado-pautas-topic", groupId = "votacao")
	public void resultadoPautaListener(Object message) {
		logger.info("Sessão de pauta finalizada: " + message);
	}
}