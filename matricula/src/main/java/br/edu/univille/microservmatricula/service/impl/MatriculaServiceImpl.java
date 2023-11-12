package br.edu.univille.microservmatricula.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.univille.microservmatricula.entity.Matricula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univille.microservmatricula.repository.MatriculaRepository;
import br.edu.univille.microservmatricula.service.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Override
    public List<Matricula> getAll() {
        var iterador = repository.findAll();
        List<Matricula> listaMatriculas = new ArrayList<>();

        iterador.forEach(listaMatriculas::add);

        return listaMatriculas;
    }

    @Override
    public Matricula getById(String id) {
        var matricula = repository.findById(id);
        if(matricula.isPresent())
            return matricula.get();
        return null;
    }

    @Override
    public Matricula saveNew(Matricula matricula) {
        matricula.setId(null);
        return repository.save(matricula);
    }

    @Override
    public Matricula delete(String id) {
        var buscaMatricula = repository.findById(id);
        if (buscaMatricula.isPresent()){
            var matricula = buscaMatricula.get();

            repository.delete(matricula);

            return matricula;
        }
        return null;
    }
}
