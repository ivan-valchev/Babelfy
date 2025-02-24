/*
 * Archivo: app.js
 * Descripción: Este archivo JavaScript se encarga de:
 *              1. Llamar a la API para obtener una lista de canciones.
 *              2. Procesar la respuesta de la API (en formato JSON).
 *              3. Crear y pintar en el HTML una tarjeta (card) para cada canción.
 * Cada paso se explica en detalle mediante comentarios.
 */

/* 
 * Paso 1: Esperar a que el documento HTML se cargue completamente.
 * Esto asegura que todos los elementos (como el contenedor) estén disponibles.
 */
document.addEventListener('DOMContentLoaded', function () {

  /*
   * Función: getSongs
   * Descripción: Llama a la API para obtener la lista de canciones.
   *              Luego, procesa la respuesta y llama a la función renderSongs.
   */
  function getSongs() {
    // URL del endpoint de la API que devuelve la lista de canciones.
    // Cambia la URL a la de tu API real si es necesario.
    const apiUrl = 'http://localhost:9000/songs';

    // Se realiza la petición a la API utilizando fetch.
    fetch(apiUrl)
      .then(function (response) {
        // Paso 2: Verificar que la respuesta sea exitosa.
        if (!response.ok) {
          // Si no es exitosa, se lanza un error para que se capture en el catch.
          throw new Error('Error en la respuesta de la API: ' + response.statusText);
        }
        // Paso 3: Convertir la respuesta a formato JSON para poder trabajar con ella.
        return response.json();
      })
      .then(function (songs) {
        // 'songs' es un array de objetos, donde cada objeto representa una canción.
        // Se llama a la función que se encarga de pintar (renderizar) los datos en el HTML.
        renderSongs(songs);
      })
      .catch(function (error) {
        // Paso 4: Manejo de errores.
        // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
        console.error('Error al cargar las canciones:', error);
        // También se muestra un mensaje de error en el contenedor HTML.
        document.getElementById('song-container').innerHTML = '<p>Error al cargar las canciones.</p>';
      });
  }

  function renderSongs(songs) {
    // Paso 5: Seleccionar el contenedor donde se mostrarán las tarjetas de canciones.
    var container = document.getElementById('songs-container');
    // Limpiar el contenedor por si ya tenía contenido previo.
    container.innerHTML = '';

    if (!songs || songs.length <= 0) {

      var warning = document.createElement('p')

      warning.innerHTML = '<span> No hay canciones </span>'

      container.appendChild(warning)
      
    } else {
      // Paso 6: Recorrer el array de canciones.
      songs.forEach(function (song) {
        // Crear un nuevo elemento <div> para la tarjeta de la canción.
        var card = document.createElement('div');
        // Agregar la clase "song-card" para aplicar los estilos CSS definidos.
        card.classList.add('song-card');

        card.innerHTML = '<h2>' + song.name + '</h2>'+
        '<button id=song-btn-info-'+song.id+' class = info>Info</button>'+
        '<div id=song-info-overlay-'+song.id+'><div id =song-info-form-'+song.id+' class = "song-info-form"> <button id="song-close-info" class=button>X</button><h2>Información</h2> <h3>Nombre:'+song.name+'</h3><h3>Duración:'+song.duration+' minutos</h3><h3>Artista:'+song.artistName+'</h3><h3>Albúm: '+song.albumName+'</h3><h3>Fecha Lanzamiento: '+song.releaseDate+' </h3></div></div>';

        // Paso 8: Añadir la tarjeta al contenedor.
        container.appendChild(card);
      });
    }
  }

  // Paso 9: Llamar a la función getSongs para iniciar el proceso cuando se carga la página.
  getSongs();

});

document.addEventListener('click',function(event){

if(event.target && event.target.id == 'add'){
  openAddPopup();
}
if(event.target && event.target.id == 'add-close-btn'){
  closeAddPopup();
}
if(event.target && event.target.id == 'add-submit'){
  addInputSong();
  closeAddPopup();
}
if(event.target && event.target.id.startsWith('song-btn-info-')){
  index = parseInt(event.target.id.split('-')[3])
  console.log(index);
  openInfo(index);
}
if(event.target && event.target.id === 'song-close-info'){
  closeInfo();
}

})

function openAddPopup(){
  document.getElementById("add-overlay").style.display="block";
}

function closeAddPopup(){
  document.getElementById("add-overlay").style.display = "none";
}
function openInfo(index){
  document.getElementById("song-info-overlay-"+index).style.display = "block";
  document.getElementById("song-info-form-"+index).style.display = "block";
}
function closeInfo(){
  document.getElementById("song-info-form-"+index).style.display = "none";
  console.log(index);
}

function addInputSong(){
  let inputName = document.getElementById("add-name-input").value;
  let inputDuration = document.getElementById("add-duration-input").value;
  let inputArtist = document.getElementById("add-artist-input").value;
  let inputAlbum = document.getElementById("add-album-input").value;
  let inputDate = document.getElementById("add-date-input").value;

  addSong(inputName,inputDuration,inputArtist,inputAlbum,inputDate);
}

function addSong(name,duration,artistName,albumName,releaseDate) {
  const apiUrl = 'http://localhost:9000/songs';
  fetch(apiUrl, {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "name": name ,"duration":duration,"artistName":artistName,"albumName":albumName,"releaseDate":releaseDate})

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      // if(text == 'Found'){
      //   alert("No se puede crear la canción, ya existe una con ese nombre")
      // }else{
      //   addMessage();
      // }
      getSongs();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}
