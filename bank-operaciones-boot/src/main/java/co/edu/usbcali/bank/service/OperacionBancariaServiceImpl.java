package co.edu.usbcali.bank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cuenta;
import co.edu.usbcali.bank.domain.TipoTransaccion;
import co.edu.usbcali.bank.domain.Transaccion;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.CuentaRepository;
import co.edu.usbcali.bank.repository.TipoTransaccionRepository;
import co.edu.usbcali.bank.repository.TransaccionRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class OperacionBancariaServiceImpl implements OperacionBancariaService {

	private final static Logger log = LoggerFactory.getLogger(OperacionBancariaServiceImpl.class);

	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoTransaccionRepository tipoTransaccionRepository;

	@Autowired
	TransaccionRepository transaccionRepository;

	private static final Long RETIRO = 1L;
	private static final Long CONSIGNAR = 2L;
	private static final Long TRANSFERIR = 3L;

	private static final BigDecimal COMISION = new BigDecimal(2000);
	private static final String DEFAULT_ACCOUNT = "1541-4277-0660-4459";

	@PostConstruct
	void postConstruct() {
		log.info("######### Se ejecut� el postConstruct #########");
	}

	@PreDestroy
	void preDestroy() {
		log.info("######### Se ejecut� el preDestroy #########");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {

		validar(cuenId, valor, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		if (cuenta.getSaldo().compareTo(valor) == -1)
			throw new Exception("Fondos insuficientes.");

		Transaccion transaccion = new Transaccion();

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(RETIRO).get();
		transaccion.setTipoTransaccion(tipoTransaccion);

		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(transaccion.getFecha());
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setTranId(null);
		transaccion.setValor(valor);

		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
		cuentaRepository.save(cuenta);

		transaccion = transaccionRepository.save(transaccion);

		return transaccion.getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		if (cuenta.getSaldo().compareTo(valor) == -1)
			throw new Exception("Fondos insuficientes.");

		Transaccion transaccion = new Transaccion();

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(CONSIGNAR).get();
		transaccion.setTipoTransaccion(tipoTransaccion);

		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(transaccion.getFecha());
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setTranId(null);
		transaccion.setValor(valor);

		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);

		transaccion = transaccionRepository.save(transaccion);

		return transaccion.getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long transferir(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {

		retirar(cuenIdOrigen, valor, usuUsuario);
		consignar(cuenIdDestino, valor, usuUsuario);
		retirar(cuenIdOrigen, COMISION, usuUsuario);
		consignar(DEFAULT_ACCOUNT, COMISION, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenIdOrigen).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		Transaccion transaccion = new Transaccion();

		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(TRANSFERIR).get();
		transaccion.setTipoTransaccion(tipoTransaccion);

		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(transaccion.getFecha());
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setTranId(null);
		transaccion.setValor(valor);

		transaccion = transaccionRepository.save(transaccion);
		return transaccion.getTranId();
	}

	private void validar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		// Validaciones b�sicas.
		if (cuenId == null || cuenId.trim().equals(""))
			throw new Exception("El numero de la cuenta es obligatorio.");

		if (valor == null || valor.signum() <= 0)
			throw new Exception("El monto a transferir no es valido.");

		if (usuUsuario == null || usuUsuario.trim().equals(""))
			throw new Exception("El usuario es obligatorio.");

		// Validaciones de cuenta.
		Optional<Cuenta> cuentaOpcional = cuentaRepository.findById(cuenId);

		if (cuentaOpcional.isPresent() == false)
			throw new Exception("La cuenta con id: " + cuenId + " no existe.");

		if (cuentaOpcional.get().getActiva().equals("N"))
			throw new Exception("La cuenta con id: " + cuenId + " se encuentra in-activa.");

		// Validaciones de usuario.
		Optional<Usuario> usuarioOpcional = usuarioRepository.findById(usuUsuario);

		if (usuarioOpcional.isPresent() == false)
			throw new Exception("El usuario con mascara: " + cuenId + " no existe.");

		if (usuarioOpcional.get().getActivo().equals("N"))
			throw new Exception("El usuario con mascara: " + cuenId + " se encuentra in-activa.");
	}
}
