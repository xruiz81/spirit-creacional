package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpresaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.EmpresaIf addEmpresa(com.spirit.general.entity.EmpresaIf model) throws GenericBusinessException;

   void saveEmpresa(com.spirit.general.entity.EmpresaIf model) throws GenericBusinessException;

   void deleteEmpresa(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpresaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.EmpresaIf getEmpresa(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpresaList() throws GenericBusinessException;

   Collection getEmpresaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpresaListSize() throws GenericBusinessException;
    java.util.Collection findEmpresaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpresaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findEmpresaByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findEmpresaByLogo(java.lang.String logo) throws GenericBusinessException;
    java.util.Collection findEmpresaByRuc(java.lang.String ruc) throws GenericBusinessException;
    java.util.Collection findEmpresaByWeb(java.lang.String web) throws GenericBusinessException;
    java.util.Collection findEmpresaByEmailContador(java.lang.String emailContador) throws GenericBusinessException;
    java.util.Collection findEmpresaByTipoIdRepresentante(java.lang.Long tipoIdRepresentante) throws GenericBusinessException;
    java.util.Collection findEmpresaByNumeroIdentificacion(java.lang.String numeroIdentificacion) throws GenericBusinessException;
    java.util.Collection findEmpresaByRucContador(java.lang.String rucContador) throws GenericBusinessException;

}
