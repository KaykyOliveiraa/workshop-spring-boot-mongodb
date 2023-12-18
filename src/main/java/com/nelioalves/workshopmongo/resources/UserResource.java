package com.nelioalves.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

@RestController  //anotation para mostrar q a classe é um recurso Rest
@RequestMapping(value="/users") //caminho do endpoint 
public class UserResource {
	
	@Autowired
	private UserService service;

	@GetMapping //anotation que define o "get" como metodo http para o endpoint
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{id}")
	//@PathVariable mostra q o id do parametro do metodo é o mesmo do que da URL passado na anotation acima
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ 
		User list = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(list));
	}
	
}
