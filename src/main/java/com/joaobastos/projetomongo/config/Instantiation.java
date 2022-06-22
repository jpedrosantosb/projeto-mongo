package com.joaobastos.projetomongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaobastos.projetomongo.domain.Post;
import com.joaobastos.projetomongo.domain.Usuario;
import com.joaobastos.projetomongo.dto.AutorDTO;
import com.joaobastos.projetomongo.dto.ComentarioDTO;
import com.joaobastos.projetomongo.repository.PostRepository;
import com.joaobastos.projetomongo.repository.UsuarioRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioReposiroty;

	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		usuarioReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		Usuario maria = new Usuario(null, "Maria Marta", "maria@gmail.com");
		Usuario joao = new Usuario(null, "Joao Carlos", "joao@gmail.com");
		Usuario pedro = new Usuario(null, "Pedro Santos", "pedro@gmail.com");
		
		usuarioReposiroty.saveAll(Arrays.asList(maria, joao, pedro));

		Post post1 = new Post(null, sdf.parse("26/10/2022"), "Aniversário", "Hoje é meu aniversário, estou muito feliz!", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("27/10/2022"), "Bom dia", "Bom dia a todos!", new AutorDTO(maria));

		ComentarioDTO c1 = new ComentarioDTO("Feliz aniversário amigo!", sdf.parse("26/10/2022"), new AutorDTO(joao));
		ComentarioDTO c2 = new ComentarioDTO("Te desejo muitos anos de vida irmão... Desculpa a demora rs", sdf.parse("27/10/2022"), new AutorDTO(pedro));
		ComentarioDTO c3 = new ComentarioDTO("Bom dia brother!", sdf.parse("27/10/2022"), new AutorDTO(joao));
		
		post1.getComentarios().addAll(Arrays.asList(c1, c2));
		post2.getComentarios().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		usuarioReposiroty.save(maria);
	}

}
