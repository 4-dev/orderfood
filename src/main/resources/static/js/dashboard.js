var Dashboard = Dashboard || {};

Dashboard.GraficoVendaPorMes = (function() {
	
	function GraficoVendaPorMes() {
		this.ctx = $('#graficoVendasPorMes')[0].getContext('2d');
	}
	
	GraficoVendaPorMes.prototype.iniciar = function() {
		$.ajax({
			url: 'pormes',
			method: 'GET', 
			success: onDadosRecebidos.bind(this)
		});
	}
	
	function onDadosRecebidos(vendaMes) {
		var meses = [];
		var valores = [];
		vendaMes.forEach(function(obj) {
			meses.unshift(obj.mes);
			valores.unshift(obj.total);
		});
		
		var graficoVendasPorMes = new Chart(this.ctx, {
		    type: 'line',
		    data: {
		    	labels: meses,
		    	datasets: [{
		    		label: 'Vendas por mês',
		    		fillColor : "rgba(128, 222, 234, 0.6)",
					strokeColor : "#ffffff",
					pointColor : "#00bcd4",
					pointStrokeColor : "#ffffff",
					pointHighlightFill : "#ffffff",
					pointHighlightStroke : "#ffffff",
	                data: valores,
	               
		    	}]
		    }, 
		    options: { 
		    	
		    }
		});
	}
	
	return GraficoVendaPorMes;
	
}());

$(function() {
	var graficoVendaPorMes = new Dashboard.GraficoVendaPorMes();
	graficoVendaPorMes.iniciar();
});
