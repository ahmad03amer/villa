package com.exalt.villaRentalSystem.builder;

public class VillaBuilder {

    private int id;
    private String name;

    private final VillaAddress villaAddress;
    private final VillaDetails villaDetails;
    private final VillaOwner villaOwner;

    private VillaBuilder(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.villaAddress = builder.villaAddress;
        this.villaDetails = builder.villaDetails;
        this.villaOwner = builder.villaOwner;
    }

    public static class  Builder{
        private int id;
        private String name;

        private VillaAddress villaAddress;
        private VillaDetails villaDetails;
        private VillaOwner villaOwner;


        public Builder id(final int id){
            this.id=id;
            return this;
        }

        public Builder name(final String name){
            this.name = name;
            return this;
        }
        public Builder villaAddress(final VillaAddress villaAddress){
            this.villaAddress = villaAddress;
            return this;
        }

        public Builder VillaAddress(final VillaOwner villaOwner){
            this.villaOwner = villaOwner;
            return this;
        }

        public VillaBuilder build(){
                return new VillaBuilder(this);
        }
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }


    public VillaAddress getVillaAddress() {
        return villaAddress;
    }

    public VillaDetails getVillaDetails() {
        return villaDetails;
    }

    public VillaOwner getVillaOwner() {
        return villaOwner;
    }
}
