/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: ComprasType.java,v 1.1 2014/03/28 18:06:47 xrf Exp $
 */

package com.spirit.sri.reoc;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class ComprasType.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:47 $
 */
public class ComprasType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _detalleComprasList
     */
    private java.util.Vector _detalleComprasList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ComprasType() 
     {
        super();
        _detalleComprasList = new Vector();
    } //-- com.spirit.sri.reoc.ComprasType()


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
    public void addDetalleCompras(com.spirit.sri.reoc.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleComprasList.addElement(vDetalleCompras);
    } //-- void addDetalleCompras(com.spirit.sri.reoc.DetalleCompras) 

    /**
     * Method addDetalleCompras
     * 
     * 
     * 
     * @param index
     * @param vDetalleCompras
     */
    public void addDetalleCompras(int index, com.spirit.sri.reoc.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        _detalleComprasList.insertElementAt(vDetalleCompras, index);
    } //-- void addDetalleCompras(int, com.spirit.sri.reoc.DetalleCompras) 

    /**
     * Method enumerateDetalleCompras
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateDetalleCompras()
    {
        return _detalleComprasList.elements();
    } //-- java.util.Enumeration enumerateDetalleCompras() 

    /**
     * Method getDetalleCompras
     * 
     * 
     * 
     * @param index
     * @return DetalleCompras
     */
    public com.spirit.sri.reoc.DetalleCompras getDetalleCompras(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleComprasList.size())) {
            throw new IndexOutOfBoundsException("getDetalleCompras: Index value '"+index+"' not in range [0.."+(_detalleComprasList.size() - 1) + "]");
        }
        
        return (com.spirit.sri.reoc.DetalleCompras) _detalleComprasList.elementAt(index);
    } //-- com.spirit.sri.reoc.DetalleCompras getDetalleCompras(int) 

    /**
     * Method getDetalleCompras
     * 
     * 
     * 
     * @return DetalleCompras
     */
    public com.spirit.sri.reoc.DetalleCompras[] getDetalleCompras()
    {
        int size = _detalleComprasList.size();
        com.spirit.sri.reoc.DetalleCompras[] mArray = new com.spirit.sri.reoc.DetalleCompras[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (com.spirit.sri.reoc.DetalleCompras) _detalleComprasList.elementAt(index);
        }
        return mArray;
    } //-- com.spirit.sri.reoc.DetalleCompras[] getDetalleCompras() 

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
     * Method removeAllDetalleCompras
     * 
     */
    public void removeAllDetalleCompras()
    {
        _detalleComprasList.removeAllElements();
    } //-- void removeAllDetalleCompras() 

    /**
     * Method removeDetalleCompras
     * 
     * 
     * 
     * @param index
     * @return DetalleCompras
     */
    public com.spirit.sri.reoc.DetalleCompras removeDetalleCompras(int index)
    {
        java.lang.Object obj = _detalleComprasList.elementAt(index);
        _detalleComprasList.removeElementAt(index);
        return (com.spirit.sri.reoc.DetalleCompras) obj;
    } //-- com.spirit.sri.reoc.DetalleCompras removeDetalleCompras(int) 

    /**
     * Method setDetalleCompras
     * 
     * 
     * 
     * @param index
     * @param vDetalleCompras
     */
    public void setDetalleCompras(int index, com.spirit.sri.reoc.DetalleCompras vDetalleCompras)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _detalleComprasList.size())) {
            throw new IndexOutOfBoundsException("setDetalleCompras: Index value '"+index+"' not in range [0.." + (_detalleComprasList.size() - 1) + "]");
        }
        _detalleComprasList.setElementAt(vDetalleCompras, index);
    } //-- void setDetalleCompras(int, com.spirit.sri.reoc.DetalleCompras) 

    /**
     * Method setDetalleCompras
     * 
     * 
     * 
     * @param detalleComprasArray
     */
    public void setDetalleCompras(com.spirit.sri.reoc.DetalleCompras[] detalleComprasArray)
    {
        //-- copy array
        _detalleComprasList.removeAllElements();
        for (int i = 0; i < detalleComprasArray.length; i++) {
            _detalleComprasList.addElement(detalleComprasArray[i]);
        }
    } //-- void setDetalleCompras(com.spirit.sri.reoc.DetalleCompras) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return ComprasType
     */
    public static com.spirit.sri.reoc.ComprasType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (com.spirit.sri.reoc.ComprasType) Unmarshaller.unmarshal(com.spirit.sri.reoc.ComprasType.class, reader);
    } //-- com.spirit.sri.reoc.ComprasType unmarshal(java.io.Reader) 

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
