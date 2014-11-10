package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteIf addCliente(com.spirit.crm.entity.ClienteIf model) throws GenericBusinessException;

   void saveCliente(com.spirit.crm.entity.ClienteIf model) throws GenericBusinessException;

   void deleteCliente(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteIf getCliente(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteList() throws GenericBusinessException;

   Collection getClienteList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteListSize() throws GenericBusinessException;
    java.util.Collection findClienteById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteByTipoidentificacionId(java.lang.Long tipoidentificacionId) throws GenericBusinessException;
    java.util.Collection findClienteByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findClienteByNombreLegal(java.lang.String nombreLegal) throws GenericBusinessException;
    java.util.Collection findClienteByRazonSocial(java.lang.String razonSocial) throws GenericBusinessException;
    java.util.Collection findClienteByRepresentante(java.lang.String representante) throws GenericBusinessException;
    java.util.Collection findClienteByCorporacionId(java.lang.Long corporacionId) throws GenericBusinessException;
    java.util.Collection findClienteByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findClienteByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findClienteByTipoclienteId(java.lang.Long tipoclienteId) throws GenericBusinessException;
    java.util.Collection findClienteByTipoproveedorId(java.lang.Long tipoproveedorId) throws GenericBusinessException;
    java.util.Collection findClienteByTiponegocioId(java.lang.Long tiponegocioId) throws GenericBusinessException;
    java.util.Collection findClienteByCuentaId(java.lang.Long cuentaId) throws GenericBusinessException;
    java.util.Collection findClienteByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findClienteByUsuariofinal(java.lang.String usuariofinal) throws GenericBusinessException;
    java.util.Collection findClienteByContribuyenteEspecial(java.lang.String contribuyenteEspecial) throws GenericBusinessException;
    java.util.Collection findClienteByTipoPersona(java.lang.String tipoPersona) throws GenericBusinessException;
    java.util.Collection findClienteByLlevaContabilidad(java.lang.String llevaContabilidad) throws GenericBusinessException;
    java.util.Collection findClienteByInformacionAdc(java.lang.String informacionAdc) throws GenericBusinessException;
    java.util.Collection findClienteByRequiereSap(java.lang.String requiereSap) throws GenericBusinessException;
    java.util.Collection findClienteByBancoId(java.lang.Long bancoId) throws GenericBusinessException;
    java.util.Collection findClienteByTipoCuenta(java.lang.String tipoCuenta) throws GenericBusinessException;
    java.util.Collection findClienteByNumeroCuenta(java.lang.String numeroCuenta) throws GenericBusinessException;

}
