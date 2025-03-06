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


/*
 * Función: getSongs
 * Descripción: Llama a la API para obtener la lista de canciones.
 *              Luego, procesa la respuesta y llama a la función renderSongs.
 */
function toggleMenu() {
  console.log("toggle");
  var toggle = document.getElementById("menu")
  if (toggle.style.display == "none") {
    toggle.style.display = "block"
  } else {
    toggle.style.display = "none"
  }
}

async function getArtists(onlydata = false) {
  console.log("get");

  // URL del endpoint de la API que devuelve la lista de canciones.
  // Cambia la URL a la de tu API real si es necesario.
  const apiUrl = 'http://localhost:9000/artists';

  // Se realiza la petición a la API utilizando fetch.
  return fetch(apiUrl)
    .then(function (response) {
      // Paso 2: Verificar que la respuesta sea exitosa.
      if (!response.ok) {
        // Si no es exitosa, se lanza un error para que se capture en el catch.
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      // Paso 3: Convertir la respuesta a formato JSON para poder trabajar con ella.
      return response.json();
    })
    .then(function (artists) {
      // 'songs' es un array de objetos, donde cada objeto representa una canción.
      // Se llama a la función que se encarga de pintar (renderizar) los datos en el HTML.
      if (!onlydata) {
        renderartists(artists);
      }
      return artists
    })
    .catch(function (error) {
      // Paso 4: Manejo de errores.
      // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
      console.error('Error al cargar los artistas:', error);
      // También se muestra un mensaje de error en el contenedor HTML.
      document.getElementById('artists-container').innerHTML = '<p>Error al cargar las categorias.</p>';
    });
}

/*
 * Función: renderSongs
 * Descripción: Recorre el array de canciones recibido de la API y crea
 *              una tarjeta HTML para cada canción. Luego, añade cada tarjeta
 *              al contenedor definido en el HTML.
 *
 * Parámetro:
 *    songs - Array de objetos, donde cada objeto contiene datos de una canción.
 */
function renderartists(artists) {
  console.log("render");

  // Paso 5: Seleccionar el contenedor donde se mostrarán las tarjetas de canciones.
  var container = document.getElementById('artists-container');
  // Limpiar el contenedor por si ya tenía contenido previo.
  //container.innerHTML = '';

  if (!artists || artists.length <= 0) {

    var warning = document.createElement('p')

    container.innerHTML = '';
    warning.innerHTML = '<span class=empty-page> No hay categorías </span>'

    container.appendChild(warning)

  } else {
    container.innerHTML = '';
    // Paso 6: Recorrer el array de canciones.
    artists.forEach(function (artist) {
      // Crear un nuevo elemento <div> para la tarjeta de la canción.
      var card = document.createElement('div');
      // Agregar la clase "song-card" para aplicar los estilos CSS definidos.
      card.classList.add('artist-card');


      card.innerHTML =
        '<h2><strong>' + artist.name + '</strong></h2>' +
        // '<i>Modificar</i>'
        '<button id = artist-delete-' + artist.id + ' class = "delete"> Delete </button>' +
        '<button id = artist-edit-' + artist.id + ' class = "edit"> Edit </button>' +
        '<button id = artist-info-' + artist.id + ' class = "info">Info</button>' +
        '<div id = artist-popup-overlay> <div id = artist-popup-form> <button id = artist-close-btn class = close-window>X</button> <h2>Modificar</h2> <form><input type ="text" id = artist-popup-input> </form> <button type= "submit" id="artist-submit-btn">Submit</button> </div></div>' +
        '<div id = artist-overlay-info' + artist.id + '> <div class = "form-info" id = artist-form-info-' + artist.id + '> <button id = artist-close-info class=close-window>X</button> <h2>Información</h2> <h3> Nombre: ' + artist.name + '</h3></div></div>';
      console.log("Distinto de 1")





      // card.innerHTML =
      //   '<h2><strong>' + category.name + '</strong></h2>' +
      //   // '<i>Modificar</i>'
      //   '<button id = delete-' + category.id + ' class = "delete"> Delete </button>' +
      //   '<button id = cat-edit-' + category.id + ' class = "edit"> Edit </button>' +
      //   '<button id = info-' + category.id + ' class = "info">Info</button>' +
      //   '<div id = popup-overlay> <div id = popup-form> <button id = cat-close-btn class = close-window>X</button> <h2>Modificar</h2> <form><input type ="text" id = popup-input> </form> <button type= "submit" id="submit-btn">Submit</button> </div></div>' +
      //   '<div id = overlay-info' + category.id + '> <div class = "form-info" id = form-info-' + category.id + '> <button id = close-info class=close-window>X</button> <h2>Información</h2> <h3> Nombre: ' + category.name + '</h3></div></div>';


      container.appendChild(card);

    });
  }
}


document.addEventListener('DOMContentLoaded', function () {

  // Paso 9: Llamar a la función getSongs para iniciar el proceso cuando se carga la página.
  // if (window.location.href === "file:///C:/Users/miguel.urquiza/Desktop/Babelfy/front/index%201.html") {
  //   getArtists();
  // }
  getArtists();

});


document.addEventListener('click', function (event) {
  if (event.target && event.target.id.startsWith('artist-delete-')) {
    const categoryId = parseInt(event.target.id.split('-')[2])
    if (categoryId != 1) {
      deleteArtist(categoryId);
      // deleteMessage();
    }

  }
  if (event.target && event.target.id.startsWith('artist-edit-')) {
    currentId = parseInt(event.target.id.split('-')[2])
    console.log(currentId);
    artistOpenPopup(currentId);
    document.getElementById("artist-popup-input").setAttribute("maxlength", 20)

  }
  if (event.target && event.target.id === 'artist-submit-btn') {
    artistAcceptInput();
    document.getElementById("artist-add-input").setAttribute("maxlength", 20)

  }
  if (event.target && event.target.id === 'artist-close-btn') {
    artistClosePopup();
  }
  if (event.target && event.target.id === 'artist-add') {

    artistOpenAdd();
  }
  if (event.target && event.target.id === 'artist-add-submit') {
    artistAddInput();
    // addMessage();
  }
  if (event.target && event.target.id === 'artist-add-close-btn') {
    artistCloseAdd();
  }

  if (event.target && event.target.id.startsWith('artist-info-')) {
    console.log(event.target.id);
    currentArtist = parseInt(event.target.id.split('-')[2])
    console.log(currentArtist);
    openInfo(currentArtist);
    // artistsList(currentArtist);
  }


  if (event.target && event.target.id === 'artist-close-info') {
    artistCloseInfo();
  }

  if (event.target && event.target.id === 'artist-close-message') {
    closeMessage();
  }
});

function artistOpenPopup(id) {
  getArtistId(id, true)
    .then(function (value) {
      document.getElementById("artist-popup-input").value = value;
      document.getElementById("artist-popup-overlay").style.display = "block";
      
    })
    document.getElementById("artist-popup-input").value = "";
      document.getElementById("artist-popup-overlay").style.display = "block";
}

function artistOpenAdd() {
  document.getElementById("artist-add-input").value = "";
  document.getElementById("artist-add-overlay").style.display = "block";
}

function openInfo(currentCat) {
  document.getElementById("artist-overlay-info" + currentCat).style.display = "block";
  document.getElementById("artist-form-info-" + currentCat).style.display = "block";
  // artistsList(currentCat);
}

function artistClosePopup() {
  document.getElementById("artist-popup-overlay").style.display = "none";
}

function artistCloseAdd() {
  document.getElementById("artist-add-overlay").style.display = "none";
}

function artistCloseInfo() {
  //document.getElementById("overlay-info").style.display = "none";
  document.getElementById("artist-form-info-" + currentArtist).style.display = "none";
  // if (document.getElementById("table") != null) {
  //   document.getElementById("table").remove();
  // }

  console.log(currentArtist);
}

function artistAcceptInput() {
  let inputValue = document.getElementById("artist-popup-input").value;
  var regex = /[A-Za-z-0-9]/;
  if (inputValue == "") {
    alert("Introduce el nombre de la categoría")
    // artistClosePopup();
    // editMessage(false)
  } else if (!regex.test(inputValue)) {
    alert("Solo se permiten letras a la hora de modificar una clase")
  } else {
    editArtist(currentId, inputValue);
    artistClosePopup();
    // editMessage();

  }



}

function artistAddInput() {
  let inputName = document.getElementById("artist-add-input").value;
  var regex = /[A-Za-z-0-9]/;
  if (inputName == "") {
    alert("Introduce el nombre de la categoría")
    // artistCloseAdd();
    // addMessage(false)
  } else if (!regex.test(inputName)) {
    alert("Solo se permiten letras a la hora de crear una clase.")
  } else {
    addArtist(inputName);
    artistCloseAdd();
  }
  // addArtist(inputName)
  // artistCloseAdd();
}


// function addMessage(funcionando = true) {
//   if (!funcionando) {
//     document.getElementById("message-add").innerHTML = '<h2>La categoría no se ha podido crear</h2>' + '<button id="close-message">Cerrar</button>'
//   } else {
//     document.getElementById("message-add").innerHTML = '<h2>La categoría se ha podido crear correctamente</h2>' + '<button id="close-message">Cerrar</button>'
//   }
//   document.getElementById("message-overlay").style.display = "block"
//   document.getElementById("message-add").style.display = "block"
//   document.getElementById("message-delete").style.display = "none"
//   document.getElementById("message-edit").style.display = "none"
// }
// function deleteMessage() {
//   console.log("DELETE MESSAGE");

//   document.getElementById("message-overlay").style.display = "block"
//   document.getElementById("message-delete").style.display = "block"
//   document.getElementById("message-add").style.display = "none"
//   document.getElementById("message-edit").style.display = "none"
// }

// function editMessage(funcionando = true) {
//   if (!funcionando) {
//     document.getElementById("message-edit").innerHTML = '<h2>La categoría no se ha podido modificar</h2>' + '<button id="close-message" class="message-button">Cerrar</button>'
//   } else {
//     document.getElementById("message-edit").innerHTML = '<h2>Se ha modificado correctamente.</h2> ' + '<button id="close-message" class="message-button">Cerrar</button>'
//   }
//   document.getElementById("message-overlay").style.display = "block"
//   document.getElementById("message-edit").style.display = "block"
//   document.getElementById("message-delete").style.display = "none"
//   document.getElementById("message-add").style.display = "none"
// }
function closeMessage() {

  document.getElementById("message-overlay").style.display = "none";

}

function addArtist(name) {
  console.log(name);
  const apiUrl = 'http://localhost:9000/artists';
  fetch(apiUrl, {
    method: 'POST',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "name": name })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      if (text == 'Found') {
        alert("Ya existe una categoría con ese nombre")
      } else {
        // addMessage();
      }
      getArtists();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

function deleteArtist(id) {
  console.log("Delete", id);
  const apiUrl = 'http://localhost:9000/artists';
  fetch(apiUrl, {
    method: 'DELETE',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "id": id })
  })
    .then(function (response) {
      // Paso 2: Verificar que la respuesta sea exitosa.
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      console.log(response)
      return response.text();
    })
    .then(function () {
      console.log("Recargo")
      getartists();
    })
    .catch(function (error) {
      // Paso 4: Manejo de errores.
      // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
      console.error('Error al borrar la categoria:', error);
      // También se muestra un mensaje de error en el contenedor HTML.
      document.getElementById('artists-container').innerHTML = '<p>Error al borrar la categoria.</p>';
    });

}

