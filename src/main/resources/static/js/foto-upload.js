var Produto = Produto || {};

Produto.UploadFoto = (function() {
	

	function UploadFoto() {
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		this.oculta = $('.js-hide');
		this.containerFoto = $('.js-container-foto');
		this.uploadSelect = $('#upload-select');
		this.uploadDrop = $('#upload-drop');
		this.fotoTemporaria = $('.foto-temporaria');
	}
	
	
	UploadFoto.prototype.iniciar = function() {
		var settings = {
				type: 'json',
				filelimit: 1,
				allow: '*.(jpg|jpeg|png)',
				action: '/foto',
				complete: onUploadCompleto.bind(this)
		};
			
		UIkit.uploadSelect(this.uploadSelect , settings);
		UIkit.uploadDrop(this.uploadDrop, settings);

		if (this.inputNomeFoto.val()) {
			onUploadCompleto.call(this, { nome:  this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		};
			
		
	}
	
	function onUploadCompleto(resposta) {
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		$('.foto-temporaria').remove();
		this.oculta.addClass('hide');
		this.containerFoto.removeClass('hide');
		this.containerFoto.prepend('<img src=/foto/temp/' + resposta.nome + ' class="responsive-img foto-temporaria"/>');
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
		
		function onRemoverFoto() {
			$.ajax({
				url: '/foto/temp/' +  resposta.nome,
				method: 'DELETE'
			});
			$('.foto-temporaria').remove();
			this.containerFoto.addClass('hide');
			this.oculta.removeClass('hide');
			this.inputNomeFoto.val('');
			this.inputContentType.val('');
			this.uploadSelect.val('');
			this.uploadDrop.val('');
		}
		
		
	}

	
	

	return UploadFoto;	
	
})();

$(function() {
	var uploadFoto = new Produto.UploadFoto();
	uploadFoto.iniciar();
});