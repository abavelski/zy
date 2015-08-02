package com.zy.rest.anumber;

import com.zy.app.anumber.main.NumberService;
import com.zy.app.anumber.model.ANumber;
import com.zy.app.anumber.model.ReservationResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zy.app.anumber.model.builder.ReservationResponseBuilder.aReservationResponse;

@RestController
@Api(value = "a-number", description = "ANumber operations")
@RequestMapping("/api/a-number")
public class ANumberController {

    @Autowired
    NumberService numberService;

    @RequestMapping(value = "/type/{type}/random/{nr}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Integer>> getRandomANumbers(@PathVariable Integer nr,
                                                           @PathVariable String type) {
        List<Integer> numbers = numberService.getRandomANumbers(nr, ANumber.Type.valueOf(type.toUpperCase()));
        return new ResponseEntity<>(numbers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{number}/reserve", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<ReservationResponse> reserveANumber(@PathVariable Integer number) {
        ReservationResponse reservationResponse =
                aReservationResponse()
                    .withReservationKey(numberService.reserveANumber(number))
                .build();
        return new ResponseEntity<>(reservationResponse, HttpStatus.OK);
    }


}
