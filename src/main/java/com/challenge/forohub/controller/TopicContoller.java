package com.challenge.forohub.controller;

import com.challenge.forohub.ValidacionException;
import com.challenge.forohub.topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicContoller {

    @Autowired
    TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopic(@RequestBody @Valid com.challenge.forohub.topic.DatosRegistroTopic datosRegistroTopic,
                                         UriComponentsBuilder uriComponentsBuilder){
        Topic topic = topicRepository.save( new Topic(datosRegistroTopic));

        DatosRespuestaTopic datosRespuestaTopic = new DatosRespuestaTopic(topic.getTitulo(), topic.getMensaje(),
                topic.getAutor(), topic.getCurso());

        URI url =uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopic);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopic>> listarTopico(@PageableDefault(size=10) Pageable paginacioon){
        return ResponseEntity.ok(topicRepository.findAll(paginacioon).map(DatosListadoTopic::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopic> retornarDetallesTopico(@PathVariable Long id){
        if (!topicRepository.findById(id).isPresent()){
            throw new ValidacionException();
        }

        if (id == null || id <= 0) {
                throw  new ValidacionException();
        }

        var topic = topicRepository.getReferenceById(id);

        var detallesTopico = new DatosListadoTopic(topic.getId(), topic.getTitulo(), topic.getMensaje(),
                topic.getFechaCreacion(), topic.getStatus(), topic.getAutor(), topic.getCurso());

        return ResponseEntity.ok(detallesTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosActualizarTopic> actualizarTopico(@RequestBody @Valid DatosActualizarTopic datosActualizarTopico){
        Topic topic = topicRepository.getReferenceById(datosActualizarTopico.id());

        topic.actualizarTopic(datosActualizarTopico);

        var detallesActualizarTopic = new DatosActualizarTopic(topic.getId(), topic.getTitulo(), topic.getMensaje(),
                topic.getFechaCreacion(), topic.getStatus(), topic.getAutor(), topic.getCurso());

        return ResponseEntity.ok(detallesActualizarTopic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){

        if (!topicRepository.findById(id).isPresent()){
            throw new ValidacionException();
        }

        topicRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
