/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.repositories;

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
public interface TallerRepository extends JpaRepository<Taller, Integer> {
     @Query("select t from Taller t" )
    public List<Taller> verUsuarios();
    
    @Query("select t from Taller t where t.nombre=?1" )
    public List<Taller> verTallerNombre(String nombre);
}
