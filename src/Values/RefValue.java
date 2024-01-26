package Values;

import Types.RefType;
import Types.StringType;
import Types.Type;

public class RefValue implements Value{

    int adress;
    Type locationType;

   public RefValue(int adresa,Type locatie){
       this.adress=adresa;
       this.locationType=locatie;
   }
    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    public int getAdress(){
       return adress;
    }
    public Type getLocationType(){
       return locationType;
    }

    @Override
    public boolean equals(Object anotherObject){
        if(!(anotherObject instanceof RefType))
            return false;
        RefValue valueToCheck=(RefValue) anotherObject;
        return valueToCheck.adress==adress && valueToCheck.locationType==locationType;
    }

    @Override
    public Value deepCopy() {
        return new RefValue(adress,locationType.deepCopy());
    }

    @Override
    public String toString(){
       return String.format("Reference Value(%d, %s)",adress,locationType);
    }
}
