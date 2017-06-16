package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
		
	private static final String CADASTRO_VIEW ="CadastroTitulo";
	
	@Autowired
	private Titulos tituloDAO;
	
	
	@RequestMapping("/novo")
	private ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	
	@RequestMapping(method = RequestMethod.POST )
	private String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes atributos){
		if(errors.hasErrors()){
			return CADASTRO_VIEW;
		}
		tituloDAO.save(titulo);
		atributos.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
	}
	
	
	@RequestMapping("{codigo}") // código pela URL
	private ModelAndView edicao(@PathVariable("codigo") Titulo titulo){//código vem como parâmetro de URL
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); // modelandview com a tela de cadastro
		mv.addObject(titulo); // joga o objeto para a tela de cadastro
		return mv;
	}
	
	
	@RequestMapping(value = "{codigo}",method = RequestMethod.DELETE)
	private String excluir(@PathVariable Long codigo){
		tituloDAO.delete(codigo);
		return "redirect:/titulos";
	}
	
	
	
	@ModelAttribute("todosStatusTitulo")
	private List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	
	@RequestMapping
	private ModelAndView pesquisar(){
		List<Titulo> todosTitulos = tituloDAO.findAll();// joga para a lista todosTitulos com o findAll, todos os títulos
		ModelAndView mv = new ModelAndView("PesquisaTitulos"); 
		mv.addObject("titulos",todosTitulos);
		return mv;
	}
	
}