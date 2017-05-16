$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney({ prefix:'R$ ', allowNegative: true, decimal: ',', thousands: '.', affixesStay: false });
	
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: '.' });
	
	var double = $('.js-double');
	double.maskMoney({ precision: 3, decimal: ',', allowZero:true });
});