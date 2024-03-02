package com.mycompany.Controller;

import com.mycompany.servico.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioService servicio;
	
	@GetMapping("/login")
	public String iniciarSessao() {
		return "login";
	}

}
