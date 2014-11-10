package com.spirit.general.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _EmpleadoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.general.entity.EmpleadoIf addEmpleado(com.spirit.general.entity.EmpleadoIf model) throws GenericBusinessException;

   void saveEmpleado(com.spirit.general.entity.EmpleadoIf model) throws GenericBusinessException;

   void deleteEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection findEmpleadoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.general.entity.EmpleadoIf getEmpleado(java.lang.Long id) throws GenericBusinessException;

   Collection getEmpleadoList() throws GenericBusinessException;

   Collection getEmpleadoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getEmpleadoListSize() throws GenericBusinessException;
    java.util.Collection findEmpleadoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findEmpleadoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findEmpleadoByNombres(java.lang.String nombres) throws GenericBusinessException;
    java.util.Collection findEmpleadoByApellidos(java.lang.String apellidos) throws GenericBusinessException;
    java.util.Collection findEmpleadoByTipoidentificacionId(java.lang.Long tipoidentificacionId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByIdentificacion(java.lang.String identificacion) throws GenericBusinessException;
    java.util.Collection findEmpleadoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByProfesion(java.lang.String profesion) throws GenericBusinessException;
    java.util.Collection findEmpleadoByDireccionDomicilio(java.lang.String direccionDomicilio) throws GenericBusinessException;
    java.util.Collection findEmpleadoByTelefonoDomicilio(java.lang.String telefonoDomicilio) throws GenericBusinessException;
    java.util.Collection findEmpleadoByCelular(java.lang.String celular) throws GenericBusinessException;
    java.util.Collection findEmpleadoByEmailOficina(java.lang.String emailOficina) throws GenericBusinessException;
    java.util.Collection findEmpleadoByDepartamentoId(java.lang.Long departamentoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByJefeId(java.lang.Long jefeId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByTipoempleadoId(java.lang.Long tipoempleadoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByExtensionOficina(java.lang.String extensionOficina) throws GenericBusinessException;
    java.util.Collection findEmpleadoByNivel(java.lang.Integer nivel) throws GenericBusinessException;
    java.util.Collection findEmpleadoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findEmpleadoByOficinaId(java.lang.Long oficinaId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByTipocontratoId(java.lang.Long tipocontratoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByBancoId(java.lang.Long bancoId) throws GenericBusinessException;
    java.util.Collection findEmpleadoByTipoCuenta(java.lang.String tipoCuenta) throws GenericBusinessException;
    java.util.Collection findEmpleadoByNumeroCuenta(java.lang.String numeroCuenta) throws GenericBusinessException;

}
