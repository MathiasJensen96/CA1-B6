import "./style.css"
import "bootstrap/dist/css/bootstrap.css"
import * as bootstrap from 'bootstrap';
import '@popperjs/core';
import { SERVER_URL } from './constants'

document.getElementById("all-content").style.display = "block"

/* 
  Add your JavaScript for all exercises Below or in separate js-files, which you must the import above
*/

/* JS For Person below */

let editModalElement = document.getElementById("editmodal")
let editModal = new bootstrap.Modal(editModalElement)
let addModalElement = document.getElementById("addmodal")
let addModal = new bootstrap.Modal(addModalElement)

document.getElementById("tablerows").addEventListener('click', e => {
  e.preventDefault();
  const node = e.target
  const name = node.getAttribute("name")
  const id = node.getAttribute("id")
  switch (name) {
    case "edit": editPerson(id); break;
    case "delete": deletePerson(id); break;
  }
})

document.getElementById("add-person-btn").addEventListener('click', e => {
  e.preventDefault()
  addModal.toggle()
  //addPerson()
})

document.getElementById("modal-add-save-btn").addEventListener('click', e => {
  const node = e.target
  addPerson()
})

 function addPerson() {

  const personObject = {
      firstName: document.getElementById("addfirstName").value,
      lastName: document.getElementById("addlastName").value,
      email: document.getElementById("addemail").value,
      address: {
          street: document.getElementById("addstreet").value,
          additionalInfo: "TODO",
          cityInfo: {
              zipCode: document.getElementById("addzip").value,
              city: "KBH"
          }
      },
      hobbies: [5],
      phones: [{number: document.getElementById("addphone").value, description: "TODO"}]
  }
  console.log(personObject)

  const options = makeOptions('POST', personObject)

  fetch(`${SERVER_URL}/person/`, options)
  .then(handleHttpErrors)
  .then(data => {
    addModal.toggle()
    getAllPersons()
  })
  .catch(errorHandling)
}

function editPerson(id) {

  fetch(`${SERVER_URL}/person/${id}`)
    .then(handleHttpErrors)
    .then(data => {
      document.getElementById("edit_id").value = data.id
      document.getElementById("firstName").value = data.firstName
      document.getElementById("lastName").value = data.lastName
      document.getElementById("email").value = data.email
      document.getElementById("phone").value = data.phones[0].number
      document.getElementById("phone_id").value = data.phones[0].id
      document.getElementById("address_id").value = data.address.id
      document.getElementById("street").value = data.address.street
      document.getElementById("zip").value = data.address.cityInfo.zipCode
      document.getElementById("city").value = data.address.cityInfo.city
      editModal.toggle()
    })
    .catch(errorHandling)
}


document.getElementById("model-edit-save-btn").addEventListener('click', updatePerson)

 function updatePerson() {

   const id = document.getElementById("edit_id").value

  const personObject = {
    id: id,
    email: document.getElementById("email").value,
    firstName: document.getElementById("firstName").value,
    lastName: document.getElementById("lastName").value,
    phone: [
      {
        id: document.getElementById("phone_id").value,
        number: document.getElementById("phone").value,
        description: "Cell"
      }],
    address: {
      id: document.getElementById("address_id").value,
      street: document.getElementById("street").value,
      additionalInfo: "4.12",
      cityInfo: {
        zipCode: document.getElementById("zip").value
      }
    }
  }

  console.log(personObject)

const options = makeOptions('PUT', personObject)

fetch(`${SERVER_URL}/person/${id}`, options)
  .then(handleHttpErrors)
  .then(data => {
    editModal.toggle()
    getAllPersons()
  })
  .catch(errorHandling)
}

function deletePerson(id) {
  alert('deletePerson: ' + id)
}


function getAllPersons() {

  fetch(`${SERVER_URL}/person/all`)
    .then(handleHttpErrors)
    .then(data => {
      // Lav tabel rækker med data
      const allRows = data.all.map(p => getPersonTableRow(p))
      document.getElementById("tablerows").innerHTML = allRows.join("")
    })
    .catch(errorHandling)
}


function getPersonTableRow(p) {
  return `<tr>
  <td>${p.id}</td>
  <td>${p.firstName}</td>
  <td>${p.lastName}</td>
  <td>${p.email}</td>
  <td>${p.phones[0].number}</td>
  <td>${p.address.street}</td>
  <td>${p.address.cityInfo.zipCode}</td>
  <td>${p.address.cityInfo.city}</td>
  <td>
    <input id="${p.id}" type="button" name="edit" value="Edit"/>
    <input id="${p.id}" type="button" name="delete" value="Delete"/>
  </td>
  </tr>`
}







/* JS For Exercise-2 below */

// function getAllHobbies() {

//   fetch(`${SERVER_URL}/person/all`)
//     .then(handleHttpErrors)
//     .then(data => {
//       // Lav tabel rækker med data
//       const allRows = data.all.map(p => getPersonTableRow(p))
//       document.getElementById("tablerows").innerHTML = allRows.join("")
//     })
//     .catch(errorHandling)
// }

/* JS For Exercise-3 below */


/* Helper functions */

function makeOptions(method, body) {
  var opts = {
    method: method,
    headers: {
      "Content-type": "application/json",
      "Accept": "application/json"
    }
  }
  if (body) {
    opts.body = JSON.stringify(body);
  }
  return opts;
}

function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() })
  }
  return res.json();
}

function errorHandling(err) {
  if (err.status) {
    err.fullError.then(e => console.log(e.message))
  }
  else {
    console.log("Network error")
  }
}


/* 
Do NOT focus on the code below, UNLESS you want to use this code for something different than
the Period2-week2-day3 Exercises
*/

function hideAllShowOne(idToShow) {
  document.getElementById("about_html").style = "display:none"
  document.getElementById("person").style = "display:none"
  document.getElementById("ex2_html").style = "display:none"
  document.getElementById("ex3_html").style = "display:none"
  document.getElementById(idToShow).style = "display:block"
}

function menuItemClicked(evt) {
  const id = evt.target.id;
  switch (id) {
    case "ex1": hideAllShowOne("person"); getAllPersons(); break
    case "ex2": hideAllShowOne("ex2_html"); break
    case "ex3": hideAllShowOne("ex3_html"); break
    default: hideAllShowOne("about_html"); break
  }
  evt.preventDefault();
}
document.getElementById("menu").onclick = menuItemClicked;
hideAllShowOne("about_html");