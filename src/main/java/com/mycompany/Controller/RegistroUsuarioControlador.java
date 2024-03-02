package com.mycompany.Controller;

import com.mycompany.dto.UsuarioRegistroDTO;
import com.mycompany.servico.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	private UsuarioService usuarioService;

	public RegistroUsuarioControlador(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNovoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarContaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		usuarioService.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
