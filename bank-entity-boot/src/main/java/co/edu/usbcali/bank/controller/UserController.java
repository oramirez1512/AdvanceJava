package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.dto.UserDTO;
import co.edu.usbcali.bank.mapper.UserMapper;
import co.edu.usbcali.bank.service.UserService;

@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			userService.deleteById(id);
			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
		try {
			Usuario user= userMapper.userDTOtoUser(userDTO);
			user= userService.update(user);
			userDTO = userMapper.userToUserDTO(user);
			return ResponseEntity.ok().body(userDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
		try {
			Usuario user= userMapper.userDTOtoUser(userDTO);
			user= userService.save(user);
			userDTO = userMapper.userToUserDTO(user);
			return ResponseEntity.ok().body(userDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError(400,e.getMessage()));
		}
	}
	
	@GetMapping("findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		Optional<Usuario> userOptional=userService.findById(id);
		if(!userOptional.isPresent()) {
			return ResponseEntity.badRequest().body(new ResponseError(400,"User don't exists"));
		}
		else {
			Usuario usuario = userOptional.get();
			return ResponseEntity.ok().body( userMapper.userToUserDTO(usuario));	
		}
		
	}
	
	@GetMapping("/findAll")
	public List<UserDTO> findAll(){
		List<Usuario> UserList = userService.findAll();
		List<UserDTO> DTOUsersList= userMapper.toDTOUsers(UserList);
		return DTOUsersList;
	}
	
	

}
