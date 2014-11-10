package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanCuentaIf extends SpiritIf{


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

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.Long getMonedaId();

   void setMonedaId(java.lang.Long monedaId);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getMascara();

   void setMascara(java.lang.String mascara);

   java.lang.String getPredeterminado();

   void setPredeterminado(java.lang.String predeterminado);


}
