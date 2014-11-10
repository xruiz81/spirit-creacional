/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: RendFinancierosType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class RendFinancierosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class RendFinancierosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleRendFinancierosList
     */
    private java.util.ArrayList _detalleRendFinancierosList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RendFinancierosType() 
     {
        super();
        _detalleRendFinancierosList = new ArrayList();
    } //-- com.spirit.sri.at.RendFinancierosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleRendFinancieros
     * 
     * 
     * 
     * @param vDetalleRendFinancieros
     */
    public void addDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros vDetalleRendFinancieros)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleRendFinancierosList.add(vDetalleRendFinancieros);
    } //-- void addDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros) 

    /**
     * Method addDetalleRendFinancieros
     * 
     * 
     * 
     * @param index
     * @param vDetalleRendFinancieros
     */
    public void addDetalleRendFinancieros(int index, com.spirit.sri.at.DetalleRendFinancieros vDetalleRendFinancieros)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleRendFinancierosList.add(index, vDetalleRendFinancieros);
    } //-- void addDetalleRendFinancieros(int, com.spirit.sri.at.DetalleRendFinancieros) 

    /**
     * Method clearDetalleRendFinancieros
     * 
     */
    public void clearDetalleRendFinancieros()
    {
        _detalleRendFinancierosList.clear();
    } //-- void clearDetalleRendFinancieros() 

    /**
     * Method enumerateDetalleRendFinancieros
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleRendFinancieros()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleRendFinancierosList.iterator());
    } //-- java.util.Enumeration enumerateDetalleRendFinancieros() 

    /**
     * Method getDetalleRendFinancieros
     * 
     * 
     * 
     * @param index
     * @return DetalleRendFinancieros
     */
    public com.spirit.sri.at.DetalleRendFinancieros getDetalleRendFinancieros(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleRendFinancierosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleRendFinancieros) _detalleRendFinancierosList.get(index);
    } //-- com.spirit.sri.at.DetalleRendFinancieros getDetalleRendFinancieros(int) 

    /**
     * Method getDetalleRendFinancieros
     * 
     * 
     * 
     * @return DetalleRendFinancieros
     */
    public com.spirit.sri.at.DetalleRendFinancieros[] getDetalleRendFinancieros()
    {
        int size = _detalleRendFinancierosList.size();
        com.spirit.sri.at.DetalleRendFinancieros[] mArray = new com.spirit.sri.at.DetalleRendFinancieros[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleRendFinancieros) _detalleRendFinancierosList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleRendFinancieros[] getDetalleRendFinancieros() 

    /**
     * Method getDetalleRendFinancierosCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleRendFinancierosCount()
    {
        return _detalleRendFinancierosList.size();
    } //-- int getDetalleRendFinancierosCount() 

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
     * Method removeDetalleRendFinancieros
     * 
     * 
     * 
     * @param vDetalleRendFinancieros
     * @return boolean
     */
    public boolean removeDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros vDetalleRendFinancieros)
    {
        boolean removed = _detalleRendFinancierosList.remove(vDetalleRendFinancieros);
        return removed;
    } //-- boolean removeDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros) 

    /**
     * Method setDetalleRendFinancieros
     * 
     * 
     * 
     * @param index
     * @param vDetalleRendFinancieros
     */
    public void setDetalleRendFinancieros(int index, com.spirit.sri.at.DetalleRendFinancieros vDetalleRendFinancieros)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleRendFinancierosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleRendFinancierosList.set(index, vDetalleRendFinancieros);
    } //-- void setDetalleRendFinancieros(int, com.spirit.sri.at.DetalleRendFinancieros) 

    /**
     * Method setDetalleRendFinancieros
     * 
     * 
     * 
     * @param detalleRendFinancierosArray
     */
    public void setDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros[] detalleRendFinancierosArray)
    {
        //-- copy array
        _detalleRendFinancierosList.clear();
        for (int i = 0; i < detalleRendFinancierosArray.length; i++) {
            _detalleRendFinancierosList.add(detalleRendFinancierosArray[i]);
        }
    } //-- void setDetalleRendFinancieros(com.spirit.sri.at.DetalleRendFinancieros) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return RendFinancierosType
     */
    public static com.spirit.sri.at.RendFinancierosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.RendFinancierosType) Unmarshaller.unmarshal(com.spirit.sri.at.RendFinancierosType.class, reader);
    } //-- com.spirit.sri.at.RendFinancierosType unmarshal(java.io.Reader) 

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
