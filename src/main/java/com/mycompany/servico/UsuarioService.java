package com.mycompany.servico;

import com.mycompany.dto.UsuarioRegistroDTO;
import com.mycompany.modelo.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UsuarioService extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
