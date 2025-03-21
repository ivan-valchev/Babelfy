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
      renderSongs(songs)

    })
    .catch(function (error) {
      // Paso 4: Manejo de errores.
      // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
      console.error('Error al cargar las canciones:', error);
      // También se muestra un mensaje de error en el contenedor HTML.
      document.getElementById('song-container').innerHTML = '<p>Error al cargar las canciones.</p>';
    });
}

async function renderSongs(songs) {
  // Paso 5: Seleccionar el contenedor donde se mostrarán las tarjetas de canciones.
  var container = document.getElementById('songs-container');
  // Limpiar el contenedor por si ya tenía contenido previo.
  container.innerHTML = '';

  if (!songs || songs.length <= 0) {
    var warning = document.createElement('p');
    warning.innerHTML = '<span class=empty-page> No hay canciones </span>';
    container.appendChild(warning);
  } else {
    // Paso 6: Recorrer el array de canciones.
    for (const song of songs) {
      // Crear un nuevo elemento <div> para la tarjeta de la canción.
      var card = document.createElement('div');
      card.classList.add('song-card');
      card.innerHTML = '<h2>' + song.name + '</h2>' +
        '<button id=song-delete-' + song.id + ' class=delete>Delete</button>' +
        '<button id =song-edit-' + song.id + ' class =edit>Edit</button>' +
        '<button id=song-btn-info-' + song.id + ' class = info>Info</button>' +
        '<div id=song-info-overlay-' + song.id + ' class = song-info-overlay><div id =song-info-form-' + song.id + ' class = "song-info-form"> <button id="song-close-info" class=close-window>X</button><h2>Información</h2> <h3>Nombre:' + song.name + '</h3><h3 id = song-duration>Duración:' + song.duration + ' minutos</h3><h3>Artista:' + song.artistName + '</h3><h3>Albúm: ' + song.albumName + '</h3><h3>Fecha Lanzamiento: ' + reverseText(song.releaseDate ) + ' </h3><h3>Categoría: ' + song.categoryName + '</h3></div></div>' +
        '<div id = edit-overlay> <div class = "edit-form" id = edit-form-' + song.id + '> <button id = close-btn class=close-window>X</button> <h2>Modificar</h2> <form><span>Nombre</span><br><input type ="text"  maxlength="20" id = edit-name-input><br><span>Duración</span><br><input type="number" min="1" id="edit-duration-input"  placeholder="Duration"> <br><span>Artista</span><br>  <input type="text"  maxlength="20" id="edit-artist-input" placeholder="Artist"><br><span>Album</span><br> <input type="text"  maxlength="20" id="edit-album-input" placeholder="Album"><br><span>Fecha</span><br> <input type="date" class=date id="edit-date-input" placeholder="YYYY-MM-DD"> <br><span>Categoría</span><br> <select id="edit-selector-select"></select> </form> <button type= "submit" id="song-btn-submit">Submit</button> </div></div>';
        

      // Paso 8: Añadir la tarjeta al contenedor.
      container.appendChild(card);

      // Retrieve the category data asynchronously
    }
  }

  // Return a resolved promise to indicate that the rendering is done.
  return Promise.resolve();
}


document.addEventListener('DOMContentLoaded', function () {

  getSongs();

});

