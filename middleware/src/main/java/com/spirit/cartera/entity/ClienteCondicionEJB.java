package com.spirit.cartera.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE_CONDICION")
@Entity
public class ClienteCondicionEJB implements Serializable, ClienteCondicionIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.Long subtipoordenId;
   private java.lang.Long formapagoId;
   private java.lang.String observaciones;
   private java.sql.Date fechaini;
   private java.sql.Date fechafin;
   private java.lang.String codigo;

   public ClienteCondicionEJB() {
   }

   public ClienteCondicionEJB(com.spirit.cartera.entity.ClienteCondicionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSubtipoordenId(value.getSubtipoordenId());
      setFormapagoId(value.getFormapagoId());
      setObservaciones(value.getObservaciones());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setCodigo(value.getCodigo());
   }

   public java.lang.Long create(com.spirit.cartera.entity.ClienteCondicionIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setSubtipoordenId(value.getSubtipoordenId());
      setFormapagoId(value.getFormapagoId());
      setObservaciones(value.getObservaciones());
      setFechaini(value.getFechaini());
      setFechafin(value.getFechafin());
      setCodigo(value.getCodigo());
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

   @Column(name = "CLIENTE_ID")
   public java.lang.Long getClienteId() {
      return clienteId;
   }

   public void setClienteId(java.lang.Long clienteId) {
      this.clienteId = clienteId;
   }

   @Column(name = "SUBTIPOORDEN_ID")
   public java.lang.Long getSubtipoordenId() {
      return subtipoordenId;
   }

   public void setSubtipoordenId(java.lang.Long subtipoordenId) {
      this.subtipoordenId = subtipoordenId;
   }

   @Column(name = "FORMAPAGO_ID")
   public java.lang.Long getFormapagoId() {
      return formapagoId;
   }

   public void setFormapagoId(java.lang.Long formapagoId) {
      this.formapagoId = formapagoId;
   }

   @Column(name = "OBSERVACIONES")
   public java.lang.String getObservaciones() {
      return observaciones;
   }

   public void setObservaciones(java.lang.String observaciones) {
      this.observaciones = observaciones;
   }

   @Column(name = "FECHAINI")
   public java.sql.Date getFechaini() {
      return fechaini;
   }

   public void setFechaini(java.sql.Date fechaini) {
      this.fechaini = fechaini;
   }

   @Column(name = "FECHAFIN")
   public java.sql.Date getFechafin() {
      return fechafin;
   }

   public void setFechafin(java.sql.Date fechafin) {
      this.fechafin = fechafin;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
	public String toString() {
		return ToStringer.toString((ClienteCondicionIf)this);
	}
}
