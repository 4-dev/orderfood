
$('.js-confirma-btn').click(function(evento){
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var url = botaoClicado.data('url');
	var objeto = botaoClicado.data('objeto');
	var total = botaoClicado.data('total');
	var mesas = $('.js-mesas');
	
	
	swal({   title: "Finalizar "+objeto+"?",   
        text: "Total: R$ "+total,   
        type: "info",   showCancelButton: true,   
        closeOnConfirm: false,   
        showLoaderOnConfirm: true, }, 
        
        function(){   
        	
        	$.ajax({
    			url: url,
    			method: 'get',
    			success: onSucesso.bind(this),
    			error: onErro.bind(this)
    		});
            function onSucesso(){
        		console.log('Finalizada com sucesso!');
        		
        		setTimeout(function(url){     
        			swal({title: objeto+" finalizada!"},
        				
        				function() {
        					window.location.reload();
        				window.open('/mesa/cupom','pop','left=500, top=100, width=300, height=550');
        				
        				
        				});  
        		}, 5000); 
            	
        	};
        	
        	function onErro(e) {
        		swal('Oops!', e.responseText, 'error');
        	};
            
           
        });

});


$('.mesa-imagem').click(function(evento){
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var id = botaoClicado.data('id');
	var tabela = $('.tabela')
	
	$.ajax({
		url:'/mesa/atualiza/'+id,
		method: 'get',
	success: function(html) {
		tabela.html(html)
		}
	});
	

});
	


	
