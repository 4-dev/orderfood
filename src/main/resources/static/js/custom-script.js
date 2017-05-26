

//maskmoney
$(function() {
	
	var decimal = $('.js-decimal');
	decimal.maskMoney({ prefix:'R$ ', decimal: ',', thousands: '.', affixesStay: false });
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: '.' });
	
	var double = $('.js-double');
	double.maskMoney({ precision: 3, allowZero:true, decimal: ',' });
});


//datepicker

$.extend($.fn.pickadate.defaults, {
    monthsFull: [ 'janeiro', 'fevereiro', 'março', 'abril', 'maio', 'junho', 'julho', 'agosto', 'setembro', 'outubro', 'novembro', 'dezembro' ],
    monthsShort: [ 'jan', 'fev', 'mar', 'abr', 'mai', 'jun', 'jul', 'ago', 'set', 'out', 'nov', 'dez' ],
    weekdaysFull: [ 'domingo', 'segunda-feira', 'terça-feira', 'quarta-feira', 'quinta-feira', 'sexta-feira', 'sábado' ],
    weekdaysShort: [ 'dom', 'seg', 'ter', 'qua', 'qui', 'sex', 'sab' ],
    today: 'hoje',
    clear: 'limpar',
    close: 'fechar',
    format: 'dd/mm/yyyy',
    formatSubmit: 'dd/mm/yyyy'
});

$.extend($.fn.pickadate.defaults, {
    clear: 'limpar'
});


//confirmar senha







