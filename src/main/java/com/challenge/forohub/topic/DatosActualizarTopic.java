package com.challenge.forohub.topic;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopic(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {
}
