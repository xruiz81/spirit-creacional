package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GrupoObjetivoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.GrupoObjetivoIf addGrupoObjetivo(com.spirit.medios.entity.GrupoObjetivoIf model) throws GenericBusinessException;

   void saveGrupoObjetivo(com.spirit.medios.entity.GrupoObjetivoIf model) throws GenericBusinessException;

   void deleteGrupoObjetivo(java.lang.Long id) throws GenericBusinessException;

   Collection findGrupoObjetivoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.GrupoObjetivoIf getGrupoObjetivo(java.lang.Long id) throws GenericBusinessException;

   Collection getGrupoObjetivoList() throws GenericBusinessException;

   Collection getGrupoObjetivoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGrupoObjetivoListSize() throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByNivelSocioEconomico(java.lang.String nivelSocioEconomico) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByCiudad1(java.math.BigDecimal ciudad1) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByCiudad2(java.math.BigDecimal ciudad2) throws GenericBusinessException;
    java.util.Collection findGrupoObjetivoByCiudad3(java.math.BigDecimal ciudad3) throws GenericBusinessException;

}
