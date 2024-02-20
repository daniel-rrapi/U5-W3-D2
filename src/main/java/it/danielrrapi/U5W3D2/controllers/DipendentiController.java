package it.danielrrapi.U5W3D2.controllers;

import it.danielrrapi.U5W3D2.entities.Dipendente;
import it.danielrrapi.U5W3D2.exceptions.BadRequestException;
import it.danielrrapi.U5W3D2.payloads.NewDipendenteDTO;
import it.danielrrapi.U5W3D2.servicies.AuthService;
import it.danielrrapi.U5W3D2.servicies.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private AuthService authService;
    @GetMapping
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.dipendenteService.getDipendenti(page, size, orderBy);
    }

    @GetMapping("/me")
    public Dipendente getProfile(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente) {
        return currentAuthenticatedDipendente;
    }

    @PutMapping("/me")
    public Dipendente getMeAndUpdate(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente, @RequestBody Dipendente updatedDipendente) {
        return this.dipendenteService.findByIdAndUpdate(currentAuthenticatedDipendente.getId(), updatedDipendente);
    }

    @DeleteMapping("/me")
    public void getMeAndDelete(@AuthenticationPrincipal Dipendente currentAuthenticatedDipendente) {
        this.dipendenteService.findByIdAndDelete(currentAuthenticatedDipendente.getId());
    }

    @GetMapping("/{id}")
    public Dipendente getDipendenteById(@PathVariable long id) {
        return this.dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO dipendente, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw new BadRequestException(valutation.getAllErrors());
        }
        return this.authService.save(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente getDipendenteByIdAndUpdate(@PathVariable long id, @RequestBody  Dipendente dipendente, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw  new BadRequestException(valutation.getAllErrors());
        }
        return this.dipendenteService.findByIdAndUpdate(id, dipendente);
    }

    @DeleteMapping("/{id}")
    public void getDipendenteByIdAndDelete(@PathVariable long id) {
        this.dipendenteService.findByIdAndDelete(id);
    }




}
