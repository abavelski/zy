package com.zy.rest.rating;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.cdr.main.CdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value="billing-record", description = "Billing record operations")
@RequestMapping("/api/billing-records")
public class RatingController {

    @Autowired
    CdrService cdrService;

    @RequestMapping(value="/", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> rate(@RequestBody BillingRecord br) {
        cdrService.rate(br);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
