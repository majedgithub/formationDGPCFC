package org.sid.formation.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.sid.formation.dao.EmployeRepository;
import org.sid.formation.entities.Action;
import org.sid.formation.entities.Employe;
import org.sid.formation.entities.Fonctions;
import org.sid.formation.entities.Formateur;
import org.sid.formation.entities.Grade;
import org.sid.formation.entities.SousDirection;
import org.sid.formation.metier.IFonctions;
import org.sid.formation.metier.IFormateur;
import org.sid.formation.metier.IGrade;
import org.sid.formation.metier.ISousDirection;
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
	IFormateur iFormateur;
	
	@Autowired
	IemployeCTRL iemploye;
	
	@Autowired
	IGrade iGrade;
	
	@Autowired
	ISousDirection iSousDirection;
	
	@Autowired
	IFonctions iFonctions;
	
	@RequestMapping(value="/")
	public String homef(Model model) throws Exception {
		
		Employe emp = iemploye.ConsulterEmployeByCnrps("984565");
		model.addAttribute("emp", emp);
		return "qsd";
	}
	
	@RequestMapping(value="/home")
	public String accueil(Model model,@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="7") int size,
			@RequestParam(name="inputintitule", defaultValue=" ") String inputintitule) {
		
			model.addAttribute("countaction", iaction.CountActions());
			model.addAttribute("countemploye", iemploye.NumbreEmploye());
		
		if(!(inputintitule.equals(" ")) ) {
			
					Page<Action> listeActions = iaction.listeActionsIntitule(inputintitule,page,size);
					int pages = listeActions.getTotalPages();
					
			}else {	
				Page<Action> listeActions = iaction.listeActions(page, size);
			
					int pages = listeActions.getTotalPages();
					model.addAttribute("listeActions", listeActions);
					model.addAttribute("pages", pages);
			
			}
		return "home";
	}
	
	@RequestMapping(value="/consulterAction")
	public String consulterAction(Model model, @RequestParam(name="idac") Long idac){
		try {
			Action ac = iaction.ConsulterAction(idac);
			Set<Employe> emps = ac.getEmployes();
			
			model.addAttribute("emps", emps);
			if(ac.getIntitule().equals("")) {
				model.addAttribute("actionvide", "vide");
				}else {
					model.addAttribute("action", ac);
				}
			if(emps.isEmpty()) {
				model.addAttribute("listevide", "vide");
				}
		} catch (Exception e) {
			model.addAttribute("exception", "pas d'action avec l'identifiant indiqué");
		}
		
		return "consulteraction";
	}
	
	@RequestMapping(value="/Attestation")
	public String Attestation(Model model, @RequestParam(name="ac" , defaultValue="0") Long idc, 
			@RequestParam(name="emp" , defaultValue="0") Long ide, 
			@RequestParam(name="op" , defaultValue="G") String op) throws Exception {
		
		switch (op) {
		case "G":
			try {
				Action actiong = iaction.ConsulterAction(idc);
				Set<Employe> emps = actiong.getEmployes();
				model.addAttribute("actiong", actiong);
				model.addAttribute("emps", emps);
				model.addAttribute("generale", "ok");
				break;
			} catch (Exception e) {
				model.addAttribute("exception", "pas d'action trouvé avec l'identifiant demandé");
			}
		

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
				model.addAttribute("exception", "employe ou action incorrecte");
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
			,@RequestParam(name="inputorgcop", defaultValue = "0") Double inputorgcop
			,@RequestParam(name="inputanimationcop", defaultValue = "0") Double inputanimationcop
			,@RequestParam(name="inputbureau", defaultValue = "") String inputbureau) throws Exception {
		
			model.addAttribute("addactionpage", "true");
			model.addAttribute("showinput", "true");
		
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
					model.addAttribute("idaction", news.getId());
					model.addAttribute("showinput", null);
					model.addAttribute("showlabel", "true");

			} catch (Exception e) {
				// TODO: handle exception
			}
					
		}else {
			List<Formateur> listFormateur = iFormateur.ListFormateur();
			model.addAttribute("listFormateur", listFormateur);
		}
		
		return "actionadd";
	}
	
	@RequestMapping(value="/actionaddb")
	public String ActionAddb(Model model, @RequestParam(name="inputcnrps2", defaultValue = "0") String inputcnrps2
			, @RequestParam(name="inputid", defaultValue = "0") String inputid) throws Exception {
		
		model.addAttribute("showlabel", "true");
		model.addAttribute("idaction", inputid);
		model.addAttribute("addactionpage", "true");
		
		if( !inputcnrps2.equals("0") &&  !inputid.equals("0")) {
			model.addAttribute("t", "1");
			Action actione = iaction.ConsulterAction(Long.parseLong(inputid));
			Employe emp = iemploye.ConsulterEmployeByCnrps(inputcnrps2);
			model.addAttribute("actiondet", actione);
			
				if(actione != null && emp != null) {
					 actione = iaction.AffecterEmployeTAction(actione, emp);
					 emp = iemploye.AffecterActionToEmploye(actione, emp);
					 
					 actione = iaction.AjouterAction(actione);
					emp = iemploye.AjouterEmploye(emp);
					 
					 model.addAttribute("emp", emp); 
					model.addAttribute("idaction", actione.getId());
					model.addAttribute("listeutilisateur", actione.getEmployes());
					model.addAttribute("actiondet", actione);
					model.addAttribute("t", "2");
				}
				
		}
		
		return "actionadd";
	}
	
	@RequestMapping(value="/Formateur")
	public String GestionFormateurs(Model model, @RequestParam(name="cin" , defaultValue="0") String cin
			,@RequestParam(name="cin2" , defaultValue="0") String cin2
			,@RequestParam(name="nom" , defaultValue="0") String nom
			,@RequestParam(name="tel" , defaultValue="0") String tel
			,@RequestParam(name="addresse" , defaultValue="0") String addresse
			,@RequestParam(name="bureau" , defaultValue="0") String bureau
			,@RequestParam(name="page" , defaultValue="0") int page
			,@RequestParam(name="size" , defaultValue="7") int size
			,@RequestParam(name="edit" , defaultValue="0") String edit
			,@RequestParam(name="saveedit" , defaultValue="0") String saveedit) {
		
		if(!cin.equals("0")) {
			
			
			Formateur f = new Formateur(cin, nom, tel, addresse, bureau);
			iFormateur.AddFormateur(f);
			
		}else if(!edit.equals("0")) {
			long editt = Long.parseLong(edit);
			Formateur f = iFormateur.getFormateur(editt);
			model.addAttribute("formateur", f);
		}else if(!saveedit.equals("0")) {
			long editt = Long.parseLong(saveedit);
			Formateur f = iFormateur.getFormateur(editt);
			
			f.setCin(cin2);
			f.setNom(nom);
			f.setTel(tel);
			f.setAddresse(addresse);
			f.setBureau(bureau);
			iFormateur.AddFormateur(f);
		}
			Page<Formateur> listFormateur = iFormateur.ListFormateurPages(page, size);
			int pages = listFormateur.getTotalPages();
			model.addAttribute("listFormateurs", listFormateur);
			model.addAttribute("pages", pages);
		
		return "Formateurs";
	}

	@RequestMapping(value="/deleteformateur")
	public String SupprimerFormateur(Model model, @RequestParam(name="idac", defaultValue="0") Long idac) {
		
		if(!idac.equals("0")) {
				iFormateur.DeleteFormateur(idac);
			
		}else {
			model.addAttribute("exception", "l'identificateur du formateur n'est pas définie");
		}
		return "redirect:/Formateurs";
		
	}
	
	
	@RequestMapping(value="/Recherche")
	public String chercher(Model model,@RequestParam(name="rech", defaultValue="0") String rechtype,
			@RequestParam(name="cinf", defaultValue="0") String cinf,
			@RequestParam(name="nomf", defaultValue="0") String nomf,
			@RequestParam(name="bureauf", defaultValue="0") String bureauf,
			
			@RequestParam(name="cnrpse", defaultValue="0") String cnrpse,
			@RequestParam(name="nome", defaultValue="0") String nome,
			@RequestParam(name="directione", defaultValue="0") String directione,
			@RequestParam(name="fonctione", defaultValue="0") String fonctione,
			@RequestParam(name="gradee", defaultValue="0") String gradee,
			
			@RequestParam(name="intitulea", defaultValue="0") String intitulea,
			@RequestParam(name="themea", defaultValue="0") String themea,
			@RequestParam(name="dateactiona", defaultValue="0") String dateactiona,
			@RequestParam(name="lieua", defaultValue="0") String lieua,
			@RequestParam(name="bureaua", defaultValue="0") String bureaua) throws ParseException {
		
		switch(rechtype) {
		case "0" : 
			
			break;
		case "recha" : 
			if(!intitulea.equals("0") && themea.equals("0") && dateactiona.equals("0") && lieua.equals("0") && bureaua.equals("0")) {
				Set<Action> listeActions = iaction.ListActionByIntitule(intitulea);
				model.addAttribute("listeActions", listeActions);
				
			}else if(intitulea.equals("0") && !themea.equals("0") && dateactiona.equals("0") && lieua.equals("0") && bureaua.equals("0")) {
				Set<Action> listeActions = iaction.ListActionByTheme(themea);
				model.addAttribute("listeActions", listeActions);
				
			}else if(intitulea.equals("0") && themea.equals("0") && !dateactiona.equals("0") && lieua.equals("0") && bureaua.equals("0")) {
				Set<Action> listeActions = iaction.ListActionByActionDate(dateactiona);
				model.addAttribute("listeActions", listeActions);
			}
			else if(intitulea.equals("0") && themea.equals("0") && dateactiona.equals("0") && !lieua.equals("0") && bureaua.equals("0")) {
				Set<Action> listeActions = iaction.ListActionByLieu(lieua);
				model.addAttribute("listeActions", listeActions);
				
			}else if(intitulea.equals("0") && themea.equals("0") && dateactiona.equals("0") && lieua.equals("0") && !bureaua.equals("0")) {
				Set<Action> listeActions = iaction.ListActionByBureau(bureaua);
				model.addAttribute("listeActions", listeActions);
			}
			break;
			
		case "reche" : 
	
			break;
			
		case "rechf" : 
			
			if(!cinf.equals("0") && nomf.equals("0") && bureauf.equals("0")) {
				Set<Action> listeActions = iFormateur.ListActionByCinFormateur(cinf);
				model.addAttribute("listeActions", listeActions);
				
			}else if(cinf.equals("0") && !nomf.equals("0") && bureauf.equals("0")) {
				Set<Action> listeActions = iFormateur.ListActionByNomFormateur(nomf);
				model.addAttribute("listeActions", listeActions);
				
			}else if(cinf.equals("0") && nomf.equals("0") && !bureauf.equals("0")) {
				Set<Action> listeActions = iFormateur.ListActionByBureauFormateur(bureauf);
				model.addAttribute("listeActions", listeActions);
			}
			
			
			break;
		}
		
		return "recherche";
	}
	
	@RequestMapping(value="/Employes")
	public String GestionEmployes(Model model, @RequestParam(name="cin" , defaultValue="0") String cin
			,@RequestParam(name="cnrps" , defaultValue="0") String cnrps
			,@RequestParam(name="cnrps2" , defaultValue="0") String cnrps2
			,@RequestParam(name="nom" , defaultValue="0") String nom
			,@RequestParam(name="tel" , defaultValue="0") String tel
			,@RequestParam(name="addresse" , defaultValue="0") String addresse
			,@RequestParam(name="grade" , defaultValue="0") String grade
			,@RequestParam(name="fonctions" , defaultValue="0") String fonctions
			,@RequestParam(name="sdirection" , defaultValue="0") String sdirection
			,@RequestParam(name="page" , defaultValue="0") int page
			,@RequestParam(name="size" , defaultValue="6") int size
			,@RequestParam(name="edit" , defaultValue="0") String edit
			,@RequestParam(name="saveedit" , defaultValue="0") String saveedit) throws Exception {
		
		if(!cnrps.equals("0")) {
			//Formateur f = new Formateur(cin, nom, tel, addresse, bureau);
			//iFormateur.AddFormateur(f);
			Employe NewEmp = new Employe(cnrps, cin, nom, addresse, tel, iGrade.GetGradeById(Long.parseLong(grade))
					, iFonctions.GetFonctionById(Long.parseLong(fonctions)), iSousDirection.GetSousDirectionById(Long.parseLong(sdirection)));
			iemploye.AjouterEmploye(NewEmp);
			
		}else if(!edit.equals("0")) {

			long editt = Long.parseLong(edit);
			Employe employe = iemploye.ConsulterEmploye(editt);
			model.addAttribute("Employe", employe);
		}else if(!saveedit.equals("0")) {
			
			long editt = Long.parseLong(saveedit);
			Employe employe = iemploye.ConsulterEmploye(editt);
			
			employe.setCnrps(cnrps2);
			employe.setCin(cin);
			employe.setAddresse(addresse);
			employe.setNom(nom);
			employe.setTel(tel);
			employe.setFonctions(iFonctions.GetFonctionById(Long.parseLong(fonctions)));
			employe.setDirection(iSousDirection.GetSousDirectionById(Long.parseLong(sdirection)));
			employe.setGrade(iGrade.GetGradeById(Long.parseLong(grade)));
			
			
			iemploye.AjouterEmploye(employe);
			
		}
			Page<Employe> listEmployes = iemploye.listeEmploye(page, size);
			int pages = listEmployes.getTotalPages();
			model.addAttribute("listEmployes", listEmployes);
			model.addAttribute("pages", pages);
			
			List<Grade> listGrade = iGrade.listGrades();
			List<Fonctions> listFonction = 	iFonctions.listFonctions();
			List<SousDirection> listSDirection = iSousDirection.listSousDirection();
			
			model.addAttribute("listGrade", listGrade);
			model.addAttribute("listFonction", listFonction);
			model.addAttribute("listSDirection", listSDirection);
		
		return "Employes";
	}
}
