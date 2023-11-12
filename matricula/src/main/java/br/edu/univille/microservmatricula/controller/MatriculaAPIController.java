package br.edu.univille.microservmatricula.controller;



import java.util.List;

import br.edu.univille.microservmatricula.entity.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.univille.microservmatricula.service.MatriculaService;

@RestController
@RequestMapping("/api/v1/matriculas")
public class MatriculaAPIController {

    @Autowired
    private MatriculaService service;

    @GetMapping
    public ResponseEntity<List<Matricula>> listaCarros(){
        var listaCarros = service.getAll();
        return 
            new ResponseEntity<List<Matricula>>
            (listaCarros, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarCarro(@PathVariable("id")  String id){
        var carro = service.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Matricula>
            (carro, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Matricula> inserirCarro(@RequestBody Matricula matricula){
        if(matricula == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        matricula = service.saveNew(matricula);
        return 
            new ResponseEntity<Matricula>
            (matricula, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Matricula> removerCarro(@PathVariable("id")  String id){
        if(id == ""  || id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var carro = service.delete(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return 
            new ResponseEntity<Matricula>
            (carro, HttpStatus.OK);
    }
}
