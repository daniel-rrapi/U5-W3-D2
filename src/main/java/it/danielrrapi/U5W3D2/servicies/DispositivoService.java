package it.danielrrapi.U5W3D2.servicies;


import it.danielrrapi.U5W3D2.entities.Dispositivo;
import it.danielrrapi.U5W3D2.exceptions.NotFoundException;
import it.danielrrapi.U5W3D2.payloads.NewDispositivoDTO;
import it.danielrrapi.U5W3D2.repositories.DispositivoDAO;
import it.danielrrapi.U5W3D2.repositories.DispositivoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoDAO dispositivoDAO;

    public Page<Dispositivo> getDispositivi(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return dispositivoDAO.findAll(pageable);
    }

    public Dispositivo save(Dispositivo payload) {
//    Dispositivo newDispositivo = new Dispositivo(payload.type(), payload.status(),)
        return this.dispositivoDAO.save(payload);
    }

    public Dispositivo findById(long id) {
        return dispositivoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dispositivo findByIdAndUpdate(long id, Dispositivo dispositivo) {
        Dispositivo found = this.findById(id);
        return dispositivoDAO.save(dispositivo);
    }

    public void findByIdAndDelete(long id) {
        Dispositivo found = this.findById(id);
        dispositivoDAO.delete(found);
    }

}
