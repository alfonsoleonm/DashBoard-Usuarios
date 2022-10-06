// Call the dataTables jQuery plugin
$(document).ready(function() {
    //onready
});

async function registrarUsuario(){
       let datos = {};
       datos.nombre = document.getElementById('txtNombre').value;
       datos.apellido = document.getElementById('txtApellido').value;
       datos.email = document.getElementById('txtEmail').value;
       datos.telefono = document.getElementById('txtTelefono').value;
       datos.password = document.getElementById('txtPassword').value;

       let passwordRepeat = document.getElementById('txtRepetirPassword').value;

       if(passwordRepeat!=datos.password){
        alert('Las contrasenas no coinciden');
        return;
       }

      const request = await fetch('usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });

      alert("La cuenta de "+datos.nombre+" ha sido registrado con exito");
      window.location.href = 'login.html';



}

