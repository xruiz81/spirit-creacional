package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface EventoContableIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getModuloId();

   void setModuloId(java.lang.Long moduloId);

   java.lang.Long getOficinaId();

   void setOficinaId(java.lang.Long oficinaId);

   java.lang.Long getDocumentoId();

   void setDocumentoId(java.lang.Long documentoId);

   java.lang.Long getLineaId();

   void setLineaId(java.lang.Long lineaId);

   java.lang.Long getPlanCuentaId();

   void setPlanCuentaId(java.lang.Long planCuentaId);

   java.lang.Long getEtapa();

   void setEtapa(java.lang.Long etapa);

   java.lang.String getAutorizacionRequerida();

   void setAutorizacionRequerida(java.lang.String autorizacionRequerida);

   java.lang.String getAgruparDetalles();

   void setAgruparDetalles(java.lang.String agruparDetalles);

   java.lang.String getUsarDetalleDescripcion();

   void setUsarDetalleDescripcion(java.lang.String usarDetalleDescripcion);

   java.lang.String getValidoAlGuardar();

   void setValidoAlGuardar(java.lang.String validoAlGuardar);

   java.lang.String getValidoAlActualizar();

   void setValidoAlActualizar(java.lang.String validoAlActualizar);

   java.lang.Long getSubtipoAsientoId();

   void setSubtipoAsientoId(java.lang.Long subtipoAsientoId);


}
