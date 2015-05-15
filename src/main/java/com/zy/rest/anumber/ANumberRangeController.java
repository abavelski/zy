package com.zy.rest.anumber;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.anumber.main.NumberService;
import com.zy.app.anumber.model.ANumberRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "number-range", description = "Number range operations")
@RequestMapping("/api/number-range")
public class ANumberRangeController {

    @Autowired
    NumberService numberService;

    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> createNumberRange(@RequestBody ANumberRange range) {
        numberService.createNumberRange(range);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
