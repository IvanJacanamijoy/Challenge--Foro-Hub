package com.challenge.forohub.topic;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private String autor;
    private String curso;
    private String respuestas;

    public Topic() {}

    public Topic(com.challenge.forohub.topic.DatosRegistroTopic datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
        this.status = datosRegistroTopico.status();
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
        this.respuestas = datosRegistroTopico.respuestas();
    }

    public void actualizarTopic(DatosActualizarTopic datosActualizarTopic){

        if (datosActualizarTopic.titulo() != null){
            this.titulo = datosActualizarTopic.titulo();
        }
        if (datosActualizarTopic.mensaje() != null){
            this.mensaje = datosActualizarTopic.mensaje();
        }
        if (datosActualizarTopic.fechaCreacion() != null){
            this.fechaCreacion = datosActualizarTopic.fechaCreacion();
        }
        if (datosActualizarTopic.status() != null){
            this.status = datosActualizarTopic.status();
        }
        if (datosActualizarTopic.autor() != null){
            this.autor = datosActualizarTopic.autor();
        }
        if (datosActualizarTopic.curso() != null){
            this.curso = datosActualizarTopic.curso();
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }
}
