// JavaScript Document
// costum jquery for login
Cookies.set('auth', 'yes')

var auth = Cookies.get('name');

if (auth){
	
	alert('ok');
	document.getElementById('userDropdown').innerHTML .= 'hidden=""';
}
