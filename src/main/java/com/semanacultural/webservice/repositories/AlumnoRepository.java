/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.repositories;

import com.semanacultural.webservice.entidades.Alumno;
import com.semanacultural.webservice.entidades.Taller;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Pablo
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno,Integer>{
    @Query("select a from Alumno a")
    public List<Alumno> verAlumnos();
    
    @Query("select a from Alumno a where a.idAlumno=?1")
    public Alumno verAlumno(Integer id);
    
    @Query("select a.tallerList from Alumno a where a.idAlumno=?1")
    public List<Taller> getTallerFromAlumno(Integer idAlu);
    
    @Transactional
    @Modifying
    @Query("Update Alumno SET nombre=?2 WHERE idAlumno=?1")
    public void updateAlumno(Integer idAlu,String nombre);
}
