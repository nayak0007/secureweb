DropDown.prototype = {
	    initEvents : function() {
	        var obj = this;
	        obj.dd.on('click', function(event){
	            $(this).toggleClass('active');
	            event.stopPropagation();
	        }); 
	    }
	}
	function initDropDown(id_1,id_2){
		var dd = new DropDown( $('#'+id_1) );
		$("#"+id_1+" ul li").off('click').click(function(){
	    $("#"+id_2).text($(this).text());
	    var dataIdval=$(this).parent().attr("data-id");
	    $("#"+id_2).attr("data-id",dataIdval);
		})
		}
$(document).click(function(e) {
	$('.wrapper-dropdown-3').removeClass('active');			   			
});
	
function DropDown(el) {
     this.dd = el;
	 this.initEvents();
	}

function monthToLength(month){
var length=0;
if(month=="jan"){
length=(50*1)+2;
}
else if(month=="feb"){
length=(50*2)+6;
}
else if(month=="mar"){
length=(50*3)+10;
}
else if(month=="apr"){
length=(50*4)+14;
}
else if(month=="may"){
length=(50*5)+18;
}
else if(month=="jun"){
length=(50*6)+22;
}
else if(month=="jul"){
length=(50*7)+26;
}
else if(month=="aug"){
length=(50*8)+30;
}
else if(month=="sep"){
length=(50*9)+34;
}
else if(month=="oct"){
length=(50*10)+38;
}
else if(month=="nov"){
length=(50*11)+42;
}
else{
length=(50*12)+46;
}
return length;
}

function createActivityTree(){
var htmlStr="";
var childCount="";
$(data).each(function(index,obj){
if(obj.children.length==0){
htmlStr+="<li><img src='resources/images/closed.png' width=15 height=15 class='img_closed'>"+obj.name+"</li>";
}
else{
childCount=obj.children.length>0?" ("+obj.children.length+")":childCount;
drawBar(obj.id,obj.end_month,8,35,obj.parent);
var childHtmlStr="";
var counter=1;
$(obj.children).each(function(index,objR){
childHtmlStr+="<div id='"+objR.id+"' class='class"+counter+"' style='padding-bottom: 2px;font-size: 14px;'><span style='float:left;width:50%' class='childActDiv' title='"+objR.name+"'>"+objR.name+"</span><span style='float:right;width:40%' class='childActDiv' title='"+objR.spa+"'>"+objR.spa+"</span></div><br>";
drawBar(objR.id,objR.end_month,8,35,objR.parent);
counter++;
});
htmlStr+="<li id='"+obj.id+"' style='font-weight: bold;'><img src='resources/images/closed.png' width=15 height=15 class='img_closed'>"+obj.name+childCount+"<div id='child-"+obj.id+"' style='width:98%;display:none;padding-top: 2px;'>"+childHtmlStr+"</div>"+"</li>";

}
});
$("#activityUL").html(htmlStr);
}

function drawAxis(elmId){
$("#" + elmId).html("");
	var mycanvas = document.createElement("canvas");
	mycanvas.id = "attchFrameCanvas";
	mycanvas.height =358;
	mycanvas.width =630;
	document.getElementById(elmId).appendChild(mycanvas);
	$(mycanvas).css('z-index',1);
	var context = mycanvas.getContext('2d');
	context.lineWidth = 1;
	context.beginPath();
	var len = 50;	
	var factor=2;
	for(i=1;i<=11;i++){
	context.moveTo((len*i)+factor, 0);
	context.lineTo((len*i)+factor, 360);
	factor=factor+4;
	}	
	context.strokeStyle = "grey";
	context.stroke();
	context.closePath();
	
	
}