document.addEventListener('click', function (event) {
  if (event.target && event.target.id == 'Song-add') {
    openAddPopup();
   
  }
  if (event.target && event.target.id == 'song-add-close-btn') {
    closeAddPopup();
  }
  if (event.target && event.target.id == 'song-add-submit') {
    addInputSong();
  }
  if (event.target && event.target.id.startsWith('song-btn-info-')) {
    index = parseInt(event.target.id.split('-')[3])
    console.log(index);
    songOpenInfo(index);
  }
  if (event.target && event.target.id === 'song-close-info') {
    songCloseInfo();
  }
  if (event.target && event.target.id.startsWith('song-delete-')) {
    id = parseInt(event.target.id.split('-')[2])
    console.log(id);
    // deleteSong(id);
    deletePopUp(id);
    // deleteSongMessage();

  }
  if (event.target && event.target.id.startsWith('song-edit-')) {
    idEdit = parseInt(event.target.id.split('-')[2])
    openEdit(idEdit);
    var currenDate = new Date();
    var year = currenDate.getFullYear();
    var month = String(currenDate.getMonth()+1).padStart(2,'0')
    var day = String(currenDate.getDate()).padStart(2,'0')

    var formattedDate = year + '-' + month +'-'+ day;

    document.getElementById('edit-date-input').setAttribute('max',formattedDate)
  }
  if (event.target && event.target.id === "close-btn") {
    closeEdit();
  }
  if (event.target && event.target.id === 'song-btn-submit') {
    submitEdit();
  }
  if (event.target && event.target.id === 'close-message') {
    closeMessage();
  }

  if(event.target&&event.target.id === 'song-accept-delete'){
    deleteSong(id);
    console.log(id);
    
    // var prueba =parseInt(document.getElementById.startsWith("song-delete-").split("-")[2]);
    // console.log(prueba);  
    closeDeletePopup();
    deleteSongMessage();
  }
  if(event.target&&event.target.id === 'song-decline-delete'){
    closeDeletePopup();
  }
  


})


function deletePopUp(id) {
  document.getElementById("popup-delete").style.display='block';
  console.log(id);
  
  // deleteSongMessage();
  // closeDeletePopup();
  return id;
}
function closeDeletePopup() {
  document.getElementById("popup-delete").style.display="none";
}

function openAddPopup() {
  console.log("POP");
  var currenDate = new Date();
  var year = currenDate.getFullYear();
  var month = String(currenDate.getMonth()+1).padStart(2,'0')
  var day = String(currenDate.getDate()).padStart(2,'0')

  var formattedDate = year + '-' + month +'-'+ day;

  document.getElementById('add-date-input').setAttribute('max',formattedDate)

  document.getElementById("song-add-overlay").style.display = "block";
  document.getElementById("add-name-input").value = "";
  document.getElementById("add-duration-input").value = "";
  document.getElementById("add-artist-input").value = "";
  document.getElementById("add-album-input").value = "";
  document.getElementById("add-date-input").value = "";
  cleanOptions();
  fillList();
}

function closeAddPopup() {
  document.getElementById("song-add-overlay").style.display = "none";
}
function songOpenInfo(index) {
  console.log(index)
  document.getElementById("song-info-overlay-" + index).style.display = "block";
  document.getElementById("song-info-form-" + index).style.display = "block";
}
function songCloseInfo() {
  document.getElementById("song-info-overlay-"+index).style.display="none";
  document.getElementById("song-info-form-" + index).style.display = "none";
  console.log(index);
}
var nameData;
function openEdit(id) {
  console.log(id);
  body = getById(id, true)
    .then(function (body) {
      console.log(body)
      document.getElementById("edit-name-input").value = body.name;
      nameData = body.name;
      document.getElementById("edit-duration-input").value = body.duration;
      document.getElementById("edit-artist-input").value = body.artistName;
      document.getElementById("edit-album-input").value = body.albumName;
      document.getElementById("edit-date-input").value = body.releaseDate;
      
      console.log(body.categoryId)
      cleanEdit();
      fillEditList()
      .then(function () {
        console.log("HOLAA");
        document.getElementById("edit-selector-select").value = body.categoryId;
      })
      console.log(document.getElementById("edit-selector-select").value);

      document.getElementById("edit-overlay").style.display = "block";


    })

}
function closeEdit() {
  document.getElementById('edit-overlay').style.display = "none";
}
function submitEdit(edited = false) {
  var regex = /[A-Za-z-0-9]/;
  var num = -1;

  let editName = document.getElementById("edit-name-input").value;
  let editDuration = document.getElementById("edit-duration-input").value;
  let editArtist = document.getElementById("edit-artist-input").value;
  let editAlbum = document.getElementById("edit-album-input").value;
  let editDate = document.getElementById("edit-date-input").value;
  let editCategory = document.getElementById("edit-selector-select").value;
  var oldName = manageEditInput(nameData)
  console.log("Name 1: "+oldName);
  

  if (editName == "") {
    num = 0;
  } else if (editDuration == "") {
    num = 1;
  } else if (editArtist == "") {
    num = 2;
  } else if (editAlbum == "") {
    num = 3;
  } else if (editDate == "") {
    num = 4;
  } else if (editCategory == "") {
    num = 5
  }

  if (num >= 0) {
    switch (num) {
      case 0:
        alert("Rellene el nombre de la canción")
        break;
      case 1:
        alert("Rellene la duración de la canción")
        break;
      case 2:
        alert("Rellene el nombre del artista")
        break;
      case 3:
        alert("Rellene el nombre del álbum")
        break;
      case 4:
        alert("Rellene la fecha de lanzamiento")
        break;
      case 5:
        alert("Rellene la categoria")
        break;
      default:
        alert("Falla")
        break;
    }
  } else if (!regex.test(editName) || !regex.test(editArtist) || !regex.test(editAlbum)) {
    alert("Introduzca un caracter válido");
  }
  else {
    console.log("Nueva cat: "+editCategory)
    editSong(idEdit, editName, editDuration, editArtist, editAlbum, editDate, editCategory,edited,oldName);

  }

}

