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

@Table(name = "NUMERADORES")
@Entity
public class NumeradoresEJB implements Serializable, NumeradoresIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.math.BigDecimal ultimoValor;
   private java.lang.String nombreTabla;
   private java.lang.Long empresaId;

   public NumeradoresEJB() {
   }

   public NumeradoresEJB(com.spirit.general.entity.NumeradoresIf value) {
      setId(value.getId());
      setUltimoValor(value.getUltimoValor());
      setNombreTabla(value.getNombreTabla());
      setEmpresaId(value.getEmpresaId());
   }

   public java.lang.Long create(com.spirit.general.entity.NumeradoresIf value) {
      setId(value.getId());
      setUltimoValor(value.getUltimoValor());
      setNombreTabla(value.getNombreTabla());
      setEmpresaId(value.getEmpresaId());
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

   @Column(name = "ULTIMO_VALOR")
   public java.math.BigDecimal getUltimoValor() {
      return ultimoValor;
   }

   public void setUltimoValor(java.math.BigDecimal ultimoValor) {
      this.ultimoValor = ultimoValor;
   }

   @Column(name = "NOMBRE_TABLA")
   public java.lang.String getNombreTabla() {
      return nombreTabla;
   }

   public void setNombreTabla(java.lang.String nombreTabla) {
      this.nombreTabla = nombreTabla;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }
	public String toString() {
		return ToStringer.toString((NumeradoresIf)this);
	}
}
