function revisarNombre() {
    var nombreUsuario = document.getElementById("nombreUsuario").value;
    var errorNombreUsuario = document.getElementById("errorNombreUsuario");

    if (nombreUsuario.trim() === "") {
        errorNombreUsuario.textContent = "El nombre no puede estar vacío.";
        errorNombreUsuario.style.color = 'red';
        return false; // Retorna falso si el nombre está vacío
    } else if (!/^[A-Za-z][A-Za-z\s]*$/.test(nombreUsuario)) {
        errorNombreUsuario.textContent = "El nombre solo puede contener letras, y no debe empezar por un espacio.";
        errorNombreUsuario.style.color = 'red';
        return false; // Retorna falso si el nombre contiene caracteres no permitidos o empieza por un espacio
    } else {
        errorNombreUsuario.textContent = "";
        return true; // Retorna verdadero si el nombre es válido
    }
}

function revisarApellidos() {
    var apellidosUsuario = document.getElementById("apellidosUsuario").value;
    var errorApellidosUsuario = document.getElementById("errorApellidosUsuario");

    if (apellidosUsuario.trim() === "") {
        errorApellidosUsuario.textContent = "Los apellidos no pueden estar vacíos.";
        errorApellidosUsuario.style.color = 'red';
        return false; // Retorna falso si los apellidos están vacíos
    } else if (!/^[A-Za-z][A-Za-z\s]*$/.test(apellidosUsuario)) {
        errorApellidosUsuario.textContent = "Los apellidos solo pueden contener letras, y no deben empezar por un espacio.";
        errorApellidosUsuario.style.color = 'red';
        return false; // Retorna falso si los apellidos contienen caracteres no permitidos o empiezan por un espacio
    } else {
        errorApellidosUsuario.textContent = "";
        return true; // Retorna verdadero si los apellidos son válidos
    }
}




function revisarDNI() {
    var dniUsuario = document.getElementById("dniUsuario").value;
    var errorDNIUsuario = document.getElementById("errorDNIUsuario");

    if (!/^[0-9]{8}[A-Za-z]{1}$/.test(dniUsuario)) {
        errorDNIUsuario.textContent = "El formato de DNI no es válido.";
        errorDNIUsuario.style.color = 'red';
        return false; // Retorna falso si el DNI no cumple con el formato esperado
    } else {
        errorDNIUsuario.textContent = "";
        return true; // Retorna verdadero si el DNI es válido
    }
}

function revisarTelefono() {
    var tlfUsuario = document.getElementById("tlfUsuario").value;
    var errorTlfUsuario = document.getElementById("errorTlfUsuario");

    if (!/^[0-9]{9}$/.test(tlfUsuario)) {
        errorTlfUsuario.textContent = "El formato de teléfono no es válido.";
        errorTlfUsuario.style.color = 'red';
        return false; // Retorna falso si el teléfono no cumple con el formato esperado
    } else {
        errorTlfUsuario.textContent = "";
        return true; // Retorna verdadero si el teléfono es válido
    }
}

function revisarCorreo() {
    var emailUsuario = document.getElementById("emailUsuario").value.trim(); // Elimina los espacios en blanco al inicio y al final
    var errorEmailUsuario = document.getElementById("errorEmailUsuario");

    if (!emailUsuario || !/^\S+@[^\s@]+\.[^\s@]+$/.test(emailUsuario)) {
        errorEmailUsuario.textContent = "El formato de correo electrónico no es válido.";
        errorEmailUsuario.style.color = 'red';
        return false; // Retorna falso si el correo electrónico no cumple con el formato esperado o está vacío
    } else {
        errorEmailUsuario.textContent = "";
        return true; // Retorna verdadero si el correo electrónico es válido
    }
}



function revisarContraseña() {
    var claveUsuario = document.getElementById("claveUsuario").value.trim(); // Elimina los espacios en blanco al inicio y al final
    var errorClaveUsuario = document.getElementById("errorClaveUsuario");

    if (!claveUsuario || /\s/.test(claveUsuario)) {
        errorClaveUsuario.textContent = "La contraseña no puede contener espacios en blanco.";
        errorClaveUsuario.style.color = 'red';
        return false; // Retorna falso si la contraseña está vacía o contiene espacios en blanco
    } else if (claveUsuario.length < 8) {
        errorClaveUsuario.textContent = "La contraseña debe tener al menos 8 caracteres.";
        errorClaveUsuario.style.color = 'red';
        return false; // Retorna falso si la contraseña es demasiado corta
    } else if (claveUsuario.length > 50) {
        errorClaveUsuario.textContent = "La contraseña no puede tener más de 50 caracteres.";
        errorClaveUsuario.style.color = 'red';
        return false; // Retorna falso si la contraseña es demasiado larga
    } else {
        errorClaveUsuario.textContent = "";
        return true; // Retorna verdadero si la contraseña es válida
    }
}

