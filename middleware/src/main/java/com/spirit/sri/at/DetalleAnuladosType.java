/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleAnuladosType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class DetalleAnuladosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleAnuladosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoComprobante
     */
    private int _tipoComprobante;

    /**
     * keeps track of state for field: _tipoComprobante
     */
    private boolean _has_tipoComprobante;

    /**
     * Field _establecimiento
     */
    private java.lang.String _establecimiento;

    /**
     * Field _puntoEmision
     */
    private java.lang.String _puntoEmision;

    /**
     * Field _secuencialInicio
     */
    private int _secuencialInicio;

    /**
     * keeps track of state for field: _secuencialInicio
     */
    private boolean _has_secuencialInicio;

    /**
     * Field _secuencialFin
     */
    private int _secuencialFin;

    /**
     * keeps track of state for field: _secuencialFin
     */
    private boolean _has_secuencialFin;

    /**
     * Field _autorizacion
     */
    private java.lang.String _autorizacion;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleAnuladosType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleAnuladosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteSecuencialFin
     * 
     */
    public void deleteSecuencialFin()
    {
        this._has_secuencialFin= false;
    } //-- void deleteSecuencialFin() 

    /**
     * Method deleteSecuencialInicio
     * 
     */
    public void deleteSecuencialInicio()
    {
        this._has_secuencialInicio= false;
    } //-- void deleteSecuencialInicio() 

    /**
     * Method deleteTipoComprobante
     * 
     */
    public void deleteTipoComprobante()
    {
        this._has_tipoComprobante= false;
    } //-- void deleteTipoComprobante() 

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
     * Returns the value of field 'secuencialFin'.
     * 
     * @return int
     * @return the value of field 'secuencialFin'.
     */
    public int getSecuencialFin()
    {
        return this._secuencialFin;
    } //-- int getSecuencialFin() 

    /**
     * Returns the value of field 'secuencialInicio'.
     * 
     * @return int
     * @return the value of field 'secuencialInicio'.
     */
    public int getSecuencialInicio()
    {
        return this._secuencialInicio;
    } //-- int getSecuencialInicio() 

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
     * Method hasSecuencialFin
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecuencialFin()
    {
        return this._has_secuencialFin;
    } //-- boolean hasSecuencialFin() 

    /**
     * Method hasSecuencialInicio
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasSecuencialInicio()
    {
        return this._has_secuencialInicio;
    } //-- boolean hasSecuencialInicio() 

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
     * Sets the value of field 'autorizacion'.
     * 
     * @param autorizacion the value of field 'autorizacion'.
     */
    public void setAutorizacion(java.lang.String autorizacion)
    {
        this._autorizacion = autorizacion;
    } //-- void setAutorizacion(java.lang.String) 

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
     * Sets the value of field 'puntoEmision'.
     * 
     * @param puntoEmision the value of field 'puntoEmision'.
     */
    public void setPuntoEmision(java.lang.String puntoEmision)
    {
        this._puntoEmision = puntoEmision;
    } //-- void setPuntoEmision(java.lang.String) 

    /**
     * Sets the value of field 'secuencialFin'.
     * 
     * @param secuencialFin the value of field 'secuencialFin'.
     */
    public void setSecuencialFin(int secuencialFin)
    {
        this._secuencialFin = secuencialFin;
        this._has_secuencialFin = true;
    } //-- void setSecuencialFin(int) 

    /**
     * Sets the value of field 'secuencialInicio'.
     * 
     * @param secuencialInicio the value of field 'secuencialInicio'
     */
    public void setSecuencialInicio(int secuencialInicio)
    {
        this._secuencialInicio = secuencialInicio;
        this._has_secuencialInicio = true;
    } //-- void setSecuencialInicio(int) 

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
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleAnuladosType
     */
    public static com.spirit.sri.at.DetalleAnuladosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleAnuladosType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleAnuladosType.class, reader);
    } //-- com.spirit.sri.at.DetalleAnuladosType unmarshal(java.io.Reader) 

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
