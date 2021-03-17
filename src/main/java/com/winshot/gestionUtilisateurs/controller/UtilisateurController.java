package com.winshot.gestionUtilisateurs.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.winshot.gestionUtilisateurs.entites.Utilisateur;
import com.winshot.gestionUtilisateurs.service.UtilisateurServiceImplementes;

@RestController
@RequestMapping("/Utilisateurs")
public class UtilisateurController {
	@Autowired
	private UtilisateurServiceImplementes utilisateurService;
	
	
	@PostMapping(path = "/import-excel")
    public String importExcelFile(@RequestParam("file") MultipartFile file) throws IOException, EncryptedDocumentException, InvalidFormatException {

	utilisateurService.importExcel(file);
		return "Import Excel done !";
    }
	
	
	@GetMapping
	public List<Utilisateur> getAllUtilisateurs() {
		return utilisateurService.getAllUtilisateurs();

	}
	
	
	//login
	@GetMapping(path = "/login/{email}/{pwd}")
	public ResponseEntity<Utilisateur> login(@PathVariable String email,@PathVariable String pwd ) {

		Utilisateur utilisateur = utilisateurService.login(email, pwd);
		if (utilisateur == null)
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Utilisateur> findUtilisateurById(@PathVariable int id) {

		Utilisateur utilisateur = utilisateurService.findUtilisateurById(id);
		if (utilisateur == null)
			return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);

	}
	
	@GetMapping(path = "/email/{email}")
	public ResponseEntity<Utilisateur> findSUtilisateurByLogin(@PathVariable String email) {

		Utilisateur utilisateur = utilisateurService.findByEmail(email);
		if (utilisateur == null)
			return new ResponseEntity<Utilisateur>(HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<Utilisateur>(utilisateur,HttpStatus.BAD_REQUEST);

	}
	@GetMapping(path = "/SimpleUtilisaeur")
	public List<Utilisateur> getUtilisateurNormal() {
		return utilisateurService.findSimpleUtilisateur();

	}
	
	@GetMapping(path = "/SuperUtilisaeur")
	public List<Utilisateur> getSuperUtilisateur() {
		return utilisateurService.findSuperUtilisateur();

	}
	@PostMapping
	public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
		
		Utilisateur ut=utilisateurService.findUtilisateurById(utilisateur.getId());
		if(ut==null)
		{
			 utilisateurService.createUtilisateur(utilisateur);
			return new ResponseEntity<Utilisateur>(utilisateur,HttpStatus.CREATED);
		}
		
		else 
			return new ResponseEntity<Utilisateur>(HttpStatus.BAD_REQUEST); 
		
	}

	@PutMapping
	public  Utilisateur updateUtilisateur (@RequestBody Utilisateur utilisateur) {
		return   utilisateurService.updateUtilisateur(utilisateur);

	}

	@DeleteMapping(path = "/{id}")
	public void deleteUtilisateur(@PathVariable int id) {
		utilisateurService.deleteUtilisateur(id);

	}
	@PutMapping(path="/updateRole")
	public  Utilisateur updateRoleUtilisateurSimple (@RequestBody Utilisateur utilisateur) {
		utilisateur.setRole("");
		return   utilisateurService.updateUtilisateur(utilisateur);

	}
	
}
