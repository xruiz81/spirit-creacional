package com.spirit.rrhh.entity;

import java.io.Serializable;
import javax.persistence.*;
import com.spirit.comun.util.ToStringer;
import com.spirit.util.HibernateSequenceAllocationSize;



/**
 *
 * @author  www.versality.com.ec
 *
 */

@Table(name = "EMPLEADO_PERSONAL")
@Entity
public class EmpleadoPersonalEJB implements Serializable, EmpleadoPersonalIf {


   @PersistenceContext
   private EntityManager manager;

   private java.lang.Long id;
   private java.lang.Long empleadoId;
   private java.lang.String titulo;
   private java.lang.String cedulaIdentidad;
   private java.lang.String afiliacionIess;
   private java.lang.String libretaMilitar;
   private java.lang.String sexo;
   private java.lang.String tipoSangre;
   private java.lang.String estadoCivil;
   private java.sql.Timestamp fechaNacimiento;
   private java.lang.Long ciudadId;
   private java.lang.String canton;
   private java.lang.String parroquia;
   private java.lang.String casoEmergencia;
   private java.lang.String telefonoEmergencia;
   private java.lang.String direccionEmergencia;
   private java.lang.Long ciudadEmergenciaId;
   private java.lang.String tallaCamisa;
   private java.lang.String tallaPantalon;
   private java.lang.String numeroCalzado;
   private java.lang.String estatura;
   private java.lang.String peso;
   private java.lang.String colorPelo;
   private java.lang.String colorOjos;
   private java.lang.String colorPiel;
   private java.lang.String senasParticulares;
   private java.lang.String exestudianteCtt;
   private java.lang.Long paisId;
   private java.lang.Long provinciaId;

   public EmpleadoPersonalEJB() {
   }

   public EmpleadoPersonalEJB(com.spirit.rrhh.entity.EmpleadoPersonalIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setTitulo(value.getTitulo());
      setCedulaIdentidad(value.getCedulaIdentidad());
      setAfiliacionIess(value.getAfiliacionIess());
      setLibretaMilitar(value.getLibretaMilitar());
      setSexo(value.getSexo());
      setTipoSangre(value.getTipoSangre());
      setEstadoCivil(value.getEstadoCivil());
      setFechaNacimiento(value.getFechaNacimiento());
      setCiudadId(value.getCiudadId());
      setCanton(value.getCanton());
      setParroquia(value.getParroquia());
      setCasoEmergencia(value.getCasoEmergencia());
      setTelefonoEmergencia(value.getTelefonoEmergencia());
      setDireccionEmergencia(value.getDireccionEmergencia());
      setCiudadEmergenciaId(value.getCiudadEmergenciaId());
      setTallaCamisa(value.getTallaCamisa());
      setTallaPantalon(value.getTallaPantalon());
      setNumeroCalzado(value.getNumeroCalzado());
      setEstatura(value.getEstatura());
      setPeso(value.getPeso());
      setColorPelo(value.getColorPelo());
      setColorOjos(value.getColorOjos());
      setColorPiel(value.getColorPiel());
      setSenasParticulares(value.getSenasParticulares());
      setExestudianteCtt(value.getExestudianteCtt());
      setPaisId(value.getPaisId());
      setProvinciaId(value.getProvinciaId());
   }

