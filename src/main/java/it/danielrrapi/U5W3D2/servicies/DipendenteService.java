package it.danielrrapi.U5W3D2.servicies;


import it.danielrrapi.U5W3D2.entities.Dipendente;
import it.danielrrapi.U5W3D2.exceptions.BadRequestException;
import it.danielrrapi.U5W3D2.exceptions.NotFoundException;
import it.danielrrapi.U5W3D2.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W3D2.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteDAO dipendenteDAO;

    public Page<Dipendente> getDipendenti(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dipendenteDAO.findAll(pageable);
    }



    public Dipendente findById(long id) { return dipendenteDAO.findById(id).orElseThrow(() -> new NotFoundException(id)); }

    public Dipendente findByIdAndUpdate(long id, Dipendente dipendente) {
        Dipendente found = this.findById(id);
        return dipendenteDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Dipendente found = this.findById(id);
        dipendenteDAO.delete(found);
    }

    public Dipendente findByEmail(String email) {
        return dipendenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("email " + email + "non trovata"));
    }


}
