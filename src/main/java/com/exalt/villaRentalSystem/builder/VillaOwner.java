package com.exalt.villaRentalSystem.builder;

public class VillaOwner {

    private final String ownerId;
    private final String ownerName;
    private final String ownerPhone;

    private VillaOwner(VillaOwner.Builder builder){
        this.ownerId = builder.ownerId;
        this.ownerName = builder.ownerName;
        this.ownerPhone = builder.ownerPhone;
    }

    public static class  Builder{

        private  String ownerId;
        private  String ownerName;
        private  String ownerPhone;


        public VillaOwner.Builder ownerId(final String  ownerId){
            this.ownerId = ownerId;
            return this;
        }

        public VillaOwner.Builder ownerName(final String  ownerName){
            this.ownerName = ownerName;
            return this;
        }

        public VillaOwner.Builder ownerPhone(final String  ownerPhone){
            this.ownerPhone = ownerPhone;
            return this;
        }

        public VillaOwner build(){
            return new VillaOwner(this);
        }
    }
}
