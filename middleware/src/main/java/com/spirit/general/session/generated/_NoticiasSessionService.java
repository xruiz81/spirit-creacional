package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _NoticiasSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.NoticiasIf addNoticias(com.spirit.general.entity.NoticiasIf model) throws GenericBusinessException;

   void saveNoticias(com.spirit.general.entity.NoticiasIf model) throws GenericBusinessException;

   void deleteNoticias(java.lang.Long id) throws GenericBusinessException;

   Collection findNoticiasByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.NoticiasIf getNoticias(java.lang.Long id) throws GenericBusinessException;

   Collection getNoticiasList() throws GenericBusinessException;

   Collection getNoticiasList(int startIndex, int endIndex) throws GenericBusinessException;

   int getNoticiasListSize() throws GenericBusinessException;
    java.util.Collection findNoticiasById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findNoticiasByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findNoticiasByUsuarioId(java.lang.Long usuarioId) throws GenericBusinessException;
    java.util.Collection findNoticiasByFechaIni(java.sql.Date fechaIni) throws GenericBusinessException;
    java.util.Collection findNoticiasByFechaFin(java.sql.Date fechaFin) throws GenericBusinessException;
    java.util.Collection findNoticiasByFechaCreacion(java.sql.Date fechaCreacion) throws GenericBusinessException;
    java.util.Collection findNoticiasByStatus(java.lang.String status) throws GenericBusinessException;
    java.util.Collection findNoticiasByNoticia(java.lang.String noticia) throws GenericBusinessException;
    java.util.Collection findNoticiasByArchivo(java.lang.String archivo) throws GenericBusinessException;
    java.util.Collection findNoticiasByAsunto(java.lang.String asunto) throws GenericBusinessException;

}
