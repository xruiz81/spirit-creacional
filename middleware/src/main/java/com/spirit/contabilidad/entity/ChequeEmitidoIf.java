package com.spirit.contabilidad.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface ChequeEmitidoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.sql.Date getFechaEmision();

   void setFechaEmision(java.sql.Date fechaEmision);

   java.lang.Long getCuentaBancariaId();

   void setCuentaBancariaId(java.lang.Long cuentaBancariaId);

   java.lang.String getNumero();

   void setNumero(java.lang.String numero);

   java.lang.String getDetalle();

   void setDetalle(java.lang.String detalle);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);

   java.lang.Long getTransaccionId();

   void setTransaccionId(java.lang.Long transaccionId);

   java.lang.String getBeneficiario();

   void setBeneficiario(java.lang.String beneficiario);

   java.sql.Date getFechaCobro();

   void setFechaCobro(java.sql.Date fechaCobro);

   java.lang.String getOrigen();

   void setOrigen(java.lang.String origen);


}
