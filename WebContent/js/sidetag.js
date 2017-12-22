$(function() {
	$("ul.navigation li a").on('click', function() {
		//$("ul.navigation li.highlight a").attr('href',$('ul.navigation li.highlight a').attr('temp'));
		//$("ul.navigation li.highlight a").removeAttr('temp');
		$("ul.navigation li.highlight").removeClass();
		$(this).parent().addClass('highlight');
		//$(this).attr('temp',$(this).attr("href"));
		//$(this).removeAttr('href');
	});
});