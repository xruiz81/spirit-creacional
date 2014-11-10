/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleExportacionesType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.math.BigDecimal;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class DetalleExportacionesType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleExportacionesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _exportacionDe
     */
    private int _exportacionDe;

    /**
     * keeps track of state for field: _exportacionDe
     */
    private boolean _has_exportacionDe;

    /**
     * Field _tipoComprobante
     */
    private int _tipoComprobante;

    /**
     * keeps track of state for field: _tipoComprobante
     */
    private boolean _has_tipoComprobante;

    /**
     * Field _distAduanero
     */
    private java.lang.String _distAduanero;

    /**
     * Field _anio
     */
    private int _anio;

    /**
     * keeps track of state for field: _anio
     */
    private boolean _has_anio;

    /**
     * Field _regimen
     */
    private java.lang.String _regimen;

    /**
     * Field _correlativo
     */
    private java.lang.String _correlativo;

    /**
     * Field _verificador
     */
    private int _verificador;

    /**
     * keeps track of state for field: _verificador
     */
    private boolean _has_verificador;

    /**
     * Field _docTransp
     */
    private java.lang.String _docTransp;

    /**
     * Field _fechaEmbarque
     */
    private java.lang.String _fechaEmbarque;

    /**
     * Field _fue
     */
    private java.lang.String _fue;

    /**
     * Field _valorFOB
     */
    private java.math.BigDecimal _valorFOB;

    /**
     * Field _valorFOBComprobante
     */
    private java.math.BigDecimal _valorFOBComprobante;

    /**
     * Field _establecimiento
     */
    private java.lang.String _establecimiento;

    /**
     * Field _puntoEmision
     */
    private java.lang.String _puntoEmision;

    /**
     * Field _secuencial
     */
    private int _secuencial;

    /**
     * keeps track of state for field: _secuencial
     */
    private boolean _has_secuencial;

    /**
     * Field _autorizacion
     */
    private java.lang.String _autorizacion;

    /**
     * Field _fechaEmision
     */
    private java.lang.String _fechaEmision;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleExportacionesType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleExportacionesType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteAnio
     * 
     */
    public void deleteAnio()
    {
        this._has_anio= false;
    } //-- void deleteAnio() 

    /**
     * Method deleteExportacionDe
     * 
     */
    public void deleteExportacionDe()
    {
        this._has_exportacionDe= false;
    } //-- void deleteExportacionDe() 

    /**
     * Method deleteSecuencial
     * 
     */
    public void deleteSecuencial()
    {
        this._has_secuencial= false;
    } //-- void deleteSecuencial() 

    /**
     * Method deleteTipoComprobante
     * 
     */
    public void deleteTipoComprobante()
    {
        this._has_tipoComprobante= false;
    } //-- void deleteTipoComprobante() 

    /**
     * Method deleteVerificador
     * 
     */
    public void deleteVerificador()
    {
        this._has_verificador= false;
    } //-- void deleteVerificador() 

    /**
     * Returns the value of field 'anio'.
     * 
     * @return int
     * @return the value of field 'anio'.
     */
    public int getAnio()
    {
        return this._anio;
    } //-- int getAnio() 

    /**
     * Returns the value of field 'autorizacion'.
     * 
     * @return String
     * @return the value of field 'autorizacion'.
     */
    public java.lang.String getAutorizacion()
    {
        return this._autorizacion;
    } //-- java.lang.String getAutorizacion() 

    /**
     * Returns the value of field 'correlativo'.
     * 
     * @return String
     * @return the value of field 'correlativo'.
     */
    public java.lang.String getCorrelativo()
    {
        return this._correlativo;
    } //-- java.lang.String getCorrelativo() 

    /**
     * Returns the value of field 'distAduanero'.
     * 
     * @return String
     * @return the value of field 'distAduanero'.
     */
    public java.lang.String getDistAduanero()
    {
        return this._distAduanero;
    } //-- java.lang.String getDistAduanero() 

    /**
     * Returns the value of field 'docTransp'.
     * 
     * @return String
     * @return the value of field 'docTransp'.
     */
    public java.lang.String getDocTransp()
    {
        return this._docTransp;
    } //-- java.lang.String getDocTransp() 

    /**
     * Returns the value of field 'establecimiento'.
     * 
     * @return String
     * @return the value of field 'establecimiento'.
     */
    public java.lang.String getEstablecimiento()
    {
        return this._establecimiento;
    } //-- java.lang.String getEstablecimiento() 

    /**
     * Returns the value of field 'exportacionDe'.
     * 
     * @return int
     * @return the value of field 'exportacionDe'.
     */
    public int getExportacionDe()
    {
        return this._exportacionDe;
    } //-- int getExportacionDe() 

    /**
     * Returns the value of field 'fechaEmbarque'.
     * 
     * @return String
     * @return the value of field 'fechaEmbarque'.
     */
    public java.lang.String getFechaEmbarque()
    {
        return this._fechaEmbarque;
    } //-- java.lang.String getFechaEmbarque() 

    /**
     * Returns the value of field 'fechaEmision'.
     * 
     * @return String
     * @return the value of field 'fechaEmision'.
     */
    public java.lang.String getFechaEmision()
    {
        return this._fechaEmision;
    } //-- java.lang.String getFechaEmision() 

    /**
     * Returns the value of field 'fue'.
     * 
     * @return String
     * @return the value of field 'fue'.
     */
    public java.lang.String getFue()
    {
        return this._fue;
    } //-- java.lang.String getFue() 

    /**
     * Returns the value of field 'puntoEmision'.
     * 
     * @return String
     * @return the value of field 'puntoEmision'.
     */
    public java.lang.String getPuntoEmision()
    {
        return this._puntoEmision;
    } //-- java.lang.String getPuntoEmision() 

    /**
     * Returns the value of field 'regimen'.
     * 
     * @return String
     * @return the value of field 'regimen'.
     */
    public java.lang.String getRegimen()
    {
        return this._regimen;
    } //-- java.lang.String getRegimen() 

    /**
     * Returns the value of field 'secuencial'.
     * 
     * @return int
     * @return the value of field 'secuencial'.
     */
    public int getSecuencial()
    {
        return this._secuencial;
    } //-- int getSecuencial() 

    /**
     * Returns the value of field 'tipoComprobante'.
     * 
     * @return int
     * @return the value of field 'tipoComprobante'.
     */
    public int getTipoComprobante()
    {
        return this._tipoComprobante;
    } //-- int getTipoComprobante() 

    /**
     * Returns the value of field 'valorFOB'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorFOB'.
     */
    public java.math.BigDecimal getValorFOB()
    {
        return this._valorFOB;
    } //-- java.math.BigDecimal getValorFOB() 

    /**
     * Returns the value of field 'valorFOBComprobante'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorFOBComprobante'.
     */
    public java.math.BigDecimal getValorFOBComprobante()
    {
        return this._valorFOBComprobante;
    } //-- java.math.BigDecimal getValorFOBComprobante() 

    /**
     * Returns the value of field 'verificador'.
     * 
     * @return int
     * @return the value of field 'verificador'.
     */
    public int getVerificador()
    {
        return this._verificador;
    } //-- int getVerificador() 

    /**
     * Method hasAnio
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasAnio()
    {
        return this._has_anio;
    } //-- boolean hasAnio() 

    /**
     * Method hasExportacionDe
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasExportacionDe()
    {
        return this._has_exportacionDe;
    } //-- boolean hasExportacionDe() 

    /**
     * Method hasSecuencial
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecuencial()
    {
        return this._has_secuencial;
    } //-- boolean hasSecuencial() 

    /**
     * Method hasTipoComprobante
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTipoComprobante()
    {
        return this._has_tipoComprobante;
    } //-- boolean hasTipoComprobante() 

    /**
     * Method hasVerificador
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasVerificador()
    {
        return this._has_verificador;
    } //-- boolean hasVerificador() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'anio'.
     * 
     * @param anio the value of field 'anio'.
     */
    public void setAnio(int anio)
    {
        this._anio = anio;
        this._has_anio = true;
    } //-- void setAnio(int) 

    /**
     * Sets the value of field 'autorizacion'.
     * 
     * @param autorizacion the value of field 'autorizacion'.
     */
    public void setAutorizacion(java.lang.String autorizacion)
    {
        this._autorizacion = autorizacion;
    } //-- void setAutorizacion(java.lang.String) 

    /**
     * Sets the value of field 'correlativo'.
     * 
     * @param correlativo the value of field 'correlativo'.
     */
    public void setCorrelativo(java.lang.String correlativo)
    {
        this._correlativo = correlativo;
    } //-- void setCorrelativo(java.lang.String) 

    /**
     * Sets the value of field 'distAduanero'.
     * 
     * @param distAduanero the value of field 'distAduanero'.
     */
    public void setDistAduanero(java.lang.String distAduanero)
    {
        this._distAduanero = distAduanero;
    } //-- void setDistAduanero(java.lang.String) 

    /**
     * Sets the value of field 'docTransp'.
     * 
     * @param docTransp the value of field 'docTransp'.
     */
    public void setDocTransp(java.lang.String docTransp)
    {
        this._docTransp = docTransp;
    } //-- void setDocTransp(java.lang.String) 

    /**
     * Sets the value of field 'establecimiento'.
     * 
     * @param establecimiento the value of field 'establecimiento'.
     */
    public void setEstablecimiento(java.lang.String establecimiento)
    {
        this._establecimiento = establecimiento;
    } //-- void setEstablecimiento(java.lang.String) 

    /**
     * Sets the value of field 'exportacionDe'.
     * 
     * @param exportacionDe the value of field 'exportacionDe'.
     */
    public void setExportacionDe(int exportacionDe)
    {
        this._exportacionDe = exportacionDe;
        this._has_exportacionDe = true;
    } //-- void setExportacionDe(int) 

    /**
     * Sets the value of field 'fechaEmbarque'.
     * 
     * @param fechaEmbarque the value of field 'fechaEmbarque'.
     */
    public void setFechaEmbarque(java.lang.String fechaEmbarque)
    {
        this._fechaEmbarque = fechaEmbarque;
    } //-- void setFechaEmbarque(java.lang.String) 

    /**
     * Sets the value of field 'fechaEmision'.
     * 
     * @param fechaEmision the value of field 'fechaEmision'.
     */
    public void setFechaEmision(java.lang.String fechaEmision)
    {
        this._fechaEmision = fechaEmision;
    } //-- void setFechaEmision(java.lang.String) 

    /**
     * Sets the value of field 'fue'.
     * 
     * @param fue the value of field 'fue'.
     */
    public void setFue(java.lang.String fue)
    {
        this._fue = fue;
    } //-- void setFue(java.lang.String) 

    /**
     * Sets the value of field 'puntoEmision'.
     * 
     * @param puntoEmision the value of field 'puntoEmision'.
     */
    public void setPuntoEmision(java.lang.String puntoEmision)
    {
        this._puntoEmision = puntoEmision;
    } //-- void setPuntoEmision(java.lang.String) 

    /**
     * Sets the value of field 'regimen'.
     * 
     * @param regimen the value of field 'regimen'.
     */
    public void setRegimen(java.lang.String regimen)
    {
        this._regimen = regimen;
    } //-- void setRegimen(java.lang.String) 

    /**
     * Sets the value of field 'secuencial'.
     * 
     * @param secuencial the value of field 'secuencial'.
     */
    public void setSecuencial(int secuencial)
    {
        this._secuencial = secuencial;
        this._has_secuencial = true;
    } //-- void setSecuencial(int) 

    /**
     * Sets the value of field 'tipoComprobante'.
     * 
     * @param tipoComprobante the value of field 'tipoComprobante'.
     */
    public void setTipoComprobante(int tipoComprobante)
    {
        this._tipoComprobante = tipoComprobante;
        this._has_tipoComprobante = true;
    } //-- void setTipoComprobante(int) 

    /**
     * Sets the value of field 'valorFOB'.
     * 
     * @param valorFOB the value of field 'valorFOB'.
     */
    public void setValorFOB(java.math.BigDecimal valorFOB)
    {
        this._valorFOB = valorFOB;
    } //-- void setValorFOB(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valorFOBComprobante'.
     * 
     * @param valorFOBComprobante the value of field
     * 'valorFOBComprobante'.
     */
    public void setValorFOBComprobante(java.math.BigDecimal valorFOBComprobante)
    {
        this._valorFOBComprobante = valorFOBComprobante;
    } //-- void setValorFOBComprobante(java.math.BigDecimal) 

    /**
     * Sets the value of field 'verificador'.
     * 
     * @param verificador the value of field 'verificador'.
     */
    public void setVerificador(int verificador)
    {
        this._verificador = verificador;
        this._has_verificador = true;
    } //-- void setVerificador(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleExportacionesType
     */
    public static com.spirit.sri.at.DetalleExportacionesType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleExportacionesType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleExportacionesType.class, reader);
    } //-- com.spirit.sri.at.DetalleExportacionesType unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
