package com.example.demo.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author __ArunPrakash__
 *
 */
@RequestMapping(value= {"/api"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface BaseResource {

}
