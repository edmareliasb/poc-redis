package com.poc.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.redis.model.Cliente;
import com.poc.redis.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private ObjectMapper mapper;

    @Cacheable(cacheNames = "Cliente", key="#root.method.name")
    public List<Cliente> findAll() {
        log.info("Buscando informacoes fora do cache");
         return List.of(new Cliente("Maria", "Rua Verde"), new Cliente("Joao", "Rua das andorinhas"));
    }

    @CacheEvict(cacheNames = "Cliente", allEntries = true)
    public void cleanCache() {
        log.info("Limpando Cache");
    }

    public List<Cliente> findClientesSpringDataRedis() {
        log.info("Buscando informacoes por Spring Data Redis");
        Map<String, Object> map = repository.findAll();

        List<Cliente> clientes = Collections.emptyList();

        try {
            Object object = map.get("clientes");
            clientes = mapper.readValue(object.toString(), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info(String.valueOf(clientes));

        return clientes;
    }

    public void save() {
        List<Cliente> clientes = List.of(new Cliente("Pedro", "Rua dos pardais"), new Cliente("Antonio", "Rua dos leoes"));
        repository.save(clientes);
    }
}
