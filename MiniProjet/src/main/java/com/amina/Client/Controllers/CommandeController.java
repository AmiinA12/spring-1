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
public class CommandeController {
	@Autowired
	CommandeService ComService;
	
	@RequestMapping("/showCreateCom")
	public String showCreateCom(ModelMap modelMap)
	{
	modelMap.addAttribute("commandes", new Commande());
	modelMap.addAttribute("mode", "new");
	return "formCommande";
	}
	
	
	
	@RequestMapping("/saveCommande")
	public String saveCommande(@ModelAttribute("commandes")Commande commande,ModelMap modelMap) throws ParseException 
	{
	Commande saveCommande = ComService.saveCommande(commande);
	return "redirect:/ListeCom";
	}
	@RequestMapping("/ListeCom")
	public String listeCommande(ModelMap modelMap)
	{
	List <Commande> cats = ComService.findAll();
	modelMap.addAttribute("commandes", cats);
	return "ListeCom";
	}
	@RequestMapping("/supprimerCom")
	public String supprimerCommande(@RequestParam("id") Long id,
	 ModelMap modelMap)
	{ 
	ComService.deleteCommandeById(id);
	List<Commande> cats = ComService.findAll();
	modelMap.addAttribute("commandes", cats);
	return "ListeCom";
	}
	
	@RequestMapping("/modifierCommande")
	public String modifierCommande(@RequestParam("id") Long id,ModelMap modelMap)
	{
	Commande c= ComService.getCommande(id);
	modelMap.addAttribute("commandes", c);
	modelMap.addAttribute("mode", "edit");
	return "formCommande";
	}
	@RequestMapping("/updateComnde")
	public String updateCommande(@ModelAttribute("commandes") Commande commande,ModelMap modelMap) throws ParseException {
		ComService.updateCommande(commande);
		 List<Commande> coms = ComService.findAll();
		 modelMap.addAttribute("commandes", coms);
		return "ListeCom";
		}
	
	@RequestMapping("/updateCommande")
	public String updateCommande(@ModelAttribute("commande") Commande commande,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException {
		//conversion de la date 
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateCreation = dateformat.parse(String.valueOf(date));

		 
		 ComService.updateCommande(commande);
		 List<Commande> prods = ComService.getAllCommande();
		 modelMap.addAttribute("commandes", prods);
		return "ListeCom";
		}
}
