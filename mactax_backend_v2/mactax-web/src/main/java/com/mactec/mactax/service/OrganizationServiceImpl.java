package com.mactec.mactax.service;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mactec.mactax.model.Authority;
import com.mactec.mactax.model.Organization;
import com.mactec.mactax.model.OrganizationAddress;
import com.mactec.mactax.model.Product;
import com.mactec.mactax.model.ServiceProvider;
import com.mactec.mactax.model.UserAuthority;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.dto.AdminDTO;
import com.mactec.mactax.model.dto.ServiceProviderDTO;
import com.mactec.mactax.model.enums.OtpType;
import com.mactec.mactax.model.enums.Status;
import com.mactec.mactax.repository.AuthorityRepository;
import com.mactec.mactax.repository.OrganizationRepository;
import com.mactec.mactax.repository.ProductRepository;
import com.mactec.mactax.repository.ServiceProviderRepository;
import com.mactec.mactax.repository.UserAuthorityRepository;
import com.mactec.mactax.repository.UserRepository;
import com.mactec.mactax.service.exception.ResourceAlreadyExistException;
import com.mactec.mactax.service.exception.ResourceNotFoundException;

/**
 * 
 * @author akshayp
 *
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Override
    public AdminDTO createGlobalOrganizationSuperAdminAndAuthority(AdminDTO adminDTO) {
        Organization organization = saveOrganizationDetails(adminDTO);
        Product product = saveProduct(adminDTO, organization);
        Users user = createAdmin(adminDTO, organization, product);
        saveAuthorityIfNotExist(organization, user);
        return adminDTO;
    }

    /**
     * saving organization details through adminDTO
     * 
     * @param adminDTO
     * @return
     */
    private Organization saveOrganizationDetails(AdminDTO adminDTO) {

        Organization organization = new Organization();
        organization.setName(adminDTO.getOrganizationName());
        organization.setContactName(adminDTO.getContactName());
        organization.setContactNumber(adminDTO.getContactNumber());
        organization.setPan(adminDTO.getPan());
        organization.setGstNumber(adminDTO.getGstNumber());
        organization.setStatus(Status.Active);

        OrganizationAddress address = new OrganizationAddress();
        address.setLine1(adminDTO.getLine1());
        address.setLine2(adminDTO.getLine2());
        address.setCity(adminDTO.getCity());
        address.setPincode(adminDTO.getPincode());
        address.setState(adminDTO.getState());
        address.setCountry(adminDTO.getCountry());

        organization.setAddress(address);
        return organizationRepository.save(organization);
    }

    /**
     * 
     * 
     * @param adminDTO
     * @param organization
     * @return
     */
    private Product saveProduct(AdminDTO adminDTO, Organization organization) {
        validateProductByDomain(adminDTO.getDomain());
        Product product = new Product();
        product.setName(adminDTO.getProductName());
        product.setLogo(adminDTO.getLogo());
        product.setDomain(adminDTO.getDomain());
        product.setEmailOtpType(OtpType.NONE);
        product.setSmsOtpType(OtpType.NONE);
        product.setOrganization(organization);
        return productRepository.save(product);
    }

    /**
     * 
     * @param domainName
     */
    private void validateProductByDomain(String domainName) {
        Product product = productRepository.findByDomain(domainName);
        if (product != null) {
            throw new ResourceAlreadyExistException("domain name is already exist. Please choose different domain name");
        }
    }

    /**
     * saving users through adminDTO
     * 
     * @param adminDTO
     * @return
     */
    private Users createAdmin(AdminDTO adminDTO, Organization organization, Product product) {
        validateUserName(adminDTO);
        Users user = setUser(adminDTO, organization, product);
        userRepository.save(user);
        return user;
    }

    /**
     * validating username for parent organization in user table
     * 
     * @param adminDTO
     */
    private void validateUserName(AdminDTO adminDTO) {
        Users existingUser = userRepository.findByUsername(adminDTO.getUserName());
        if (existingUser != null) {
            Organization organization = existingUser.getOrganization();
            throw new ResourceAlreadyExistException("username '" + existingUser.getUsername() + "' is already exists for organization '"
                    + organization.getName() + "' username should be unique");
        }

    }

    /**
     * setting values for User from adminDTO
     * 
     * @param adminDTO
     * @return
     */
    private Users setUser(AdminDTO adminDTO, Organization organization, Product product) {
        Users user = new Users();
        user.setOrganization(organization);
        user.setProduct(product);
        user.setFirstName(adminDTO.getFirstName());
        user.setLastName(adminDTO.getLastName());
        user.setEmail(adminDTO.getEmail());
        user.setMobile(adminDTO.getMobile());
        user.setUsername(adminDTO.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(adminDTO.getPassword()));
        user.setDecryptPassword(adminDTO.getPassword());
        user.setStatus(Status.Active);
        return user;
    }

    /**
     * saving authority through adminDTO
     * 
     * @param applicationUrls
     * @param designation
     */
    private Authority saveAuthorityIfNotExist(Organization organization, Users user) {
        Authority authority = new Authority();
        String role = "ADMIN";
        Authority existingAuthority = authorityRepository.findByRole(role);
        if (existingAuthority == null) {
            authority.setRole(role);
            authority.setStatus(Status.Active);
            authority.setCreatedBy(user);
        } else {
            throw new ResourceAlreadyExistException(" Authority is already available with given role " + role);
        }
        authorityRepository.save(authority);
        saveUserAuthority(authority, user);
        return authority;
    }

    /**
     * save user-authority from authority and user
     * 
     * @param masterAdmin
     */
    private UserAuthority saveUserAuthority(Authority authority, Users user) {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);
        userAuthority.setCreatedBy(user);
        return userAuthorityRepository.save(userAuthority);
    }

    @Override
    public Organization findById(Integer id) throws ResourceNotFoundException {
        return organizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("organization is not found with id " + id));
    }

    @Override
    public ServiceProviderDTO saveServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ServiceProvider serviceProvider = setServiceProvider(serviceProviderDTO);
        serviceProviderRepository.save(serviceProvider);
        return serviceProviderDTO;
    }

    /**
     * 
     * @param serviceProviderDTO
     * @return
     */
    private ServiceProvider setServiceProvider(ServiceProviderDTO serviceProviderDTO) {
        ServiceProvider serviceProvider = new ServiceProvider();
        if (serviceProviderDTO.getId() != null) {
            serviceProvider = findServiceProvider(serviceProviderDTO.getId());
        }
        serviceProvider.setEmailHostName(serviceProviderDTO.getEmailHostName());
        serviceProvider.setEmailHostPort(serviceProviderDTO.getEmailHostPort());
        serviceProvider.setEmailAuthUser(serviceProviderDTO.getEmailAuthUser());
        serviceProvider.setEmailAuthPassword(serviceProviderDTO.getEmailAuthPassword());
        serviceProvider.setEmailHostType(serviceProviderDTO.getEmailHostType());
        serviceProvider.setEmailAuthType(serviceProviderDTO.getEmailAuthType());
        serviceProvider.setEmailTrasportProtocol(serviceProviderDTO.getEmailTrasportProtocol());
        serviceProvider.setEmailTrasportAuth(serviceProviderDTO.isEmailTrasportAuth());
        serviceProvider.setEmailDebug(serviceProviderDTO.isEmailDebug());
        serviceProvider.setEmailSenderName(serviceProviderDTO.getEmailSenderName());

        serviceProvider.setSmsProvider(serviceProviderDTO.getSmsProvider());
        serviceProvider.setSmsHost(serviceProviderDTO.getSmsHost());
        serviceProvider.setSmsSenderId(serviceProviderDTO.getSmsSenderId());
        serviceProvider.setSmsSenderPassword(serviceProviderDTO.getSmsSenderPassword());
        serviceProvider.setSmsSenderName(serviceProviderDTO.getSmsSenderName());
        serviceProvider.setSmsAwsMaxPrice(serviceProviderDTO.getSmsAwsMaxPrice());
        serviceProvider.setStatus(Status.Active);
        return serviceProvider;
    }

    @Override
    public ServiceProvider findServiceProvider(Integer id) {
        return serviceProviderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no ServiceProvider found with id" + id));
    }
}
