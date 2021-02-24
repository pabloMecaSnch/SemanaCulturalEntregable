/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.DaoImplements;

import com.semanacultural.webservice.entidades.Profesor;
import xml.handler.ProfesorHandlerXML;
import java.util.ArrayList;
import xml.interfacesDao.IDaoProfesor;

/**
 *
 * @author enrique
 */
public class DaoProfesorXML extends DaoXML implements IDaoProfesor {
    

    private String nombreFichero;
    private  ProfesorHandlerXML handler;
  
    
    public DaoProfesorXML(String nombreFichero){
       
        
        super(new ProfesorHandlerXML());
        handler=(ProfesorHandlerXML)this.getHandler();
        this.nombreFichero=nombreFichero;

    }
    
  
    public void guardarProfesor(Profesor p) {
       
    }

    public ArrayList<Profesor> verProfesores(){
      
        try {
            this.leerXML(nombreFichero);
            return handler.getProfesores();
        } catch (Exception ex) {
           return null;
        }
           
    }
  
        
    
}
