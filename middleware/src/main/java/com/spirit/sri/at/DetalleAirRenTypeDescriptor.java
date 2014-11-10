/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: DetalleAirRenTypeDescriptor.java,v 1.1 2014/03/28 18:06:35 xrf Exp $
 */

package com.spirit.sri.at;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.mapping.AccessMode;
import org.exolab.castor.xml.TypeValidator;
import org.exolab.castor.xml.XMLFieldDescriptor;
import org.exolab.castor.xml.validators.*;

/**
 * Class DetalleAirRenTypeDescriptor.
 * 
 * @version $Revision: 1.1 $ $Date: 2014/03/28 18:06:35 $
 */
public class DetalleAirRenTypeDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field elementDefinition
     */
    private boolean elementDefinition;

    /**
     * Field nsPrefix
     */
    private java.lang.String nsPrefix;

    /**
     * Field nsURI
     */
    private java.lang.String nsURI;

    /**
     * Field xmlName
     */
    private java.lang.String xmlName;

    /**
     * Field identity
     */
    private org.exolab.castor.xml.XMLFieldDescriptor identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public DetalleAirRenTypeDescriptor() 
     {
        super();
        xmlName = "detalleAirRenType";
        elementDefinition = false;
        
        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors
        
        //-- initialize element descriptors
        
        //-- _codRetAir
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_codRetAir", "codRetAir", org.exolab.castor.xml.NodeType.Element);
        desc.setImmutable(true);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DetalleAirRenType target = (DetalleAirRenType) object;
                return target.getCodRetAir();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DetalleAirRenType target = (DetalleAirRenType) object;
                    target.setCodRetAir( (java.lang.String) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return null;
            }
        };
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _codRetAir
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            StringValidator typeValidator = new StringValidator();
            typeValidator.setMinLength(3);
            typeValidator.setMaxLength(5);
            typeValidator.setWhiteSpace("preserve");
            typeValidator.setPattern("[A-Za-z0-9]*");
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- _deposito
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.math.BigDecimal.class, "_deposito", "deposito", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DetalleAirRenType target = (DetalleAirRenType) object;
                return target.getDeposito();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DetalleAirRenType target = (DetalleAirRenType) object;
                    target.setDeposito( (java.math.BigDecimal) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new java.math.BigDecimal(0);
            }
        };
        desc.setImmutable(true);
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _deposito
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            DecimalValidator typeValidator = new DecimalValidator();
            typeValidator.setMinInclusive(new java.math.BigDecimal("0"));
            typeValidator.setMaxInclusive(new java.math.BigDecimal("9999999999.99"));
            typeValidator.setTotalDigits(12);
            typeValidator.setFractionDigits(2);
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- _baseImpAir
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.math.BigDecimal.class, "_baseImpAir", "baseImpAir", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DetalleAirRenType target = (DetalleAirRenType) object;
                return target.getBaseImpAir();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DetalleAirRenType target = (DetalleAirRenType) object;
                    target.setBaseImpAir( (java.math.BigDecimal) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new java.math.BigDecimal(0);
            }
        };
        desc.setImmutable(true);
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _baseImpAir
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            DecimalValidator typeValidator = new DecimalValidator();
            typeValidator.setMinInclusive(new java.math.BigDecimal("0"));
            typeValidator.setMaxInclusive(new java.math.BigDecimal("9999999999.99"));
            typeValidator.setTotalDigits(12);
            typeValidator.setFractionDigits(2);
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- _porcentajeAir
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.math.BigDecimal.class, "_porcentajeAir", "porcentajeAir", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DetalleAirRenType target = (DetalleAirRenType) object;
                return target.getPorcentajeAir();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DetalleAirRenType target = (DetalleAirRenType) object;
                    target.setPorcentajeAir( (java.math.BigDecimal) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new java.math.BigDecimal(0);
            }
        };
        desc.setImmutable(true);
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _porcentajeAir
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            DecimalValidator typeValidator = new DecimalValidator();
            typeValidator.setMinInclusive(new java.math.BigDecimal("0"));
            typeValidator.setMaxInclusive(new java.math.BigDecimal("100"));
            typeValidator.setTotalDigits(5);
            typeValidator.setFractionDigits(2);
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
        //-- _valRetAir
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.math.BigDecimal.class, "_valRetAir", "valRetAir", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                DetalleAirRenType target = (DetalleAirRenType) object;
                return target.getValRetAir();
            }
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    DetalleAirRenType target = (DetalleAirRenType) object;
                    target.setValRetAir( (java.math.BigDecimal) value);
                }
                catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            public java.lang.Object newInstance( java.lang.Object parent ) {
                return new java.math.BigDecimal(0);
            }
        };
        desc.setImmutable(true);
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        
        //-- validation code for: _valRetAir
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            DecimalValidator typeValidator = new DecimalValidator();
            typeValidator.setMinInclusive(new java.math.BigDecimal("0"));
            typeValidator.setMaxInclusive(new java.math.BigDecimal("9999999999.99"));
            typeValidator.setTotalDigits(12);
            typeValidator.setFractionDigits(2);
            fieldValidator.setValidator(typeValidator);
        }
        desc.setValidator(fieldValidator);
    } //-- com.spirit.sri.at.DetalleAirRenTypeDescriptor()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode
     * 
     * 
     * 
     * @return AccessMode
     */
    public org.exolab.castor.mapping.AccessMode getAccessMode()
    {
        return null;
    } //-- org.exolab.castor.mapping.AccessMode getAccessMode() 

    /**
     * Method getExtends
     * 
     * 
     * 
     * @return ClassDescriptor
     */
    public org.exolab.castor.mapping.ClassDescriptor getExtends()
    {
        return null;
    } //-- org.exolab.castor.mapping.ClassDescriptor getExtends() 

    /**
     * Method getIdentity
     * 
     * 
     * 
     * @return FieldDescriptor
     */
    public org.exolab.castor.mapping.FieldDescriptor getIdentity()
    {
        return identity;
    } //-- org.exolab.castor.mapping.FieldDescriptor getIdentity() 

    /**
     * Method getJavaClass
     * 
     * 
     * 
     * @return Class
     */
    public java.lang.Class getJavaClass()
    {
        return com.spirit.sri.at.DetalleAirRenType.class;
    } //-- java.lang.Class getJavaClass() 

    /**
     * Method getNameSpacePrefix
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String getNameSpacePrefix()
    {
        return nsPrefix;
    } //-- java.lang.String getNameSpacePrefix() 

    /**
     * Method getNameSpaceURI
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String getNameSpaceURI()
    {
        return nsURI;
    } //-- java.lang.String getNameSpaceURI() 

    /**
     * Method getValidator
     * 
     * 
     * 
     * @return TypeValidator
     */
    public org.exolab.castor.xml.TypeValidator getValidator()
    {
        return this;
    } //-- org.exolab.castor.xml.TypeValidator getValidator() 

    /**
     * Method getXMLName
     * 
     * 
     * 
     * @return String
     */
    public java.lang.String getXMLName()
    {
        return xmlName;
    } //-- java.lang.String getXMLName() 

    /**
     * Method isElementDefinition
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isElementDefinition()
    {
        return elementDefinition;
    } //-- boolean isElementDefinition() 

}
