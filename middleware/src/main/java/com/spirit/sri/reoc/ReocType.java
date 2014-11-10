/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: ReocType.java,v 1.1 2014/03/28 18:06:46 xrf Exp $
 */

package com.spirit.sri.reoc;

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
 * Class ReocType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:46 $
 */
public class ReocType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _numeroRuc
     */
    private java.lang.String _numeroRuc;

    /**
     * Field _anio
     */
    private int _anio;

    /**
     * keeps track of state for field: _anio
     */
    private boolean _has_anio;

    /**
     * Field _mes
     */
    private java.lang.String _mes;

    /**
     * Field _compras
     */
    private com.spirit.sri.reoc.Compras _compras;


      //----------------/
     //- Constructors -/
    //----------------/

    public ReocType() 
     {
        super();
    } //-- com.spirit.sri.reoc.ReocType()


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
     * Returns the value of field 'compras'.
     * 
     * @return Compras
     * @return the value of field 'compras'.
     */
    public com.spirit.sri.reoc.Compras getCompras()
    {
        return this._compras;
    } //-- com.spirit.sri.reoc.Compras getCompras() 

    /**
     * Returns the value of field 'mes'.
     * 
     * @return String
     * @return the value of field 'mes'.
     */
    public java.lang.String getMes()
    {
        return this._mes;
    } //-- java.lang.String getMes() 

    /**
     * Returns the value of field 'numeroRuc'.
     * 
     * @return String
     * @return the value of field 'numeroRuc'.
     */
    public java.lang.String getNumeroRuc()
    {
        return this._numeroRuc;
    } //-- java.lang.String getNumeroRuc() 

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
     * Sets the value of field 'compras'.
     * 
     * @param compras the value of field 'compras'.
     */
    public void setCompras(com.spirit.sri.reoc.Compras compras)
    {
        this._compras = compras;
    } //-- void setCompras(com.spirit.sri.reoc.Compras) 

    /**
     * Sets the value of field 'mes'.
     * 
     * @param mes the value of field 'mes'.
     */
    public void setMes(java.lang.String mes)
    {
        this._mes = mes;
    } //-- void setMes(java.lang.String) 

    /**
     * Sets the value of field 'numeroRuc'.
     * 
     * @param numeroRuc the value of field 'numeroRuc'.
     */
    public void setNumeroRuc(java.lang.String numeroRuc)
    {
        this._numeroRuc = numeroRuc;
    } //-- void setNumeroRuc(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ReocType
     */
    public static com.spirit.sri.reoc.ReocType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.reoc.ReocType) Unmarshaller.unmarshal(com.spirit.sri.reoc.ReocType.class, reader);
    } //-- com.spirit.sri.reoc.ReocType unmarshal(java.io.Reader) 

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
