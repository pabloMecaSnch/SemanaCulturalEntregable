/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.controller;

import com.semanacultural.webservice.entidades.Alumno;
import com.semanacultural.webservice.entidades.Profesor;
import com.semanacultural.webservice.entidades.Taller;
import com.semanacultural.webservice.service.WebService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import xml.DaoImplements.DaoTallerXML;
import xml.handler.TallerHandlerXML;
/**
 *
 * @author Pablo
 */
@Controller
public class ControladorWeb {
    private final WebService service;
    
    public ControladorWeb(WebService aluService){
        this.service = aluService;
    }
    
    @RequestMapping("/")
    public String listaralumnos(Model modelo){
        List<Alumno> alumnos = service.getAlumnos();
        modelo.addAttribute("alumnos", alumnos );
        
        return"listar_alumnos";
        
    }
    
    //-----------------Peticiones alumno-----------------
    @RequestMapping("/crear_alumno")
    public String crearAlumno(Model modelo){
        Alumno alumno= new Alumno();
        modelo.addAttribute("alumno", alumno);
        List<Taller> talleres = service.getTalleres();
        modelo.addAttribute("talleres",talleres);
        return "crear_alumno";
    }
    
    @RequestMapping("/guardar_alumno")
    public String guardarAlumno(Alumno alumno,@RequestParam Integer[] selectTaller){
        alumno.addTaller(selectTaller);
        /*
        List<Taller> talleres = new ArrayList<>();
        talleres.add(taller);
        alumno.setTallerList(talleres);
        */
        service.guardarAlumno(alumno);
        return"redirect:/";
    }
    @RequestMapping("/borrar_alumno/{id}")
    public String borrarAlumno(@PathVariable(value="id") Integer idAlumno){
        Alumno alumno= new Alumno(idAlumno);
        service.borrarAlumno(alumno);
        return"redirect:/";
    }
    @RequestMapping("/alumno_edit/{id}")
    public String editTarea(@PathVariable(value="id") Integer idAlumno, Model modelo){
        Alumno alummnoBase = service.getOneAlumno(idAlumno);
        modelo.addAttribute("alumno",alummnoBase);
        List<Taller> talleres = service.getTalleres();
        modelo.addAttribute("talleres",talleres);
        
        System.out.println(alummnoBase.getNombre());
        return "/alumno_edit";
    }
    @RequestMapping("/act_alumno")
    public String actAlumno(Alumno alumno,@RequestParam Integer[] selectTaller){
        //Alumno alu = service.getOneAlumno(idAlumno);
        alumno.addTaller(selectTaller);
        service.guardarAlumno(alumno);
        
        return"redirect:/";
    }

    
    //-----------------Peticiones Profesor-----------------
    @RequestMapping("/listar_profesores")
    public String listarProfesores(Model modelo){
         List<Profesor> profesores = service.getProfesores();
        modelo.addAttribute("profesores", profesores );   
        return "listar_profesores";
    }
    
    
    @RequestMapping("/crear_profesor")
    public String crearProfesor(Model modelo){
        Profesor profesor= new Profesor();
        modelo.addAttribute("profesor", profesor);
        List<Taller> talleres = service.getTalleres();
        modelo.addAttribute("talleres",talleres);
        return "crear_profesor";
    }
    
    @RequestMapping("/guardar_profesor")
    public String guardarProfesor(Profesor profesor,@RequestParam Integer selectTaller){
        Taller taller = new Taller(selectTaller);
        profesor.setTalleridTaller(taller);
        service.guardarProfesor(profesor);
        return"redirect:/listar_profesores";
    }
    @RequestMapping("/borrar_profesor/{id}")
    public String borrarProfesor(@PathVariable(value="id") Integer idProfesor){
        Profesor profesor= new Profesor(idProfesor);
        service.borrarProfesor(profesor);
        return"redirect:/listar_profesores";
    }
    @RequestMapping("/profesor_edit/{id}")
    public String editPorfesor(@PathVariable(value="id") Integer idProfesor, Model modelo){
        Profesor profesorBase = service.getOneProfesor(idProfesor);
        modelo.addAttribute("profesor",profesorBase);
        List<Taller> talleres = service.getTalleres();
        modelo.addAttribute("talleres",talleres);
        System.out.println(profesorBase.getNombre());
        return "/profesor_edit";
    }
     @RequestMapping("/act_profesor")
    public String actProfesor(Profesor profesor,@RequestParam Integer selectTaller){
        //Alumno alu = service.getOneAlumno(idAlumno);
        profesor.setTalleridTaller(new Taller(selectTaller));
        service.guardarProfesor(profesor);
        
        return"redirect:/listar_profesores";
    }
    //-----------------Peticiones Taller-----------------
    
