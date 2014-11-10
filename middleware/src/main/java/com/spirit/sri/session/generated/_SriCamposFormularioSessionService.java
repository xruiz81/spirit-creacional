package com.spirit.sri.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _SriCamposFormularioSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.sri.entity.SriCamposFormularioIf addSriCamposFormulario(com.spirit.sri.entity.SriCamposFormularioIf model) throws GenericBusinessException;

   void saveSriCamposFormulario(com.spirit.sri.entity.SriCamposFormularioIf model) throws GenericBusinessException;

   void deleteSriCamposFormulario(java.lang.Long id) throws GenericBusinessException;

   Collection findSriCamposFormularioByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.sri.entity.SriCamposFormularioIf getSriCamposFormulario(java.lang.Long id) throws GenericBusinessException;

   Collection getSriCamposFormularioList() throws GenericBusinessException;

   Collection getSriCamposFormularioList(int startIndex, int endIndex) throws GenericBusinessException;

   int getSriCamposFormularioListSize() throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByImpuesto(java.lang.String impuesto) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByConcepto(java.lang.String concepto) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findSriCamposFormularioByValor(java.lang.String valor) throws GenericBusinessException;
   
    Collection findCamposFormularioByQueryOrderCodigo(java.lang.String observacion,java.lang.String tipoImpuesto) throws GenericBusinessException ;
    
    
}
