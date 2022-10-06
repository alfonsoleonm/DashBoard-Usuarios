// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
    $('#usuarios_table').DataTable();

    //actualizar email del usuario
    actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
    document.getElementById('txtEmailUsuario').outerHTML = localStorage.email;
}

async function cargarUsuarios(){

      const request = await fetch('usuarios', {
        method: 'GET',
        headers: getHeaders()
      });
      const usuarios = await request.json();

      console.log(usuarios);
      let listadoUsuariosHTML = '';
      for(let usuario of usuarios){
        let usuariohtml = '<tr><td>'
        +usuario.id+'</td><td>'+
        usuario.nombre+' '+
        usuario.apellido
        +'</td><td>'+usuario.email
        +'</td><td>'+usuario.telefono
        +'</td><td><a href="#" onClick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>';

        listadoUsuariosHTML += usuariohtml;
      }

      document.querySelector('#usuarios_table tbody').outerHTML = listadoUsuariosHTML;

}
function getHeaders(){
    return {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
              'Authorization': localStorage.token
                   }
}
async function eliminarUsuario(id){

    if(confirm('Desea eliminar este usuario?')){
        const request = await fetch('usuarios/'+id, {
                    method: 'DELETE',
                    headers: getHeaders()
                  });
    }

    location.reload();



}