function addInputSong() {
  var regex = /[A-Za-z-0-9]/;
  var num = -1;
  let inputName = document.getElementById("add-name-input").value;
  let inputDuration = document.getElementById("add-duration-input").value;
  let inputArtist = document.getElementById("add-artist-input").value;
  let inputAlbum = document.getElementById("add-album-input").value;
  let inputDate = document.getElementById("add-date-input").value;
  let categoryId = document.getElementById("categories-list").value;
  console.log(categoryId);

  if (inputName == "") {
    num = 0;
  } else if (inputDuration == "") {
    num = 1;
  } else if (inputArtist == "") {
    num = 2;
  } else if (inputAlbum == "") {
    num = 3;
  } else if (inputDate == "") {
    num = 4
  }

  if (num >= 0) {
    switch (num) {
      case 0:
        alert("Rellene el nombre de la canción")
        break;
      case 1:
        alert("Rellene la duración de la canción")
        break;
      case 2:
        alert("Rellene el nombre del artista")
        break;
      case 3:
        alert("Rellene el nombre del álbum")
        break;
      case 4:
        alert("Rellene la fecha de lanzamiento")
        break;
      case 5:
        alert("Seleccione una categoría")
        break;
      default:
        alert("Falla")
        break;
    }
  } else if (!regex.test(inputName) || !regex.test(inputArtist) || !regex.test(inputAlbum)) {
    alert("Introduzca un caracter válido");
  }
  else {
    addSong(inputName, inputDuration, inputArtist, inputAlbum, inputDate, categoryId);
    closeAddPopup();
    // songAddMessage();
  }


}

function addSong(name, duration, artistName, albumName, releaseDate, categoryId) {
  const apiUrl = 'http://localhost:9000/songs';
  fetch(apiUrl, {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "name": name, "duration": duration, "artistName": artistName, "albumName": albumName, "releaseDate": releaseDate, "categoryId": categoryId })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      if(text == 'Found'){
        alert("No se puede crear la canción, ya existe una con ese nombre")
      }else{
        songAddMessage();
      }
      getSongs();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}
function editSong(id, name, duration, artistName, albumName, releaseDate, category,edited,oldName) {
  const apiUrl = 'http://localhost:9000/songs';
  fetch(apiUrl, {
    method: 'PUT',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "id": id, "name": name, "duration": duration, "artistName": artistName, "albumName": albumName, "releaseDate": releaseDate, "categoryId": category })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      if(text == 'Found'&& oldName == ""){
        console.log("Alertaaa");
        console.log("Nombre:"+oldName);
        
        alert("No se puede modificar la canción, ya existe una con ese nombre")
      }else if(text == 'Found'&& oldName == "Same"){
        editSongMessage();
      }
      getSongs();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}
