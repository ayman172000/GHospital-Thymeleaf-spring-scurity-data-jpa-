package com.example.ghospital.web;

import com.example.ghospital.entities.Medcin;
import com.example.ghospital.entities.Patient;
import com.example.ghospital.repositories.MedcinRepositorie;
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
public class MedcinController {
    private MedcinRepositorie medcinRepositorie;

    @GetMapping(path = "/user/medcins")
    public  String medcins(
            Model model,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5") int size,
            @RequestParam(name = "KeyWord",defaultValue = "") String KeyWord) {
        Page<Medcin> PagePatients=medcinRepositorie.findByNomContains(KeyWord, PageRequest.of(page,size));
        //Page<Patient> PagePatients=patientRepository.findAll(PageRequest.of(page,size));
        model.addAttribute("listPatients",PagePatients.getContent());
        model.addAttribute("pages",new int[PagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("KeyWord",KeyWord);
        return "medcins";//une vue basée sur lae moteur de recherche tymelift
    }

    @GetMapping ("/admin/deleteMedcin")
    public String Delete(long id,String KeyWord,int page)
    {
        medcinRepositorie.deleteById(id);
        return "redirect:/user/medcins?page="+page+"&KeyWord="+KeyWord;
    }

    @GetMapping("/admin/formMedcins")
    public String formMedcins(Model model){
        //pour des valeur par default
        model.addAttribute("medcin",new Medcin());
        return "formMedcins";
    }

    @PostMapping("/admin/saveMedcin")
    public String save(
            Model model,
            @Valid Medcin medcin,
            BindingResult bindingResult,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page)
    {
        if(bindingResult.hasErrors()) return "formMedcins";
        medcinRepositorie.save(medcin);
        return "redirect:/user/medcins?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editMedcin")
    public String editMedcin(Model model,Long id,String keyword,int page){
        //pour des valeur par default
        Medcin medcin=medcinRepositorie.findById(id).orElse(null);
        if(medcin==null) throw new RuntimeException("medcin introuvable");
        model.addAttribute("medcin",medcin);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editMedcin";
    }
}
