package com.mactec.mactax.service;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mactec.mactax.model.Product;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.dto.TaxConsultantDTO;
import com.mactec.mactax.model.dto.TaxPayerDTO;
import com.mactec.mactax.repository.ProductRepository;
import com.mactec.mactax.repository.UserRepository;
import com.mactec.mactax.service.exception.ResourceNotFoundException;
import com.mactec.mactax.service.mail.Mailer;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mailer mailer;

    @Override
    public Users registerTaxConsultant(TaxConsultantDTO taxConsultantDTO) {
        Users taxConsultant = setTaxConsultantDetails(taxConsultantDTO);
        userRepository.save(taxConsultant);
        return taxConsultant;
    }

    /**
     * 
     * @param taxConsultantDTO
     * @return
     */
    private Users setTaxConsultantDetails(TaxConsultantDTO taxConsultantDTO) {
        Product product = getProductByDomain(taxConsultantDTO.getDomain());
        Users taxConsultant = new Users();
        taxConsultant.setFirstName(taxConsultantDTO.getFirstName());
        taxConsultant.setLastName(taxConsultantDTO.getLastName());
        taxConsultant.setEmail(taxConsultantDTO.getEmail());
        taxConsultant.setMobile(taxConsultantDTO.getMobile());
        taxConsultant.setUsername(taxConsultantDTO.getEmail());
        taxConsultant.setPassword(passwordEncoder.encode(taxConsultantDTO.getPassword()));
        taxConsultant.setDecryptPassword(taxConsultantDTO.getPassword());
        taxConsultant.setProduct(product);
        taxConsultant.setOrganization(product.getOrganization());
        return taxConsultant;
    }

    @Override
    public Users registerTaxPayer(TaxPayerDTO taxPayerDTO) {
        Users taxPayer = setTaxPayerDetails(taxPayerDTO);
        userRepository.save(taxPayer);
        return taxPayer;
    }

    /**
     * 
     * @param taxPayerDTO
     * @return
     */
    private Users setTaxPayerDetails(TaxPayerDTO taxPayerDTO) {
        Product product = getProductByDomain(taxPayerDTO.getDomain());
        Users taxPayer = new Users();
        taxPayer.setFirstName(taxPayerDTO.getFirstName());
        taxPayer.setLastName(taxPayerDTO.getLastName());
        taxPayer.setEmail(taxPayerDTO.getEmail());
        taxPayer.setMobile(taxPayerDTO.getMobile());
        taxPayer.setUsername(taxPayerDTO.getEmail());
        taxPayer.setPassword(passwordEncoder.encode(taxPayerDTO.getPassword()));
        taxPayer.setDecryptPassword(taxPayerDTO.getPassword());
        taxPayer.setProduct(product);
        taxPayer.setOrganization(product.getOrganization());
        return taxPayer;
    }

    /**
     * 
     * @param domainName
     * @return
     */
    private Product getProductByDomain(String domainName) {
        Product product = productRepository.findByDomain(domainName);
        if (product == null) {
            throw new ResourceNotFoundException("No product found with the domain name please contact admiin");
        }
        return product;
    }

}
