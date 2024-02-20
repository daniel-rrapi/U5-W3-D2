package it.danielrrapi.U5W3D2.controllers;

import it.danielrrapi.U5W3D2.entities.Dispositivo;
import it.danielrrapi.U5W3D2.exceptions.BadRequestException;
import it.danielrrapi.U5W3D2.payloads.NewDispositivoDTO;
import it.danielrrapi.U5W3D2.servicies.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;
    @GetMapping
    public Page<Dispositivo> getDispositivo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.dispositivoService.getDispositivi(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Dispositivo getDispositivoById(@PathVariable long id) {
        return this.dispositivoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo saveDispositivo(@RequestBody  Dispositivo dispositivo, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw new BadRequestException(valutation.getAllErrors());
        }
        return this.dispositivoService.save(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo getDispositivoByIdAndUpdate(@PathVariable long id, @RequestBody  Dispositivo dispositivo, BindingResult valutation) {
        if (valutation.hasErrors()) {
            throw  new BadRequestException(valutation.getAllErrors());
        }
        return this.dispositivoService.findByIdAndUpdate(id, dispositivo);
    }

    @DeleteMapping("/{id}")
    public void getDispositivoByIdAndDelete(@PathVariable long id) {
        this.dispositivoService.findByIdAndDelete(id);
    }
}
