$(document).ready(function(e) {
	$('.btn_modal').click(function() {
		document.querySelector('.modal').style.display = 'flex';
		$('.modal').fadeIn(500);
	});

	$('.fechar').click(function() {
		$('.modal').fadeOut(500);
	});

});