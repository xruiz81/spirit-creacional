package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RubroIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.String getFrecuencia();

   void setFrecuencia(java.lang.String frecuencia);

   java.lang.String getTipoRubro();

   void setTipoRubro(java.lang.String tipoRubro);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.Long getTiporolId();

   void setTiporolId(java.lang.Long tiporolId);

   java.sql.Date getFecha();

   void setFecha(java.sql.Date fecha);

   java.lang.String getPolitica();

   void setPolitica(java.lang.String politica);

   java.lang.String getModoOperacion();

   void setModoOperacion(java.lang.String modoOperacion);

   java.lang.String getPagoIndividual();

   void setPagoIndividual(java.lang.String pagoIndividual);

   java.lang.String getNemonico();

   void setNemonico(java.lang.String nemonico);


}
