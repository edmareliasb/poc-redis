package com.poc.redis.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Cliente implements Serializable {

    private String name;
    private String endereco;
}