    @RequestMapping("/listar_talleres")
    public String listarTalleres(Model modelo){
        List<Taller> talleres = service.getTalleres();
        modelo.addAttribute("talleres", talleres );
   
        return "/listar_talleres";
    }
    @RequestMapping("/crear_taller")
    public String crearTaller(Model modelo){
        Taller taller= new Taller();
        modelo.addAttribute("taller", taller);
        List<Profesor> profesores = service.getProfesores();
        modelo.addAttribute("profesores",profesores);
        return "crear_taller";
    }
    
    @RequestMapping("/guardar_taller")
    public String guardarTaller(Taller taller){
        service.guardarTaller(taller);
        return"redirect:/";
    }
    @RequestMapping("taller_edit/borrar_taller/{id}")
    public String borrarTallerEdit(@PathVariable(value="id") Integer idTaller){
        Taller taller= new Taller(idTaller);
        service.borrarTaller(taller);
        return"redirect:/";
    }
     @RequestMapping("/borrar_taller/{id}")
    public String borrarTaller(@PathVariable(value="id") Integer idTaller){
        Taller taller= service.getOneTaller(idTaller);
        if(taller.getAlumnoList().isEmpty()){
            List<Profesor> profesoresTaller = taller.getProfesorList();
            profesoresTaller.forEach(profesor->{
               service.actualizarProfesor(profesor);
            });
            service.borrarTaller(taller);
        }
        return"redirect:/listar_talleres";
    }
    @RequestMapping("/taller_edit/{id}")
    public String editTaller(@PathVariable(value="id") Integer idTaller, Model modelo){
        Taller tallerBase = service.getOneTaller(idTaller);
        modelo.addAttribute("taller",tallerBase);
        System.out.println(tallerBase.getNombre());
        return "/taller_edit";
    }
    @RequestMapping("/act_taller")
    public String actTaller(Taller taller){
        service.guardarTaller(taller);
        return"redirect:/";
    }
    
    //leer XMLO
    @RequestMapping("/leer_fichero")
    public String leerFichero(){
        //modificar la ruta del archivo
        DaoTallerXML dp= new DaoTallerXML("C:\\Users\\usuario\\Documents\\GitHub\\SemanaCulturalEntregable\\src\\main\\java\\xml\\archivo\\profesoresJulioVerne.xml");
        ArrayList<Taller> talleres=dp.verTalleres();
        talleres.forEach((taller)->{
            service.guardarTaller(taller);
            taller.getProfesorList().forEach(profesor->{
              service.guardarProfesor(profesor);
            });
        });
        return "redirect:/listar_talleres";
    }
    @RequestMapping("/borrar_fichero")
    public String borrarFichero(){
        DaoTallerXML dp= new DaoTallerXML("C:\\Users\\usuario\\Documents\\GitHub\\SemanaCulturalEntregable\\src\\main\\java\\xml\\archivo\\profesoresJulioVerne.xml");
        ArrayList<Taller> talleres=dp.verTalleres();
        ArrayList<Taller> talleres2=new ArrayList<>();
        
        talleres.forEach(taller->{
            List<Taller> f = service.verTallerNombre(taller);
            talleres2.addAll(service.verTallerNombre(taller));
        });
        talleres2.forEach(taller->{
            
            if(taller.getAlumnoList().isEmpty()){
                List<Profesor> profesoresTaller = taller.getProfesorList();
                profesoresTaller.forEach(profesor->{
                   service.actualizarProfesor(profesor);
                });
                service.borrarTaller(taller);
            }
        });
        
        return "redirect:/listar_talleres";
    }
}
