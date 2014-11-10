package com.spirit.contabilidad.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class CuentaData implements CuentaIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long plancuentaId;

   public java.lang.Long getPlancuentaId() {
      return plancuentaId;
   }

   public void setPlancuentaId(java.lang.Long plancuentaId) {
      this.plancuentaId = plancuentaId;
   }

   private java.lang.String codigo;

   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   private java.lang.String nombre;

   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   private java.lang.String nombreCorto;

   public java.lang.String getNombreCorto() {
      return nombreCorto;
   }

   public void setNombreCorto(java.lang.String nombreCorto) {
      this.nombreCorto = nombreCorto;
   }

   private java.lang.Long tipocuentaId;

   public java.lang.Long getTipocuentaId() {
      return tipocuentaId;
   }

   public void setTipocuentaId(java.lang.Long tipocuentaId) {
      this.tipocuentaId = tipocuentaId;
   }

   private java.lang.Long padreId;

   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   private java.lang.Long relacionada;

   public java.lang.Long getRelacionada() {
      return relacionada;
   }

   public void setRelacionada(java.lang.Long relacionada) {
      this.relacionada = relacionada;
   }

   private java.lang.String imputable;

   public java.lang.String getImputable() {
      return imputable;
   }

   public void setImputable(java.lang.String imputable) {
      this.imputable = imputable;
   }

   private java.lang.Integer nivel;

   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   private java.lang.Long tiporesultadoId;

   public java.lang.Long getTiporesultadoId() {
      return tiporesultadoId;
   }

   public void setTiporesultadoId(java.lang.Long tiporesultadoId) {
      this.tiporesultadoId = tiporesultadoId;
   }

   private java.lang.String efectivo;

   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   private java.lang.String activofijo;

   public java.lang.String getActivofijo() {
      return activofijo;
   }

   public void setActivofijo(java.lang.String activofijo) {
      this.activofijo = activofijo;
   }

   private java.lang.String departamento;

   public java.lang.String getDepartamento() {
      return departamento;
   }

   public void setDepartamento(java.lang.String departamento) {
      this.departamento = departamento;
   }

   private java.lang.String linea;

   public java.lang.String getLinea() {
      return linea;
   }

   public void setLinea(java.lang.String linea) {
      this.linea = linea;
   }

   private java.lang.String empleado;

   public java.lang.String getEmpleado() {
      return empleado;
   }

   public void setEmpleado(java.lang.String empleado) {
      this.empleado = empleado;
   }

   private java.lang.String centrogasto;

   public java.lang.String getCentrogasto() {
      return centrogasto;
   }

   public void setCentrogasto(java.lang.String centrogasto) {
      this.centrogasto = centrogasto;
   }

   private java.lang.String cliente;

   public java.lang.String getCliente() {
      return cliente;
   }

   public void setCliente(java.lang.String cliente) {
      this.cliente = cliente;
   }

   private java.lang.String gasto;

   public java.lang.String getGasto() {
      return gasto;
   }

   public void setGasto(java.lang.String gasto) {
      this.gasto = gasto;
   }

   private java.lang.String estado;

   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   private java.lang.String cuentaBanco;

   public java.lang.String getCuentaBanco() {
      return cuentaBanco;
   }

   public void setCuentaBanco(java.lang.String cuentaBanco) {
      this.cuentaBanco = cuentaBanco;
   }
   public CuentaData() {
   }

   public CuentaData(com.spirit.contabilidad.entity.CuentaIf value) {
      setId(value.getId());
      setPlancuentaId(value.getPlancuentaId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setNombreCorto(value.getNombreCorto());
      setTipocuentaId(value.getTipocuentaId());
      setPadreId(value.getPadreId());
      setRelacionada(value.getRelacionada());
      setImputable(value.getImputable());
      setNivel(value.getNivel());
      setTiporesultadoId(value.getTiporesultadoId());
      setEfectivo(value.getEfectivo());
      setActivofijo(value.getActivofijo());
      setDepartamento(value.getDepartamento());
      setLinea(value.getLinea());
      setEmpleado(value.getEmpleado());
      setCentrogasto(value.getCentrogasto());
      setCliente(value.getCliente());
      setGasto(value.getGasto());
      setEstado(value.getEstado());
      setCuentaBanco(value.getCuentaBanco());
   }



    public java.lang.Long getPrimaryKey() {
        return getId();
    }

    public void setPrimaryKey(java.lang.Long pk) {
       setId(pk);
    }


   public String getPrimaryKeyParameters() {
      String parameters = "";
      parameters += "&id=" + getId();
      return parameters;
   }



	public String toString() {
		return ToStringer.toString((CuentaIf)this);
	}
}
