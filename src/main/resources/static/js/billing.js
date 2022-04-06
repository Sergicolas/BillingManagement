$('#deleteConfirmationModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);

	var billCode = button.data('code');
	var billDescription = button.data('description');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + billCode);

	modal.find('.modal-body span').html('Do you really want to remove <strong>' + billDescription + '</strong> bill?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();

	$('.js-currency').maskMoney();

	$('.js-update-status').on('click', function(event) {
    		event.preventDefault();

    		var btnReceive = $(event.currentTarget);
    		var urlReceive = btnReceive.attr('href');

    		var response = $.ajax({
    			url: urlReceive,
    			type: 'PUT'
    		});


    		response.done(function(e) {
    			var billCode = btnReceive.data('code');
    			$('[data-role=' + billCode + ']').html('<span class="label label-success">' + e + '</span>');
    			btnReceive.hide();
    		});

    		response.fail(function(e) {
    			console.log(e);
    			alert('Error receiving bill');
    		});

    	});
});