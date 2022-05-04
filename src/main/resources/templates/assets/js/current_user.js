async function getCurrentUser() {
    const response = await fetch('http://localhost:8080/getCurrentUser');
    const user = await response.json();
    console.log(user.authorities);
    //navaBar(user);
}
getCurrentUser();
function navaBar(user) {
    document.querySelector('.navbar-brand text-white').innerHTML = '<b><span  text="${user.email}}"></span></b> with roles:<span text="${user.authorities}"></span>'
}


async function getCurrentUser() {
    fetch('http://localhost:8080/getCurrentUser').then(function (response) {
        response.json().then(function (data) {
            let user = data;
            console.log(data);
            return user;
        });
    }).catch(function (error) {
        console.log('Fetch Error:', error);
    });
}
let value  = getCurrentUser();
console.log();

async function getCurrentUserInfo() {
    let response = await fetch('http://localhost:8080/getCurrentUser')
    let content = await response.json
    console.log(content)
    let doc = document.querySelector('user-info');
    doc.innerHTML += '<td th:text="${content.id}"></td>'
    doc.innerHTML += '<td th:text="${content.name}"></td>'
    doc.innerHTML += '<td th:text="${content.lastname}"></td>'
    doc.innerHTML += '<td th:text="${content.age}"></td>'
    doc.innerHTML += '<td th:text="${content.email}"></td>'

}
//getCurrentUser()
getCurrentUserInfo()
/* 
fetch('http://localhost:8080/getCurrentUser')
    .then((res) => res.json()) 
    .then((data) => setUser(data));
function setUser({email, authorities}) {
    document.body.insertAdjacentHTML(
        "afterbegin",
        `
        <p class="navbar-brand text-white">
        <b><span"${email}"></span></b> with roles:
        <span"${authorities.map().role.map()}"></span>
        </p>
        `
    )
}
 */