function revisarConfirmarContraseña() {
    var confirmarClaveUsuario = document.getElementById("confirmarClaveUsuario").value.trim(); // Elimina los espacios en blanco al inicio y al final
    var errorConfirmarClaveUsuario = document.getElementById("errorConfirmarClaveUsuario");

    if (!confirmarClaveUsuario || /\s/.test(confirmarClaveUsuario)) {
        errorConfirmarClaveUsuario.textContent = "La contraseña no puede contener espacios en blanco.";
        errorConfirmarClaveUsuario.style.color = 'red';
        return false; // Retorna falso si la confirmación de contraseña está vacía o contiene espacios en blanco
    } else if (confirmarClaveUsuario !== document.getElementById("claveUsuario").value.trim()) {
        errorConfirmarClaveUsuario.textContent = "Las contraseñas no coinciden.";
        errorConfirmarClaveUsuario.style.color = 'red';
        return false; // Retorna falso si la confirmación de contraseña no coincide con la contraseña
    } else {
        errorConfirmarClaveUsuario.textContent = "Las contraseñas coinciden";
        errorConfirmarClaveUsuario.style.color = 'green';
        return true; // Retorna verdadero si la confirmación de contraseña es válida
    }
}

function validarFormulario() {
    return revisarNombre() && revisarApellidos() && revisarDNI() && revisarTelefono() && revisarCorreo() && revisarContraseña() && revisarConfirmarContraseña();
}
function validarFormularioRecuperar() {
    return revisarContraseña() && revisarConfirmarContraseña();
}
function validarFormularioEditar() {
    return revisarNombre() && revisarApellidos()  && revisarTelefono() ;
}
function validarMarca() {
    var marcaMoto = document.getElementById("marcaMoto").value.trim();
    var errorMarcaMoto = document.getElementById("errorMarcaMoto");

    if (marcaMoto === "") {
        errorMarcaMoto.textContent = "La marca no puede estar vacía.";
        return false;
    } else if (!/^[a-zA-Z0-9\s]*$/.test(marcaMoto)) {
        errorMarcaMoto.textContent = "La marca solo puede contener letras y números.";
        return false;
    } else if (/^\s/.test(marcaMoto)) {
        errorMarcaMoto.textContent = "La marca no puede empezar con un espacio en blanco.";
        return false;
    } else {
        errorMarcaMoto.textContent = "";
        return true;
    }
}



function validarMatricula() {
    var matriculaMoto = document.getElementById("matriculaMoto").value.trim();
    var errorMatriculaMoto = document.getElementById("errorMatriculaMoto");

    if (matriculaMoto === "") {
        errorMatriculaMoto.textContent = "La matrícula no puede estar vacía.";
        return false;
    } else if (!/^\d{4}[A-Za-z]{3}$/.test(matriculaMoto)) {
        errorMatriculaMoto.textContent = "La matrícula debe contener exactamente 4 números seguidos de 3 letras.";
        return false;
    } else {
        errorMatriculaMoto.textContent = "";
        return true;
    }
}

document.getElementById("form4").addEventListener("submit", function(event) {
    if (!validarMarca() || !validarMatricula()) {
        event.preventDefault();
    }
});

function validarFormularioMoto() {
			return validarMarca() && validarMatricula();
}

function validarFormularioEmail() {
			return revisarCorreo();
}

// Función para validar el nombre del producto
function validarNombreProducto() {
    var nombreProducto = document.getElementById("nombreProducto").value.trim();
    var errorNombreProducto = document.getElementById("errorNombreProducto");

    if (!/^[a-zA-Z\s]+$/.test(nombreProducto)) {
        errorNombreProducto.textContent = "El nombre del producto solo puede contener letras y espacios.";
        errorNombreProducto.style.color = 'red';
        return false; // Retorna falso si el nombre no cumple con el formato esperado
    } else {
        errorNombreProducto.textContent = "";
        return true; // Retorna verdadero si el nombre es válido
    }
}


// Función para validar el precio unitario del producto
function validarPrecioUnitario() {
    var precioUnitario = document.getElementById("precioUnitario").value.trim();
    var errorPrecioUnitario = document.getElementById("errorPrecioUnitario");

    // Actualizamos la expresión regular para que acepte hasta dos decimales y no acepte comas
    if (!/^\s*(\d+(\.\d{1,2})?|\.\d{1,2})\s*$/.test(precioUnitario)) {
        errorPrecioUnitario.textContent = "El precio unitario del producto solo puede contener números enteros o decimales (con punto) con hasta dos decimales, sin espacios ni comas.";
        errorPrecioUnitario.style.color = 'red';
        return false; // Retorna falso si el precio no cumple con el formato esperado
    } else {
        errorPrecioUnitario.textContent = "";
        return true; // Retorna verdadero si el precio es válido
    }
}



