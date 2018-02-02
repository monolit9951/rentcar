var elems;
var check = [];
var MILI_IN_DAY = 1000 * 60 * 60 * 24;
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
	resetError(elems.name.parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var name = elems.name.value;
	var matches = name.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.name.parentNode, ' Укажите имя верно');
	return false;
}
function checkSurname() {

	resetError(elems.surname.parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var surname = elems.surname.value;
	var matches = surname.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.surname.parentNode, ' Укажите фамилию верно');
	return false;
}
function checkCity() {
	resetError(elems.city.parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var city = elems.city.value;
	var matches = city.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.city.parentNode, ' Укажите место рождения верно');
	return false;
}
function checkNumber() {
	resetError(elems.number.parentNode);
	var regexp = /[\d]{12,12}$/;
	var number = elems.number.value;
	var matches = number.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.number.parentNode, ' Укажите номер верно');
	return false;
}
function checkLogin() {
	resetError(elems.login.parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var login = elems.login.value;
	var matches = login.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.login.parentNode, ' Укажите логин верно');
	return false;
}
function checkPass() {
	resetError(elems.password.parentNode);
	if (!elems.password.value) {
		showError(elems.password.parentNode, 'Укажите пароль');
		return false;
	} else if (elems.password.value != elems.password2.value) {
		showError(elems.password.parentNode, ' Пароли не совпадают.');
		return false;
	}
	return true;
}
function checkDate() {
	var ch = true;
	resetError(elems.dateBegin.parentNode);
	resetError(elems.dateEnd.parentNode);
	var dateBegin = elems.dateBegin.value;
	var dateEnd = elems.dateEnd.value;
	var converted = Date.parse(dateBegin);
	var myDateBegin = new Date(converted);
	converted = Date.parse(dateEnd);
	var myDateEnd = new Date(converted);
	if (isNaN(myDateBegin)) {
		resetError(elems.dateBegin.parentNode);
		showError(elems.dateBegin.parentNode, 'Введите дату');
		return false;
	}
	if (isNaN(myDateEnd)) {
		resetError(elems.dateEnd.parentNode);
		showError(elems.dateEnd.parentNode, 'Введите дату');
		return false;
	}
	var now = new Date();
	var result = myDateBegin - now;
	if (result < 0) {
		showError(elems.dateBegin.parentNode, ' Укажите корректную дату');
		ch = false;
	}
	var result2 = myDateEnd - myDateBegin;
	if (result2 < 0) {
		showError(elems.dateEnd.parentNode, 'Дата не должна быть раньше');
		ch = false;
	}
	if (result2 > MILI_IN_DAY * 7) {
		resetError(elems.dateEnd.parentNode);
		showError(elems.dateEnd.parentNode, 'Максимальный срок аренды - 7 дней');
		ch = false;
	}
	return ch;
}
function checkPatronymic() {
	resetError(elems.patronymic.parentNode);
	var regexp = /^[A-Za-zА-Яа-яЁё]{3,20}$/;
	var patronymic = elems.patronymic.value;
	var matches = patronymic.match(regexp);
	if (matches != null) {
		return true;
	}
	showError(elems.patronymic.parentNode, ' Укажите отчество верно');
	return false;
}
function birth() {
	resetError(elems.dateBirth.parentNode);
	var dateBirth = elems.dateBirth.value;
	var converted = Date.parse(dateBirth);
	var myDateBirth = new Date(converted);
	if (isNaN(myDateBirth)) {
		resetError(elems.dateBirth.parentNode);
		showError(elems.dateBirth.parentNode, 'Введите дату');
		return false;
	}
	var now = new Date();
	var res = now.getFullYear() - myDateBirth.getFullYear();
	if (res < 21) {
		resetError(elems.dateBirth.parentNode);
		showError(elems.dateBirth.parentNode, ' Минимальный возраст - 21 лет');
		return false;
	}
	if (res > 85) {
		resetError(elems.dateBirth.parentNode);
		showError(elems.dateBirth.parentNode, 'Максимальный возраст - 85 лет');
		return false;
	}
}

function validate_order(form) {
	check = [];
	elems = form.elements;
	check.push(checkName());
	check.push(checkSurname());
	check.push(checkCity());
	check.push(checkDate());
	check.push(checkPatronymic());
	check.push(birth());
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