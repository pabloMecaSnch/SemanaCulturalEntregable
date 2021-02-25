/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.DaoImplements;

import com.semanacultural.webservice.entidades.Profesor;
import com.semanacultural.webservice.entidades.Taller;
import xml.handler.TallerHandlerXML;
import java.util.ArrayList;
import xml.interfacesDao.IDaoTaller;

/**
 *
 * @author enrique
 */
public class DaoTallerXML extends DaoXML implements IDaoTaller {
    

    private String nombreFichero;
    private  TallerHandlerXML handler;
  
    
    public DaoTallerXML(String nombreFichero){
       
        
        super(new TallerHandlerXML());
        handler=(TallerHandlerXML)this.getHandler();
        this.nombreFichero=nombreFichero;

    }
    
  
    public void guardarProfesor(Profesor p) {
       
    }

    public ArrayList<Taller> verTalleres(){
        try {
            this.leerXML(nombreFichero);
            return handler.getTalleres();
        } catch (Exception ex) {
           return null;
        }
           
    }
  
        
    
}
