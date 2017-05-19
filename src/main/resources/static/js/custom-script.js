

//maskmoney
$(function() {
	
	var decimal = $('.js-decimal');
	decimal.maskMoney({ prefix:'R$ ', decimal: ',', thousands: '.', affixesStay: false });
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: '.' });
	
	var double = $('.js-double');
	double.maskMoney({ precision: 3, allowZero:true, decimal: ',' });
});

//fechar alertas automatico
$(".alert-dismissible").fadeTo(7000, 500).slideUp(500, function(){
    $(".alert-dismissible").alert('close');
});


$(function() {
	var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/foto',
			complete: function(resposta) {
				var inputNomeFoto = $('input[name=foto]');
				var inputContentType = $('input[name=contentType]');
				var oculta = $('.js-hide');
				var containerFoto = $('.js-container-foto');
				var uploadSelect = $('#upload-select');
				var uploadDrop = $('#upload-drop');
				var fotoTemporaria = $('#foto-temporaria');
				inputNomeFoto.val(resposta.nome);
				inputContentType.val(resposta.contentType);
				
				fotoTemporaria.remove();
				oculta.addClass('hide');
				containerFoto.removeClass('hide');
				containerFoto.prepend('<img src=/foto/temp/' + resposta.nome + ' class="responsive-img" id="foto-temporaria"/>');
				
				$('.js-remove-foto').on('click', function() {
					$.ajax({
						url: '/foto/temp/' + resposta.nome,
						method: 'DELETE'
					});
					
					
					containerFoto.addClass('hide');
					oculta.removeClass('hide');
					inputNomeFoto.val('');
					inputContentType.val('');
					uploadSelect.val('');
					uploadDrop.val('');
				});

				
				
				
			}
	};
	
	UIkit.uploadSelect($('#upload-select'), settings);
	UIkit.uploadDrop($('#upload-drop'), settings);
});