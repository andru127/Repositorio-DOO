
package co.edu.uco.nose.business.assembler.dto.impl;

import java.util.ArrayList;
import java.util.List;

import static co.edu.uco.nose.business.assembler.dto.impl.CityDTOAssembler.getCityDTOAssembler;
import static co.edu.uco.nose.business.assembler.dto.impl.IdTypeDTOAssembler.getIdTypeDTOAssembler;

import co.edu.uco.nose.business.assembler.dto.DTOAssembler;
import co.edu.uco.nose.business.domain.UserDomain;
import co.edu.uco.nose.crosscutting.helpers.ObjectHelper;
import co.edu.uco.nose.crosscutting.helpers.UUIDHelper;
import co.edu.uco.nose.dto.UserDTO;

public final class UserDTOAssembler implements DTOAssembler<UserDTO, UserDomain> {

	
	private static final DTOAssembler<UserDTO, UserDomain> instance = new UserDTOAssembler();
	private UserDTOAssembler() {
		
	}
	
	public static DTOAssembler<UserDTO, UserDomain> getUserDTOAssembler() {
		return instance;
	}
	
	@Override
	public UserDTO toDTO(final UserDomain domain) {
		var domainTmp = ObjectHelper.getDefault(domain, new UserDomain(UUIDHelper.getUUIDHelper().getDefault()));
		var identificationTypeTmp = getIdTypeDTOAssembler().toDTO(domainTmp.getIdentificationType());
		var cityTmp = getCityDTOAssembler().toDTO(domainTmp.getResidenceCity());
		return new UserDTO(domainTmp.getId(), identificationTypeTmp, domainTmp.getIdentificationNumber(), 
				domainTmp.getFirstName(), domainTmp.getMiddleName(), domainTmp.getLastName(), 
				domainTmp.getSecondLastName(), cityTmp, domainTmp.getEmail(), 
				domainTmp.getCellPhoneNumber(), domainTmp.isEmailConfirmed(), domainTmp.isCellPhoneNumberConfirmed());
	}

	@Override
	public UserDomain toDomain(final UserDTO dto) {
		var dtoTmp = ObjectHelper.getDefault(dto, new UserDTO());
		var identificationTypeDomainTmp = getIdTypeDTOAssembler().toDomain(dtoTmp.getIdentificationType());
		var cityDomainTmp = getCityDTOAssembler().toDomain(dtoTmp.getResidenceCity());
		return new UserDomain(dtoTmp.getId(), identificationTypeDomainTmp, dtoTmp.getIdentificationNumber(), 
				dtoTmp.getFirstName(), dtoTmp.getMiddleName(), dtoTmp.getLastName(), 
				dtoTmp.getSecondLastName(), cityDomainTmp, dtoTmp.getEmail(), 
				dtoTmp.getCellPhoneNumber(), dtoTmp.isEmailConfirmed(), dtoTmp.isCellPhoneNumberConfirmed());
	}

	@Override
	public List<UserDTO> toDTO(final List<UserDomain> domainList) {
		var userDTOList = new ArrayList<UserDTO>();
		
		for (var userDomain : domainList) {
			userDTOList.add(toDTO(userDomain));
		}
		return userDTOList;
	}

}
