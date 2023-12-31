package com.volgsys.api.Controller;


import com.volgsys.api.model.CepResponse;
import com.volgsys.api.CepApi;
import com.volgsys.api.service.ServiceCEP;
import io.swagger.annotations.Api;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = {"Endereco"}, value = "Controller", description = "buscar dados de endereco")
public class ControllerCEP implements CepApi {

    @Autowired
    private ServiceCEP serviceCEP;


    @Override
    public Mono<ResponseEntity<CepResponse>> buscaCep(String cepId, ServerWebExchange exchange) {
        try {
            var endereco = serviceCEP.BuscaCep(cepId);
            CepResponse cepResponse = new CepResponse();
            cepResponse.setCep(endereco.getBody().getCep());
            cepResponse.setLogradouro(endereco.getBody().getLogradouro());
            cepResponse.setComplemento(endereco.getBody().getComplemento());
            cepResponse.setBairro(endereco.getBody().getBairro());
            cepResponse.setLocalidade(endereco.getBody().getLocalidade());
            cepResponse.setUf(endereco.getBody().getUf());
            cepResponse.setIbge(endereco.getBody().getIbge());
            cepResponse.setGia(endereco.getBody().getGia());
            cepResponse.setDdd(endereco.getBody().getDdd());
            cepResponse.setSiafi(endereco.getBody().getSiafi());

            return Mono.just(ResponseEntity.ok(cepResponse));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
