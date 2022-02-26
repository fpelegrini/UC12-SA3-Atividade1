package com.Choppin.Choppin.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Choppin.Choppin.models.Cliente;
import com.Choppin.Choppin.models.Pedido;
import com.Choppin.Choppin.repository.ClienteRepository;
import com.Choppin.Choppin.repository.PedidoRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository cr;

	@Autowired
	private PedidoRepository pr;

	// GET que chama o form para cadastrar Clientes
	@RequestMapping("/cadastrarCliente")
	public String form() {
		return "cliente/form-cliente";
	}

	// POST que cadastra clientes
	@RequestMapping(value = "/cadastrarCliente", method = RequestMethod.POST)
	public String form(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarCliente";
		}

		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso!");
		return "redirect:/cadastrarCliente";
	}

	// GET que lista clientes
	@RequestMapping("/clientes")
	public ModelAndView listaclientes() {
		ModelAndView mv = new ModelAndView("cliente/lista-cliente");
		Iterable<Cliente> clientes = cr.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}

	// GET que lista pedidos e detalhes do cliente
	@RequestMapping("/detalhes-cliente/{id}")
	public ModelAndView detalhesCliente(@PathVariable("id") long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("cliente/detalhes-cliente");
		mv.addObject("clientes", cliente);

		// lista de pedidos baseado no id do cliente
		Iterable<Pedido> pedidos = pr.findByCliente(cliente);
		mv.addObject("pedidos", pedidos);

		return mv;

	}

	// POST que adiciona pedidos
	@RequestMapping(value="/detalhes-cliente/{id}", method = RequestMethod.POST)
	public String detalhesClientePost(@PathVariable("id") long id, Pedido pedidos, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhes-cliente/{id}";
		}
		
	
		Cliente cliente = cr.findById(id);
		pedidos.setCliente(cliente);
		pr.save(pedidos);
		attributes.addFlashAttribute("mensagem", "Pedido adicionado com sucesso");
		return "redirect:/detalhes-cliente/{id}";
		
	}
	
	//GET que deleta cliente
	@RequestMapping("/deletarCliente")
	public String deletarCliente(long id) {
		Cliente cliente = cr.findById(id);
		cr.delete(cliente);
		return "redirect:/clientes";
		
	}
	
	// Métodos que atualizam cliente
	// GET que chama o FORM de edição do cliente
	@RequestMapping("/editar-cliente")
	public ModelAndView editarCliente(long id) {
		Cliente cliente = cr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/update-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}
	
	// POST que atualiza o cliente
	@RequestMapping(value = "/editar-cliente", method = RequestMethod.POST)
	public String updateCliente(@Valid Cliente cliente,  BindingResult result, RedirectAttributes attributes){
		
		cr.save(cliente);
		attributes.addFlashAttribute("success", "Cliente alterado com sucesso!");
		
		long idLong = cliente.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-cliente/" + id;
		
	}
	
	// GET que deleta pedido
	@RequestMapping("/deletarPedido")
	public String deletarPedido(long id) {
		Pedido pedido = pr.findById(id);
		
		Cliente cliente = pedido.getCliente();
		String codigo = "" + cliente.getId();
		
		pr.delete(pedido);
		return "redirect:/detalhes-cliente/" + codigo;
	
	}
}
