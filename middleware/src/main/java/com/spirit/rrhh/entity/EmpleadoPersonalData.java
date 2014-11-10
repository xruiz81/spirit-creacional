package com.spirit.rrhh.entity;

import java.io.Serializable;
import com.spirit.comun.util.ToStringer;


/**
 *
 * @author  www.versality.com.ec
 *
 */
public class EmpleadoPersonalData implements EmpleadoPersonalIf, Serializable    {


   private java.lang.Long id;

   public java.lang.Long getId() {
      return id;
   }

   public void setId(java.lang.Long id) {
      this.id = id;
   }

   private java.lang.Long empleadoId;

   public java.lang.Long getEmpleadoId() {
      return empleadoId;
   }

   public void setEmpleadoId(java.lang.Long empleadoId) {
      this.empleadoId = empleadoId;
   }

   private java.lang.String titulo;

   public java.lang.String getTitulo() {
      return titulo;
   }

   public void setTitulo(java.lang.String titulo) {
      this.titulo = titulo;
   }

   private java.lang.String cedulaIdentidad;

   public java.lang.String getCedulaIdentidad() {
      return cedulaIdentidad;
   }

   public void setCedulaIdentidad(java.lang.String cedulaIdentidad) {
      this.cedulaIdentidad = cedulaIdentidad;
   }

   private java.lang.String afiliacionIess;

   public java.lang.String getAfiliacionIess() {
      return afiliacionIess;
   }

   public void setAfiliacionIess(java.lang.String afiliacionIess) {
      this.afiliacionIess = afiliacionIess;
   }

   private java.lang.String libretaMilitar;

   public java.lang.String getLibretaMilitar() {
      return libretaMilitar;
   }

   public void setLibretaMilitar(java.lang.String libretaMilitar) {
      this.libretaMilitar = libretaMilitar;
   }

   private java.lang.String sexo;

   public java.lang.String getSexo() {
      return sexo;
   }

   public void setSexo(java.lang.String sexo) {
      this.sexo = sexo;
   }

   private java.lang.String tipoSangre;

   public java.lang.String getTipoSangre() {
      return tipoSangre;
   }

   public void setTipoSangre(java.lang.String tipoSangre) {
      this.tipoSangre = tipoSangre;
   }

   private java.lang.String estadoCivil;

   public java.lang.String getEstadoCivil() {
      return estadoCivil;
   }

   public void setEstadoCivil(java.lang.String estadoCivil) {
      this.estadoCivil = estadoCivil;
   }

   private java.sql.Timestamp fechaNacimiento;

   public java.sql.Timestamp getFechaNacimiento() {
      return fechaNacimiento;
   }

   public void setFechaNacimiento(java.sql.Timestamp fechaNacimiento) {
      this.fechaNacimiento = fechaNacimiento;
   }

   private java.lang.Long ciudadId;

   public java.lang.Long getCiudadId() {
      return ciudadId;
   }

   public void setCiudadId(java.lang.Long ciudadId) {
      this.ciudadId = ciudadId;
   }

   private java.lang.String canton;

   public java.lang.String getCanton() {
      return canton;
   }

   public void setCanton(java.lang.String canton) {
      this.canton = canton;
   }

   private java.lang.String parroquia;

   public java.lang.String getParroquia() {
      return parroquia;
   }

   public void setParroquia(java.lang.String parroquia) {
      this.parroquia = parroquia;
   }

   private java.lang.String casoEmergencia;

   public java.lang.String getCasoEmergencia() {
      return casoEmergencia;
   }

   public void setCasoEmergencia(java.lang.String casoEmergencia) {
      this.casoEmergencia = casoEmergencia;
   }

   private java.lang.String telefonoEmergencia;

   public java.lang.String getTelefonoEmergencia() {
      return telefonoEmergencia;
   }

   public void setTelefonoEmergencia(java.lang.String telefonoEmergencia) {
      this.telefonoEmergencia = telefonoEmergencia;
   }

   private java.lang.String direccionEmergencia;

   public java.lang.String getDireccionEmergencia() {
      return direccionEmergencia;
   }

   public void setDireccionEmergencia(java.lang.String direccionEmergencia) {
      this.direccionEmergencia = direccionEmergencia;
   }

   private java.lang.Long ciudadEmergenciaId;

   public java.lang.Long getCiudadEmergenciaId() {
      return ciudadEmergenciaId;
   }

   public void setCiudadEmergenciaId(java.lang.Long ciudadEmergenciaId) {
      this.ciudadEmergenciaId = ciudadEmergenciaId;
   }

   private java.lang.String tallaCamisa;

   public java.lang.String getTallaCamisa() {
      return tallaCamisa;
   }

   public void setTallaCamisa(java.lang.String tallaCamisa) {
      this.tallaCamisa = tallaCamisa;
   }

   private java.lang.String tallaPantalon;

   public java.lang.String getTallaPantalon() {
      return tallaPantalon;
   }

   public void setTallaPantalon(java.lang.String tallaPantalon) {
      this.tallaPantalon = tallaPantalon;
   }

   private java.lang.String numeroCalzado;

   public java.lang.String getNumeroCalzado() {
      return numeroCalzado;
   }

   public void setNumeroCalzado(java.lang.String numeroCalzado) {
      this.numeroCalzado = numeroCalzado;
   }

   private java.lang.String estatura;

   public java.lang.String getEstatura() {
      return estatura;
   }

   public void setEstatura(java.lang.String estatura) {
      this.estatura = estatura;
   }

   private java.lang.String peso;

   public java.lang.String getPeso() {
      return peso;
   }

   public void setPeso(java.lang.String peso) {
      this.peso = peso;
   }

   private java.lang.String colorPelo;

   public java.lang.String getColorPelo() {
      return colorPelo;
   }

   public void setColorPelo(java.lang.String colorPelo) {
      this.colorPelo = colorPelo;
   }

   private java.lang.String colorOjos;

   public java.lang.String getColorOjos() {
      return colorOjos;
   }

   public void setColorOjos(java.lang.String colorOjos) {
      this.colorOjos = colorOjos;
   }

   private java.lang.String colorPiel;

   public java.lang.String getColorPiel() {
      return colorPiel;
   }

   public void setColorPiel(java.lang.String colorPiel) {
      this.colorPiel = colorPiel;
   }

   private java.lang.String senasParticulares;

   public java.lang.String getSenasParticulares() {
      return senasParticulares;
   }

   public void setSenasParticulares(java.lang.String senasParticulares) {
      this.senasParticulares = senasParticulares;
   }

   private java.lang.String exestudianteCtt;

   public java.lang.String getExestudianteCtt() {
      return exestudianteCtt;
   }

   public void setExestudianteCtt(java.lang.String exestudianteCtt) {
      this.exestudianteCtt = exestudianteCtt;
   }

   private java.lang.Long paisId;

   public java.lang.Long getPaisId() {
      return paisId;
   }

   public void setPaisId(java.lang.Long paisId) {
      this.paisId = paisId;
   }

   private java.lang.Long provinciaId;

   public java.lang.Long getProvinciaId() {
      return provinciaId;
   }

   public void setProvinciaId(java.lang.Long provinciaId) {
      this.provinciaId = provinciaId;
   }
   public EmpleadoPersonalData() {
   }

   public EmpleadoPersonalData(com.spirit.rrhh.entity.EmpleadoPersonalIf value) {
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
		return ToStringer.toString((EmpleadoPersonalIf)this);
	}
}
