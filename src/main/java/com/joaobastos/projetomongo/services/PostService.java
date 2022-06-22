package com.joaobastos.projetomongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaobastos.projetomongo.domain.Post;
import com.joaobastos.projetomongo.repository.PostRepository;
import com.joaobastos.projetomongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitulo(String texto) {
		return repo.findByTituloContainingIgnoreCase(texto);
	}
	
	public List<Post> pesquisaCompleta(String texto, Date minData, Date maxData) {
		maxData = new Date(maxData.getTime() + 24 * 60 * 60 * 1000);
		return repo.pesquisaCompleta(texto, minData, maxData);
	}
}
