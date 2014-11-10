package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface CajaSesionIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.Long getUsuarioId();

   void setUsuarioId(java.lang.Long usuarioId);

   java.lang.Long getCajaId();

   void setCajaId(java.lang.Long cajaId);

   java.sql.Timestamp getFechaini();

   void setFechaini(java.sql.Timestamp fechaini);

   java.sql.Timestamp getFechafin();

   void setFechafin(java.sql.Timestamp fechafin);

   java.math.BigDecimal getValorInicial();

   void setValorInicial(java.math.BigDecimal valorInicial);

   java.math.BigDecimal getValorFinal();

   void setValorFinal(java.math.BigDecimal valorFinal);

   java.math.BigDecimal getDescuadre();

   void setDescuadre(java.math.BigDecimal descuadre);

   java.math.BigDecimal getValorMultas();

   void setValorMultas(java.math.BigDecimal valorMultas);

   java.math.BigDecimal getValorDocumentos();

   void setValorDocumentos(java.math.BigDecimal valorDocumentos);

   java.lang.String getTurno();

   void setTurno(java.lang.String turno);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getRevisado();

   void setRevisado(java.lang.String revisado);


}
