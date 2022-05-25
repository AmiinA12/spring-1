package com.amina.Client.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amina.Client.entity.Commande;
import com.amina.Client.repos.*;

@Service

public class CommandeServiceImpl implements CommandeService {

	@Autowired
    private CommandeRepository commandeRepository;

    @Override
    public List <Commande> findAll() {
        return commandeRepository.findAll();
    }

	@Override
	public Commande saveCommande(Commande c) {
		return commandeRepository.save(c);
	}

	@Override
	public Commande updateCommande(Commande c) {
		return commandeRepository.save(c);
	}

	@Override
	public void deleteCommande(Commande c) {
		commandeRepository.delete(c);
	}

	@Override
	public void deleteCommandeById(Long idCom) {
		commandeRepository.deleteById(idCom);
	}

	@Override
	public Commande getCommande(Long idCom) {
		return commandeRepository.findById(idCom).get();
	}

	@Override
	public Commande updateCategorie(Commande c) {
		return commandeRepository.save(c);
		
	}

	@Override
	public List<Commande> getAllCommande() {
		return commandeRepository.findAll();
	}



	
}
