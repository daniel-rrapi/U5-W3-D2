package it.danielrrapi.U5W3D2.servicies;

import it.danielrrapi.U5W3D2.entities.Dipendente;
import it.danielrrapi.U5W3D2.exceptions.BadRequestException;
import it.danielrrapi.U5W3D2.exceptions.UnauthorizedException;
import it.danielrrapi.U5W3D2.payloads.DipendenteLoginDTO;
import it.danielrrapi.U5W3D2.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W3D2.repositories.DipendenteDAO;
import it.danielrrapi.U5W3D2.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private DipendenteDAO dipendenteDAO;

    public String authenticateDipendenteAndGenerateToken(DipendenteLoginDTO payload) {
        Dipendente dipendente = dipendenteService.findByEmail(payload.email());
        if(bcrypt.matches(payload.password(), dipendente.getPassword())) {
            return jwtTools.createToken(dipendente);
        } else {
            throw  new UnauthorizedException("Credenziali sbagliate");
        }
    }

    public Dipendente save(NewDipendenteDTO payload) {
        dipendenteDAO.findByEmail(payload.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email " + payload.email() + " è gia in uso!");
        });
        Dipendente newDipendente = new Dipendente(payload.username(), payload.name(), payload.cognome(), payload.email(), bcrypt.encode(payload.password()));
        return dipendenteDAO.save(newDipendente);
    }
}
