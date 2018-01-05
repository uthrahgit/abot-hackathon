<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="/resources/styles/default.css">
<link rel="stylesheet" type="text/css" href="/resources/styles/colorbox.css">
<script src="/resources/js/jquery.min.js"></script>
<script src="/resources/js/jquery.colorbox.js"></script>
<script type="text/javascript">

	function fnsubmitForm(){
    	var FileUploadPath = document.getElementById("fileId").value;
	    if (FileUploadPath == '') {
        	alert("Please select an image to upload");
	    } else {
        	var Extension = FileUploadPath.substring(FileUploadPath.lastIndexOf('.') + 1).toLowerCase();
			if (Extension == "gif" || Extension == "png" || Extension == "bmp" || Extension == "jpeg" || Extension == "jpg") {
				openColorBox();
				document.getElementById("fileUploadFormId").submit();
			} else {
            	alert("Only allows file types of GIF, PNG, JPG, JPEG and BMP. ");
	        }
     	}
	}
	
	function openColorBox(){
        $.colorbox({iframe:true, width:"80%", height:"80%", href: "../resources/loader.html"});
    }

</script>
<style>
#informationTable {
	margin :2% 0% 0% 24%;
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 50%;
}

#informationTable td, #informationTable th {
    border: 1px solid #ddd;
    padding: 8px;
}

#informationTable tr:nth-child(even){background-color: #f2f2f2;}

#informationTable tr:hover {background-color: #ddd;}

#informationTable th {
    text-align: center;
    background-color: #468fda;
    color: white;
}




<style>
.alert {
    padding: 20px;
    background-color: #f44336;
    color: white;
}

.closebtn {
    margin-left: 15px;
    color: white;
    font-weight: bold;
    float: right;
    font-size: 22px;
    line-height: 20px;
    cursor: pointer;
    transition: 0.3s;
}

.closebtn:hover {
    color: black;
}
</style>

