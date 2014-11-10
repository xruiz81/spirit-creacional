package com.spirit.inventario.session.generated;

import com.spirit.exception.GenericBusinessException;


import java.util.*;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface _GenericoSessionService {

   /*******************************************************************************************************************
    *                                  B U S I N E S S   M E T H O D S
    *******************************************************************************************************************/


   /*******************************************************************************************************************
    *                                  P E R S I S T E N C E    M E T H O D S
    *******************************************************************************************************************/


   com.spirit.inventario.entity.GenericoIf addGenerico(com.spirit.inventario.entity.GenericoIf model) throws GenericBusinessException;

   void saveGenerico(com.spirit.inventario.entity.GenericoIf model) throws GenericBusinessException;

   void deleteGenerico(java.lang.Long id) throws GenericBusinessException;

   Collection findGenericoByQuery(Map aMap) throws GenericBusinessException;



   com.spirit.inventario.entity.GenericoIf getGenerico(java.lang.Long id) throws GenericBusinessException;

   Collection getGenericoList() throws GenericBusinessException;

   Collection getGenericoList(int startIndex, int endIndex) throws GenericBusinessException;

   int getGenericoListSize() throws GenericBusinessException;
    java.util.Collection findGenericoById(java.lang.Long id) throws GenericBusinessException;
    java.util.Collection findGenericoByCodigo(java.lang.String codigo) throws GenericBusinessException;
    java.util.Collection findGenericoByNombre(java.lang.String nombre) throws GenericBusinessException;
    java.util.Collection findGenericoByAbreviado(java.lang.String abreviado) throws GenericBusinessException;
    java.util.Collection findGenericoByNombreGenerico(java.lang.String nombreGenerico) throws GenericBusinessException;
    java.util.Collection findGenericoByCambioDescripcion(java.lang.String cambioDescripcion) throws GenericBusinessException;
    java.util.Collection findGenericoByEmpresaId(java.lang.Long empresaId) throws GenericBusinessException;
    java.util.Collection findGenericoByTipoproductoId(java.lang.Long tipoproductoId) throws GenericBusinessException;
    java.util.Collection findGenericoByMedidaId(java.lang.Long medidaId) throws GenericBusinessException;
    java.util.Collection findGenericoByPartidaArancelaria(java.lang.String partidaArancelaria) throws GenericBusinessException;
    java.util.Collection findGenericoByFechaCreacion(java.sql.Timestamp fechaCreacion) throws GenericBusinessException;
    java.util.Collection findGenericoByReferencia(java.lang.String referencia) throws GenericBusinessException;
    java.util.Collection findGenericoByUsaLote(java.lang.String usaLote) throws GenericBusinessException;
    java.util.Collection findGenericoByLineaId(java.lang.Long lineaId) throws GenericBusinessException;
    java.util.Collection findGenericoByIva(java.math.BigDecimal iva) throws GenericBusinessException;
    java.util.Collection findGenericoByIce(java.math.BigDecimal ice) throws GenericBusinessException;
    java.util.Collection findGenericoByOtroImpuesto(java.math.BigDecimal otroImpuesto) throws GenericBusinessException;
    java.util.Collection findGenericoByServicio(java.lang.String servicio) throws GenericBusinessException;
    java.util.Collection findGenericoByFamiliaGenericoId(java.lang.Long familiaGenericoId) throws GenericBusinessException;
    java.util.Collection findGenericoBySerie(java.lang.String serie) throws GenericBusinessException;
    java.util.Collection findGenericoByAfectastock(java.lang.String afectastock) throws GenericBusinessException;
    java.util.Collection findGenericoByEstado(java.lang.String estado) throws GenericBusinessException;
    java.util.Collection findGenericoByCobraIva(java.lang.String cobraIva) throws GenericBusinessException;
    java.util.Collection findGenericoByCobraIce(java.lang.String cobraIce) throws GenericBusinessException;
    java.util.Collection findGenericoByMediosProduccion(java.lang.String mediosProduccion) throws GenericBusinessException;
    java.util.Collection findGenericoByLlevaInventario(java.lang.String llevaInventario) throws GenericBusinessException;
    java.util.Collection findGenericoByAceptaDescuento(java.lang.String aceptaDescuento) throws GenericBusinessException;
    java.util.Collection findGenericoByCobraIvaCliente(java.lang.String cobraIvaCliente) throws GenericBusinessException;

}
