package com.spirit.crm.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "CLIENTE_INDICADOR")
@Entity
public class ClienteIndicadorEJB implements Serializable, ClienteIndicadorIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long clienteOficinaId;
   private java.lang.Long tipoindicadorId;
   private java.math.BigDecimal valor;
   private java.lang.String codigo;

   public ClienteIndicadorEJB() {
   }

   public ClienteIndicadorEJB(com.spirit.crm.entity.ClienteIndicadorIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTipoindicadorId(value.getTipoindicadorId());
      setValor(value.getValor());
      setCodigo(value.getCodigo());
   }

   public java.lang.Long create(com.spirit.crm.entity.ClienteIndicadorIf value) {
      setId(value.getId());
      setClienteOficinaId(value.getClienteOficinaId());
      setTipoindicadorId(value.getTipoindicadorId());
      setValor(value.getValor());
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

   @Column(name = "CLIENTE_OFICINA_ID")
   public java.lang.Long getClienteOficinaId() {
      return clienteOficinaId;
   }

   public void setClienteOficinaId(java.lang.Long clienteOficinaId) {
      this.clienteOficinaId = clienteOficinaId;
   }

   @Column(name = "TIPOINDICADOR_ID")
   public java.lang.Long getTipoindicadorId() {
      return tipoindicadorId;
   }

   public void setTipoindicadorId(java.lang.Long tipoindicadorId) {
      this.tipoindicadorId = tipoindicadorId;
   }

   @Column(name = "VALOR")
   public java.math.BigDecimal getValor() {
      return valor;
   }

   public void setValor(java.math.BigDecimal valor) {
      this.valor = valor;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }
	public String toString() {
		return ToStringer.toString((ClienteIndicadorIf)this);
	}
}