function validarDescripcion() {
    var descripcion = document.getElementById("descripcion").value.trim();
    var errorDescripcion = document.getElementById("errorDescripcion");

    if (!/^[a-zA-Z\s]+$/.test(descripcion)) {
        errorDescripcion.textContent = "La descripción del producto solo puede contener letras y espacios.";
        errorDescripcion.style.color = 'red';
        return false; // Retorna falso si la descripción no cumple con el formato esperado
    } else {
        errorDescripcion.textContent = "";
        return true; // Retorna verdadero si la descripción es válida
    }
}


// Función para validar todo el formulario
function validarFormularioPrecioProducto() {
    return validarNombreProducto() && validarPrecioUnitario() && validarDescripcion();
}
function validarNombreTienda() {
    var nombreTienda = document.getElementById("nombreTienda").value.trim();
    var errorNombreTienda = document.getElementById("errorNombreTienda");

    if (nombreTienda === "") {
        errorNombreTienda.textContent = "El nombre de la tienda no puede estar vacío.";
        errorNombreTienda.style.color = 'red';
        return false; // Retorna falso si el nombre está vacío
    } else if (!/^[a-zA-Z\s]*$/.test(nombreTienda)) {
        errorNombreTienda.textContent = "El nombre de la tienda solo puede contener letras.";
        errorNombreTienda.style.color = 'red';
        return false; // Retorna falso si el nombre no cumple con el formato esperado
    } else if (nombreTienda.charAt(0) === ' ') {
        errorNombreTienda.textContent = "El nombre de la tienda no puede empezar por espacio.";
        errorNombreTienda.style.color = 'red';
        return false; // Retorna falso si el nombre comienza con un espacio
    } else {
        errorNombreTienda.textContent = "";
        return true; // Retorna verdadero si el nombre es válido
    }
}

function validarDireccionTienda() {
    var direccionTienda = document.getElementById("direccionTienda").value.trim();
    var errorDireccionTienda = document.getElementById("errorDireccionTienda");

    if (direccionTienda === "") {
        errorDireccionTienda.textContent = "La dirección de la tienda no puede estar vacía.";
        errorDireccionTienda.style.color = 'red';
        return false; // Retorna falso si la dirección está vacía
    } else if (!/^[^\s][a-zA-Z0-9\s]*$/.test(direccionTienda)) {
        errorDireccionTienda.textContent = "La dirección de la tienda no puede empezar por espacio.";
        errorDireccionTienda.style.color = 'red';
        return false; // Retorna falso si la dirección no cumple con el formato esperado
    } else {
        errorDireccionTienda.textContent = "";
        return true; // Retorna verdadero si la dirección es válida
    }
}

function validarCodigoPostalTienda() {
    var codigoPostalTienda = document.getElementById("codigopostalTienda").value.trim();
    var errorCodigoPostalTienda = document.getElementById("errorCodigoPostalTienda");

    if (codigoPostalTienda === "") {
        errorCodigoPostalTienda.textContent = "El código postal de la tienda no puede estar vacío.";
        errorCodigoPostalTienda.style.color = 'red';
        return false; // Retorna falso si el código postal está vacío
    } else if (!/^\d{1,5}$/.test(codigoPostalTienda)) {
        errorCodigoPostalTienda.textContent = "El código postal debe ser un número de máximo 5 dígitos.";
        errorCodigoPostalTienda.style.color = 'red';
        return false; // Retorna falso si el código postal no cumple con el formato esperado
    } else {
        errorCodigoPostalTienda.textContent = "";
        return true; // Retorna verdadero si el código postal es válido
    }
}

    // Función para validar todo el formulario
    function validarFormularioTienda() {
        return validarNombreTienda() && validarDireccionTienda() && validarCodigoPostalTienda();
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
				window.location.href = 'https://farmasupply.desappweb.es/privada/eliminar/' + idUsuario;
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
				window.location.href = 'https://farmasupply.desappweb.es/privada/eliminar-moto/' + idMoto;
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
				window.location.href = 'https://farmasupply.desappweb.es/privada/eliminar-tienda/' + idTienda;
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
    // Clona la tabla original
    const tableClone = document.querySelector('.tableCustom').cloneNode(true);

    // Genera el PDF a partir del clon
    const options = {
        margin: 10,
        filename: 'Mis_Pedidos.pdf',
        image: { type: 'jpeg', quality: 0.98 },
        html2canvas: { scale: 2 },
        jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
    };
    html2pdf().from(tableClone).set(options).save().then(() => {
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


