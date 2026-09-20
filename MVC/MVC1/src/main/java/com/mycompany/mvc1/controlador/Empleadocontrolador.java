/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.controlador;

import com.mycompany.mvc1.dao.EmpleadoDAO;
import com.mycompany.mvc1.modelos.Empleado;
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
@RequestMapping("/empleados")
public class Empleadocontrolador {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @GetMapping()
    public String listar(Model model) {

        model.addAttribute("empleados", empleadoDAO.listaTodos());

        if (!model.containsAttribute("empleado")) {
            model.addAttribute("empleado", new Empleado());
        }

        return "empleados";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {

        model.addAttribute("empleados", empleadoDAO.listaTodos());
        model.addAttribute("empleado", empleadoDAO.buscarporId(id));

        return "empleados";
    }

    @PostMapping("/guardar")
    public String guardar(
        @ModelAttribute Empleado empleado, RedirectAttributes ra) {

    try {

        boolean ok = empleado.getIdEmpleado() == 0
                ? empleadoDAO.insertar(empleado)
                : empleadoDAO.actualizar(empleado);

        if (!ok) {
            ra.addFlashAttribute("error", "Error al momento de almacenar");
        }

    } catch (Exception e) {

        ra.addFlashAttribute("error", e.getMessage());
    }

    return "redirect:/empleados";
}

    @PostMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable int id,
            RedirectAttributes ra) {

        Empleado empleado = empleadoDAO.buscarporId(id);

        if (empleado != null) {

            boolean ok = empleadoDAO.eliminar(empleado);

            if (!ok) {
                ra.addFlashAttribute("error", "Error al eliminar el empleado");
            }
        }

        return "redirect:/empleados";
    }
}