<title>Welcome Upload file</title>
</head>
<body>
	<form method="POST" action="/upload" id="fileUploadFormId" enctype="multipart/form-data">
		<div class="jfCard-question">
			<div class="jfQuestion-fullscreen isHidden"></div>
			<span class="jfQuestion-description"
				id="input_8_description" style="display: block;"></span>
			<div class="jfCard-mobileError jsMobileErrorWrapper"></div>
			<div class="jfQuestion-fields" data-wrapper-react="true"
				style="max-height: 100%;">
				<div class="jfField isFilled">
					<div class="jfUpload-wrapper">
						<div class="jfUpload-container" style="margin: 0% 0% 3% 39%;">
							<div class="jfUpload-text-container" style="cursor: pointer;">
								<div class="jfUpload-icon forDesktop">
									<svg width="54px" height="47px" viewBox="0 0 54 47"
										version="1.1">
										<g stroke="none" stroke-width="1" fill="none">
										<g transform="translate(-1506.000000, -2713.000000)">
										<g transform="translate(1421.000000, 2713.000000)">
										<path d="M125.212886,10.1718048 C127.110227,10.3826204 128.89335,10.9096517 130.562307,11.7529143 C132.231264,12.596177 133.689384,13.676591 134.93671,14.9941889 C136.184036,16.3117868 137.167828,17.8226097 137.888114,19.5267029 C138.608401,21.2307962 138.968539,23.049054 138.968539,24.9815309 C138.968539,26.8086 138.687456,28.6356416 138.125281,30.4627107 C137.563106,32.2897797 136.746207,33.9323605 135.674561,35.3905021 C134.602915,36.8486438 133.267769,38.0520318 131.669084,39.0007022 C130.070398,39.9493727 128.217005,40.4588363 126.108848,40.5291081 L122.261482,40.5291081 C121.804714,40.5291081 121.409441,40.3622149 121.07565,40.0284235 C120.741858,39.694632 120.574965,39.2993586 120.574965,38.8425913 C120.574965,38.385824 120.741858,37.9905506 121.07565,37.6567591 C121.409441,37.3229677 121.804714,37.1560744 122.261482,37.1560744 L126.108848,37.1560744 C127.549422,37.1560744 128.858216,36.7871526 130.03527,36.0492978 C131.212324,35.3114429 132.222468,34.3627867 133.06573,33.2033006 C133.908993,32.0438144 134.558998,30.743804 135.015765,29.3032303 C135.472533,27.8626567 135.700913,26.4221046 135.700913,24.9815309 C135.700913,23.4004134 135.384694,21.9159421 134.752247,20.5280723 C134.1198,19.1402026 133.258983,17.9280307 132.169768,16.8915204 C131.080554,15.85501 129.833247,15.0293277 128.427809,14.4144487 C127.022371,13.7995697 125.529116,13.4921348 123.947999,13.4921348 L122.735815,13.4394312 L122.366889,12.2799508 C121.48849,9.46907537 120.07429,7.28189569 118.124245,5.71834621 C116.1742,4.15479672 113.53026,3.37303371 110.192346,3.37303371 C108.084189,3.37303371 106.186876,3.73317173 104.500351,4.45345857 C102.813826,5.17374541 101.36449,6.17510478 100.1523,7.45756671 C98.9401098,8.74002865 98.0090213,10.2684193 97.3590063,12.0427844 C96.7089914,13.8171496 96.3839888,15.7232459 96.3839888,17.7611306 L96.4366924,17.7611306 L96.5420997,19.3422402 L95.0136938,19.6057584 C93.1514888,19.9219819 91.5703951,20.9233413 90.2703652,22.6098666 C88.9703353,24.2963919 88.3203301,26.1937043 88.3203301,28.301861 C88.3203301,30.6911051 89.1196608,32.7640947 90.7183462,34.5208919 C92.3170316,36.277689 94.2055603,37.1560744 96.3839888,37.1560744 L101.232725,37.1560744 C101.724628,37.1560744 102.128685,37.3229677 102.444909,37.6567591 C102.761132,37.9905506 102.919242,38.385824 102.919242,38.8425913 C102.919242,39.2993586 102.761132,39.694632 102.444909,40.0284235 C102.128685,40.3622149 101.724628,40.5291081 101.232725,40.5291081 L96.3839888,40.5291081 C94.8380073,40.5291081 93.3798875,40.2041055 92.0095857,39.5540906 C90.6392839,38.9040756 89.4358959,38.0169064 88.3993855,36.8925562 C87.3628752,35.768206 86.5371929,34.4681956 85.9223139,32.992486 C85.3074349,31.5167763 85,29.9532503 85,28.301861 C85,25.5963933 85.7554115,23.1544819 87.266257,20.9760534 C88.7771026,18.7976249 90.7095505,17.3395051 93.0636587,16.6016503 C93.2042025,14.2475421 93.7224499,12.0603624 94.6184164,10.0400456 C95.514383,8.0197289 96.7089871,6.26295807 98.2022647,4.76968048 C99.6955423,3.27640288 101.452313,2.10815028 103.47263,1.26488764 C105.492947,0.421624997 107.732829,0 110.192346,0 C112.089686,0 113.82889,0.237164061 115.410007,0.711499298 C116.991124,1.18583453 118.414109,1.8621913 119.679003,2.74058989 C120.943897,3.61898847 122.033095,4.69061868 122.946629,5.95551264 C123.860164,7.22040661 124.615575,8.62582326 125.212886,10.1718048 Z M113.249157,23.611236 L119.468188,30.4627107 C119.71414,30.7086623 119.837114,30.9985295 119.837114,31.3323209 C119.837114,31.6661124 119.71414,31.9735473 119.468188,32.2546348 L119.046559,32.5181531 C118.835743,32.7641047 118.563444,32.8607271 118.229652,32.8080232 C117.895861,32.7553193 117.605994,32.6059937 117.360042,32.3600421 L113.670787,28.2491573 L113.670787,45.2197331 C113.670787,45.7116364 113.503893,46.1156936 113.170102,46.4319171 C112.83631,46.7481406 112.441037,46.90625 111.98427,46.90625 C111.492366,46.90625 111.088309,46.7481406 110.772086,46.4319171 C110.455862,46.1156936 110.297753,45.7116364 110.297753,45.2197331 L110.297753,28.2491573 L106.713904,32.2546348 C106.467953,32.5005864 106.178086,32.649912 105.844294,32.7026159 C105.510503,32.7553198 105.220636,32.6586974 104.974684,32.4127458 L104.553055,32.1492275 C104.307103,31.86814 104.184129,31.5607051 104.184129,31.2269136 C104.184129,30.8931222 104.307103,30.603255 104.553055,30.3573034 L110.666678,23.611236 L110.666678,23.5585323 L111.088308,23.1369031 C111.193715,22.9963593 111.325473,22.8997369 111.483585,22.847033 C111.641697,22.7943291 111.791022,22.7679775 111.931566,22.7679775 C112.107246,22.7679775 112.265355,22.7943291 112.405899,22.847033 C112.546443,22.8997369 112.686984,22.9963593 112.827528,23.1369031 L113.249157,23.5585323 L113.249157,23.611236 Z"></path></g></g></g></svg>
								</div>
								<div  class="jfUpload-textfalse">
									<div class="jfUpload-heading forDesktop"><input type="file" id="fileId" name="file" /></div>
									<div class="jfUpload-subheading" style="display:;"> &nbsp Only  <br> 
										Images</div> 
								</div>
							</div>
							<div class="jfUpload-button-container">
								<div class="jfUpload-button">Upload a File</div>
							</div>
							
						</div>
						<div class="jfUpload-button-container">
								<div style="cursor: pointer;" class="jfUpload-button" onclick="fnsubmitForm();">Submit</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>
		<div>
	
	<c:if test="${not empty Error}">
		<div class="alert" style="background-color: #f44336; color: white; padding: 8px; width:43%; margin: 0% 18% 0% 26%; border-radius :15px;">
  			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  			<strong>Failure!</strong> <c:out value="${Error}" />.
		</div>
	</c:if>
	<c:if test="${empty Error and not empty  Message}">
		<div class="alert" style="background-color: #4CAF50; color: white; padding: 8px; width:43%; margin: 0% 18% 0% 26%; border-radius :15px;">
  			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  			<strong>Success!</strong> <c:out value="${Message}" />.
		</div>

		<table id="informationTable">
  		<tr>
    		<th colspan="2" >Information of the image</th>
	  </tr>
  		<tr>
   	 		<td>Tracking Number</td>
    		<td><c:out value="${trackingNumber}" /></td>
  		</tr>
	</table>
	
	</c:if>
	
	</div>
	
</body>



</html>
