
function showListUsers() {
    const tabListUsers = document.getElementById("v-pills-home");
    const tabUser = document.getElementById("v-pills-profile");
    tabListUsers.style.display = 'block';
    tabListUsers.style.opacity = '1';
    tabUser.style.display = 'none';

}

function showUser() {
    const tabListUsers = document.getElementById("v-pills-home");
    const tabUser = document.getElementById("v-pills-profile");
    tabListUsers.style.display = 'none';
    tabUser.style.display = 'block';
    tabUser.style.opacity = '1';

}

async function checkParamForm(path, method, userId, buttonSubmit) {

    let user;
    if (userId !== null) {
        let response = await fetch("admin/user/" + userId);
        if (response.ok) {
            user = await response.json();
        }
    }
    const form = document.getElementById('formUserM');
    form.setAttribute('action', path);
    form.setAttribute('method', method);
    if (user != null) {
        const userId = document.getElementById('userIdM');
        const firstName = document.getElementById('firstNameM');
        const lastName = document.getElementById('lastNameM');
        const age = document.getElementById('ageM');
        const email = document.getElementById('emailM');
        userId.setAttribute('value', user.id);
        firstName.setAttribute('value', user.firstName);
        lastName.setAttribute('value', user.lastName);
        age.setAttribute('value', user.age);
        email.setAttribute('value', user.email);
        document.getElementById('buttonSubmitM').innerText = buttonSubmit;
        document.getElementById('exampleModalLabel').innerText = buttonSubmit +' User';

    }
    let elems = document.querySelectorAll('#formUserM div input');
    if (path.includes('delete')) {
        elems.forEach(value => value.setAttribute('readonly', 'readonly'));
    }else {
        elems.forEach(value => value.removeAttribute('readonly'));
    }
}