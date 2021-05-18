package com.lczapparolli.avaliacao.goldenraspberyawards;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class GoldenRaspberyAwardsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Consulta o relatório de intervalo de vitórias dos produtores
     * e verifica se o retorno é o esperado
     * @throws Exception Dispara qualquer exceção que tenha ocorrido
     */
    @Test
    void getProducersByInterval() throws Exception {
        //Executa a requisição
        mockMvc.perform(get("/reports/producers/byInterval"))
               //Verifica se retornou com sucesso
               .andExpect(status().isOk()) 
               //Verifica se o retorno do método é o valor esperado
               .andExpect(content().string("{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}"));
    }

}
