package com.spirit.medios.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _HerramientasMediosSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.medios.entity.HerramientasMediosIf addHerramientasMedios(com.spirit.medios.entity.HerramientasMediosIf model) throws GenericBusinessException;

   void saveHerramientasMedios(com.spirit.medios.entity.HerramientasMediosIf model) throws GenericBusinessException;

   void deleteHerramientasMedios(java.lang.Long id) throws GenericBusinessException;

   Collection findHerramientasMediosByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.medios.entity.HerramientasMediosIf getHerramientasMedios(java.lang.Long id) throws GenericBusinessException;

   Collection getHerramientasMediosList() throws GenericBusinessException;

   Collection getHerramientasMediosList(int startIndex, int endIndex) throws GenericBusinessException;

   int getHerramientasMediosListSize() throws GenericBusinessException;
    java.util.Collection findHerramientasMediosById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findHerramientasMediosByClienteOficinaId(java.lang.Long clienteOficinaId) throws GenericBusinessException;
    java.util.Collection findHerramientasMediosByProveedorOficinaId(java.lang.Long proveedorOficinaId) throws GenericBusinessException;
    java.util.Collection findHerramientasMediosByFrecuencia(java.lang.String frecuencia) throws GenericBusinessException;

}
