$('.js-itens-btn').click(function(evento){
	
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var urlItem = botaoClicado.data('url');
    $(".jsGrid-odata").jsGrid({
        height: "auto",
        width: "100%",
        sorting: true,
        paging: false,
        autoload: true,
        controller: {
            loadData: function() {
                var deferred = $.Deferred();
                $.ajax({
                    url: urlItem,
                    contentType: "application/json",
                    dataType: "json",
                    success: function(data){
//                    	console.log('Url', urlItem);
//                    	console.log('Dados', data);
                        deferred.resolve(data);
//                        console.log('deferred', deferred.resolve(data));
                    }
                });
 
                return deferred.promise();
            }
        },
        fields: [
            { name: "produto", type: "number", align: "center", width: 30 },
            { name: "nome", type: "text", width: 150, align: "center" },
            { name: "quantidade", type: "number", width: 50, align: "center" },
            { name: "valorUnitario", type: "number", width: 50, align: "center",
                itemTemplate: function(value) {
                    return "R$ "+value.toFixed(2); } },
            { name: "valorTotal", type: "number", width: 50, align: "center",
                        itemTemplate: function(value) {
                            return "R$ "+value.toFixed(2); } }
        ]
    });
 
});

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
	
	$.ajax({
		url:'/mesa/atualiza/'+id,
		method: 'get',
	success: function(data) {
		console.log(data);
		}
	});
	

});
	


	
