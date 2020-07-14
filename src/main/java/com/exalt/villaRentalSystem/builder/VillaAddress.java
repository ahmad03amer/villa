package com.exalt.villaRentalSystem.builder;

import lombok.Builder;

public class VillaAddress {

    private final int villaNumber;
    private final String street;
    private final String city;
    private final String zipCode;

    private VillaAddress(Builder builder){
        this.villaNumber = builder.villaNumber;
        this.street = builder.street;
        this.city = builder.city;
        this.zipCode = builder.zipCode;

    }
    public static class  Builder{
        private int villaNumber;
        private String street;
        private String city;
        private String zipCode;

        public Builder villaNumber(final int  villaNumber){
            this.villaNumber = villaNumber;
            return this;
        }
        public Builder street(final String  street){
            this.street = street;
            return this;
        }
        public Builder city(final String  city){
            this.city = city;
            return this;
        }
        public Builder zipCode(final String  zipCode){
            this.zipCode = zipCode;
            return this;
        }
        public VillaAddress build(){
            return new VillaAddress(this);
        }
    }

    public int getVillaNumber() {
        return villaNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}
