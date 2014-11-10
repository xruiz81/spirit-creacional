/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: AhorroPN.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class AhorroPN.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class AhorroPN implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _totalDep
     */
    private java.math.BigDecimal _totalDep;

    /**
     * Field _rendGen
     */
    private java.math.BigDecimal _rendGen;


      //----------------/
     //- Constructors -/
    //----------------/

    public AhorroPN() 
     {
        super();
    } //-- com.spirit.sri.at.AhorroPN()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'rendGen'.
     * 
     * @return BigDecimal
     * @return the value of field 'rendGen'.
     */
    public java.math.BigDecimal getRendGen()
    {
        return this._rendGen;
    } //-- java.math.BigDecimal getRendGen() 

    /**
     * Returns the value of field 'totalDep'.
     * 
     * @return BigDecimal
     * @return the value of field 'totalDep'.
     */
    public java.math.BigDecimal getTotalDep()
    {
        return this._totalDep;
    } //-- java.math.BigDecimal getTotalDep() 

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
     * Sets the value of field 'rendGen'.
     * 
     * @param rendGen the value of field 'rendGen'.
     */
    public void setRendGen(java.math.BigDecimal rendGen)
    {
        this._rendGen = rendGen;
    } //-- void setRendGen(java.math.BigDecimal) 

    /**
     * Sets the value of field 'totalDep'.
     * 
     * @param totalDep the value of field 'totalDep'.
     */
    public void setTotalDep(java.math.BigDecimal totalDep)
    {
        this._totalDep = totalDep;
    } //-- void setTotalDep(java.math.BigDecimal) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return AhorroPN
     */
    public static com.spirit.sri.at.AhorroPN unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.AhorroPN) Unmarshaller.unmarshal(com.spirit.sri.at.AhorroPN.class, reader);
    } //-- com.spirit.sri.at.AhorroPN unmarshal(java.io.Reader) 

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