function deleteSong(id) {
  const apiUrl = 'http://localhost:9000/songs';
  console.log("delete" + id);
  fetch(apiUrl, {
    method: 'DELETE',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "id": id })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
     
      getSongs();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

async function getById(id, name = true) {
  const apiUrl = 'http://localhost:9000/songs/' + id;
  return fetch(apiUrl, {
    method: 'GET',

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.json();
    })
    .then(function (text) {
      if (name) {
        return text;
      } else {
        // songAddMessage();
      }
      getSongs();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

function reverseText(text) {
  return text.split('-').reverse().join('-');

}

function deleteSongMessage() {
  console.log("DELETE MESSAGE");

  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("song-message-delete").style.display = "block"
  document.getElementById("song-message-add").style.display = "none"
  document.getElementById("song-message-edit").style.display = "none"
}
function editSongMessage() {
  document.getElementById("song-message-edit").innerHTML = '<h2>La canción se ha modificado correctamente</h2>' + '<button id="close-message" class="message-button">Cerrar</button>'
  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("song-message-delete").style.display = "none"
  document.getElementById("song-message-add").style.display = "none"
  document.getElementById("song-message-edit").style.display = "block"
}

function songAddMessage() {
  document.getElementById("song-message-add").innerHTML = '<h2>La canción se ha creado correctamente</h2>' + '<button class=message-button id="close-message">Cerrar</button>'
  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("song-message-delete").style.display = "none"
  document.getElementById("song-message-add").style.display = "block"
  document.getElementById("song-message-edit").style.display = "none"
}

function closeMessage() {
  document.getElementById("message-overlay").style.display = "none";
}



function fillList() {
  var list = document.getElementById("categories-list");
  getCategories(true)
    .then(function (data) {
      var optionFirst = document.createElement('option')
      console.log(data)
      // optionFirst.classList.add("categories-options")
      // optionFirst.value = -1
      // optionFirst.textContent = ''
      // list.appendChild(optionFirst)

      data.forEach(function (category) {
        var option = document.createElement('option')
        option.classList.add("categories-options")
        option.value = category.id
        option.textContent = category.name
        list.appendChild(option)
      })
    })
}
async function fillEditList() {
  var list2 = document.getElementById("edit-selector-select");
  return getCategories(true)
    .then(function (data) {
      var firstOption = document.createElement('option')
      console.log(data)
      // firstOption.classList.add("categories-options")
      // firstOption.value = -1
      // firstOption.textContent = 'None'
      // list2.appendChild(firstOption)

      data.forEach(function (category) {
        var option2 = document.createElement('option')
        option2.classList.add("categories-options")
        option2.value = category.id
        option2.textContent = category.name
        list2.appendChild(option2)
      })
      return true;
    })
}


function cleanOptions() {
  let list = document.getElementById("categories-list");
  let options = list.getElementsByTagName("option")
  for (var i = options.length; i--;) {
    list.removeChild(options[i])
  }

}

  
function cleanEdit(){
  let list2 = document.getElementById("edit-selector-select")
  let options = list2.getElementsByTagName("option")
  for(var i = options.length;i--;){
    list2.removeChild(options[i]);
  }
}




function manageEditInput(text){
    var result ="";
    // var textBox = document.getElementById("edit-name-input");
    // var oldText = textBox.value;

    if(text == document.getElementById("edit-name-input").value){
      result = "Same"
    }
    
    console.log("Result: "+result);
    

    return result;
  
}

// document.addEventListener("input",function (event){
//   var result =false;
//   var textBox = document.getElementById("edit-name-input");
//   var oldText = textBox.value;
//   if(event.target && event.target.id === "edit-name-input" &&(textBox!==oldText) ){
//     alert("Modified")
//     result = true;
    
//   }

//   return result;
// })