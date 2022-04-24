package com.example.ghospital.web;

import com.example.ghospital.entities.Patient;
import com.example.ghospital.entities.RendezVous;
import com.example.ghospital.entities.StatusRdv;
import com.example.ghospital.repositories.MedcinRepositorie;
import com.example.ghospital.repositories.PatientRepositorie;
import com.example.ghospital.repositories.RendezVousRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RendezVousController {
    RendezVousRepositorie rendezVousRepositorie;
    PatientRepositorie patientRepositorie;
    MedcinRepositorie medcinRepositorie;

    @GetMapping(path = "/user/RendezVous")
    public  String RendezVous(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size,
            @RequestParam(name = "KeyWord",defaultValue = "ayman") String KeyWord) {
        Page<RendezVous> PageRendezVous=rendezVousRepositorie.search(KeyWord,KeyWord, PageRequest.of(page,size));
        //Page<Patient> PagePatients=patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listRendezVous",PageRendezVous.getContent());
        model.addAttribute("pages",new int[PageRendezVous.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("KeyWord",KeyWord);
        return "RendezVous";//une vue basée sur lae moteur de recherche tymelift
    }

    @GetMapping ("/admin/deleteRendezVous")
    public String Delete(String id,String KeyWord,int page)
    {
        rendezVousRepositorie.deleteById(id);
        return "redirect:/user/RendezVous?page="+page+"&KeyWord="+KeyWord;
    }

    @GetMapping("/admin/formRendezVous")
    public String formRendezVous(Model model){
        //pour des valeur par default
        model.addAttribute("RendezVous",new RendezVous());
        model.addAttribute("Patients",medcinRepositorie.findAll());
        model.addAttribute("Medcins",medcinRepositorie.findAll());
        return "formRendezVous";
    }

    @PostMapping("/admin/saveRendezVous")
    public String save(
            Model model,
            @Valid RendezVous rendezVous,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors()) return "formRendezVous";
        rendezVous.setStatus(StatusRdv.PENDING);
        rendezVousRepositorie.save(rendezVous);
        return "redirect:/user/RendezVous?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editRendezVous")
    public String editRendezVous(Model model,String id,String keyword,int page){
        //pour des valeur par default
        RendezVous rendezVous=rendezVousRepositorie.findById(id).orElse(null);
        if(rendezVous==null) throw new RuntimeException("Rendez Vous introuvable");
        model.addAttribute("rendezVous",rendezVous);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        model.addAttribute("Patients",medcinRepositorie.findAll());
        model.addAttribute("Medcins",medcinRepositorie.findAll());
        return "editRendezVous";
    }
}
