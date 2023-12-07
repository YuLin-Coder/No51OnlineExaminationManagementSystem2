/**
 * 
 */
$(function() {

	$('#switch_qlogin').click(function() {
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({
			left: '0px',
			width: '64px'
		});
		$('#qlogin').css('display', 'none');
		$('#web_qr_login').css('display', 'block');
		$('.b2').animate({
			opacity: '1',
		}, 500);
		$('.b1').animate({
			opacity: '0',
		}, 1000);
	});
	$('#switch_login').click(function() {
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({
			left: '154px',
			width: '64px'
		});
		$('#qlogin').css('display', 'block');
		$('#web_qr_login').css('display', 'none');
		$('.b2').animate({
			opacity: '0',
		}, 1000);
		$('.b1').animate({
			opacity: '1',
		}, 500);
	});
});