/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Contratista;
import persistencia.ContratistaFacadeLocal;

/**
 *
 * @author Kevin
 */
@Stateless
public class ContratistaLogica implements ContratistaLogicaLocal {

    //Entreprise java beans
    @EJB
    public ContratistaFacadeLocal contratistaDAO;

    @Override
    public void registrarContratista(Contratista c) throws Exception {
        if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (c.getNitcontratista() == 0) {
            throw new Exception("Nit obligatorio");
        }
        if (c.getNombrecontratista().equals("")) {
            throw new Exception("Nombre obligatorio");
        }

        contratistaDAO.create(c);

    }

    @Override
    public List<Contratista> consultarContratistas() {
        return contratistaDAO.findAll();
    }

    @Override
    public Contratista consultarxCodigo(Integer codigo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificarContratista(Contratista c) throws Exception {
        if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        if (c.getNitcontratista() == 0) {
            throw new Exception("Nit obligatorio");
        }
        if (c.getNombrecontratista().equals("")) {
            throw new Exception("Nombre obligatorio");
        }

        Contratista objetoContratista = contratistaDAO.findxNit(c.getNitcontratista());
        if (objetoContratista == null) {
            throw new Exception("El contratista no existe");
        }
        contratistaDAO.edit(c);
    }

    @Override
    public void eliminarContratista(Contratista c) throws Exception {
        if (c == null) {
            throw new Exception("Error, los campos son obligatorios");
        }
        Contratista objBorrar = contratistaDAO.find(c.getCodigocontratista());
        if(objBorrar == null) {
            throw new Exception("Error, contratista no existe");
        }
        //Validación que el contratista no tenga ingresos ni contratos 
        if(objBorrar.getContratosList().size()>0 || objBorrar.getIngresoList().size()>0){
            throw new Exception("El contratista tiene ingresos o contratos asociados");
        }
        contratistaDAO.remove(objBorrar);
    }

    @Override
    public String importarDatosContratista(String archivo) throws Exception {
        int contratistasR = 0; //Contratistas Registrados
        int contratistasE = 0; //Contratistas Existentes
        Workbook archivoExcel = Workbook.getWorkbook(new File(archivo));
        Sheet hoja = archivoExcel.getSheet(0);
        int filas = hoja.getRows();
        for(int i = 1; i < filas; i++){
            Contratista nuevoContratista = new Contratista();
            nuevoContratista.setNitcontratista(Long.parseLong(hoja.getCell(0, i).getContents()));
            nuevoContratista.setNombrecontratista(hoja.getCell(1, i).getContents());
            nuevoContratista.setEstadocontratista("ACTIVO");
            // Validamos el contratista
            Contratista objC = contratistaDAO.findxNit(nuevoContratista.getNitcontratista());
            
            if (objC == null) {
                contratistaDAO.create(nuevoContratista);
                contratistasR++;
            }else{
                contratistasE++;
            }
                  
        }
        return "Se registraton " + contratistasR +  " contratistas y ya existían " + contratistasE;
    }

    @Override
    public Contratista consultarxNit(Long nit) throws Exception {
        return contratistaDAO.findxNit(nit);
    }

}
