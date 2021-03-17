package com.winshot.gestionUtilisateurs.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winshot.gestionUtilisateurs.entites.Utilisateur;
import com.winshot.gestionUtilisateurs.repository.UtilisateurRepository;

@Service
public class UtilisateurServiceImplementes implements UtilisateurService{

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public List<Utilisateur> getAllUtilisateurs() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur findUtilisateurById(int id) {
		// TODO Auto-generated method stub
		Optional<Utilisateur> UtUtilisateurOptional =utilisateurRepository.findById(id);
		if(UtUtilisateurOptional.isEmpty())
		return null;
		else
			return UtUtilisateurOptional.get();	}

	@Override
	public Utilisateur createUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		utilisateur.setRole("Super Utilisateur");
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		Optional<Utilisateur> UtUtilisateurOptional =utilisateurRepository.findById(utilisateur.getId());
		if(UtUtilisateurOptional.isEmpty())
		return null;
		else
			utilisateur.setRole("Utilisateur");
			return utilisateurRepository.save(utilisateur);
	}

	@Override
	public void deleteUtilisateur(int id) {
		// TODO Auto-generated method stub
		utilisateurRepository.deleteById(id);
	}

	@Override
	public Utilisateur login(String email, String pwd) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByEmailAndPwd(email, pwd);
	}
	


	@Override
	public List<Utilisateur> findSimpleUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findSimpleUtilisateur();
	}

	@Override
	public void importExcel(MultipartFile file) throws EncryptedDocumentException, InvalidFormatException, IOException
 {
		// TODO Auto-generated method stub
     
	        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
	        XSSFSheet sheet = workbook.getSheetAt(0);
	        
	        int rows=sheet.getLastRowNum();
	  
	        for(int r=1;r<=rows;r++) {
	            Utilisateur u = new Utilisateur();
	            XSSFRow row=sheet.getRow(r);
	            
	               
	                XSSFCell nom =row.getCell(0);
	                u.setNom(nom.getStringCellValue());
	                
	                XSSFCell prenom =row.getCell(1);
	                u.setPrenom(prenom.getStringCellValue());
	                
	                XSSFCell email =row.getCell(2);
	                u.setEmail(email.getStringCellValue());
	                
	                XSSFCell motdepasse =row.getCell(3);
	                u.setPwd(motdepasse.getStringCellValue());
	                
	                u.setRole("Utilisateur");
	                     
	                utilisateurRepository.save(u);

	        }
	    
	}

	@Override
	public List<Utilisateur> findSuperUtilisateur() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findSuperUtilisateur();
	}

	@Override
	public Utilisateur findByEmail(String email) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findByEmail(email);
	}
}
