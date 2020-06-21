//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.06.21 at 07:56:09 PM CEST 
//


package com.ftnxml.soapservice.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftnxml.soapservice.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftnxml.soapservice.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AllBrandsResponse }
     * 
     */
    public AllBrandsResponse createAllBrandsResponse() {
        return new AllBrandsResponse();
    }

    /**
     * Create an instance of {@link Brand }
     * 
     */
    public Brand createBrand() {
        return new Brand();
    }

    /**
     * Create an instance of {@link BrandByIdResponse }
     * 
     */
    public BrandByIdResponse createBrandByIdResponse() {
        return new BrandByIdResponse();
    }

    /**
     * Create an instance of {@link AllBrandsRequest }
     * 
     */
    public AllBrandsRequest createAllBrandsRequest() {
        return new AllBrandsRequest();
    }

    /**
     * Create an instance of {@link BrandByIdRequest }
     * 
     */
    public BrandByIdRequest createBrandByIdRequest() {
        return new BrandByIdRequest();
    }

    /**
     * Create an instance of {@link Model }
     * 
     */
    public Model createModel() {
        return new Model();
    }

}
