package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface GrupoObjetivoIf extends SpiritIf{


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

   java.lang.String getNivelSocioEconomico();

   void setNivelSocioEconomico(java.lang.String nivelSocioEconomico);

   java.math.BigDecimal getCiudad1();

   void setCiudad1(java.math.BigDecimal ciudad1);

   java.math.BigDecimal getCiudad2();

   void setCiudad2(java.math.BigDecimal ciudad2);

   java.math.BigDecimal getCiudad3();

   void setCiudad3(java.math.BigDecimal ciudad3);


}
