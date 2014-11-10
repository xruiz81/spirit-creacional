/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: RecapType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class RecapType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class RecapType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleRecapList
     */
    private java.util.ArrayList _detalleRecapList;


      //----------------/
     //- Constructors -/
    //----------------/

    public RecapType() 
     {
        super();
        _detalleRecapList = new ArrayList();
    } //-- com.spirit.sri.at.RecapType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleRecap
     * 
     * 
     * 
     * @param vDetalleRecap
     */
    public void addDetalleRecap(com.spirit.sri.at.DetalleRecap vDetalleRecap)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleRecapList.add(vDetalleRecap);
    } //-- void addDetalleRecap(com.spirit.sri.at.DetalleRecap) 

    /**
     * Method addDetalleRecap
     * 
     * 
     * 
     * @param index
     * @param vDetalleRecap
     */
    public void addDetalleRecap(int index, com.spirit.sri.at.DetalleRecap vDetalleRecap)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleRecapList.add(index, vDetalleRecap);
    } //-- void addDetalleRecap(int, com.spirit.sri.at.DetalleRecap) 

    /**
     * Method clearDetalleRecap
     * 
     */
    public void clearDetalleRecap()
    {
        _detalleRecapList.clear();
    } //-- void clearDetalleRecap() 

    /**
     * Method enumerateDetalleRecap
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleRecap()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleRecapList.iterator());
    } //-- java.util.Enumeration enumerateDetalleRecap() 

    /**
     * Method getDetalleRecap
     * 
     * 
     * 
     * @param index
     * @return DetalleRecap
     */
    public com.spirit.sri.at.DetalleRecap getDetalleRecap(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleRecapList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleRecap) _detalleRecapList.get(index);
    } //-- com.spirit.sri.at.DetalleRecap getDetalleRecap(int) 

    /**
     * Method getDetalleRecap
     * 
     * 
     * 
     * @return DetalleRecap
     */
    public com.spirit.sri.at.DetalleRecap[] getDetalleRecap()
    {
        int size = _detalleRecapList.size();
        com.spirit.sri.at.DetalleRecap[] mArray = new com.spirit.sri.at.DetalleRecap[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleRecap) _detalleRecapList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleRecap[] getDetalleRecap() 

    /**
     * Method getDetalleRecapCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleRecapCount()
    {
        return _detalleRecapList.size();
    } //-- int getDetalleRecapCount() 

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
     * Method removeDetalleRecap
     * 
     * 
     * 
     * @param vDetalleRecap
     * @return boolean
     */
    public boolean removeDetalleRecap(com.spirit.sri.at.DetalleRecap vDetalleRecap)
    {
        boolean removed = _detalleRecapList.remove(vDetalleRecap);
        return removed;
    } //-- boolean removeDetalleRecap(com.spirit.sri.at.DetalleRecap) 

    /**
     * Method setDetalleRecap
     * 
     * 
     * 
     * @param index
     * @param vDetalleRecap
     */
    public void setDetalleRecap(int index, com.spirit.sri.at.DetalleRecap vDetalleRecap)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleRecapList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleRecapList.set(index, vDetalleRecap);
    } //-- void setDetalleRecap(int, com.spirit.sri.at.DetalleRecap) 

    /**
     * Method setDetalleRecap
     * 
     * 
     * 
     * @param detalleRecapArray
     */
    public void setDetalleRecap(com.spirit.sri.at.DetalleRecap[] detalleRecapArray)
    {
        //-- copy array
        _detalleRecapList.clear();
        for (int i = 0; i < detalleRecapArray.length; i++) {
            _detalleRecapList.add(detalleRecapArray[i]);
        }
    } //-- void setDetalleRecap(com.spirit.sri.at.DetalleRecap) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return RecapType
     */
    public static com.spirit.sri.at.RecapType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.RecapType) Unmarshaller.unmarshal(com.spirit.sri.at.RecapType.class, reader);
    } //-- com.spirit.sri.at.RecapType unmarshal(java.io.Reader) 

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
