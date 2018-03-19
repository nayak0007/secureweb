<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/calendar.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.datetimepicker.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.thcls {
	border-bottom: 1px solid #147075;
	font-weight: normal !important;
	background: #aabcfe;
	padding: 11px;
	
}
.tdcls {
	border-bottom: 1px solid #147075;
	font-weight: normal !important;
	background: #e8edff;
	word-wrap: break-word;
}
</style>
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
		<h2><img src=></h2>
        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>

</div>
<div id="imgDiv">

</div>
 <div id="biggrid_1">
<c:if test="${pageContext.request.userPrincipal.name != 'superadmin'}">
<h2 onclick="getUser();" style="cursor:pointer" id="profileHdr">Show Profile Details</h2>
</c:if>
<c:if test="${pageContext.request.userPrincipal.name == 'superadmin'}">
<h2 ><span onclick="getUser();" style="cursor:pointer" id="profileHdr">Show All Profiles</span><span><a href="${contextPath}/registration">Add User</a></span></h2>
</c:if>
<table id="profileTable" class="responsive"
	style="table-layout: fixed; width: 100%; margin: auto; font-size: 15px;display:none">
	<thead>
		<tr>
			<th width="11.11%" align="left" class="thcls">UserId</th>
			<th width="11.11%" align="left" class="thcls">Name</th>
			<th width="11.11%" align="left" class="thcls">DOB</th>
			<th width="11.11%" align="left" class="thcls">State</th>
		    <th width="11.11%" align="left" class="thcls">City</th>
			<th width="11.11%" align="left" class="thcls">Mobile</th>
			<th width="11.11%" align="left" class="thcls">Email</th>
			<!-- <th width="11.11%" align="left" class="thcls">Profile Image</th> -->
						<th width="11.11%" align="left" class="thcls">Edit</th>
	</thead>
	 <tbody id="recordgrid">
	 
	</tbody>
	
</table> 
</div>
<div id="editUser" style="display:none">
<input id="uId" type="text" value=""/><br>
<input id="name" type="text" value=""/><br>
<input id="dob" type="text" value=""/><br>
<input id="state" type="text" value=""/><br>
<input id="city" type="text" value=""/><br>
<input id="mobile" type="text" value=""/><br>
<input id="email" type="text" value=""/><br>
<input type="button" id="updateUser" value="Update User" onclick="updateUser();">
</div>
<div id=pUpload style="">
<input type="hidden" value="" id="photoId"/>
<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <input type="text" name="extraField"/><br/><br/>
    <input type="file" name="file"/><br/><br/>
    <input type="submit" value="Submit" id="btnSubmit"/>
</form>
</div>
<div id="biggrid">
</div> 
<!-- /container -->
<!-- <div id="elementContainer" style="display:none">
<div id="textBoxElement">
</div>
<div id="calendarElement">
</div>
<div id="multiChkElement">
</div>
<div id="dropDownElement">
	<div id="$ddownOne" class="wrapper-dropdown-3" style="margin-right:101px">
		<span id="$DefSelOne" data-id="">Select</span>
		<ul class="dropdown" id="$ddownOneData">
		</ul>
	</div>
</div>
</div>
<div style="width:1120px;">
	<div id="headerDiv" style="height:30px;width:1118px;">
		<span style="float:right;width:30px;height:30px" id="addNew" onclick="toggleDiv()" class="addBtn">
		</span>
		<ul id="decideAddNew" class="addDropDown">
			<li onclick="openActPop();">Add New Activity</li>
			<li>Add New Tag</li>
		</ul>
		<span style="float:left;" id="selectYear">
		<div id="ddownTop" class="wrapper-dropdown-top" style="margin-right:101px">
			<span id="DefSelTop" data-id="">2017</span>
				<ul class="dropdown" id="ddownTopData">
					<li>2017</li>
					<li>2016</li>
				</ul>
		</div>
		</span>
		<span style="float:left;" id="selectFunction">
		<div id="ddownFunction" class="wrapper-dropdown-top" style="margin-right:101px">
			<span id="DefSelTop" data-id="">FPM</span>
				<ul class="dropdown" id="ddownTopData">
					<li>FPM</li>
				</ul>
		</div>
		</span>
	</div>
	<div id="activity" class="activityContainer">
		<div style="height:45px;background:#09961b;margin-top: 15px;color:white">
			<span style="float:left;margin-left:20px;padding-top: 7px;">Activity</span>
			<span style="float:right;margin-right:100px;padding-top: 7px;">SPA</span>
		</div>
		<div style="height:355px;overflow:hidden" id="activityULParent">
			<ul class="activityUl" id="activityUL">
				
			</ul>
		</div>
	</div>
	<div id="viewChart" class="chartContainer">
		<div id='axisHolder' style="top:50px;z-index:-5"></div>
		<div id="chart_header" class="" style="z-index:2">
			<ul class="headerUl">
				<li class="headerLi2" style="padding-bottom: 7px;">
					<span>Q1</span>
					<span>Q2</span>
					<span>Q3</span>
					<span>Q4</span>
				</li>
				<li class="headerLi1">
					<span>Jan</span>
					<span>Feb</span>
					<span>Mar</span>
					<span>Apr</span>
					<span>May</span>
					<span>Jun</span>
					<span>Jul</span>
					<span>Aug</span>
					<span>Sep</span>
					<span>Oct</span>
					<span>Nov</span>
					<span>Dec</span>
				</li>
			</ul>
			<ul id="canvasParent">
			
			</ul>
		</div>
	</div>
