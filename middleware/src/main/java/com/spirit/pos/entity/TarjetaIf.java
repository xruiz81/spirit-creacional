package com.spirit.pos.entity;
import com.spirit.server.SpiritIf;

/**
 *
 * @author  www.versality.com.ec
 *
 */
public interface TarjetaIf extends SpiritIf{


    java.lang.Long getPrimaryKey();

    void setPrimaryKey(java.lang.Long pk);


   java.lang.Long getId();

   void setId(java.lang.Long id);

   java.lang.String getCodigo();

   void setCodigo(java.lang.String codigo);

   java.lang.Long getTipoId();

   void setTipoId(java.lang.Long tipoId);

   java.lang.Long getClienteoficinaId();

   void setClienteoficinaId(java.lang.Long clienteoficinaId);

   java.lang.Long getReferidoporId();

   void setReferidoporId(java.lang.Long referidoporId);

   java.lang.Long getGarante();

   void setGarante(java.lang.Long garante);

   java.lang.Long getFechaValidez();

   void setFechaValidez(java.lang.Long fechaValidez);

   java.lang.Long getFechaEmision();

   void setFechaEmision(java.lang.Long fechaEmision);

   java.math.BigDecimal getPuntosAcumulados();

   void setPuntosAcumulados(java.math.BigDecimal puntosAcumulados);

   java.math.BigDecimal getPuntosUtilizados();

   void setPuntosUtilizados(java.math.BigDecimal puntosUtilizados);

   java.math.BigDecimal getPuntosComprometidos();

   void setPuntosComprometidos(java.math.BigDecimal puntosComprometidos);

   java.math.BigDecimal getValor();

   void setValor(java.math.BigDecimal valor);

   java.math.BigDecimal getSaldo();

   void setSaldo(java.math.BigDecimal saldo);

   java.math.BigDecimal getCupo();

   void setCupo(java.math.BigDecimal cupo);

   java.lang.String getEstado();

   void setEstado(java.lang.String estado);

   java.lang.String getValidador();

   void setValidador(java.lang.String validador);

   java.math.BigDecimal getPuntosAcumuladosStatus();

   void setPuntosAcumuladosStatus(java.math.BigDecimal puntosAcumuladosStatus);

   java.math.BigDecimal getDineroAcumulado();

   void setDineroAcumulado(java.math.BigDecimal dineroAcumulado);

   java.math.BigDecimal getDineroUtilizado();

   void setDineroUtilizado(java.math.BigDecimal dineroUtilizado);

   java.math.BigDecimal getDineroComprometido();

   void setDineroComprometido(java.math.BigDecimal dineroComprometido);

   java.math.BigDecimal getDineroAcumuladoStatus();

   void setDineroAcumuladoStatus(java.math.BigDecimal dineroAcumuladoStatus);

   java.lang.Long getEmpresaId();

   void setEmpresaId(java.lang.Long empresaId);

   java.lang.Long getFechaUltimoCambioStatus();

   void setFechaUltimoCambioStatus(java.lang.Long fechaUltimoCambioStatus);

   java.lang.Long getProductoId();

   void setProductoId(java.lang.Long productoId);


}
