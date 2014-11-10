/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetallefValorType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class DetallefValorType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetallefValorType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoFideicomiso
     */
    private int _tipoFideicomiso;

    /**
     * keeps track of state for field: _tipoFideicomiso
     */
    private boolean _has_tipoFideicomiso;

    /**
     * Field _totalF
     */
    private java.math.BigDecimal _totalF;

    /**
     * Field _individualF
     */
    private java.math.BigDecimal _individualF;

    /**
     * Field _porRetF
     */
    private java.math.BigDecimal _porRetF;

    /**
     * Field _valorRetF
     */
    private java.math.BigDecimal _valorRetF;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetallefValorType() 
     {
        super();
    } //-- com.spirit.sri.at.DetallefValorType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteTipoFideicomiso
     * 
     */
    public void deleteTipoFideicomiso()
    {
        this._has_tipoFideicomiso= false;
    } //-- void deleteTipoFideicomiso() 

    /**
     * Returns the value of field 'individualF'.
     * 
     * @return BigDecimal
     * @return the value of field 'individualF'.
     */
    public java.math.BigDecimal getIndividualF()
    {
        return this._individualF;
    } //-- java.math.BigDecimal getIndividualF() 

    /**
     * Returns the value of field 'porRetF'.
     * 
     * @return BigDecimal
     * @return the value of field 'porRetF'.
     */
    public java.math.BigDecimal getPorRetF()
    {
        return this._porRetF;
    } //-- java.math.BigDecimal getPorRetF() 

    /**
     * Returns the value of field 'tipoFideicomiso'.
     * 
     * @return int
     * @return the value of field 'tipoFideicomiso'.
     */
    public int getTipoFideicomiso()
    {
        return this._tipoFideicomiso;
    } //-- int getTipoFideicomiso() 

    /**
     * Returns the value of field 'totalF'.
     * 
     * @return BigDecimal
     * @return the value of field 'totalF'.
     */
    public java.math.BigDecimal getTotalF()
    {
        return this._totalF;
    } //-- java.math.BigDecimal getTotalF() 

    /**
     * Returns the value of field 'valorRetF'.
     * 
     * @return BigDecimal
     * @return the value of field 'valorRetF'.
     */
    public java.math.BigDecimal getValorRetF()
    {
        return this._valorRetF;
    } //-- java.math.BigDecimal getValorRetF() 

    /**
     * Method hasTipoFideicomiso
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTipoFideicomiso()
    {
        return this._has_tipoFideicomiso;
    } //-- boolean hasTipoFideicomiso() 

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
     * Sets the value of field 'individualF'.
     * 
     * @param individualF the value of field 'individualF'.
     */
    public void setIndividualF(java.math.BigDecimal individualF)
    {
        this._individualF = individualF;
    } //-- void setIndividualF(java.math.BigDecimal) 

    /**
     * Sets the value of field 'porRetF'.
     * 
     * @param porRetF the value of field 'porRetF'.
     */
    public void setPorRetF(java.math.BigDecimal porRetF)
    {
        this._porRetF = porRetF;
    } //-- void setPorRetF(java.math.BigDecimal) 

    /**
     * Sets the value of field 'tipoFideicomiso'.
     * 
     * @param tipoFideicomiso the value of field 'tipoFideicomiso'.
     */
    public void setTipoFideicomiso(int tipoFideicomiso)
    {
        this._tipoFideicomiso = tipoFideicomiso;
        this._has_tipoFideicomiso = true;
    } //-- void setTipoFideicomiso(int) 

    /**
     * Sets the value of field 'totalF'.
     * 
     * @param totalF the value of field 'totalF'.
     */
    public void setTotalF(java.math.BigDecimal totalF)
    {
        this._totalF = totalF;
    } //-- void setTotalF(java.math.BigDecimal) 

    /**
     * Sets the value of field 'valorRetF'.
     * 
     * @param valorRetF the value of field 'valorRetF'.
     */
    public void setValorRetF(java.math.BigDecimal valorRetF)
    {
        this._valorRetF = valorRetF;
    } //-- void setValorRetF(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return DetallefValorType
     */
    public static com.spirit.sri.at.DetallefValorType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.DetallefValorType) Unmarshaller.unmarshal(com.spirit.sri.at.DetallefValorType.class, reader);
    } //-- com.spirit.sri.at.DetallefValorType unmarshal(java.io.Reader) 

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