</div>
<div id="popupDiv" style="display:none">
	<div id="dataContainer" style="width:700px;height:400px;overflow:hidden">
		<div style="width:450px;float:left;padding-top:3px" id="content_1">
			<span id="label" style="float:left">Year</span>
			<span id="content" style="float:right">
				<div id="ddownOne" class="wrapper-dropdown-3" style="margin-right:101px">
					<span id="DefSelOne" data-id="">Select</span>
					<ul class="dropdown" id="ddownOneData">
						<li>2017</li>
						<li>2016</li>
					</ul>
				</div>
			</span>
		</div>
		<div style="width:450px;float:left;padding-top:3px" id="content_2">
			<span id="label" style="float:left">Sub Function/Region</span>
			<span id="label" style="float:Right">
				<div style="position:relative;float:right">
					<div class="multiCheckboxContainer" id="$multiCheckboxContainerId">
						<input type="hidden" id="$hiddenFieldId" name="selectedCheckBoxes" value=""/>
						<div class="liMultiChkbox">
							<span id="multiChkLabelId-101">option 1</span>
							<span class="multiChkboxParentStyle">
								<input id="multiChkBoxId-101"  type="checkbox" onclick=""/>
							</span>
						</div>
						<div class="liMultiChkbox">
							<span id="multiChkLabelId-102">option 2</span>
							<span class="multiChkboxParentStyle">
								<input id="multiChkBoxId-102"  type="checkbox" onclick=""/>
							</span>
						</div>
						<div class="liMultiChkbox">
							<span id="multiChkLabelId-103">option 3</span>
							<span class="multiChkboxParentStyle">
								<input id="multiChkBoxId-103"  type="checkbox" onclick=""/>
							</span>
						</div>
					</div>
				</div>
			</span>
		</div>
		<div id="content_3" style="width:600px;border-top:0.5px solid #d9d9d9;float:left;margin-top: 5px;">
		
			<ul id="milestoneGrid" class="milestoneGrid">
				<li>
					<span id="milestoneCal"><input readonly type="text" class="simpleCalbox calIcon" id="cal_1"/></span>
					<span id="milestoneDesc"><input type="text" class="simpleTextbox" id="cal_1"/></span>
					<span id="milestoneStatus" >
						<div id="ddownMilestone" class="wrapper-dropdown-Milestone" style="height: 18px;">
							<span id="DefSelMilestone" data-id="">2017</span>
							<ul class="dropdown" id="ddownMilestoneData">
								<li>2017</li>
								<li>2016</li>
							</ul>
						</div>
					</span>
					<span id="deleteMilestone" class="deleteMilestone"></span>
				</li>
				
			<ul>
		
		</div>
	</div>
	<div id="buttonBar" style="float:right">
		<span id="saveBtn" class="saveBtn">Save</span>
		<span id="cancelBtn" class="cancelBtn">Cancel</span>
	</div>
</div> -->

<script type="text/javascript" src="${contextPath}/resources/js/jquery.2.1.1.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/d3.min.js"></script>
<%-- <script src="${contextPath}/resources/js/jquery-ui.js"></script>
<script src="${contextPath}/resources/js/enscroll-0.6.0.js"></script>
<script src="${contextPath}/resources/js/commonscrollbarutil.js"></script>
<script src="${contextPath}/resources/js/jquery.datetimepicker.cpt.js"></script>
<script src="${contextPath}/resources/js/data.js"></script> --%>
<%-- <script src="${contextPath}/resources/js/calendar.js"></script> --%>
<script type="text/javascript">
var uId="${pageContext.request.userPrincipal.name}";
 $(document).ready(function(){
	$("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });
	$.ajax({
		url : "${contextPath}"+"/getImg",
		async : true,
		type : 'GET',
		data:{'userId':uId},
		cache : false,
		success : function(data) {
			$("#imgDiv").html("<img height=50 width=150 src='"+data+"'/>");

		},
		error : function(response) {

		}
	});
}); 
function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
    data.append("uId", uId);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $("input[name=_csrf]").val());},
        url: "${contextPath}/upload",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}
