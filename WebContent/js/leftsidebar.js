$(function() {
	// 
	var duration = 300;

	// aside ----------------------------------------
	var $aside = $('.page-main > aside');
	var $asidButton = $aside.find('button');

	if (!!window.chrome && !(!!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0)) {
		$asidButton.children('img').on('click',function() {
					$aside.toggleClass('open');
					if ($aside.hasClass('open')) {
						$aside.stop(true).animate({
							left : '0px'
						}, duration, 'easeOutBack');
						$asidButton.find('img').attr('src',
								'/ChatChatCatProject/images/sidebar-arrow-left.png');
					} else {
						$aside.stop(true).animate({
							left : '-350px'
						}, duration, 'easeInBack');
						$asidButton.find('img').attr('src',
								'/ChatChatCatProject/images/sidebar-arrow-right.png');
					}
					;
				});
	} else {
		$asidButton.on('click', function() {
			$aside.toggleClass('open');
			if ($aside.hasClass('open')) {
				$aside.stop(true).animate({
					left : '0px'
				}, duration, 'easeOutBack');
				$asidButton.find('img').attr('src',
						'/ChatChatCatProject/images/sidebar-arrow-left.png');
			} else {
				$aside.stop(true).animate({
					left : '-350px'
				}, duration, 'easeInBack');
				$asidButton.find('img').attr('src',
						'/ChatChatCatProject/images/sidebar-arrow-right.png');
			}
			;
		});
	}
});
