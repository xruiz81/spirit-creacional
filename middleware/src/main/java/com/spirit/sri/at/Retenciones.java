/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: Retenciones.java,v 1.1 2014/03/28 18:06:36 xrf Exp $
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
 * Class Retenciones.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:36 $
 */
public class Retenciones implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detRetList
     */
    private java.util.ArrayList _detRetList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Retenciones() 
     {
        super();
        _detRetList = new ArrayList();
    } //-- com.spirit.sri.at.Retenciones()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetRet
     * 
     * 
     * 
     * @param vDetRet
     */
    public void addDetRet(com.spirit.sri.at.DetRet vDetRet)
        throws java.lang.IndexOutOfBoundsException
    {
        _detRetList.add(vDetRet);
    } //-- void addDetRet(com.spirit.sri.at.DetRet) 

    /**
     * Method addDetRet
     * 
     * 
     * 
     * @param index
     * @param vDetRet
     */
    public void addDetRet(int index, com.spirit.sri.at.DetRet vDetRet)
        throws java.lang.IndexOutOfBoundsException
    {
        _detRetList.add(index, vDetRet);
    } //-- void addDetRet(int, com.spirit.sri.at.DetRet) 

    /**
     * Method clearDetRet
     * 
     */
    public void clearDetRet()
    {
        _detRetList.clear();
    } //-- void clearDetRet() 

    /**
     * Method enumerateDetRet
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetRet()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detRetList.iterator());
    } //-- java.util.Enumeration enumerateDetRet() 

    /**
     * Method getDetRet
     * 
     * 
     * 
     * @param index
     * @return DetRet
     */
    public com.spirit.sri.at.DetRet getDetRet(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detRetList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetRet) _detRetList.get(index);
    } //-- com.spirit.sri.at.DetRet getDetRet(int) 

    /**
     * Method getDetRet
     * 
     * 
     * 
     * @return DetRet
     */
    public com.spirit.sri.at.DetRet[] getDetRet()
    {
        int size = _detRetList.size();
        com.spirit.sri.at.DetRet[] mArray = new com.spirit.sri.at.DetRet[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetRet) _detRetList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetRet[] getDetRet() 

    /**
     * Method getDetRetCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetRetCount()
    {
        return _detRetList.size();
    } //-- int getDetRetCount() 

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
     * Method removeDetRet
     * 
     * 
     * 
     * @param vDetRet
     * @return boolean
     */
    public boolean removeDetRet(com.spirit.sri.at.DetRet vDetRet)
    {
        boolean removed = _detRetList.remove(vDetRet);
        return removed;
    } //-- boolean removeDetRet(com.spirit.sri.at.DetRet) 

    /**
     * Method setDetRet
     * 
     * 
     * 
     * @param index
     * @param vDetRet
     */
    public void setDetRet(int index, com.spirit.sri.at.DetRet vDetRet)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detRetList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detRetList.set(index, vDetRet);
    } //-- void setDetRet(int, com.spirit.sri.at.DetRet) 

    /**
     * Method setDetRet
     * 
     * 
     * 
     * @param detRetArray
     */
    public void setDetRet(com.spirit.sri.at.DetRet[] detRetArray)
    {
        //-- copy array
        _detRetList.clear();
        for (int i = 0; i < detRetArray.length; i++) {
            _detRetList.add(detRetArray[i]);
        }
    } //-- void setDetRet(com.spirit.sri.at.DetRet) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Retenciones
     */
    public static com.spirit.sri.at.Retenciones unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.Retenciones) Unmarshaller.unmarshal(com.spirit.sri.at.Retenciones.class, reader);
    } //-- com.spirit.sri.at.Retenciones unmarshal(java.io.Reader) 

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
