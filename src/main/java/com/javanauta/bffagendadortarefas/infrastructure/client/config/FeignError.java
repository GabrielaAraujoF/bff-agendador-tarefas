package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {


    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: Atributo já existente ");
            case 403:
                return new ResourceNotFoundException("Erro: Atributo não encontrado ");
            case 401:
                return new UnauthorizedException("Eroo: Usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor ");
        }
    }
}
