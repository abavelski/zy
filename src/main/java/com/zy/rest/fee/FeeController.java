package com.zy.rest.fee;

import com.wordnik.swagger.annotations.Api;
import com.zy.app.fee.dao.FeeDao;
import com.zy.app.fee.main.FeeService;
import com.zy.app.fee.model.Fee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value="fees", description = "Fees operations")
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    FeeDao feeDao;

    @Autowired
    FeeService feeService;

    @RequestMapping(value="", method = RequestMethod.GET, produces="application/json")
    public List<Fee> getAllFees() {
        return feeDao.getAllFees();
    }

    @RequestMapping(value="/{code}", method = RequestMethod.GET, produces="application/json")
    public Fee getFeeByCode(@PathVariable String code) {
        return feeDao.findFeeByCode(code);
    }

    @RequestMapping(value="/charge", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<String> chargeAllFees() {
        feeService.chargeAllFees();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
