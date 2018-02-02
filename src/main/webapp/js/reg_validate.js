var elems;
var check = [];
function showError(container, errorMessage) {
	container.className = 'error';
	var msgElem = document.createElement('span');
	msgElem.className = "error-message";
	msgElem.innerHTML = errorMessage;
	container.appendChild(msgElem);
}

function resetError(container) {
	container.className = '';
	if (container.lastChild.className == "error-message") {
		container.removeChild(container.lastChild);
	}
}
function checkName() {
	resetError(elems[0].parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var name = elems[0].value;
	var matches = name.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems[0].parentNode, ' Укажите имя верно');
	return false;
}
function checkSurname() {

	resetError(elems[1].parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var surname = elems[1].value;
	var matches = surname.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems[1].parentNode, ' Укажите фамилию верно');
	return false;
}
function checkCity() {
	resetError(elems[2].parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var city = elems[2].value;
	var matches = city.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems[2].parentNode, ' Укажите город верно');
	return false;
}
function checkNumber() {
	resetError(elems[3].parentNode);
	var regexp = /[\d]{12,12}$/;
	// var regexp = /"^380[\\d]{9,9}$"/;
	var number = elems[3].value;
	var matches = number.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems[3].parentNode, ' Укажите номер верно');
	return false;
}
function checkLogin() {
	resetError(elems[4].parentNode);
	var regexp = /^[\w]{4,40}$/;
	var login = elems[4].value;
	var matches = login.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems[4].parentNode, ' Укажите логин верно');
	return false;
}
function checkPass() {
	resetError(elems[5].parentNode);
	var pass = elems[5].value;
	var regexp = /^[\w]{6,40}$/;
	var matches = pass.match(regexp);
	if (matches == null) {
		showError(elems[5].parentNode, 'Минимальная длина - 6 символов');
		return false;
	}
	if (!elems[5].value) {
		showError(elems[5].parentNode, 'Укажите пароль');
		return false;
	} else if (elems[5].value != elems[6].value) {
		showError(elems[5].parentNode, ' Пароли не совпадают.');
		return false;
	}
	return true;
}
function validate(form) {
	check = [];
	elems = form.elements;
	console.log(elems);
	check.push(checkName());
	check.push(checkSurname());
	check.push(checkCity());
	check.push(checkNumber());
	check.push(checkLogin());
	check.push(checkPass());
	var status = true;
	check.forEach(function(item, i, check) {
		if (item == false) {
			status = false;
		}
	});
	if (status) {
		form.submit();
	}
}