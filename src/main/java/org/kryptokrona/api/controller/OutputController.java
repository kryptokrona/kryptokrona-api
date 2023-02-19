package org.kryptokrona.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.kryptokrona.api.service.OutputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.kryptokrona.api.controller.OutputController.VERSION;

/**
 * Output Controller.
 *
 * @author Marcus Cvjeticanin
 */
@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v" + VERSION + "/output")
@Tag(name = "outputs", description = "Set of endpoints to get data of outputs.")
public class OutputController {

    static final String VERSION = "1";

    private static final Logger logger = LoggerFactory.getLogger(OutputController.class);

    private final OutputService outputService;

    @Autowired
    public OutputController(OutputService outputService) {
        this.outputService = outputService;
    }
}
