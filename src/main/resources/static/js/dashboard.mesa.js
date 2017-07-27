var Grafico = Grafico || {};

Grafico.GraficoVendaPorMesa = (function() {
	
	function GraficoVendaPorMesa() {
		this.ctx = $('#graficoPorMesa')[0].getContext('2d');
	}
	
	GraficoVendaPorMesa.prototype.iniciar = function() {
		$.ajax({
			url: 'pormesa',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(mesas) {
		
		var idmesa = [];
		var valores = [];
		
		mesas.forEach(function(obj) {
			idmesa.unshift('Mesa '+obj.id);
			valores.unshift(obj.total);
		});
		var graficoVendasPorMesa = new Chart(this.ctx, {
		    type: 'doughnut',
		    data: 
			    { 
		    		datasets: [{
			    
		    			data: valores,
		    			
		    			backgroundColor: [
			                 'orange',
			                 'blue',
			                 'green',
			                 'red',
			                 'yelow',
			                 'pink',
			                 '#006064',
			                 '#4db6ac',
			                 '#b71c1c',
			                 '#880e4f'
			             ],
		
		    		}],
		    		
		    		 
		    		labels: idmesa
			    	
			    },
			    
			    options: {
		            responsive: true,
		            legend: {
		                position: 'top',
		            },
		            title: {
		                display: true,
		                text: 'Mesas mais Rentáveis'
		            },
		            animation: {
		                animateScale: true,
		                animateRotate: true
		            }
			    }
		    
		});
	}
	
	return GraficoVendaPorMesa;
	
}());

$(function() {
	var graficoVendaPorMesa = new Grafico.GraficoVendaPorMesa();
	graficoVendaPorMesa.iniciar();
});
