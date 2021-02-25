/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.service;

import com.semanacultural.webservice.entidades.Alumno;
import com.semanacultural.webservice.entidades.Profesor;
import com.semanacultural.webservice.entidades.Taller;
import com.semanacultural.webservice.repositories.ProfesorRepository;
import com.semanacultural.webservice.repositories.AlumnoRepository;
import com.semanacultural.webservice.repositories.TallerRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pablo
 */
@Service
public class WebService {
    
    private final AlumnoRepository aluReposirtory;
    private final ProfesorRepository profReposirtory;
    private final TallerRepository tallerReposirtory;
    
    public WebService(AlumnoRepository aluReposirtory,ProfesorRepository profReposirtory, TallerRepository tallerReposirtory){
        this.aluReposirtory=aluReposirtory;
        this.profReposirtory = profReposirtory;
        this.tallerReposirtory = tallerReposirtory;
    }
    
    //--------CRUD Alumno
    public void guardarAlumno(Alumno alumno){
        aluReposirtory.save(alumno);
    }
    public void borrarAlumno(Alumno alumno){
        aluReposirtory.delete(alumno);
    }
    public List<Alumno> getAlumnos(){
        List<Alumno> alumnos = aluReposirtory.findAll();
        return alumnos;
    }
    public Alumno getOneAlumno(Integer id){
        return aluReposirtory.getOne(id);
        
    }
    public void actualizarAlumno(Alumno alumno){
        aluReposirtory.updateAlumno(alumno.getIdAlumno(), alumno.getNombre());
    }
    
    //--------CRUD Profesor
    public void guardarProfesor(Profesor profesor){
        profReposirtory.save(profesor);
    }
    public void borrarProfesor(Profesor profesor){
        profReposirtory.delete(profesor);
    }
    public List<Profesor> getProfesores(){
        List<Profesor> profesores = profReposirtory.findAll();
        return profesores;
    }
    public Profesor getOneProfesor(Integer id){
        return profReposirtory.getOne(id);
    }
    public void actualizarProfesor(Profesor profesor){
        profReposirtory.updateProfesor(profesor.getIdProfesor(),profesor.getNombre());
    }
    
    //--------CRUD Taller
    public void guardarTaller(Taller taller){
        tallerReposirtory.save(taller);
    }
    public void borrarTaller(Taller taller){
        tallerReposirtory.delete(taller);
    }
    public List<Taller> verTallerNombre(Taller taller){
        return tallerReposirtory.verTallerNombre(taller.getNombre());
    }
    public List<Taller> getTalleres(){
        List<Taller> taller = tallerReposirtory.findAll();
        return taller;
    }
    public Taller getOneTaller(Integer id){
        return tallerReposirtory.getOne(id);
        
    }
}
