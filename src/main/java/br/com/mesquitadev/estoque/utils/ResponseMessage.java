package br.com.mesquitadev.estoque.utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMessage {
    private Long id;
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public ResponseMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

}
