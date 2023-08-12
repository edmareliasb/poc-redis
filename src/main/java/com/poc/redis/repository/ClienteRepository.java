package com.poc.redis.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.redis.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ClienteRepository  {

    private static final String CACHE_CLIENTE = "CACHE_CLIENTE";

    private HashOperations hashOperations;
    private ObjectMapper mapper;

    public ClienteRepository(final RedisTemplate redisTemplate, final ObjectMapper mapper) {
        this.hashOperations = redisTemplate.opsForHash();
        this.mapper = mapper;
    }

    public Map<String, Object> findAll(){
        Map entries = hashOperations.entries(CACHE_CLIENTE);
        return entries;
    }

    public void save(List<Cliente> clientes) {

        try {
            //Primeiro Deleto a linha depois salvo
            Long clientes1 = hashOperations.delete(CACHE_CLIENTE, "clientes");

            String json = mapper.writeValueAsString(clientes);
            hashOperations.put(CACHE_CLIENTE, "clientes", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}