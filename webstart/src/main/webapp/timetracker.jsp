<html>
<meta http-equiv="Pragma" content="no-cache">
<!-- Pragma content set to no-cache tells the browser not to cache the page
This may or may not work in IE -->

<meta http-equiv="expires" content="0">
<!-- Setting the page to expire at 0 means the page is immediately expired
Any vales less then one will set the page to expire some time in past and
not be cached. This may not work with Navigator -->
<script type="text/javascript">
function webstartVersionCheck(versionString) {
    // Mozilla may not recognize new plugins without this refresh
    navigator.plugins.refresh(true);
    // First, determine if Web Start is available
    if (navigator.mimeTypes['application/x-java-jnlp-file']) {
        // Next, check for appropriate version family
        for (var i = 0; i < navigator.mimeTypes.length; ++i) {
            pluginType = navigator.mimeTypes[i].type;
            if (pluginType == "application/x-java-applet;version=" + versionString) {
                return true;
            }
         }
     }
 }

function checkPlatform(string)
{
    var detect = navigator.userAgent.toLowerCase();
    place = detect.indexOf(string) + 1;
    thestring = string;
    return place;
}

function launchJNLP(app) {
    	if (webstartVersionCheck("1.5")) 
	{
      	 clearInterval(launchTID);
       	 window.location = app;
   	}
	else
	{
		window.open("http://jdl.sun.com/webapps/getjava/BrowserRedirect?locale=en&host=java.com","needdownload");
	}
}

function versionCheck(url)
{
	//alert(url);
	var windowsIE = (checkPlatform('msie') && checkPlatform('win'));
	var jnlpDir = url+"spirit-client/webstart/timetracker.jnlp";
	if (windowsIE) {
		document.write("<OBJECT codeBase=http://java.sun.com/update/1.5.0/jinstall-1_5_0_05-windows-i586.cab classid=clsid:5852F5ED-8BF4-11D4-A245-0080C6F74284 height=0 width=0>");
		document.write("<PARAM name=app VALUE="+jnlpDir+">");
		document.write("<PARAM NAME=back VALUE=false>");
		document.write("</OBJECT>");
 	}
	else
	{
		launchTID =setInterval('launchJNLP("/spirit-client/webstart/timetracker.jnlp")',100);
	}
}

</script>
<head>
<title>Auto Install</title>
</head>
<body onload="versionCheck('<%="http://"+request.getServerName()+":"+request.getServerPort()+"/"%>')">
</body>
</html>
