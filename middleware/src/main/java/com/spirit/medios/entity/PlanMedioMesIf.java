package com.spirit.medios.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface PlanMedioMesIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getPlanMedioId();

   void setPlanMedioId(java.lang.Long planMedioId);

   java.sql.Timestamp getFechaInicio();

   void setFechaInicio(java.sql.Timestamp fechaInicio);

   java.sql.Timestamp getFechaFin();

   void setFechaFin(java.sql.Timestamp fechaFin);

   java.math.BigDecimal getValorSubtotal();

   void setValorSubtotal(java.math.BigDecimal valorSubtotal);

   java.math.BigDecimal getValorDescuento();

   void setValorDescuento(java.math.BigDecimal valorDescuento);

   java.lang.String getTipo();

   void setTipo(java.lang.String tipo);

   java.math.BigDecimal getValorIva();

   void setValorIva(java.math.BigDecimal valorIva);

   java.math.BigDecimal getValorTotal();

   void setValorTotal(java.math.BigDecimal valorTotal);

   java.math.BigDecimal getValorDescuentoVenta();

   void setValorDescuentoVenta(java.math.BigDecimal valorDescuentoVenta);

   java.math.BigDecimal getValorComisionAgencia();

   void setValorComisionAgencia(java.math.BigDecimal valorComisionAgencia);


}
