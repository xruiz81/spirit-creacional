package com.spirit.nomina.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface RolPagoDetalleUtilidadIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getRolpagoId();

   void setRolpagoId(java.lang.Long rolpagoId);

   java.lang.Long getContratoUtilidadId();

   void setContratoUtilidadId(java.lang.Long contratoUtilidadId);

   java.lang.Long getContratoId();

   void setContratoId(java.lang.Long contratoId);

   java.lang.String getCargo();

   void setCargo(java.lang.String cargo);

   java.sql.Date getFechaIngreso();

   void setFechaIngreso(java.sql.Date fechaIngreso);

   java.sql.Date getFechaSalida();

   void setFechaSalida(java.sql.Date fechaSalida);

   java.lang.String getGenero();

   void setGenero(java.lang.String genero);

   java.lang.Integer getDiasLaborados();

   void setDiasLaborados(java.lang.Integer diasLaborados);

   java.math.BigDecimal getUtilidadContrato();

   void setUtilidadContrato(java.math.BigDecimal utilidadContrato);

   java.lang.Integer getNumeroCargas();

   void setNumeroCargas(java.lang.Integer numeroCargas);

   java.lang.Integer getDiasCargas();

   void setDiasCargas(java.lang.Integer diasCargas);

   java.math.BigDecimal getUtilidadCargas();

   void setUtilidadCargas(java.math.BigDecimal utilidadCargas);

   java.math.BigDecimal getTotal();

   void setTotal(java.math.BigDecimal total);


}
