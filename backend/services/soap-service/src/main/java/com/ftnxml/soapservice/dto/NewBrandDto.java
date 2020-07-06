package com.ftnxml.soapservice.dto;

import java.io.Serializable;

public class NewBrandDto implements Serializable {
    public String name;

    @Override
    public String toString() {
        return "NewBrandDto [name=" + name + "]";
    }

}
