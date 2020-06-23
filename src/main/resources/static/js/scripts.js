function mostrarMensaje(texto,color){
	var segundos = 7;
	var mensaje = $('#mensajeRespuesta');
	mensaje.text(""+texto);
	mensaje.css("background-color",color);
	mensaje.show();
	setTimeout(function() {
		mensaje.hide();
	}, segundos * 1000);
}

function mensajeConfirmacion(mensaje){
	window.history.pushState({}, document.title, window.location.pathname);
	if (mensaje != ""){		
		mostrarMensaje(mensaje,"green");
	}
}

function validarCampos(){
	var valido = true;
	
	$('#idCliente').css("border","1px solid #ced4da");				
	if ($('#idCliente').val() == 0){
		$('#idCliente').css("border","1px solid red");
		valido = false;
	}

	$('#idJuego').css("border","1px solid #ced4da");
	if ($('#idJuego').val() == 0){
		$('#idJuego').css("border","1px solid red");
		valido = false;
	}

	$('#dias').css("border","1px solid #ced4da");				
	if ($('#dias').val() == ""){
		$('#dias').css("border","1px solid red");
		valido = false;
	}
	
	if (!valido){
		mostrarMensaje("Debe ingresar todos los campos obligatorios","red");
	}
	
	return valido;
}