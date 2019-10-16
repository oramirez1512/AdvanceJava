package co.edu.usbcali.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operaciones")
public class MathOperations {
	
	@GetMapping("/add/{n1}/{n2}")
	public Result add(@PathVariable("n1") Integer nu1,@PathVariable("n2") Integer nu2) {
		return new Result(nu1+nu2);
	}
	
	@GetMapping("/subtract/{n1}/{n2}")
	public Result subtract(@PathVariable("n1") Integer nu1,@PathVariable("n2") Integer nu2) {
		return new Result(nu1-nu2);
	}
	
	@GetMapping("/multiply/{n1}/{n2}")
	public Result multiply(@PathVariable("n1") Integer nu1,@PathVariable("n2") Integer nu2) {
		return new Result(nu1*nu2);
	}
	
	@GetMapping("/divide/{n1}/{n2}")
	public Result divide(@PathVariable("n1") Integer nu1,@PathVariable("n2") Integer nu2) {
		return new Result(nu1/nu2);
	}
	
	class Result{
		private Integer value;

		private Result() {
			
		}
		
		public Result(Integer value) {
			super();
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

}
