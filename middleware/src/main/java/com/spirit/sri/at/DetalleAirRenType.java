/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleAirRenType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetalleAirRenType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleAirRenType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codRetAir
     */
    private java.lang.String _codRetAir;

    /**
     * Field _deposito
     */
    private java.math.BigDecimal _deposito;

    /**
     * Field _baseImpAir
     */
    private java.math.BigDecimal _baseImpAir;

    /**
     * Field _porcentajeAir
     */
    private java.math.BigDecimal _porcentajeAir;

    /**
     * Field _valRetAir
     */
    private java.math.BigDecimal _valRetAir;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleAirRenType() 
     {
        super();
    } //-- com.spirit.sri.at.DetalleAirRenType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'baseImpAir'.
     * 
     * @return BigDecimal
     * @return the value of field 'baseImpAir'.
     */
    public java.math.BigDecimal getBaseImpAir()
    {
        return this._baseImpAir;
    } //-- java.math.BigDecimal getBaseImpAir() 

    /**
     * Returns the value of field 'codRetAir'.
     * 
     * @return String
     * @return the value of field 'codRetAir'.
     */
    public java.lang.String getCodRetAir()
    {
        return this._codRetAir;
    } //-- java.lang.String getCodRetAir() 

    /**
     * Returns the value of field 'deposito'.
     * 
     * @return BigDecimal
     * @return the value of field 'deposito'.
     */
    public java.math.BigDecimal getDeposito()
    {
        return this._deposito;
    } //-- java.math.BigDecimal getDeposito() 

    /**
     * Returns the value of field 'porcentajeAir'.
     * 
     * @return BigDecimal
     * @return the value of field 'porcentajeAir'.
     */
    public java.math.BigDecimal getPorcentajeAir()
    {
        return this._porcentajeAir;
    } //-- java.math.BigDecimal getPorcentajeAir() 

    /**
     * Returns the value of field 'valRetAir'.
     * 
     * @return BigDecimal
     * @return the value of field 'valRetAir'.
     */
    public java.math.BigDecimal getValRetAir()
    {
        return this._valRetAir;
    } //-- java.math.BigDecimal getValRetAir() 

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
     * Sets the value of field 'baseImpAir'.
     * 
     * @param baseImpAir the value of field 'baseImpAir'.
     */
    public void setBaseImpAir(java.math.BigDecimal baseImpAir)
    {
        this._baseImpAir = baseImpAir;
    } //-- void setBaseImpAir(java.math.BigDecimal) 

    /**
     * Sets the value of field 'codRetAir'.
     * 
     * @param codRetAir the value of field 'codRetAir'.
     */
    public void setCodRetAir(java.lang.String codRetAir)
    {
        this._codRetAir = codRetAir;
    } //-- void setCodRetAir(java.lang.String) 

    /**
     * Sets the value of field 'deposito'.
     * 
     * @param deposito the value of field 'deposito'.
     */
    public void setDeposito(java.math.BigDecimal deposito)
    {
        this._deposito = deposito;
    } //-- void setDeposito(java.math.BigDecimal) 

    /**
     * Sets the value of field 'porcentajeAir'.
     * 
     * @param porcentajeAir the value of field 'porcentajeAir'.
     */
    public void setPorcentajeAir(java.math.BigDecimal porcentajeAir)
    {
        this._porcentajeAir = porcentajeAir;
    } //-- void setPorcentajeAir(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valRetAir'.
     * 
     * @param valRetAir the value of field 'valRetAir'.
     */
    public void setValRetAir(java.math.BigDecimal valRetAir)
    {
        this._valRetAir = valRetAir;
    } //-- void setValRetAir(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetalleAirRenType
     */
    public static com.spirit.sri.at.DetalleAirRenType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetalleAirRenType) Unmarshaller.unmarshal(com.spirit.sri.at.DetalleAirRenType.class, reader);
    } //-- com.spirit.sri.at.DetalleAirRenType unmarshal(java.io.Reader) 

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
