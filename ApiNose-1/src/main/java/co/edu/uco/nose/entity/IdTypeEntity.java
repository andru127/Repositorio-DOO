package co.edu.uco.nose.entity;

import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.TextHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;

import java.util.UUID;

public class IdTypeEntity  {

    private UUID IdTypeId;
	private String name;

   public IdTypeEntity() {
	   setIdTypeId(UUIDHelper.getUUIDHelper().getDefault());
	   setName(TextHelper.getDefault());
   }
   
   public IdTypeEntity(final UUID IdTypeId) {
	   setIdTypeId(IdTypeId);
	   setName(TextHelper.getDefault());
   }
   
   private IdTypeEntity(UUID IdTypeId, String name) {
	   setIdTypeId(IdTypeId);
	   setName(name);
   }
	
	
	
	
	
	static IdTypeEntity getDefault() {
		return new IdTypeEntity();
   }
   
   static IdTypeEntity getDefault(final IdTypeEntity idType) {
	   return ObjectHelper.getDefault(idType, getDefault());
   }

   
   
   public UUID getIdTypeId() {
	return IdTypeId;
   }

   public void setIdTypeId(UUID idTypeId) {
	IdTypeId = UUIDHelper.getUUIDHelper().getDefault(idTypeId);
   }

   public String getName() {
	return name;
   }

   public void setName(String name) {
	this.name = TextHelper.getDefaultWithTrim(name);	
   }
   
}