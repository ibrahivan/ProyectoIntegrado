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
	const dataUsuario = event.currentTarget.getAttribute("data-usuario");
    const usuarioProps = dataUsuario.split('|');
    const idUsuario = usuarioProps[0];
    const emailUsuario = usuarioProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar este usuario",
		input: "text",
		inputLabel: `Introduce '${emailUsuario}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== emailUsuario.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === emailUsuario.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar/' + idUsuario;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
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
	const dataMoto = event.currentTarget.getAttribute("data-moto");
    const motoProps = dataMoto.split('|');
    const idMoto = motoProps[0];
    const nombreMoto = motoProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar esta moto",
		input: "text",
		inputLabel: `Introduce '${nombreMoto}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== nombreMoto.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === nombreMoto.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar-moto/' + idMoto;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
		}
	});
}


function confirmarEliminarProducto(event) {
	const dataProducto = event.currentTarget.getAttribute("data-producto");
    const productoProps = dataProducto.split('|');
    const idProducto = productoProps[0];
    const nombreProducto = productoProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar este producto",
		input: "text",
		inputLabel: `Introduce '${nombreProducto}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== nombreProducto.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === nombreProducto.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar-producto/' + idProducto;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
		}
	});
}

function confirmarEliminarTienda(event) {
	const dataTienda = event.currentTarget.getAttribute("data-tienda");
    const tiendaProps = dataTienda.split('|');
    const idTienda = tiendaProps[0];
    const nombreTienda = tiendaProps[1].trim();

	Swal.fire({
		title: "¡Esta acción es irreversible!",
		text: "Si está seguro de que desea eliminar esta tienda",
		input: "text",
		inputLabel: `Introduce '${nombreTienda}' para confirmar la acción`,
		inputPlaceholder: "Introduce aquí",
		showCancelButton: true,
		confirmButtonText: "Eliminar",
		cancelButtonText: "Cancelar",
		preConfirm: (inputValue) => {
			if (inputValue.trim().toLowerCase() !== nombreTienda.toLowerCase()) {
				Swal.showValidationMessage('La palabra introducida no es correcta');
			}
			return inputValue;
		}
	}).then((result) => {
		if (result.isConfirmed) {
			const inputValue = result.value;
			if (inputValue.trim().toLowerCase() === nombreTienda.toLowerCase()) {
				window.location.href = 'http://localhost:8080/privada/eliminar-tienda/' + idTienda;
			} else {
				Swal.fire({
					icon: "error",
					title: "Error",
					text: "La palabra introducida no es correcta"
				});
			}
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

function generarPDF() {
    const element = document.querySelector('.card-body');
    const options = {
        margin: 10,
        filename: 'Mis_Pedidos.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().from(element).set(options).save().then(() => {
        console.log('PDF generado correctamente');
    }).catch((error) => {
        console.error('Error al generar el PDF:', error);
    });
    }
function cambiarPlaceholder() {
	let valorSelect = document.querySelector('select[name="filtro"]').value;
	let input = document.getElementById('busquedaUser');

	if (valorSelect === 'email_usuario') {
		input.placeholder = "Buscar por el email del usuario";
	} else if (valorSelect === 'nombre_usuario') {
		input.placeholder = "Buscar por nombre";
	}
}


