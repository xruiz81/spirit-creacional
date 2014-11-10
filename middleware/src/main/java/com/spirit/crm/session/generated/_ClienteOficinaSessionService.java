package com.spirit.crm.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _ClienteOficinaSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.crm.entity.ClienteOficinaIf addClienteOficina(com.spirit.crm.entity.ClienteOficinaIf model) throws GenericBusinessException;

   void saveClienteOficina(com.spirit.crm.entity.ClienteOficinaIf model) throws GenericBusinessException;

   void deleteClienteOficina(java.lang.Long id) throws GenericBusinessException;

   Collection findClienteOficinaByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.crm.entity.ClienteOficinaIf getClienteOficina(java.lang.Long id) throws GenericBusinessException;

   Collection getClienteOficinaList() throws GenericBusinessException;

   Collection getClienteOficinaList(int startIndex, int endIndex) throws GenericBusinessException;

   int getClienteOficinaListSize() throws GenericBusinessException;
    java.util.Collection findClienteOficinaById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByClienteId(java.lang.Long clienteId) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByCiudadId(java.lang.Long ciudadId) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByDireccion(java.lang.String direccion) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByTelefono(java.lang.String telefono) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByFax(java.lang.String fax) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByEmail(java.lang.String email) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByMontoCredito(java.math.BigDecimal montoCredito) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByMontoGarantia(java.math.BigDecimal montoGarantia) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByCalificacion(java.lang.String calificacion) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByObservacion(java.lang.String observacion) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByDescripcion(java.lang.String descripcion) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByCodigoProveedorAuto(java.lang.String codigoProveedorAuto) throws GenericBusinessException;
    java.util.Collection findClienteOficinaByVendedorId(java.lang.Long vendedorId) throws GenericBusinessException;

}
