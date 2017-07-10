$('.js-itens-btn').click(function(evento){
	
	event.preventDefault(); 
	var botaoClicado = $(evento.currentTarget);
	var urlItem = botaoClicado.data('url');
    $("#jsGrid-odata").jsGrid({
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
                    dataType: "json",
                    success: function(data){
                    	console.log('Dados', data);
                        deferred.resolve(data);
                        console.log('deferred', deferred.resolve(data));
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
	swal({   title: "Finalizar "+objeto+"?",   
        text: "Total: R$ ",   
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
        	}
        	
        	function onErro(e) {
        		swal('Oops!', e.responseText, 'error');
        	}
            setTimeout(function(){     
            	swal("Conta finalizada!");   
            	}, 2000); 
        });
});
	
