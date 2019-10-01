package co.edu.usbcali.demo.vehiculo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleService {
	
	private final static Logger log= LoggerFactory.getLogger(VehicleService.class);
	
	private static VehicleService vehicleService;
	
	private VehicleService() {
		
	}
	
	public static VehicleService createInstance() {
		if(vehicleService==null) {
			vehicleService = new VehicleService();
		}
		log.info("VehicleService was created");
		return vehicleService;
	}

}
