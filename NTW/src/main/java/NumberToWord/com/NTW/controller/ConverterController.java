package NumberToWord.com.NTW.controller;

import NumberToWord.com.NTW.service.ConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConverterController {

    private final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @PostMapping("/converting")
    public ResponseEntity<String> converting(@RequestBody String number) {
        try {
            String result = converterService.handleConverter(number);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid input: " + e.getMessage());
        }
    }
}
