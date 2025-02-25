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

function getCategories() {
  console.log("get");
  
  // URL del endpoint de la API que devuelve la lista de canciones.
  // Cambia la URL a la de tu API real si es necesario.
  const apiUrl = 'http://localhost:9000/categories';

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
    .then(function (categories) {
      // 'songs' es un array de objetos, donde cada objeto representa una canción.
      // Se llama a la función que se encarga de pintar (renderizar) los datos en el HTML.
      renderCategories(categories);
    })
    .catch(function (error) {
      // Paso 4: Manejo de errores.
      // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
      console.error('Error al cargar las categorias:', error);
      // También se muestra un mensaje de error en el contenedor HTML.
      document.getElementById('categories-container').innerHTML = '<p>Error al cargar las categorias.</p>';
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
function renderCategories(categories) {
  console.log("render");
  
  // Paso 5: Seleccionar el contenedor donde se mostrarán las tarjetas de canciones.
  var container = document.getElementById('categories-container');
  // Limpiar el contenedor por si ya tenía contenido previo.
  //container.innerHTML = '';

  if (!categories || categories.length <= 0) {

    var warning = document.createElement('p')

    container.innerHTML = '';
    warning.innerHTML = '<span> No hay canciones </span>'

    container.appendChild(warning)

  } else {
    container.innerHTML = '';
    // Paso 6: Recorrer el array de canciones.
    categories.forEach(function (category) {
      // Crear un nuevo elemento <div> para la tarjeta de la canción.
      var card = document.createElement('div');
      // Agregar la clase "song-card" para aplicar los estilos CSS definidos.
      card.classList.add('category-card');


      // Paso 7: Asignar el contenido HTML de la tarjeta.
      // Se muestran los datos: título, artista, año y categoría.
      card.innerHTML =
        '<h2><strong>' + category.name + '</strong></h2>' +
        // '<i>Modificar</i>'
        '<button id = delete-' + category.id + ' class = "delete"> Delete </button>' +
        '<button id = edit-' + category.id + ' class = "edit"> Edit </button>' +
        '<button id = info-'+category.id+' class = "info">Info</button>'+
        '<div id = popup-overlay> <div id = popup-form> <button id = close-btn>X</button> <p>Modificar</p> <form><input type ="text" id = popup-input> </form> <button type= "submit" id="submit-btn">Submit</button> </div></div>'+
        '<div id = overlay-info'+category.id+'> <div class = "form-info" id = form-info-'+category.id+'> <button id = close-info>X</button> <h2>Información</h2> <h3> Nombre: '+category.name+'</h3></div></div>';

      container.appendChild(card);


    });
  }
}


document.addEventListener('DOMContentLoaded', function () {

  // Paso 9: Llamar a la función getSongs para iniciar el proceso cuando se carga la página.
  getCategories();

});


document.addEventListener('click', function (event) {    
  if (event.target && event.target.id.startsWith('delete-')) {
    const categoryId = parseInt(event.target.id.split('-')[1])
    deleteCategory(categoryId);
    deleteMessage();
  }
  if (event.target && event.target.id.startsWith('edit-')) {
    currentId = parseInt(event.target.id.split('-')[1])
    console.log(currentId);
    openPopup(currentId);
    
  }
  if (event.target && event.target.id === 'submit-btn') {
    acceptInput();
    
  }
  if (event.target && event.target.id === 'close-btn') {
    closePopup();
  }
  if (event.target && event.target.id === 'add') {
    
    openAdd();
  }
  if (event.target && event.target.id === 'add-submit') {
    addInput();
    // addMessage();
  }
  if (event.target && event.target.id === 'add-close-btn') {
    closeAdd();
  }
 
  if(event.target &&  event.target.id.startsWith('info-')){
    console.log(event.target.id);
    currentCat = parseInt(event.target.id.split('-')[1])
    console.log(currentCat);
    openInfo(currentCat);
  }


  if(event.target && event.target.id === 'close-info'){      
    closeInfo();
  } 

  if(event.target && event.target.id === 'close-message'){
    closeMessage();
  }
});

function openPopup(id) {
  getCategoryId(id,true)
  .then(function(value) {
    document.getElementById("popup-input").value = value;
    document.getElementById("popup-overlay").style.display = "block";
  })
}

function openAdd() {
  document.getElementById("add-input").value = "";
  document.getElementById("add-overlay").style.display = "block";
}

function openInfo(currentCat){    
  document.getElementById("overlay-info"+currentCat).style.display = "block";
  document.getElementById("form-info-"+currentCat).style.display = "block";
}

function closePopup() {
  document.getElementById("popup-overlay").style.display = "none";
}

function closeAdd() {
  document.getElementById("add-overlay").style.display = "none";
}

function closeInfo(){
  //document.getElementById("overlay-info").style.display = "none";
  document.getElementById("form-info-"+currentCat).style.display = "none";
  console.log(currentCat);
}

function acceptInput() {
  let inputValue = document.getElementById("popup-input").value;
  var regex = /[A-Za-z]/;
  if(inputValue == ""){
    closePopup();
    editMessage(false)
  }else if(!regex.test(inputValue)){
    alert("Solo se permiten letras a la hora de modificar una clase")
  } else{
    editCategory(currentId, inputValue);
    closePopup();
    // editMessage();
  
  }
  
 
  
}

function addInput() {
  let inputName = document.getElementById("add-input").value;
  var regex = /[A-Za-z]/;
  if(inputName ==""){
    closeAdd();
    addMessage(false)
  }else if(!regex.test(inputName)){
    alert("Solo se permiten letras a la hora de crear una clase.")
  }else{
    addCategory(inputName);
    closeAdd();
  }
  // addCategory(inputName)
  // closeAdd();
}


function addMessage(funcionando = true){
  if(!funcionando){
    document.getElementById("message-add").innerHTML = '<h2>La categoría no se ha podido crear</h2>' +'<button id="close-message">Cerrar</button>'
  }else{
    document.getElementById("message-add").innerHTML = '<h2>La categoría se ha podido crear correctamente</h2>' +'<button id="close-message">Cerrar</button>'
  }
  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("message-add").style.display = "block"
  document.getElementById("message-delete").style.display = "none"
  document.getElementById("message-edit").style.display = "none"
}
function deleteMessage(){
  console.log("DELETE MESSAGE");
  
  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("message-delete").style.display = "block"
  document.getElementById("message-add").style.display = "none"
  document.getElementById("message-edit").style.display = "none"
}

function editMessage(funcionando = true){
  if(!funcionando){
    document.getElementById("message-edit").innerHTML = '<h2>La categoría no se ha podido modificar</h2>' +'<button id="close-message" class="message-button">Cerrar</button>'
  }else{
    document.getElementById("message-edit").innerHTML = '<h2>Se ha modificado correctamente.</h2> '+'<button id="close-message" class="message-button">Cerrar</button>'
  }
  document.getElementById("message-overlay").style.display = "block"
  document.getElementById("message-edit").style.display = "block"
  document.getElementById("message-delete").style.display = "none"
  document.getElementById("message-add").style.display = "none"
}
function closeMessage(){

  document.getElementById("message-overlay").style.display = "none";

}

function addCategory(name) {
  console.log(name);
  const apiUrl = 'http://localhost:9000/categories';
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
      if(text == 'Found'){
        alert("No se puede crear la categoria, ya existe una con ese nombre")
      }else{
        addMessage();
      }
      getCategories();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

function deleteCategory(id) {
  console.log("Delete", id);
  const apiUrl = 'http://localhost:9000/categories';
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
      getCategories();
    })
    .catch(function (error) {
      // Paso 4: Manejo de errores.
      // Si ocurre algún error durante la petición o la conversión, se muestra en la consola.
      console.error('Error al borrar la categoria:', error);
      // También se muestra un mensaje de error en el contenedor HTML.
      document.getElementById('categories-container').innerHTML = '<p>Error al borrar la categoria.</p>';
    });

}

function editCategory(id, name) {
  const apiUrl = 'http://localhost:9000/categories';
  fetch(apiUrl, {
    method: 'PUT',
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ "name": name, "id":id })

  })
    .then(function (response) {
      if (!response.ok) {
        throw new Error('Error en la respuesta de la API: ' + response.statusText);
      }
      return response.text();
    })
    .then(function (text) {
      console.log(text);
      if(text == "Found"){
        alert("No se puede modificar la categoría.")
      }else{
        editMessage();
      }
      getCategories();
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })
}

function getCategoryId(id,name = true){
  const apiUrl = 'http://localhost:9000/categories/' + id ;
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
        getCategories();
      }
    })
    .catch(function (error) {
      console.error('Error editing category', error)
    })    
  return value
} 

