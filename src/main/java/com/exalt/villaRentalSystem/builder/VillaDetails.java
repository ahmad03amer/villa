package com.exalt.villaRentalSystem.builder;

public class VillaDetails {

    private final String description;
    private final String cost;


    private VillaDetails(VillaDetails.Builder builder){
        this.description = builder.description;
        this.cost = builder.cost;

    }
    public static class  Builder{
        private String description;
        private String cost;

        public VillaDetails.Builder description(final String  description){
            this.description = description;
            return this;
        }

        public VillaDetails.Builder cost(final String  cost){
            this.cost = cost;
            return this;
        }

        public VillaDetails build(){
            return new VillaDetails(this);
        }
    }
}
