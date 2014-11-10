package com.spirit.medios.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "TIMETRACKER2_DETALLE")
@Entity
public class Timetracker2DetalleEJB implements Serializable, Timetracker2DetalleIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long timetracker2EmpleadoId;
   private java.sql.Timestamp fecha;
   private java.lang.Float tiempo;
   private java.lang.String estado;
   private java.lang.Long clienteOficinaId;
   private java.lang.Integer tiempoDesignado;

   public Timetracker2DetalleEJB() {
   }

   public Timetracker2DetalleEJB(com.spirit.medios.entity.Timetracker2DetalleIf value) {
      setId(value.getId());
      setTimetracker2EmpleadoId(value.getTimetracker2EmpleadoId());
      setFecha(value.getFecha());
      setTiempo(value.getTiempo());
      setEstado(value.getEstado());
      setClienteOficinaId(value.getClienteOficinaId());
      setTiempoDesignado(value.getTiempoDesignado());
   }

   public java.lang.Long create(com.spirit.medios.entity.Timetracker2DetalleIf value) {
      setId(value.getId());
      setTimetracker2EmpleadoId(value.getTimetracker2EmpleadoId());
      setFecha(value.getFecha());
      setTiempo(value.getTiempo());
      setEstado(value.getEstado());
      setClienteOficinaId(value.getClienteOficinaId());
      setTiempoDesignado(value.getTiempoDesignado());
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

   @Column(name = "TIMETRACKER2_EMPLEADO_ID")
   public java.lang.Long getTimetracker2EmpleadoId() {
      return timetracker2EmpleadoId;
   }

   public void setTimetracker2EmpleadoId(java.lang.Long timetracker2EmpleadoId) {
      this.timetracker2EmpleadoId = timetracker2EmpleadoId;
   }

   @Column(name = "FECHA")
   public java.sql.Timestamp getFecha() {
      return fecha;
   }

   public void setFecha(java.sql.Timestamp fecha) {
      this.fecha = fecha;
   }

   @Column(name = "TIEMPO")
   public java.lang.Float getTiempo() {
      return tiempo;
   }

   public void setTiempo(java.lang.Float tiempo) {
      this.tiempo = tiempo;
   }

   @Column(name = "ESTADO")
   public java.lang.String getEstado() {
      return estado;
   }

   public void setEstado(java.lang.String estado) {
      this.estado = estado;
   }

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "TIEMPO_DESIGNADO")
   public java.lang.Integer getTiempoDesignado() {
      return tiempoDesignado;
   }

   public void setTiempoDesignado(java.lang.Integer tiempoDesignado) {
      this.tiempoDesignado = tiempoDesignado;
   }
	public String toString() {
		return ToStringer.toString((Timetracker2DetalleIf)this);
	}
}
