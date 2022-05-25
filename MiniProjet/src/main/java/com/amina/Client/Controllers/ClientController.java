package com.amina.Client.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amina.Client.entity.*;

import com.amina.Client.Service.ClientService;
import com.amina.Client.Service.CommandeService;

@Controller
public class ClientController {
	@Autowired
	ClientService clService;
	@Autowired
	CommandeService comService;
	
	@RequestMapping("/saveClient")
	public String saveClient(ModelMap modelMap,@Valid Client client,
	BindingResult bindingResult)
	{
		List<Commande> com = comService.findAll();
		modelMap.addAttribute("commandes", com);
	System.out.println(client);
	if (bindingResult.hasErrors()) 
		return "formClient";
	clService.saveClient(client);
	
	return "listeClients";
	}
	

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
	List<Commande> com = comService.findAll();
	modelMap.addAttribute("commandes", com);
	modelMap.addAttribute("client", new Client());
	modelMap.addAttribute("mode", "new");
	return "formClient";
	}
	
	@RequestMapping("/ListeClient")
	public String ListeClient(ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
	Page<Client> clis =clService.getAllClientsParPage(page, size);
	modelMap.addAttribute("clients", clis);
	 modelMap.addAttribute("pages", new int[clis.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	return "listeClients";
	}
	
	@RequestMapping("/supprimerClient")
	public String supprimerProduit(@RequestParam("id") Long id,
	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "3") int size)
	{
		clService.deleteClientById(id);
	Page<Client> clis = clService.getAllClientsParPage(page, 
	size);
	modelMap.addAttribute("clients", clis);
	modelMap.addAttribute("pages", new int[clis.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeClients";
	}
	
	
	@RequestMapping("/modifierClient")
	public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
	{
		List<Commande> cat= comService.findAll();
		modelMap.addAttribute("commandes", cat);
	Client p= clService.getClient(id);
	modelMap.addAttribute("client", p);
	modelMap.addAttribute("mode", "edit");
	return "formClient";
	}
	@RequestMapping("/updateClient")
	public String updateProduit(@ModelAttribute("client") Client client,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException {
		//conversion de la date 
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateCreation = dateformat.parse(String.valueOf(date));

		 
		 clService.updateClient(client);
		 List<Client> prods = clService.getAllClient();
		 modelMap.addAttribute("clients", prods);
		return "listeClients";
		}

}
