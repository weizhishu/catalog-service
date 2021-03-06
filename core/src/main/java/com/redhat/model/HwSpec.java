/*
 * Make.java
 *
 * This file is the exclusive property of RedHat.
 * Reproduction and Unauthorized use are forbidden.
 *
 * Copyright (C) 2014 RedHat. All rights reserved.
 */
package com.redhat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.util.xmlrpc.annotation.XmlRpcBean;

/**
 *
 */
@Entity
@Table(name = "hw_spec")
@Indexed
@XmlRpcBean
public class HwSpec extends BaseObject {

    /** */
    private static final long serialVersionUID = 4755592464042723105L;

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(HwSpec.class);
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(unique = true, nullable = false, length = 30)
    @Field
    private String name;
    @Column(unique = true, nullable = true)
    @Field
    private String url;

    /**
     * Creates a new Specification.
     *
     */
    public HwSpec() {
    };

    /**
     * Creates a new Specification.
     *
     * @param name
     */
    public HwSpec(final String name) {
        super();
        this.name = name;
        LOGGER.debug(toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Specification [name=" + name + ", url=" + url + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (url == null ? 0 : url.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final HwSpec other = (HwSpec) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(final String url) {
        this.url = url;
    }

}
