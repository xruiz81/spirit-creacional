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

@Table(name = "PRENSA_INSERTOS")
@Entity
public class PrensaInsertosEJB implements Serializable, PrensaInsertosIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteId;
   private java.lang.String codigo;
   private java.math.BigDecimal maxPaginas;
   private java.math.BigDecimal tarifa;

   public PrensaInsertosEJB() {
   }

   public PrensaInsertosEJB(com.spirit.medios.entity.PrensaInsertosIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setMaxPaginas(value.getMaxPaginas());
      setTarifa(value.getTarifa());
   }

   public java.lang.Long create(com.spirit.medios.entity.PrensaInsertosIf value) {
      setId(value.getId());
      setClienteId(value.getClienteId());
      setCodigo(value.getCodigo());
      setMaxPaginas(value.getMaxPaginas());
      setTarifa(value.getTarifa());
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

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "MAX_PAGINAS")
   public java.math.BigDecimal getMaxPaginas() {
      return maxPaginas;
   }

   public void setMaxPaginas(java.math.BigDecimal maxPaginas) {
      this.maxPaginas = maxPaginas;
   }

   @Column(name = "TARIFA")
   public java.math.BigDecimal getTarifa() {
      return tarifa;
   }

   public void setTarifa(java.math.BigDecimal tarifa) {
      this.tarifa = tarifa;
   }
	public String toString() {
		return ToStringer.toString((PrensaInsertosIf)this);
	}
}