function editArtist(id, name) {
  const apiUrl = 'http://localhost:9000/artists';
  fetch(apiUrl, {
    method: 'PUT',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "name": name, "id": id })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      console.log(text);
      if (text == "Found") {
        alert("Ya existe una categoría con ese nombre")
      } else {
        // editMessage();
      }
      getArtists();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

function getArtistId(id, name = true) {
  const apiUrl = 'http://localhost:9000/artists/' + id;
  var value;
  value = fetch(apiUrl, {
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
        return text.name;
      } else {
        getArtists();
      }
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
  return value
}

// function artistsList(category) {

//   fetch('http://localhost:9000/artists/' + category)
//     .then(function (response) {
//       if (!response.ok) {
//         throw new Error('No se pudo obtener la categoría: ' + response.statusText);
//       }
//       return response.json();  // Suponiendo que la respuesta está en formato JSON
//     })
//     .then(function (category) {
//       var table = document.createElement('table')
//       var tbody = document.createElement('tbody')
//       console.log(category)
//       table.setAttribute('id', 'table')
//       table.innerHTML =
//         '<thead> <th>Nombre</th> <th>Duración</th> <th>Artista</th><th> Albúm</th> <th>Fecha</th></thead>';

//       if (category.songs.length > 0) {
//         category.songs.forEach(function (song) {


//           var tr = document.createElement('tr');
//           tr.innerHTML = `
//             <td>${song.name}</td>
//             <td>${song.duration}</td>
//             <td>${song.artistName}</td>
//             <td>${song.albumName}</td>
//             <td>${reverseText(song.releaseDate)}</td>`
//           tbody.appendChild(tr);
//         });
//         table.appendChild(tbody)
//         document.getElementById("form-info-" + category.id).appendChild(table);
//       } else {
//         var message;
//         if (document.getElementById("asd") == null) {
//           message = document.createElement('h3')
//           message.id = "asd"
//         } else {
//           message = document.getElementById("asd")
//         }
//         message.innerHTML = '';
//         message.innerHTML = 'Esta categoría no contiene canciones';
//         document.getElementById("form-info-" + category.id).appendChild(message);
//       }


//     })

// }

function reverseText(text) {
  return text.split('-').reverse().join('-');

}









