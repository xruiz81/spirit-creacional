package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CuentaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPlancuentaId();

   void setPlancuentaId(java.lang.Long plancuentaId);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getNombreCorto();

   void setNombreCorto(java.lang.String nombreCorto);

   java.lang.Long getTipocuentaId();

   void setTipocuentaId(java.lang.Long tipocuentaId);

   java.lang.Long getPadreId();

   void setPadreId(java.lang.Long padreId);

   java.lang.Long getRelacionada();

   void setRelacionada(java.lang.Long relacionada);

   java.lang.String getImputable();

   void setImputable(java.lang.String imputable);

   java.lang.Integer getNivel();

   void setNivel(java.lang.Integer nivel);

   java.lang.Long getTiporesultadoId();

   void setTiporesultadoId(java.lang.Long tiporesultadoId);

   java.lang.String getEfectivo();

   void setEfectivo(java.lang.String efectivo);

   java.lang.String getActivofijo();

   void setActivofijo(java.lang.String activofijo);

   java.lang.String getDepartamento();

   void setDepartamento(java.lang.String departamento);

   java.lang.String getLinea();

   void setLinea(java.lang.String linea);

   java.lang.String getEmpleado();

   void setEmpleado(java.lang.String empleado);

   java.lang.String getCentrogasto();

   void setCentrogasto(java.lang.String centrogasto);

   java.lang.String getCliente();

   void setCliente(java.lang.String cliente);

   java.lang.String getGasto();

   void setGasto(java.lang.String gasto);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getCuentaBanco();

   void setCuentaBanco(java.lang.String cuentaBanco);


}