function drawBar(id,length,barHeight,circleX,parent) {
var liHtml="";
liHtml="<li id='bar_"+id+"'></li>";
$("#canvasParent").append(liHtml);
var canvas = document.createElement("canvas");
canvas.id = "canvas_bar"+id;
canvas.height =10;
canvas.width =660;
length=monthToLength(length);
var region = {x: 0, y: 0, w: length, h: barHeight};
document.getElementById('bar_'+id).appendChild(canvas);

$(canvas).css('overflow-x','hidden');
$(canvas).css('overflow-y','scroll');
	var ctx = canvas.getContext('2d');
  
  ctx.rect(0,0, length,barHeight);
  if(parent!=0){
  ctx.fillStyle = '#8cf442';
  }
  else{
  ctx.fillStyle = 'white';
  }
  ctx.fill();
  if(parent!=0){
  var radius = 2;

  ctx.beginPath();
  ctx.arc(circleX, 4, radius, 0, 2 * Math.PI, false);
  ctx.fillStyle = 'yellow';
  ctx.fill();
  ctx.lineWidth = 1;
  ctx.strokeStyle = 'yellow';
  ctx.stroke();

  var tipCanvas=document.createElement("canvas");
  tipCanvas.id="tip-"+id;
  tipCanvas.height =20;
  tipCanvas.width =120;
  document.getElementById('bar_'+id).prepend(tipCanvas);
  $(tipCanvas).css({'position':'absolute','margin-top':'-5px','margin-left':circleX+'px','z-index':10,'display':'none'});
  ctx = tipCanvas.getContext('2d');
  ctx.rect(0,0, 100,19);
  ctx.font = "12px Arial";
  ctx.fillStyle = "grey";
  ctx.textAlign = "center";
  ctx.fillText("Notification"+id,10,5);
  
var evtLsn=document.getElementById(id);
  canvas.addEventListener('mousemove', function (e) {
    var clientCrds = '('+ e.clientX +', '+ e.clientY +')';
	console.log("current coordinates>>>"+clientCrds);
	$(tipCanvas).css('display','block');		
   });
   canvas.addEventListener('mouseout', function (e) {
    var clientCrds = '('+ e.clientX +', '+ e.clientY +')';
	if($(tipCanvas).is(":visible")){
	$(tipCanvas).hide();
	}
		
   });
   $('#bar_'+id).css('display','none');
   }
  $('#canvas_bar'+id).css('display','none'); 
   
}
function toggleDiv(){
	if($("#decideAddNew").is(":visible")){
		$('#decideAddNew').hide();
	}
	else{
		$('#decideAddNew').show()
	}
	
}
function openActPop(){
	$("#popupDiv").dialog({
						open : function(event, ui) {							
							//$(this).prev().find(".ui-dialog-title").html("<span id='leftTitleSpan'></span><span id='updatedByspan' style='font-size: 14px;position: relative;top: 4px;'></span><span id='rightSideSpan' class='historyLinkClass'></span><span id='rightSideSpanRight' class='historyLinkClassRight'></span>");
							$("#popupDiv").show();
							initDropDown("ddownOne","DefSelOne");
							if($("#dataContainer").parent().html().indexOf('enscroll-track')==-1){
								addScrollBar("#dataContainer");
									}
									//loadMileStoneData();
							
							$("#cal_1").datetimepicker({  
			   			 	validateOnBlur:true,
			   				i18n:{
			   					 en:{
			   					   months:[
			   					    'Jan','Feb','Mar','Apr',
			   					    'May','Jun','Jul','Aug',
			   					    'Sep','Oct','Nov','Dec',
			   					   ],
			   					   dayOfWeek:[
			   					    "Sun", "Mon", "Tue", "Wed", 
			   					    "Thu", "Fri", "Sat",
			   					   ]
			   					  }
			   					 },
			   			    format: 'd/m/Y',
			   			    timepicker:false,
			   			    closeOnDateSelect:true,
			   			    opened:false,
			   			    scrollInput: false			   			    
			   			});
					
						},
						close: function(event,ui) {
							$("#popupDiv").hide();
							$(this).dialog("destroy");
							
						},
						autoOpen : false,
						height : 500,
						width : 800,
						modal : true,
						draggable : false,
						resizable : false,
						dialogClass : 'cptcl'
					});	
					$("#popupDiv").dialog('open');		
	
}

function generateMultiCheckBox(){
	
}
function multiSelectCheck(recordId,fieldId){
	var checkedFieldIds="";
	$("#$multiCheckboxContainerId input:checkbox:checked").each(function(){
		if(checkedFieldIds!=""){
			checkedFieldIds+="#";
		}
		checkedFieldIds+=this.id.replace("multiChkBoxId-"+recordId,"");
	});
	$("#hiddenFieldId-"+recordId).val(checkedFieldIds);	
}
function loadMileStoneData(){
	var calStr="";
	var milestoneDescStr="";
	var statusStr="";
	var finalString="";
	$("#milestoneGrid").html("");
	$(milestoneData).each(function(index,obj){
		var dropDownStr="";
		var selected="";
		/*$(obj.milestoneStatus).each(function(index,objR){
			dropDownStr="<li id='li_"+objR.id+"'>"+objR.name+"</li>";
			if(objR.selected=="y"){
				selected=objR.name;
			}
		});*/
	calStr+="<span id='milestoneCal"+obj.id+"'><input readonly='' type='text' class='simpleCalbox calIcon' id='cal_"+obj.id+"'></span>";
	milestoneDescStr+="<span id='milestoneDesc'><input type='text' class='simpleTextbox' id='cal_1'></span>";
	statusStr+="<span id='milestoneStatus'>"+
						"<div id='ddownMilestone_"+obj.id+"' class='wrapper-dropdown-Milestone' style='height: 18px;'>"+
							"<span id='DefSelMilestone_"+obj.id+"' data-id=''></span>"+
							"<ul class='dropdown' id='ddownMilestoneData_"+obj.id+"'>"+
							"</ul></div></span>";
	
	finalString="<li>"+calStr+milestoneDescStr+statusStr+"</li>";
	$("#milestoneGrid").append(finalString);
	populateDropDown(obj.id);
	$("#cal_"+obj.id).datetimepicker({
		validateOnBlur:true,
			   				i18n:{
			   					 en:{
			   					   months:[
			   					    'Jan','Feb','Mar','Apr',
			   					    'May','Jun','Jul','Aug',
			   					    'Sep','Oct','Nov','Dec',
			   					   ],
			   					   dayOfWeek:[
			   					    "Sun", "Mon", "Tue", "Wed", 
			   					    "Thu", "Fri", "Sat",
			   					   ]
			   					  }
			   					 },
			   			    format: 'd/m/Y',
			   			    timepicker:false,
			   			    closeOnDateSelect:true,
			   			    opened:false,
			   			    scrollInput: false	
		
	});
	});
	
	
}
function populateDropDown(id){
	var liStr="";
	var selected="";
	$(milestoneStatusData).each(function(index,obj){
		liStr+="<li id='li_"+obj.id+"'>"+obj.name+"</li>";
		if(obj.selected=="y"){
			selected=obj.name;
		}
	});
	$("#ddownMilestoneData_"+id).html(liStr);
	$("#DefSelMilestone_"+id).html(selected);	
}
function addMilestone(){
	
}