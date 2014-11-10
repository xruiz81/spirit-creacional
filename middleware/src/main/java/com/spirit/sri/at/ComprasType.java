/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: ComprasType.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
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
 * Class ComprasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class ComprasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleComprasList
     */
    private java.util.ArrayList _detalleComprasList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ComprasType() 
     {
        super();
        _detalleComprasList = new ArrayList();
    } //-- com.spirit.sri.at.ComprasType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addDetalleCompras
     * 
     * 
     * 
     * @param vDetalleCompras
     */
    public void addDetalleCompras(com.spirit.sri.at.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleComprasList.add(vDetalleCompras);
    } //-- void addDetalleCompras(com.spirit.sri.at.DetalleCompras) 

    /**
     * Method addDetalleCompras
     * 
     * 
     * 
     * @param index
     * @param vDetalleCompras
     */
    public void addDetalleCompras(int index, com.spirit.sri.at.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleComprasList.add(index, vDetalleCompras);
    } //-- void addDetalleCompras(int, com.spirit.sri.at.DetalleCompras) 

    /**
     * Method clearDetalleCompras
     * 
     */
    public void clearDetalleCompras()
    {
        _detalleComprasList.clear();
    } //-- void clearDetalleCompras() 

    /**
     * Method enumerateDetalleCompras
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleCompras()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_detalleComprasList.iterator());
    } //-- java.util.Enumeration enumerateDetalleCompras() 

    /**
     * Method getDetalleCompras
     * 
     * 
     * 
     * @param index
     * @return DetalleCompras
     */
    public com.spirit.sri.at.DetalleCompras getDetalleCompras(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleComprasList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (com.spirit.sri.at.DetalleCompras) _detalleComprasList.get(index);
    } //-- com.spirit.sri.at.DetalleCompras getDetalleCompras(int) 

    /**
     * Method getDetalleCompras
     * 
     * 
     * 
     * @return DetalleCompras
     */
    public com.spirit.sri.at.DetalleCompras[] getDetalleCompras()
    {
        int size = _detalleComprasList.size();
        com.spirit.sri.at.DetalleCompras[] mArray = new com.spirit.sri.at.DetalleCompras[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.at.DetalleCompras) _detalleComprasList.get(index);
        }
        return mArray;
    } //-- com.spirit.sri.at.DetalleCompras[] getDetalleCompras() 

    /**
     * Method getDetalleComprasCount
     * 
     * 
     * 
     * @return int
     */
    public int getDetalleComprasCount()
    {
        return _detalleComprasList.size();
    } //-- int getDetalleComprasCount() 

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
     * Method removeDetalleCompras
     * 
     * 
     * 
     * @param vDetalleCompras
     * @return boolean
     */
    public boolean removeDetalleCompras(com.spirit.sri.at.DetalleCompras vDetalleCompras)
    {
        boolean removed = _detalleComprasList.remove(vDetalleCompras);
        return removed;
    } //-- boolean removeDetalleCompras(com.spirit.sri.at.DetalleCompras) 

    /**
     * Method setDetalleCompras
     * 
     * 
     * 
     * @param index
     * @param vDetalleCompras
     */
    public void setDetalleCompras(int index, com.spirit.sri.at.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleComprasList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _detalleComprasList.set(index, vDetalleCompras);
    } //-- void setDetalleCompras(int, com.spirit.sri.at.DetalleCompras) 

    /**
     * Method setDetalleCompras
     * 
     * 
     * 
     * @param detalleComprasArray
     */
    public void setDetalleCompras(com.spirit.sri.at.DetalleCompras[] detalleComprasArray)
    {
        //-- copy array
        _detalleComprasList.clear();
        for (int i = 0; i < detalleComprasArray.length; i++) {
            _detalleComprasList.add(detalleComprasArray[i]);
        }
    } //-- void setDetalleCompras(com.spirit.sri.at.DetalleCompras) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ComprasType
     */
    public static com.spirit.sri.at.ComprasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.at.ComprasType) Unmarshaller.unmarshal(com.spirit.sri.at.ComprasType.class, reader);
    } //-- com.spirit.sri.at.ComprasType unmarshal(java.io.Reader) 

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
