package com.challenge.forohub.topic;

import java.time.LocalDateTime;

public record DatosListadoTopic(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {
    public DatosListadoTopic(Topic topic){
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getFechaCreacion(),
                topic.getStatus(), topic.getAutor(), topic.getCurso());
    }
}
