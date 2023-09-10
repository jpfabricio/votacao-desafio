package br.com.brq.votacao.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErroDTO {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    private String error;

    private Integer status;

    private List<String> message;

    private String path;

    @Setter
    private int code;

}
