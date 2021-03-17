package com.winshot.gestionUtilisateurs.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import com.winshot.gestionUtilisateurs.entites.Utilisateur;


public interface UtilisateurService {
	public List<Utilisateur> getAllUtilisateurs();
	public Utilisateur findUtilisateurById(int id);
	public Utilisateur findByEmail(String email);
	public Utilisateur createUtilisateur(Utilisateur utilisateur);
	public Utilisateur updateUtilisateur(Utilisateur utilisateur);
	public void deleteUtilisateur(int id);
	public Utilisateur login(String email,String pwd);
	public List<Utilisateur> findSimpleUtilisateur();
	public List<Utilisateur> findSuperUtilisateur();
	public void importExcel(MultipartFile file) throws EncryptedDocumentException, InvalidFormatException, IOException;
}
