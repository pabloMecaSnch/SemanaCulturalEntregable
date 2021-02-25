/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.interfacesDao;

import com.semanacultural.webservice.entidades.Profesor;
import com.semanacultural.webservice.entidades.Taller;
import java.util.ArrayList;

/**
 *
 * @author enrique
 */
public interface IDaoTaller {
    
    public void guardarProfesor(Profesor p);
    public ArrayList<Taller> verTalleres();
    
    
}
