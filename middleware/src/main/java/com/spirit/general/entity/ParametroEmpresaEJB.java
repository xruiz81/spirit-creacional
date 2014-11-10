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

@Table(name = "PARAMETRO_EMPRESA")
@Entity
public class ParametroEmpresaEJB implements Serializable, ParametroEmpresaIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long tipoparametroId;
   private java.lang.Long empresaId;
   private java.lang.String valor;
   private java.lang.String codigo;
   private java.lang.String descripcion;

   public ParametroEmpresaEJB() {
   }

   public ParametroEmpresaEJB(com.spirit.general.entity.ParametroEmpresaIf value) {
      setId(value.getId());
      setTipoparametroId(value.getTipoparametroId());
      setEmpresaId(value.getEmpresaId());
      setValor(value.getValor());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
   }

   public java.lang.Long create(com.spirit.general.entity.ParametroEmpresaIf value) {
      setId(value.getId());
      setTipoparametroId(value.getTipoparametroId());
      setEmpresaId(value.getEmpresaId());
      setValor(value.getValor());
      setCodigo(value.getCodigo());
      setDescripcion(value.getDescripcion());
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

   @Column(name = "TIPOPARAMETRO_ID")
   public java.lang.Long getTipoparametroId() {
      return tipoparametroId;
   }

   public void setTipoparametroId(java.lang.Long tipoparametroId) {
      this.tipoparametroId = tipoparametroId;
   }

   @Column(name = "EMPRESA_ID")
   public java.lang.Long getEmpresaId() {
      return empresaId;
   }

   public void setEmpresaId(java.lang.Long empresaId) {
      this.empresaId = empresaId;
   }

   @Column(name = "VALOR")
   public java.lang.String getValor() {
      return valor;
   }

   public void setValor(java.lang.String valor) {
      this.valor = valor;
   }

   @Column(name = "CODIGO")
   public java.lang.String getCodigo() {
      return codigo;
   }

   public void setCodigo(java.lang.String codigo) {
      this.codigo = codigo;
   }

   @Column(name = "DESCRIPCION")
   public java.lang.String getDescripcion() {
      return descripcion;
   }

   public void setDescripcion(java.lang.String descripcion) {
      this.descripcion = descripcion;
   }
	public String toString() {
		return ToStringer.toString((ParametroEmpresaIf)this);
	}
}
