package mx.uam.spring.practicaSpring.presentacion;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;


/**
 * Controlador web
 * @author wuich
 *
 */

@Controller
@Slf4j
public class MainController {
	
	@GetMapping("/")
	public String index() {
		log.info("Se llamo al método index()");
		return "index";
	}
	
	@RequestMapping("/ejemplo")
	@ResponseBody
	public String ejemplo() {
		return "Esto es un ejemplo";
	}

	
}
