function revisarContraseña() {
	var contraseña = document.getElementById('claveUsuario').value;
	var confContraseña = document.getElementById('confirmarClaveUsuario').value;
	var mensajeContraseña = document.getElementById('mensajeContrasenya');

	if (contraseña === "" && confContraseña === "") {
		mensajeContraseña.textContent = 'No puede dejar campos vacíos';
		document.getElementById("btnRegistro").disabled = true;//deshabilita el boton		
	} else if (contraseña === confContraseña) {
		mensajeContraseña.textContent = 'Las contraseñas coinciden';
		mensajeContraseña.style.color = 'green';
		document.getElementById("btnRegistro").disabled = false;//habilita el boton
	} else {
		mensajeContraseña.textContent = 'Las contraseñas no coinciden';
		mensajeContraseña.style.color = 'red';
		document.getElementById("btnRegistro").disabled = true;//deshabilita el boton
	}
}

function mostrarNotificacion(titulo, mensaje, tipo) {
	Swal.fire({
		title: titulo,
		text: mensaje,
		icon: tipo,
		confirmButtonText: 'OK'
	});
}

function confirmarLogout() {
	Swal.fire({
		title: '¿Estás seguro de que deseas cerrar sesión?',
		text: 'Serás redirigido a la página de bienvenida.',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Sí, cerrar sesión'
	}).then((result) => {
		if (result.isConfirmed) {
			document.getElementById('logoutForm').submit();
		} else {
			console.log('Logout cancelado');
		}
	});
}
function confirmar() {
	return Swal.fire({
		title: '¿Estás seguro de que deseas eliminar?',
		text: 'Esta acción es irreversible.',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Sí, eliminar.'
	}).then((result) => {
		return result.isConfirmed;
	});
}

function confirmarEliminar(event) {
	const idUsuario = event.currentTarget.getAttribute("data-id");
	confirmar().then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/eliminar/' + idUsuario;
		}
	});

}

function confirmarEliminarTienda(event) {
	const idTienda = event.currentTarget.getAttribute("data-id");
	confirmar().then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/eliminar-tienda/' + idTienda;
		}
	});
}

function confirmarEliminarMoto(event) {
	const idMoto = event.currentTarget.getAttribute("data-id");
	confirmar().then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/eliminar-moto/' + idMoto;
		}
	});
}
function confirmarEliminarCubeta(event) {
	const idCubeta = event.currentTarget.getAttribute("data-id");
	confirmar().then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/eliminar-cubeta/' + idCubeta;
		}
	});
}

function confirmarEliminarProducto(event) {
	const idCatalogoProducto = event.currentTarget.getAttribute("data-id");
	confirmar().then(function(confirmado) {
		if (confirmado) {
			window.location.href = 'http://localhost:8080/privada/eliminar-producto/' + idCatalogoProducto;
		}
	});
}


	window.onload = function() {
		// Obtener el botón de realizar pedido
		var btnRealizarPedido = document.getElementById("btnRealizarPedido");

		// Obtener los elementos de los checkbox de productos
		var checkboxes = document.querySelectorAll("input[type='checkbox']");

		// Obtener los inputs de cantidad
		var cantidadInputs = document.querySelectorAll("input[type='number']");

		// Función para verificar si al menos un producto está seleccionado y la cantidad es mayor que 0
		function verificarPedido() {
			var alMenosUnProductoSeleccionado = Array.from(checkboxes).some(
					function(checkbox) {
						return checkbox.checked;
					});

			var cantidadValida = Array.from(cantidadInputs).some(
					function(input) {
						return parseInt(input.value) > 0
								&& input.disabled === false;
					});

			if (alMenosUnProductoSeleccionado && cantidadValida) {
				btnRealizarPedido.disabled = false;
			} else {
				btnRealizarPedido.disabled = true;
			}
		}

		// Escuchar cambios en los checkbox de productos
		checkboxes.forEach(function(checkbox) {
			checkbox.addEventListener("change", function() {
				verificarPedido();
			});
		});

		// Escuchar cambios en los inputs de cantidad
		cantidadInputs.forEach(function(input) {
			input.addEventListener("input", function() {
				verificarPedido();
			});
		});
	}


