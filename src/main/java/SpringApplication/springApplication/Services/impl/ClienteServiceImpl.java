package SpringApplication.springApplication.Services.impl;

import SpringApplication.springApplication.Services.ClienteService;
import SpringApplication.springApplication.Services.ViaCepService;
import SpringApplication.springApplication.model.Cliente;
import SpringApplication.springApplication.model.ClienteRepository;
import SpringApplication.springApplication.model.Endereco;
import SpringApplication.springApplication.model.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteCep(cliente);
    }
    @Override
    public void atualizar(long id, Cliente cliente) {
        Optional<Cliente> clienteDB = clienteRepository.findById(id);
        if (clienteDB.isPresent()) {
            salvarClienteCep(cliente);
        }
    }

    @Override
    public void deletar(long id) {
    clienteRepository.deleteById(id);
    }

    private void salvarClienteCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
