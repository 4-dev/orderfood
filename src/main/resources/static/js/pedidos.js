
$('.js-confirma-btn').click(function(evento){
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var url = botaoClicado.data('url');
	var mesa = botaoClicado.data('mesa');
	var total = botaoClicado.data('total');
	var mesas = $('.js-mesas');
	
	if(total == undefined) {
		swal({   title: "Finalizar Mesa "+mesa+" sem nenhum pedido?",   
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
	        		window.location.reload();
	            	
	        	};
	        	
	        	function onErro(e) {
	        		swal('Oops!', e.responseText, 'error');
	        	};
	            
	           
	        });
		
	}else{
		swal({   title: "Finalizar Mesa "+mesa+"?",   
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
	        			swal({title: "mesa "+mesa+" finalizada!"},
	        				
	        				function() {
	        					window.location.reload();
	        					window.open('/mesa/imprime/'+mesa,'pop','left=500, top=100, width=300, height=550');
	        				});  
	        		}, 2000); 
	            	
	        	};
	        	
	        	function onErro(e) {
	        		swal('Oops!', e.responseText, 'error');
	        	};
	            
	           
	        });
		
	}
	


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
	


	
