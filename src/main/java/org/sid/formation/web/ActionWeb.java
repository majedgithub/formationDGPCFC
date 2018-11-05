package org.sid.formation.web;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.sid.formation.metier.Iaction;
import org.sid.formation.metier.IemployeCTRL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ActionWeb {

	@Autowired
	Iaction iaction;
	
	@Autowired
	IemployeCTRL iemploye;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@RequestMapping(value="/t")
	public String homef() {
		return "template1";
	}
	
	@RequestMapping(value="/home")
	public String accueil(Model model,@RequestParam(name="page", defaultValue="0") int page, @RequestParam(name="size", defaultValue="5") int size) {
		
		Page<Action> listeActions = iaction.listeActions(page, size);
		int pages = listeActions.getTotalPages();
		int siz = listeActions.getNumberOfElements();
		model.addAttribute("listeActions", listeActions);
		model.addAttribute("pages", pages);
		model.addAttribute("siz", siz);
		model.addAttribute("sizeemployes", iemploye.NumbreEmploye());
		return "home";
	}
	
	@RequestMapping(value="/consulterAction")
	public String consulterAction(Model model, @RequestParam(name="idac") Long idac) throws Exception {
		
		Action ac = iaction.ConsulterAction(idac);
		Set<Employe> emps = ac.getEmployes();
		model.addAttribute("action", ac);
		model.addAttribute("emps", emps);
		return "consulteraction";
	}
	
	@RequestMapping(value="/Attestation")
	public String Attestation(Model model, @RequestParam(name="ac" , defaultValue="0") Long idc, 
			@RequestParam(name="emp" , defaultValue="0") Long ide, 
			@RequestParam(name="op" , defaultValue="G") String op) throws Exception {
		
		switch (op) {
		case "G":
			Action actiong = iaction.ConsulterAction(idc);
			Set<Employe> emps = actiong.getEmployes();
			model.addAttribute("actiong", actiong);
			model.addAttribute("emps", emps);
			model.addAttribute("generale", "ok");
			break;

		case "E":
			try {
				Action actione = iaction.ConsulterAction(idc);
				Set<Employe> empse = actione.getEmployes();
				model.addAttribute("name", "majed");
				Employe e = iemploye.ConsulterEmploye(ide);
				
				if(empse.contains(e)) {
					model.addAttribute("actione", actione);
					model.addAttribute("emp", e);
					model.addAttribute("single", "ok");
				}
			}catch(Exception e){
				  
			}
			
			break;
		}
		 
		/*
		 Map<String,String> data = new HashMap<String,String>();
		    data.put("name","James");
		    pdfGenaratorUtil.createPdf("Attestation",data); */
		
		return "Attestation";
	}
	
	@RequestMapping(value="/actionadd")
	public String ActionAdd(Model model, String nombreutilisateur) {
		if(nombreutilisateur !=null ) {
			
			model.addAttribute("nombreutilisateur", Integer.parseInt(nombreutilisateur));
		}
		
		return "actionadd";
	}
}
