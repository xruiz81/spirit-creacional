package com.spirit.general.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TIPO_EMPLEADO")
@Entity
public class TipoEmpleadoEJB implements Serializable, TipoEmpleadoIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.String codigo;
   private java.lang.String nombre;
   private java.lang.Long empresaId;
   private java.lang.String vendedor;
   private java.lang.String supervisor;
   private java.lang.String administrador;

   public TipoEmpleadoEJB() {
   }

   public TipoEmpleadoEJB(com.spirit.general.entity.TipoEmpleadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setVendedor(value.getVendedor());
      setSupervisor(value.getSupervisor());
      setAdministrador(value.getAdministrador());
   }

   public java.lang.Long create(com.spirit.general.entity.TipoEmpleadoIf value) {
      setId(value.getId());
      setCodigo(value.getCodigo());
      setNombre(value.getNombre());
      setEmpresaId(value.getEmpresaId());
      setVendedor(value.getVendedor());
      setSupervisor(value.getSupervisor());
      setAdministrador(value.getAdministrador());
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

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "VENDEDOR")
   public java.lang.String getVendedor() {
      return vendedor;
   }

   public void setVendedor(java.lang.String vendedor) {
      this.vendedor = vendedor;
   }

   @Column(name = "SUPERVISOR")
   public java.lang.String getSupervisor() {
      return supervisor;
   }

   public void setSupervisor(java.lang.String supervisor) {
      this.supervisor = supervisor;
   }

   @Column(name = "ADMINISTRADOR")
   public java.lang.String getAdministrador() {
      return administrador;
   }

   public void setAdministrador(java.lang.String administrador) {
      this.administrador = administrador;
   }
	public String toString() {
		return ToStringer.toString((TipoEmpleadoIf)this);
	}
}
