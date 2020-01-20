package ru.rcslabs.taxes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rcslabs.taxes.dto.RequestDto;
import ru.rcslabs.taxes.services.SimpleService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @Autowired
    SimpleService simpleService;

    @GetMapping
    public ResponseEntity<List> getAll() {
        return ResponseEntity.ok(simpleService.getAll());
    }

    @GetMapping("/a")
    public ResponseEntity<List> getColumnA() {
        return ResponseEntity.ok(simpleService.getColumnA());
    }

    @GetMapping("/b/{val}")
    public ResponseEntity<List> getColumnB(@PathVariable("val") String valueA) {
        return ResponseEntity.ok(simpleService.getColumnB(valueA));
    }

    @GetMapping("/c")
    public ResponseEntity<List> getColumnC() {
        return ResponseEntity.ok(simpleService.getColumnC());
    }

    @GetMapping("/d/{val}")
    public ResponseEntity<List> getColumnD(@PathVariable("val") String valueC) {
        return ResponseEntity.ok(simpleService.getColumnD(valueC));
    }

    @GetMapping("/y")
    public ResponseEntity<List> getColumnY() {
        return ResponseEntity.ok(simpleService.getColumnY());
    }

    @PostMapping("/taxes")
    public ResponseEntity<List> getAllByParameters(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(simpleService.getAllByParameters(requestDto));
    }

}
