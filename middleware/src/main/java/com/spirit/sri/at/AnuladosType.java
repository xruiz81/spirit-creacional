/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: AnuladosType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class AnuladosType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class AnuladosType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleAnuladosList
     */
    private java.util.ArrayList _detalleAnuladosList;


      //----------------/
     //- Constructors -/
    //----------------/

    public AnuladosType() 
     {
        super();
        _detalleAnuladosList = new ArrayList();
    } //-- com.spirit.sri.at.AnuladosType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleAnulados
     * 
     * 
     * 
     * @param vDetalleAnulados
     */
    public void addDetalleAnulados(com.spirit.sri.at.DetalleAnulados vDetalleAnulados)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAnuladosList.add(vDetalleAnulados);
    } //-- void addDetalleAnulados(com.spirit.sri.at.DetalleAnulados) 

    /**
     * Method addDetalleAnulados
     * 
     * 
     * 
     * @param index
     * @param vDetalleAnulados
     */
    public void addDetalleAnulados(int index, com.spirit.sri.at.DetalleAnulados vDetalleAnulados)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleAnuladosList.add(index, vDetalleAnulados);
    } //-- void addDetalleAnulados(int, com.spirit.sri.at.DetalleAnulados) 

    /**
     * Method clearDetalleAnulados
     * 
     */
    public void clearDetalleAnulados()
    {
        _detalleAnuladosList.clear();
    } //-- void clearDetalleAnulados() 

    /**
     * Method enumerateDetalleAnulados
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleAnulados()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleAnuladosList.iterator());
    } //-- java.util.Enumeration enumerateDetalleAnulados() 

    /**
     * Method getDetalleAnulados
     * 
     * 
     * 
     * @param index
     * @return DetalleAnulados
     */
    public com.spirit.sri.at.DetalleAnulados getDetalleAnulados(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAnuladosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleAnulados) _detalleAnuladosList.get(index);
    } //-- com.spirit.sri.at.DetalleAnulados getDetalleAnulados(int) 

    /**
     * Method getDetalleAnulados
     * 
     * 
     * 
     * @return DetalleAnulados
     */
    public com.spirit.sri.at.DetalleAnulados[] getDetalleAnulados()
    {
        int size = _detalleAnuladosList.size();
        com.spirit.sri.at.DetalleAnulados[] mArray = new com.spirit.sri.at.DetalleAnulados[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleAnulados) _detalleAnuladosList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleAnulados[] getDetalleAnulados() 

    /**
     * Method getDetalleAnuladosCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleAnuladosCount()
    {
        return _detalleAnuladosList.size();
    } //-- int getDetalleAnuladosCount() 

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
     * Method removeDetalleAnulados
     * 
     * 
     * 
     * @param vDetalleAnulados
     * @return boolean
     */
    public boolean removeDetalleAnulados(com.spirit.sri.at.DetalleAnulados vDetalleAnulados)
    {
        boolean removed = _detalleAnuladosList.remove(vDetalleAnulados);
        return removed;
    } //-- boolean removeDetalleAnulados(com.spirit.sri.at.DetalleAnulados) 

    /**
     * Method setDetalleAnulados
     * 
     * 
     * 
     * @param index
     * @param vDetalleAnulados
     */
    public void setDetalleAnulados(int index, com.spirit.sri.at.DetalleAnulados vDetalleAnulados)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleAnuladosList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleAnuladosList.set(index, vDetalleAnulados);
    } //-- void setDetalleAnulados(int, com.spirit.sri.at.DetalleAnulados) 

    /**
     * Method setDetalleAnulados
     * 
     * 
     * 
     * @param detalleAnuladosArray
     */
    public void setDetalleAnulados(com.spirit.sri.at.DetalleAnulados[] detalleAnuladosArray)
    {
        //-- copy array
        _detalleAnuladosList.clear();
        for (int i = 0; i < detalleAnuladosArray.length; i++) {
            _detalleAnuladosList.add(detalleAnuladosArray[i]);
        }
    } //-- void setDetalleAnulados(com.spirit.sri.at.DetalleAnulados) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return AnuladosType
     */
    public static com.spirit.sri.at.AnuladosType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.AnuladosType) Unmarshaller.unmarshal(com.spirit.sri.at.AnuladosType.class, reader);
    } //-- com.spirit.sri.at.AnuladosType unmarshal(java.io.Reader) 

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
