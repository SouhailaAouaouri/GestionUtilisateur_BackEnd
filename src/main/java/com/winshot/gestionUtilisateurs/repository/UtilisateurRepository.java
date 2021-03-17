package com.winshot.gestionUtilisateurs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.winshot.gestionUtilisateurs.entites.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	public Utilisateur findByEmailAndPwd(String email,String pwd);
	
	public Utilisateur findByEmail(String email);

	@Query("select u from Utilisateur u where u.role='Utilisateur' ")
	public List<Utilisateur> findSimpleUtilisateur();
	@Query("select u from Utilisateur u where u.role='Super Utilisateur' ")
	public List<Utilisateur> findSuperUtilisateur();


}
