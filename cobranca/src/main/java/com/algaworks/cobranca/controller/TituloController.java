package com.algaworks.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	//private Titulos tituloDAO;
	
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	public String salvar(Titulo titulo){
		System.out.println("Titulo : "+titulo.getDescricao());
		return "CadastroTitulo";
	}
	
}