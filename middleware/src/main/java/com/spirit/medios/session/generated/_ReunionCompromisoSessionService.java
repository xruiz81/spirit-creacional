package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ReunionCompromisoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.ReunionCompromisoIf addReunionCompromiso(com.spirit.medios.entity.ReunionCompromisoIf model) throws GenericBusinessException;

   void saveReunionCompromiso(com.spirit.medios.entity.ReunionCompromisoIf model) throws GenericBusinessException;

   void deleteReunionCompromiso(java.lang.Long id) throws GenericBusinessException;

   Collection findReunionCompromisoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.ReunionCompromisoIf getReunionCompromiso(java.lang.Long id) throws GenericBusinessException;

   Collection getReunionCompromisoList() throws GenericBusinessException;

   Collection getReunionCompromisoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getReunionCompromisoListSize() throws GenericBusinessException;
    java.util.Collection findReunionCompromisoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findReunionCompromisoByReunionId(java.lang.Long reunionId) throws GenericBusinessException;
    java.util.Collection findReunionCompromisoByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findReunionCompromisoByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findReunionCompromisoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findReunionCompromisoByTemaTratado(java.lang.String temaTratado) throws GenericBusinessException;

}
