

$('.js-exclusao-btn').click(function(evento){
	if (window.location.search.indexOf('excluido') > -1) {
		swal('Pronto!', 'Excluído com sucesso!', 'success');
	}
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var url = botaoClicado.data('url');
	var objeto = botaoClicado.data('objeto');
    	swal({
    		title: "Tem certeza?",
    		text:  'Excluir "' + objeto + '"? Você não poderá recuperar depois.',
    		type: 'warning',
    		showCancelButton: true,
    		confirmButtonColor: '#DD6B55',
    		confirmButtonText: 'Sim, exclua agora!',
    		closeOnConfirm: false
    	},onExcluirConfirmado.bind(this, url));
    	
    	
    function onExcluirConfirmado(url) {
    		$.ajax({
    			url: url,
    			method: 'DELETE',
    			success: onExcluidoSucesso.bind(this),
    			error: onErroExcluir.bind(this)
    		});
    	}
    	
    	function onExcluidoSucesso(){
    		var urlAtual = window.location.href;
    		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
    		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
    		
    		window.location = novaUrl;
//    		window.location.reload();
    	}
    	
    	function onErroExcluir(e) {
    		console.log('ahahahah', e.responseText);
    		swal('Oops!', e.responseText, 'error');
    	}
});


