package SpringApplication.springApplication.Services;


import SpringApplication.springApplication.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    /* Alternativa de anotação
    @GetMapping("/{cep}/json/")
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{cep}/json/")
    Endereco consultarCep(@PathVariable("cep") String cep);
}
