package com.amina.Client.Service;

import java.util.List;

import com.amina.Client.entity.*;

public interface CommandeService {
	  List <Commande> findAll();
	    
	    Commande saveCommande(Commande c);
	    Commande updateCommande(Commande c);
	    void deleteCommande(Commande c);
	     void deleteCommandeById(Long id);
	     Commande getCommande (Long idCom);

	     Commande updateCategorie(Commande c);

		List<Commande> getAllCommande();
}