function getUser() {
	$.ajax({
		url : "${contextPath}"+"/detail",
		async : true,
		type : 'GET',
		data:{'userId':uId},
		cache : false,
		success : function(data) {
			$("#profileTable").show();
			//document.title="";
				$("#profileHdr").text("Profile Details");

			var str = '';

			$(data)
			.each(
					function(index, obj) {
						str += "<tr class='tdcls'><td >"
							+ obj.username
							+ "</td><td>"
							+ obj.name
							+ "</td><td>"
							+ obj.dob
							+ "</td><td>"
							+ obj.state
							+ "</td><td>"
							+ obj.city
							+ "</td><td>"
							+ obj.mobile
							+ "</td><td>"
							+ obj.email
							+ "</td>"
							+ "<td onclick=\"editUser('"
							+ obj.username
							+ "')\"><img src='resources/images/Edit.png' alt='' height='14' width='14' style='cursor:pointer;margin-left: 15px;'/>"
							+ "</td></tr>";

					});
			$("#recordgrid").html(str);
		},
		error : function(response) {

		}
	});
}

function editUser(uname){
	$.ajax({
		url : "${contextPath}"+"/edituser",
		async : true,
		type : 'GET',
		data:{'userId':uname},
		cache : false,
		success : function(data) {
			$("#biggrid").hide();
			$("#editUser").show();
			$(data)
			.each(
					function(index, obj) {
						$("#uId").val(obj.username);
						$("#name").val(obj.name);
						$("#dob").val(obj.dob);
						$("#state").val(obj.state);
						$("#city").val(obj.city);
						$("#mobile").val(obj.mobile);
						$("#email").val(obj.email);
					});

		},
		error : function(response) {

		}
	});
	
}
function updateUser(){
	$.ajax({
		url : "${contextPath}"+"/updateuser",
		async : true,
		type : 'POST',
		beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $("input[name=_csrf]").val());},
		dataType: "text",
		data:{'userId':$("#uId").val(),
			'name':$("#name").val(),
			'dob':$("#dob").val(),
			'state':$("#state").val(),
			'city':$("#city").val(),
			'mobile':$("#mobile").val(),
			'email':$("#email").val()
			},
		cache : false,
		success : function(data) {
		if(data=="S"){
			alert("Profile updated successfully");
		}
		},
		error : function(response) {
			alert("Error in Update process");
		}
	});
	
}

/* $( document ).ready(function() {
	initDropDown("ddownMilestone","DefSelMilestone");
	initDropDown("ddownTop","DefSelTop");
	initDropDown("ddownFunction","DefSelFunction");
	createActivityTree();
	var elm = document.querySelector( ".headerLi1" );
	var pos = elm.getBoundingClientRect();
	$("#axisHolder").css('left',pos.left);
	$("#axisHolder").css('top',pos.top);
	drawAxis('axisHolder');
	var elmImg=document.getElementsByTagName('img');
	$(elmImg).click(function(){
	if($(this).hasClass('img_closed')){
		$(this).removeClass('img_closed').addClass('img_open');
		$(this).attr('src','resources/images/open.png');
		$(this).next().show();
		
		for(var i=0;i<$(this).next().find('div').length;i++){
		$('#canvas_bar'+$(this).next().find('.class'+(i+1)).attr('id')).show();
		$('#bar_'+$(this).next().find('.class'+(i+1)).attr('id')).show();
		}
	}
	else{
	    $(this).removeClass('img_open').addClass('img_closed');
		$(this).attr('src','resources/images/closed.png');
	    $(this).next().hide();
	   for(var i=0;i<$(this).next().find('div').length;i++){
	   $('#bar_'+$(this).next().find('.class'+(i+1)).attr('id')).hide();
		//$('#canvas_bar'+$(this).next().find('.class'+(i+1)).attr('id')).hide();
		} 
	   }
	   if($("#activityULParent").parent().html().indexOf('enscroll-track')==-1){
								addScrollBar("#activityULParent");
							}
	 if($("#canvasParent").parent().html().indexOf('enscroll-track')==-1){
								addScrollBar("#canvasParent");
							}
	});

	}); */
</script>
</body>
</html>