   public java.lang.Long create(com.spirit.rrhh.entity.EmpleadoPersonalIf value) {
      setId(value.getId());
      setEmpleadoId(value.getEmpleadoId());
      setTitulo(value.getTitulo());
      setCedulaIdentidad(value.getCedulaIdentidad());
      setAfiliacionIess(value.getAfiliacionIess());
      setLibretaMilitar(value.getLibretaMilitar());
      setSexo(value.getSexo());
      setTipoSangre(value.getTipoSangre());
      setEstadoCivil(value.getEstadoCivil());
      setFechaNacimiento(value.getFechaNacimiento());
      setCiudadId(value.getCiudadId());
      setCanton(value.getCanton());
      setParroquia(value.getParroquia());
      setCasoEmergencia(value.getCasoEmergencia());
      setTelefonoEmergencia(value.getTelefonoEmergencia());
      setDireccionEmergencia(value.getDireccionEmergencia());
      setCiudadEmergenciaId(value.getCiudadEmergenciaId());
      setTallaCamisa(value.getTallaCamisa());
      setTallaPantalon(value.getTallaPantalon());
      setNumeroCalzado(value.getNumeroCalzado());
      setEstatura(value.getEstatura());
      setPeso(value.getPeso());
      setColorPelo(value.getColorPelo());
      setColorOjos(value.getColorOjos());
      setColorPiel(value.getColorPiel());
      setSenasParticulares(value.getSenasParticulares());
      setExestudianteCtt(value.getExestudianteCtt());
      setPaisId(value.getPaisId());
      setProvinciaId(value.getProvinciaId());
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

   @Column(name = "EMPLEADO_ID")
   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   @Column(name = "TITULO")
   public java.lang.String getTitulo() {
      return titulo;
   }

   public void setTitulo(java.lang.String titulo) {
      this.titulo = titulo;
   }

   @Column(name = "CEDULA_IDENTIDAD")
   public java.lang.String getCedulaIdentidad() {
      return cedulaIdentidad;
   }

   public void setCedulaIdentidad(java.lang.String cedulaIdentidad) {
      this.cedulaIdentidad = cedulaIdentidad;
   }

   @Column(name = "AFILIACION_IESS")
   public java.lang.String getAfiliacionIess() {
      return afiliacionIess;
   }

   public void setAfiliacionIess(java.lang.String afiliacionIess) {
      this.afiliacionIess = afiliacionIess;
   }

   @Column(name = "LIBRETA_MILITAR")
   public java.lang.String getLibretaMilitar() {
      return libretaMilitar;
   }

   public void setLibretaMilitar(java.lang.String libretaMilitar) {
      this.libretaMilitar = libretaMilitar;
   }

   @Column(name = "SEXO")
   public java.lang.String getSexo() {
      return sexo;
   }

   public void setSexo(java.lang.String sexo) {
      this.sexo = sexo;
   }

   @Column(name = "TIPO_SANGRE")
   public java.lang.String getTipoSangre() {
      return tipoSangre;
   }

   public void setTipoSangre(java.lang.String tipoSangre) {
      this.tipoSangre = tipoSangre;
   }

   @Column(name = "ESTADO_CIVIL")
   public java.lang.String getEstadoCivil() {
      return estadoCivil;
   }

   public void setEstadoCivil(java.lang.String estadoCivil) {
      this.estadoCivil = estadoCivil;
   }

   @Column(name = "FECHA_NACIMIENTO")
   public java.sql.Timestamp getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   @Column(name = "CIUDAD_ID")
   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   @Column(name = "CANTON")
   public java.lang.String getCanton() {
      return canton;
   }

   public void setCanton(java.lang.String canton) {
      this.canton = canton;
   }

   @Column(name = "PARROQUIA")
   public java.lang.String getParroquia() {
      return parroquia;
   }

   public void setParroquia(java.lang.String parroquia) {
      this.parroquia = parroquia;
   }

   @Column(name = "CASO_EMERGENCIA")
   public java.lang.String getCasoEmergencia() {
      return casoEmergencia;
   }

   public void setCasoEmergencia(java.lang.String casoEmergencia) {
      this.casoEmergencia = casoEmergencia;
   }

   @Column(name = "TELEFONO_EMERGENCIA")
   public java.lang.String getTelefonoEmergencia() {
      return telefonoEmergencia;
   }

   public void setTelefonoEmergencia(java.lang.String telefonoEmergencia) {
      this.telefonoEmergencia = telefonoEmergencia;
   }

   @Column(name = "DIRECCION_EMERGENCIA")
   public java.lang.String getDireccionEmergencia() {
      return direccionEmergencia;
   }

   public void setDireccionEmergencia(java.lang.String direccionEmergencia) {
      this.direccionEmergencia = direccionEmergencia;
   }

   @Column(name = "CIUDAD_EMERGENCIA_ID")
   public java.lang.Long getCiudadEmergenciaId() {
      return ciudadEmergenciaId;
   }

   public void setCiudadEmergenciaId(java.lang.Long ciudadEmergenciaId) {
      this.ciudadEmergenciaId = ciudadEmergenciaId;
   }

   @Column(name = "TALLA_CAMISA")
   public java.lang.String getTallaCamisa() {
      return tallaCamisa;
   }

   public void setTallaCamisa(java.lang.String tallaCamisa) {
      this.tallaCamisa = tallaCamisa;
   }

   @Column(name = "TALLA_PANTALON")
   public java.lang.String getTallaPantalon() {
      return tallaPantalon;
   }

   public void setTallaPantalon(java.lang.String tallaPantalon) {
      this.tallaPantalon = tallaPantalon;
   }

   @Column(name = "NUMERO_CALZADO")
   public java.lang.String getNumeroCalzado() {
      return numeroCalzado;
   }

   public void setNumeroCalzado(java.lang.String numeroCalzado) {
      this.numeroCalzado = numeroCalzado;
   }

   @Column(name = "ESTATURA")
   public java.lang.String getEstatura() {
      return estatura;
   }

   public void setEstatura(java.lang.String estatura) {
      this.estatura = estatura;
   }

   @Column(name = "PESO")
   public java.lang.String getPeso() {
      return peso;
   }

   public void setPeso(java.lang.String peso) {
      this.peso = peso;
   }

   @Column(name = "COLOR_PELO")
   public java.lang.String getColorPelo() {
      return colorPelo;
   }

   public void setColorPelo(java.lang.String colorPelo) {
      this.colorPelo = colorPelo;
   }

   @Column(name = "COLOR_OJOS")
   public java.lang.String getColorOjos() {
      return colorOjos;
   }

   public void setColorOjos(java.lang.String colorOjos) {
      this.colorOjos = colorOjos;
   }

   @Column(name = "COLOR_PIEL")
   public java.lang.String getColorPiel() {
      return colorPiel;
   }

   public void setColorPiel(java.lang.String colorPiel) {
      this.colorPiel = colorPiel;
   }

   @Column(name = "SENAS_PARTICULARES")
   public java.lang.String getSenasParticulares() {
      return senasParticulares;
   }

   public void setSenasParticulares(java.lang.String senasParticulares) {
      this.senasParticulares = senasParticulares;
   }

   @Column(name = "EXESTUDIANTE_CTT")
   public java.lang.String getExestudianteCtt() {
      return exestudianteCtt;
   }

   public void setExestudianteCtt(java.lang.String exestudianteCtt) {
      this.exestudianteCtt = exestudianteCtt;
   }

   @Column(name = "PAIS_ID")
   public java.lang.Long getPaisId() {
      return paisId;
   }

   public void setPaisId(java.lang.Long paisId) {
      this.paisId = paisId;
   }

   @Column(name = "PROVINCIA_ID")
   public java.lang.Long getProvinciaId() {
      return provinciaId;
   }

   public void setProvinciaId(java.lang.Long provinciaId) {
      this.provinciaId = provinciaId;
   }
	public String toString() {
		return ToStringer.toString((EmpleadoPersonalIf)this);
	}
}
