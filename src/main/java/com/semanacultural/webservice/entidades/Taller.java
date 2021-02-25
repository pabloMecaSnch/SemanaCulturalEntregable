/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.semanacultural.webservice.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pablo
 */
@Entity
@Table(name = "taller")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taller.findAll", query = "SELECT t FROM Taller t"),
    @NamedQuery(name = "Taller.findByIdTaller", query = "SELECT t FROM Taller t WHERE t.idTaller = :idTaller"),
    @NamedQuery(name = "Taller.findByNombre", query = "SELECT t FROM Taller t WHERE t.nombre = :nombre")})
public class Taller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Taller")
    private Integer idTaller;
    @Column(name = "nombre")
    private String nombre;
    @Column(name="horas")
    private Integer horas;

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }
    @ManyToMany(mappedBy = "tallerList")
    private List<Alumno> alumnoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "talleridTaller")
    private List<Profesor> profesorList;

    public Taller() {
        this.alumnoList=new ArrayList<>();
    }

    public Taller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Alumno> getAlumnoList() {
        if(this.alumnoList==null)
            this.alumnoList = new ArrayList<>();
        return alumnoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    @XmlTransient
    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    public void addProfesor(Profesor profesor){
        if(this.profesorList==null){
            this.profesorList = new ArrayList<Profesor>();
        }
        this.profesorList.add(profesor);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTaller != null ? idTaller.hashCode() : 0);
        return hash;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taller)) {
            return false;
        }
        Taller other = (Taller) object;
        if ((this.idTaller == null && other.idTaller != null) || (this.idTaller != null && !this.idTaller.equals(other.idTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.semanacultural.webservice.entidades.Taller[ idTaller=" + idTaller + " ]";
    }
    
}
