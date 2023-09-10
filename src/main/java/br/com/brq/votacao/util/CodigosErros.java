package br.com.brq.votacao.util;


import java.util.Map;

public class CodigosErros {

    public static final Map<String, Object> CPF_JA_UTILIZADO = Map
            .of("mensagem", "Já existe um associado cadastrado com esse CPF!", "codigo", 1);

    public static final Map<String, Object> JA_EXISTE_PAUTA_COM_ESSE_NOME = Map
            .of("mensagem", "Já existe uma pauta criada com esse nome!", "codigo", 2);

    public static final Map<String, Object> DATA_EXPIRACAO_INVALIDA = Map
            .of("mensagem", "Data de expiração de sessão inválida!", "codigo", 3);

    public static final Map<String, Object> SESSAO_NAO_DISPONIVEL = Map
            .of("mensagem", "Sessão de votação para esta pauta não está disponivel!", "codigo", 4);

    public static final Map<String, Object> ASSOCIADO_JA_VOTOU = Map
            .of("mensagem", "Associado já votou nessa pauta!", "codigo", 5);

    public static final Map<String, Object> VOTACAO_AINDA_ATIVA = Map
            .of("mensagem", "A sessao ainda não acabou ", "codigo", 6);

    public static final int ERRO_DE_VALIDACAO_DO_DTO = 0;

    public static String getMensagem(Map<String, Object> map){
        return (String) map.get("mensagem");
    }

    public static int getCodigo(Map<String, Object> map){
        return (int) map.get("codigo");
    }


}
