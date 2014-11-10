/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleRendFinancierosType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetalleRendFinancierosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleRendFinancierosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _retenido
     */
    private java.lang.String _retenido;

    /**
     * Field _idRetenido
     */
    private java.lang.String _idRetenido;

    /**
     * Field _ahorroPN
     */
    private com.spirit.sri.at.AhorroPN _ahorroPN;

    /**
     * Field _ctaExenta
     */
    private com.spirit.sri.at.CtaExenta _ctaExenta;

    /**
     * Field _retenciones
     */
    private com.spirit.sri.at.Retenciones _retenciones;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleRendFinancierosType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleRendFinancierosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'ahorroPN'.
     * 
     * @return AhorroPN
     * @return the value of field 'ahorroPN'.
     */
    public com.spirit.sri.at.AhorroPN getAhorroPN()
    {
        return this._ahorroPN;
    } //-- com.spirit.sri.at.AhorroPN getAhorroPN() 

    /**
     * Returns the value of field 'ctaExenta'.
     * 
     * @return CtaExenta
     * @return the value of field 'ctaExenta'.
     */
    public com.spirit.sri.at.CtaExenta getCtaExenta()
    {
        return this._ctaExenta;
    } //-- com.spirit.sri.at.CtaExenta getCtaExenta() 

    /**
     * Returns the value of field 'idRetenido'.
     * 
     * @return String
     * @return the value of field 'idRetenido'.
     */
    public java.lang.String getIdRetenido()
    {
        return this._idRetenido;
    } //-- java.lang.String getIdRetenido() 

    /**
     * Returns the value of field 'retenciones'.
     * 
     * @return Retenciones
     * @return the value of field 'retenciones'.
     */
    public com.spirit.sri.at.Retenciones getRetenciones()
    {
        return this._retenciones;
    } //-- com.spirit.sri.at.Retenciones getRetenciones() 

    /**
     * Returns the value of field 'retenido'.
     * 
     * @return String
     * @return the value of field 'retenido'.
     */
    public java.lang.String getRetenido()
    {
        return this._retenido;
    } //-- java.lang.String getRetenido() 

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
     * Sets the value of field 'ahorroPN'.
     * 
     * @param ahorroPN the value of field 'ahorroPN'.
     */
    public void setAhorroPN(com.spirit.sri.at.AhorroPN ahorroPN)
    {
        this._ahorroPN = ahorroPN;
    } //-- void setAhorroPN(com.spirit.sri.at.AhorroPN) 

    /**
     * Sets the value of field 'ctaExenta'.
     * 
     * @param ctaExenta the value of field 'ctaExenta'.
     */
    public void setCtaExenta(com.spirit.sri.at.CtaExenta ctaExenta)
    {
        this._ctaExenta = ctaExenta;
    } //-- void setCtaExenta(com.spirit.sri.at.CtaExenta) 

    /**
     * Sets the value of field 'idRetenido'.
     * 
     * @param idRetenido the value of field 'idRetenido'.
     */
    public void setIdRetenido(java.lang.String idRetenido)
    {
        this._idRetenido = idRetenido;
    } //-- void setIdRetenido(java.lang.String) 

    /**
     * Sets the value of field 'retenciones'.
     * 
     * @param retenciones the value of field 'retenciones'.
     */
    public void setRetenciones(com.spirit.sri.at.Retenciones retenciones)
    {
        this._retenciones = retenciones;
    } //-- void setRetenciones(com.spirit.sri.at.Retenciones) 

    /**
     * Sets the value of field 'retenido'.
     * 
     * @param retenido the value of field 'retenido'.
     */
    public void setRetenido(java.lang.String retenido)
    {
        this._retenido = retenido;
    } //-- void setRetenido(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleRendFinancierosType
     */
    public static com.spirit.sri.at.DetalleRendFinancierosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleRendFinancierosType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleRendFinancierosType.class, reader);
    } //-- com.spirit.sri.at.DetalleRendFinancierosType unmarshal(java.io.Reader) 

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
