/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: FValorType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class FValorType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class FValorType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detallefValorList
     */
    private java.util.ArrayList _detallefValorList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FValorType() 
     {
        super();
        _detallefValorList = new ArrayList();
    } //-- com.spirit.sri.at.FValorType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetallefValor
     * 
     * 
     * 
     * @param vDetallefValor
     */
    public void addDetallefValor(com.spirit.sri.at.DetallefValor vDetallefValor)
        throws java.lang.IndexOutOfBoundsException
    {
        _detallefValorList.add(vDetallefValor);
    } //-- void addDetallefValor(com.spirit.sri.at.DetallefValor) 

    /**
     * Method addDetallefValor
     * 
     * 
     * 
     * @param index
     * @param vDetallefValor
     */
    public void addDetallefValor(int index, com.spirit.sri.at.DetallefValor vDetallefValor)
        throws java.lang.IndexOutOfBoundsException
    {
        _detallefValorList.add(index, vDetallefValor);
    } //-- void addDetallefValor(int, com.spirit.sri.at.DetallefValor) 

    /**
     * Method clearDetallefValor
     * 
     */
    public void clearDetallefValor()
    {
        _detallefValorList.clear();
    } //-- void clearDetallefValor() 

    /**
     * Method enumerateDetallefValor
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetallefValor()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detallefValorList.iterator());
    } //-- java.util.Enumeration enumerateDetallefValor() 

    /**
     * Method getDetallefValor
     * 
     * 
     * 
     * @param index
     * @return DetallefValor
     */
    public com.spirit.sri.at.DetallefValor getDetallefValor(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detallefValorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetallefValor) _detallefValorList.get(index);
    } //-- com.spirit.sri.at.DetallefValor getDetallefValor(int) 

    /**
     * Method getDetallefValor
     * 
     * 
     * 
     * @return DetallefValor
     */
    public com.spirit.sri.at.DetallefValor[] getDetallefValor()
    {
        int size = _detallefValorList.size();
        com.spirit.sri.at.DetallefValor[] mArray = new com.spirit.sri.at.DetallefValor[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetallefValor) _detallefValorList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetallefValor[] getDetallefValor() 

    /**
     * Method getDetallefValorCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetallefValorCount()
    {
        return _detallefValorList.size();
    } //-- int getDetallefValorCount() 

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
     * Method removeDetallefValor
     * 
     * 
     * 
     * @param vDetallefValor
     * @return boolean
     */
    public boolean removeDetallefValor(com.spirit.sri.at.DetallefValor vDetallefValor)
    {
        boolean removed = _detallefValorList.remove(vDetallefValor);
        return removed;
    } //-- boolean removeDetallefValor(com.spirit.sri.at.DetallefValor) 

    /**
     * Method setDetallefValor
     * 
     * 
     * 
     * @param index
     * @param vDetallefValor
     */
    public void setDetallefValor(int index, com.spirit.sri.at.DetallefValor vDetallefValor)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detallefValorList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detallefValorList.set(index, vDetallefValor);
    } //-- void setDetallefValor(int, com.spirit.sri.at.DetallefValor) 

    /**
     * Method setDetallefValor
     * 
     * 
     * 
     * @param detallefValorArray
     */
    public void setDetallefValor(com.spirit.sri.at.DetallefValor[] detallefValorArray)
    {
        //-- copy array
        _detallefValorList.clear();
        for (int i = 0; i < detallefValorArray.length; i++) {
            _detallefValorList.add(detallefValorArray[i]);
        }
    } //-- void setDetallefValor(com.spirit.sri.at.DetallefValor) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return FValorType
     */
    public static com.spirit.sri.at.FValorType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.FValorType) Unmarshaller.unmarshal(com.spirit.sri.at.FValorType.class, reader);
    } //-- com.spirit.sri.at.FValorType unmarshal(java.io.Reader) 

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
