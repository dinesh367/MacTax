package com.mactec.mactax.service;

import org.springframework.stereotype.Service;

import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.dto.TaxConsultantDTO;
import com.mactec.mactax.model.dto.TaxPayerDTO;

@Service
public interface UserService {

    /**
     * 
     * @param taxConsultantDTO
     * @return
     */
    Users registerTaxConsultant(TaxConsultantDTO taxConsultantDTO);

    /**
     * 
     * @param taxPayerDTO
     * @return
     */
    Users registerTaxPayer(TaxPayerDTO taxPayerDTO);
}
