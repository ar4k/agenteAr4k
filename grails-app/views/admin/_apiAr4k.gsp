<script type="text/javascript">
	function windowDimensions() { 
		var myWidth = 0, myHeight = 0;
		if (typeof (window.innerWidth) == 'number') {
			//Non-IE or IE 9+ non-quirks
			myWidth = window.innerWidth;
			myHeight = window.innerHeight;
		} else if (document.documentElement
				&& (document.documentElement.clientWidth || document.documentElement.clientHeight)) {
			//IE 6+ in 'standards compliant mode'
			myWidth = document.documentElement.clientWidth;
			myHeight = document.documentElement.clientHeight;
		} else if (document.body
				&& (document.body.clientWidth || document.body.clientHeight)) {
			//IE 5- (lol) compatible
			myWidth = document.body.clientWidth;
			myHeight = document.body.clientHeight;
		}
		if (myWidth < 1)
			myWidth = screen.width; // emergency fallback to prevent division by zero
		if (myHeight < 1)
			myHeight = screen.height;
		return [ myWidth, myHeight ];
	}
	var dim = windowDimensions();
	myIframe = $('#embed-iframe-full-id');
	myIframe.height((dim[1]-72) + "px");
	myIframe.width((dim[0]-298) + "px");
	$(window).on('resize', function(){
		myIframef = $('#embed-iframe-full-id');
		var dimf = windowDimensions();
		if (dimf[0]>767) {
			myIframef.height((dimf[1]-72) + "px");
		} else {
			myIframef.height((dimf[1]-122) + "px");
			}
		if (dimf[0]>767) {
			myIframef.width((dimf[0]-298) + "px");
		} else {
			myIframef.width((dimf[0]-20) + "px");
			}
	});
</script>
<div class="row">
	<div class="embed-container-full">
		<iframe width="100%" name="docAPI" class="embed-iframe-full"
			style="align-content: stretch;"
			ng-src="<g:createLink controller='docs' action='gapi'
			absolute='true'/>" id="embed-iframe-full-id"
			class="panel panel-default"></iframe>
	</div>
</div>
