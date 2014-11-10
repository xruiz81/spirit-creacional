package com.spirit.nomina.session.generated;

import java.util.Collection;
import java.util.Map;

import com.spirit.exception.GenericBusinessException;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _RubroSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.nomina.entity.RubroIf addRubro(com.spirit.nomina.entity.RubroIf model) throws GenericBusinessException;

   void saveRubro(com.spirit.nomina.entity.RubroIf model) throws GenericBusinessException;

   void deleteRubro(java.lang.Long id) throws GenericBusinessException;

   Collection findRubroByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.nomina.entity.RubroIf getRubro(java.lang.Long id) throws GenericBusinessException;

   Collection getRubroList() throws GenericBusinessException;

   Collection getRubroList(int startIndex, int endIndex) throws GenericBusinessException;

   int getRubroListSize() throws GenericBusinessException;
    java.util.Collection findRubroById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findRubroByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findRubroByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findRubroByFrecuencia(java.lang.String frecuencia) throws GenericBusinessException;
    java.util.Collection findRubroByTipoRubro(java.lang.String tipoRubro) throws GenericBusinessException;
    java.util.Collection findRubroByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findRubroByTiporolId(java.lang.Long tiporolId) throws GenericBusinessException;
    java.util.Collection findRubroByFecha(java.sql.Date fecha) throws GenericBusinessException;
    java.util.Collection findRubroByPolitica(java.lang.String politica) throws GenericBusinessException;
    java.util.Collection findRubroByModoOperacion(java.lang.String modoOperacion) throws GenericBusinessException;
    java.util.Collection findRubroByPagoIndividual(java.lang.String pagoIndividual) throws GenericBusinessException;
    java.util.Collection findRubroByNemonico(java.lang.String nemonico) throws GenericBusinessException;

}
