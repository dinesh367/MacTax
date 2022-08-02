package com.mactec.mactax.service;

import com.mactec.mactax.model.Organization;
import com.mactec.mactax.model.ServiceProvider;
import com.mactec.mactax.model.dto.AdminDTO;
import com.mactec.mactax.model.dto.ServiceProviderDTO;
import com.mactec.mactax.service.exception.ResourceNotFoundException;

/**
 * 
 * @author akshayp
 *
 */
public interface OrganizationService {

    /**
     * saving organization,user and authority,user and organization details by using adminDTO
     * 
     * @param adminDTO
     * @return
     */
    AdminDTO createGlobalOrganizationSuperAdminAndAuthority(AdminDTO adminDTO);

    /**
     * getting details of organization through id
     * 
     * @param id
     * @return
     */
    Organization findById(Integer id) throws ResourceNotFoundException;

    /**
     * 
     * 
     * @param serviceProviderDTO
     * @return
     */
    ServiceProviderDTO saveServiceProvider(ServiceProviderDTO serviceProviderDTO);

    /**
     * 
     * @param id
     * @return
     */
    ServiceProvider findServiceProvider(Integer id);

}
