$(function() {
	setInterval(function() {
		$('.refreshButton').click();
	}, 3000);
});

function fullRefresh() {
	location.reload(true);
}
