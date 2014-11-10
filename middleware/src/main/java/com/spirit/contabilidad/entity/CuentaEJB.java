package com.spirit.contabilidad.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CUENTA")
@Entity
public class CuentaEJB implements Serializable, CuentaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long plancuentaId;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.String nombreCorto;
   private java.lang.Long tipocuentaId;
   private java.lang.Long padreId;
   private java.lang.Long relacionada;
   private java.lang.String imputable;
   private java.lang.Integer nivel;
   private java.lang.Long tiporesultadoId;
   private java.lang.String efectivo;
   private java.lang.String activofijo;
   private java.lang.String departamento;
   private java.lang.String linea;
   private java.lang.String empleado;
   private java.lang.String centrogasto;
   private java.lang.String cliente;
   private java.lang.String gasto;
   private java.lang.String estado;
   private java.lang.String cuentaBanco;

   public CuentaEJB() {
   }

   public CuentaEJB(com.spirit.contabilidad.entity.CuentaIf value) {
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

   public java.lang.Long create(com.spirit.contabilidad.entity.CuentaIf value) {
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
      return value.getPrimaryKey();
   }

   @javax.persistence.Transient public java.lang.Long getPrimaryKey() {
        return getId();
    }

   @javax.persistence.Transient public void setPrimaryKey(java.lang.Long primaryKey) {
       setId(primaryKey);
    }

   @Column(name = "ID")
@TableGenerator(name="SEQ_GEN",
			allocationSize=HibernateSequenceAllocationSize.allocationSize
)
   @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="SEQ_GEN")
   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   @Column(name = "PLANCUENTA_ID")
   public java.lang.Long getPlancuentaId() {
      return plancuentaId;
   }

   public void setPlancuentaId(java.lang.Long plancuentaId) {
      this.plancuentaId = plancuentaId;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "NOMBRE")
   public java.lang.String getNombre() {
      return nombre;
   }

   public void setNombre(java.lang.String nombre) {
      this.nombre = nombre;
   }

   @Column(name = "NOMBRE_CORTO")
   public java.lang.String getNombreCorto() {
      return nombreCorto;
   }

   public void setNombreCorto(java.lang.String nombreCorto) {
      this.nombreCorto = nombreCorto;
   }

   @Column(name = "TIPOCUENTA_ID")
   public java.lang.Long getTipocuentaId() {
      return tipocuentaId;
   }

   public void setTipocuentaId(java.lang.Long tipocuentaId) {
      this.tipocuentaId = tipocuentaId;
   }

   @Column(name = "PADRE_ID")
   public java.lang.Long getPadreId() {
      return padreId;
   }

   public void setPadreId(java.lang.Long padreId) {
      this.padreId = padreId;
   }

   @Column(name = "RELACIONADA")
   public java.lang.Long getRelacionada() {
      return relacionada;
   }

   public void setRelacionada(java.lang.Long relacionada) {
      this.relacionada = relacionada;
   }

   @Column(name = "IMPUTABLE")
   public java.lang.String getImputable() {
      return imputable;
   }

   public void setImputable(java.lang.String imputable) {
      this.imputable = imputable;
   }

   @Column(name = "NIVEL")
   public java.lang.Integer getNivel() {
      return nivel;
   }

   public void setNivel(java.lang.Integer nivel) {
      this.nivel = nivel;
   }

   @Column(name = "TIPORESULTADO_ID")
   public java.lang.Long getTiporesultadoId() {
      return tiporesultadoId;
   }

   public void setTiporesultadoId(java.lang.Long tiporesultadoId) {
      this.tiporesultadoId = tiporesultadoId;
   }

   @Column(name = "EFECTIVO")
   public java.lang.String getEfectivo() {
      return efectivo;
   }

   public void setEfectivo(java.lang.String efectivo) {
      this.efectivo = efectivo;
   }

   @Column(name = "ACTIVOFIJO")
   public java.lang.String getActivofijo() {
      return activofijo;
   }

   public void setActivofijo(java.lang.String activofijo) {
      this.activofijo = activofijo;
   }

   @Column(name = "DEPARTAMENTO")
   public java.lang.String getDepartamento() {
      return departamento;
   }

   public void setDepartamento(java.lang.String departamento) {
      this.departamento = departamento;
   }

   @Column(name = "LINEA")
   public java.lang.String getLinea() {
      return linea;
   }

   public void setLinea(java.lang.String linea) {
      this.linea = linea;
   }

   @Column(name = "EMPLEADO")
   public java.lang.String getEmpleado() {
      return empleado;
   }

   public void setEmpleado(java.lang.String empleado) {
      this.empleado = empleado;
   }

   @Column(name = "CENTROGASTO")
   public java.lang.String getCentrogasto() {
      return centrogasto;
   }

   public void setCentrogasto(java.lang.String centrogasto) {
      this.centrogasto = centrogasto;
   }

   @Column(name = "CLIENTE")
   public java.lang.String getCliente() {
      return cliente;
   }

   public void setCliente(java.lang.String cliente) {
      this.cliente = cliente;
   }

   @Column(name = "GASTO")
   public java.lang.String getGasto() {
      return gasto;
   }

   public void setGasto(java.lang.String gasto) {
      this.gasto = gasto;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "CUENTA_BANCO")
   public java.lang.String getCuentaBanco() {
      return cuentaBanco;
   }

   public void setCuentaBanco(java.lang.String cuentaBanco) {
      this.cuentaBanco = cuentaBanco;
   }
	public String toString() {
		return ToStringer.toString((CuentaIf)this);
	}
}
