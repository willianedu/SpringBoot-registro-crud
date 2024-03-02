package com.mycompany.Controller;

import com.mycompany.Exceptions.AlunoNotFoundException;
import com.mycompany.modelo.Aluno;
import com.mycompany.servico.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AlunoController {
    @Autowired private AlunoService service;
    
    @GetMapping("/alunos")
    public String showAlunoList(Model model) {
        List<Aluno> listAlunos = service.listAll();
        model.addAttribute("listAlunos", listAlunos);

        return "alunos";
    }

    @GetMapping("/alunos/new")
    public String showNewForm(Model model) {
        model.addAttribute("aluno", new Aluno());
        model.addAttribute("pageTitle", "Add New Aluno");
        return "aluno_form";
    }

    @PostMapping("/alunos/save")
    public String saveAluno(Aluno aluno, RedirectAttributes ra) {
        service.save(aluno);
        ra.addFlashAttribute("message", "The aluno has been saved successfully.");
        return "redirect:/alunos";
    }

    @GetMapping("/alunos/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Aluno aluno = service.get(id);
            model.addAttribute("aluno", aluno);
            model.addAttribute("pageTitle", "Edit Aluno (ID: " + id + ")");

            return "aluno_form";
        } catch (AlunoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/alunos";
        }
    }

    @GetMapping("/alunos/delete/{id}")
    public String deleteAluno(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (AlunoNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/alunos";
    }
}
