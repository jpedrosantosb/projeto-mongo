package com.joaobastos.projetomongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joaobastos.projetomongo.domain.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}
