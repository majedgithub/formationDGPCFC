package org.sid.formation.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sid.formation.dao.EmployeRepository;
import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.sid.formation.entities.Formateur;
import org.sid.formation.metier.IFormateur;
import org.sid.formation.metier.Iaction;
import org.sid.formation.metier.IemployeCTRL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.util.logging.Log;

@Controller
public class ActionWeb {

	@Autowired
	Iaction iaction;
	
	@Autowired
	IFormateur iFormateur;
	
	@Autowired
	IemployeCTRL iemploye;
	
	@Autowired
	EmployeRepository iemployeRepo;
	
	@Autowired
	PdfGenaratorUtil pdfGenaratorUtil;
	
	@RequestMapping(value="/")
	public String homef() {
		return "home";
	}
	
	@RequestMapping(value="/home")
	public String accueil(Model model,@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size,
			@RequestParam(name="inputintitule", defaultValue=" ") String inputintitule) {
		
			model.addAttribute("countaction", iaction.CountActions());
			model.addAttribute("countemploye", iemploye.NumbreEmploye());
		
		if(!(inputintitule.equals(" ")) ) {
			Page<Action> listeActions = iaction.listeActionsIntitule(inputintitule,0,5);
			int pages = listeActions.getTotalPages();
				model.addAttribute("listeActions", listeActions);
			}	
		
		Page<Action> listeActions = iaction.listeActions(page, size);
		int pages = listeActions.getTotalPages();
		model.addAttribute("listeActions", listeActions);
		model.addAttribute("pages", pages);
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
	
	@RequestMapping(value="/actionadda")
	public String ActionAdda(Model model ,@RequestParam(name="inputintitule", defaultValue = "o") String inputintitule
			,@RequestParam(name="inputtheme", defaultValue = "") String inputtheme
			,@RequestParam(name="inputdate", defaultValue = "29-Apr-2010,13:00:14 PM") String inputdate
			,@RequestParam(name="inputduree", defaultValue = "") Double inputduree
			,@RequestParam(name="inputsousparag", defaultValue = "") String inputsousparag
			,@RequestParam(name="inputlieu", defaultValue = "") String inputlieu
			,@RequestParam(name="inputavis", defaultValue = "") String inputavis
			,@RequestParam(name="inputorgc", defaultValue = "") Double inputorgc
			,@RequestParam(name="inputanc", defaultValue = "") Double inputanc
			,@RequestParam(name="inputformateur", defaultValue = "") String inputformateur
			,@RequestParam(name="inputorgcop", defaultValue = "") Double inputorgcop
			,@RequestParam(name="inputanimationcop", defaultValue = "") Double inputanimationcop
			,@RequestParam(name="inputbureau", defaultValue = "") String inputbureau) throws Exception {
		
		if(!inputintitule.equals("o")) {
			
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			    Date convertedDate = dateFormat.parse(inputdate); 
				Double tot1 = inputorgc + inputanc;
				Double tot2 = inputorgcop + inputanimationcop;
				long form = Integer.parseInt(inputformateur);
				Formateur f = iFormateur.getFormateur(form);
			
					Action act = new Action(inputintitule, inputtheme, inputlieu, inputbureau, convertedDate, inputduree, inputavis, inputanc, inputorgc, tot1, inputsousparag, 1, inputanimationcop, inputorgcop, tot2, f);

					Action news = iaction.AjouterAction(act);
					model.addAttribute("actiondet", news);
					model.addAttribute("test", "ok");
					model.addAttribute("idaction", news.getId());
					

			} catch (Exception e) {
				// TODO: handle exception
			}
					
		}else {
			
			List<Formateur> listFormateur = iFormateur.ListFormateur();
			model.addAttribute("listFormateur", listFormateur);
			model.addAttribute("test", "no");
		}
		
		return "actionadd";
	}
	
	@RequestMapping(value="/actionaddb")
	public String ActionAddb(Model model, @RequestParam(name="inputcnrps2", defaultValue = "0") String inputcnrps2
			, @RequestParam(name="inputid", defaultValue = "0") String inputid) {
		
		if( !inputcnrps2.equals("0") &&  !inputid.equals("0")) {
			
			try {
				Action actione = iaction.ConsulterAction(Long.parseLong(inputid));
				Employe emp = iemploye.ConsulterEmployeByCnrps(inputcnrps2);
				
				if(actione!= null) {
					iaction.AffecterEmployeTAction(actione, emp);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return "actionadd";
	}
	
}
