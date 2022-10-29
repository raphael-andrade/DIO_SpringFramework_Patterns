package SpringApplication.springApplication.Services;

import SpringApplication.springApplication.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId (Long id);
    void inserir (Cliente cliente);
    void atualizar (long id, Cliente cliente);
    void deletar (long id);
}
