package com.poc.redis.rest;

import ch.qos.logback.core.net.server.Client;
import com.poc.redis.model.Cliente;
import com.poc.redis.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteRest {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/clean-cache")
    public ResponseEntity cleanCache() {
        clienteService.cleanCache();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/v2/clientes")
    public ResponseEntity<List<Cliente>> findAllV2() {
        List<Cliente> clientes = clienteService.findClientesSpringDataRedis();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/v2/salva-cliente-cache")
    public ResponseEntity save() {
        clienteService.save();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
