// Call the dataTables jQuery plugin
$(document).ready(function() {
    //onready
});

async function iniciarSesion(){
       let datos = {};
       datos.email = document.getElementById('txtEmail').value;
       datos.password = document.getElementById('txtPassword').value;

      const request = await fetch('login', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
      });
      const respuesta = await request.text();
      //Error 401 significa que hay un problema de autenticacion con el server
      if(respuesta!="FAIL"){
      //save the token info
        localStorage.token = respuesta;
        localStorage.email = datos.email;

        window.location.href = 'usuarios.html';
      }else{
        alert('Las credenciales son incorrectas');
      }

}
