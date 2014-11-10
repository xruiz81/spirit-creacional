package com.spirit.general.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface DocumentoIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.String getNombre();

   void setNombre(java.lang.String nombre);

   java.lang.String getAbreviado();

   void setAbreviado(java.lang.String abreviado);

   java.lang.Long getTipoDocumentoId();

   void setTipoDocumentoId(java.lang.Long tipoDocumentoId);

   java.lang.String getPideAutorizacion();

   void setPideAutorizacion(java.lang.String pideAutorizacion);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getDiferido();

   void setDiferido(java.lang.String diferido);

   java.lang.String getBonificacion();

   void setBonificacion(java.lang.String bonificacion);

   java.lang.String getPrecioEspecial();

   void setPrecioEspecial(java.lang.String precioEspecial);

   java.lang.String getDescuentoEspecial();

   void setDescuentoEspecial(java.lang.String descuentoEspecial);

   java.lang.String getMulta();

   void setMulta(java.lang.String multa);

   java.lang.String getInteres();

   void setInteres(java.lang.String interes);

   java.lang.String getProtesto();

   void setProtesto(java.lang.String protesto);

   java.lang.String getCheque();

   void setCheque(java.lang.String cheque);

   java.lang.String getRetencionRenta();

   void setRetencionRenta(java.lang.String retencionRenta);

   java.lang.String getRetencionIva();

   void setRetencionIva(java.lang.String retencionIva);

   java.lang.String getEfectivo();

   void setEfectivo(java.lang.String efectivo);

   java.lang.String getDebitoBancario();

   void setDebitoBancario(java.lang.String debitoBancario);

   java.lang.String getTarjetaCredito();

   void setTarjetaCredito(java.lang.String tarjetaCredito);

   java.lang.String getTransaccionElectronica();

   void setTransaccionElectronica(java.lang.String transaccionElectronica);

   java.lang.String getTransferenciaBancaria();

   void setTransferenciaBancaria(java.lang.String transferenciaBancaria);

   java.lang.String getNivelacion();

   void setNivelacion(java.lang.String nivelacion);

   java.lang.String getAnticipo();

   void setAnticipo(java.lang.String anticipo);


}
