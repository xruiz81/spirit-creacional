package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface VentasConsolidadoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Timestamp getFechaCierre();

   void setFechaCierre(java.sql.Timestamp fechaCierre);

   java.lang.String getCajeroNombre();

   void setCajeroNombre(java.lang.String cajeroNombre);

   java.lang.String getCajeroCedula();

   void setCajeroCedula(java.lang.String cajeroCedula);

   java.lang.String getCajaCodigo();

   void setCajaCodigo(java.lang.String cajaCodigo);

   java.lang.String getCajaNombre();

   void setCajaNombre(java.lang.String cajaNombre);

   java.math.BigDecimal getValorEfectivo();

   void setValorEfectivo(java.math.BigDecimal valorEfectivo);

   java.math.BigDecimal getValorTarjeta();

   void setValorTarjeta(java.math.BigDecimal valorTarjeta);

   java.math.BigDecimal getValorGiftcards();

   void setValorGiftcards(java.math.BigDecimal valorGiftcards);

   java.math.BigDecimal getValorDevoluciones();

   void setValorDevoluciones(java.math.BigDecimal valorDevoluciones);

   java.math.BigDecimal getValorCajaInicial();

   void setValorCajaInicial(java.math.BigDecimal valorCajaInicial);

   java.math.BigDecimal getValorCheque();

   void setValorCheque(java.math.BigDecimal valorCheque);

   java.math.BigDecimal getValorDonacion();

   void setValorDonacion(java.math.BigDecimal valorDonacion);

   java.math.BigDecimal getValorCredito();

   void setValorCredito(java.math.BigDecimal valorCredito);

   java.math.BigDecimal getValorCajaIngreso();

   void setValorCajaIngreso(java.math.BigDecimal valorCajaIngreso);

   java.math.BigDecimal getValorCajaEgreso();

   void setValorCajaEgreso(java.math.BigDecimal valorCajaEgreso);

   java.math.BigDecimal getValorDescuadre();

   void setValorDescuadre(java.math.BigDecimal valorDescuadre);

   java.math.BigDecimal getValorMultas();

   void setValorMultas(java.math.BigDecimal valorMultas);

   java.math.BigDecimal getValorDocumentos();

   void setValorDocumentos(java.math.BigDecimal valorDocumentos);

   java.sql.Timestamp getFechaApertura();

   void setFechaApertura(java.sql.Timestamp fechaApertura);


}
