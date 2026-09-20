/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.controlador;

import com.mycompany.mvc1.dao.MarcaDAO;
import com.mycompany.mvc1.modelos.Marca;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller 
@RequestMapping("/marcas")
public class Marcacontrolador {
    private MarcaDAO marcaDAO = new MarcaDAO();

    @GetMapping()
    public String listar(Model model) {

        model.addAttribute("marcas", marcaDAO.listaTodos());

        if (!model.containsAttribute("marca")) {
            model.addAttribute("marca", new Marca());
        }

        return "marcas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        model.addAttribute("marcas", marcaDAO.listaTodos());
        model.addAttribute("marca", marcaDAO.buscarporId(id));

        return "marcas";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Marca marca,
            RedirectAttributes ra) {

        try {

            boolean ok = marca.getIdMarca() == 0
                    ? marcaDAO.insertar(marca)
                    : marcaDAO.actualizar(marca);

            if (!ok) {
                ra.addFlashAttribute("error", "Error al momento de almacenar");
            }

        } catch (Exception e) {

            ra.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/marcas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable int id,
            RedirectAttributes ra) {Marca marca = marcaDAO.buscarporId(id);

        if (marca != null) {

            boolean ok = marcaDAO.eliminar(marca);

            if (!ok) {
                ra.addFlashAttribute("error", "Error al eliminar la marca");
            }
        }

        return "redirect:/marcas";
    }
}
