/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.repositories;

import com.semanacultural.webservice.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Pablo
 */
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
  
    @Query("select p from Profesor p where p.idProfesor=?1")
    public Profesor verProfesor(int id);
}
