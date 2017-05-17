$(function() {
	
	var decimal = $('.js-decimal');
	decimal.maskMoney({ prefix:'R$ ', decimal: ',', thousands: '.', affixesStay: false });
	
	var plain = $('.js-plain');
	plain.maskMoney({ precision: 0, thousands: '.' });
	
	var double = $('.js-double');
	double.maskMoney({ precision: 3, allowZero:true, decimal: ',' });
});