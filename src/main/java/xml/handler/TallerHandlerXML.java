/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.handler;

import com.semanacultural.webservice.entidades.Profesor;
import com.semanacultural.webservice.entidades.Taller;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.DaoImplements.DaoTallerXML;

/**
 *
 * @author enrique
 */
public class TallerHandlerXML extends DefaultHandler{
    
    private StringBuilder cadena;
    
    
    private Taller taller;
    private ArrayList<Taller> talleres;
   
    
  
  //Parte que recoge los objetos creados para ser enviados fuera de la clase
    
    public ArrayList<Taller> getTalleres() {
  
        return talleres;
    }
    
    
    
    
    
    //parte que mapea el documento e introduce los datos en objetos
    
          
    @Override
    public void startDocument() throws SAXException {
        cadena=new StringBuilder();
        talleres=new ArrayList<Taller>();
        System.out.println("startDocument");
          
    }
    
    @Override
    public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atrbts) throws SAXException {
        cadena.setLength(0);
        if (nombreCualif.equals("taller")){
            taller=new Taller();
            taller.setNombre(atrbts.getValue("nombre"));
            taller.setHoras(Integer.parseInt(atrbts.getValue("horas")));
            talleres.add(taller);
        }
       
         System.out.println("startElement: "+nombreLocal+ " "+nombreCualif);
     }
    
    @Override
    public void characters(char[] chars, int inicio, int lon) throws SAXException {
        
         cadena.append(chars, inicio, lon);
        
         System.out.println("dato final: "+cadena);
            
    }
    
    @Override
    public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {
        if(nombreCualif.equals("Profesor")){
            Profesor profesor = new Profesor();
            profesor.setTalleridTaller(taller);
            profesor.setNombre(cadena.toString());
            taller.addProfesor(profesor);
        }
        System.out.println("endtElement: "+nombreLocal+ " "+nombreCualif);
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
       
    }
   

   

   

    

   
    
}
