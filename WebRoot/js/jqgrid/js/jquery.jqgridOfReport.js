;(function($){
$.fn.jqgridOfReport = function(o){
	var d = $(this);
	var showTotalOfM;
	var showHOfR=0;
	var w_s=0;
	var kCode=null;
	var li="";
	var counter=-1;
	var reportHref=null;
	var fieldsRow=new Array();
	jqgrid=function(id,url,dUrl){
		$("#"+id).jqGrid({ 
			url: url+encodeURI(dUrl),
			datatype:"json",
			colNames:o.colNames, 
			colModel:o.colModel, 
			rowNum:o.rowNum || 10, 
			rowList:o.rowList || [10,20,30,40], 
			height: 'auto', 
			pager: o.pager, 
			sortname: o.sortname, 
			viewrecords: true, 
			sortorder: o.sortorder || "desc", 
			caption:o.caption,
			mtype:o.mtype || "post"
					});
		total(id,encodeURI(dUrl));
	};
	total=function(id,url){
		var s=o.reportSum;
		if(s==null){return;}
		var div=document.createElement("div");
		$(div).css({"width":$("#"+o.pager).width()+"px"}).attr("class","reportSum_div");
		$.ajax({
			type:"post",
			url:s.sumUrl+url,
			async:false,
			dataType:"json",
			success:function(r){
				showTotalOfM="";
				w_s=0;
				showHOfR=s.colSum.length;
				//$(div).append("sum:");
				$.each(s.colSum,function(i){
					if(this.special){return;}
					var dec=s.colSum[i].decimalPlaces || 2;
					var m=s.colSum[i].format=="money"?formatMoneyOfReport(r[s.colSum[i].name],dec):r[s.colSum[i].name];
					//var l=document.createElement("font");
					//var s_p=document.createElement("label");
					//$(s_p).attr("class","report_span").text(s.colSum[i].tableName);
					//$(l).attr("class","report_label").text((m || 0));
					//$(div).append(s_p).append(l).append(i+1==s.colSum.length?"":"&nbsp;| ");
					var prefix = s.colSum[i].format=="money"?"￥":"";
					$(div).append(s.colSum[i].tableName).append(prefix+(m || 0)).append(i+1==s.colSum.length?"":"&nbsp;| ");
					showTotalOfM+="<tt>"+s.colSum[i].tableName+"</tt><dd>"+ prefix +(m || 0)+"</dd>";
					var sname=new String(m);
					w_s+=s.colSum[i].tableName.length*2+sname.length+3+prefix.length*2;
				});
			}
		});	
		if(w_s!=0){$("#"+id).after(div);}
		showMoney(div);
	}
	dequ=function(c){$(c).dequeue();}
	showMoney=function(div_show){
		if($(div_show).width()-(w_s*6)>0 ){return;}
		div=document.createElement("div");
		$(div_show).bind("mousemove",function(){
			var w=$(div_show).width();
			var l=$(div_show).offset().left;
			var t=$(div_show).offset().top;
			var h=$(d).height();
			if($(".show_total_model").length<=0){
				//var span=document.createElement("span");"height":(11*showHOfR)+"px"
				//$(span).css({"left":(w+l+10)+"px","top":(t+h)+"px","position":"absolute"}).text("sssssssssss");
				$(div).css({"paddingRight":"20px","left":(w+l+10)+"px","top":(t-21*showHOfR)+"px","height":(26.5*showHOfR+10)+"px","position":"absolute"}).html(showTotalOfM).attr("class","show_total_model");
				$("body").append(div);
			}
			else{
				if($(".show_total_model").css("display")=="none"){
					$(".show_total_model").html(showTotalOfM).css({"paddingRight":"20px","left":(w+l+10)+"px","top":(t-21*showHOfR)+"px","position":"absolute"});
					$(".show_total_model").queue(function(){
						$(".show_total_model").fadeIn("fast");
						dequ(".show_total_model");
					});
				}
			}
			$(window).scrollLeft(350);
			//alert($(window).scrollLeft());
		});
		$(div_show).bind("mouseout",function(){
			if($(".show_total_model").css("display")!="none"){
				$(".show_total_model").queue(function(){
					$(".show_total_model").fadeOut("fast");
					dequ(".show_total_model");
				});
			}
		});
		
	}
	showPrompt=function(url,Pi,Pj,k){
		var div = document.createElement("div");
		$("#"+Pi).attr("autocomplete","off").bind("keyup",function(e){
			var input=$(this);
			if($.trim($(this).val())==""){return;}
			kCode = e.keyCode;
 			switch(e.keyCode) {
			case 38: // up
				moveSelect(Pi,input,-1);
				e.preventDefault();
				break;
			case 40: // down
				moveSelect(Pi,input,1);
				e.preventDefault();
				break;
			case 9:  // tab
			case 37: e.preventDefault();break;//left
			case 39: e.preventDefault();break;//right
			case 8: if(input.val()!=""){counter=-1;changeList(url,Pi,Pj,input,k);}e.preventDefault();break;
			case 13: 
					if($("#show_prompt_div_"+Pi+" li[liType!=no]").length==1){input.val($("#show_prompt_div_"+Pi+" li[liType!=no]").text()).blur();}
					else{input.val($("#show_prompt_div_"+Pi+" li[liType!=no][class=ui-state-hover]").text()).blur();}
					e.preventDefault();
				break;
			default:
				counter=-1;
				changeList(url,Pi,Pj,input,k);
				e.preventDefault();
				break;
		}
		}).bind("blur",function(){
			$("#show_prompt_div_"+Pi).fadeOut("normal");
		});
	}
	moveSelect=function(Pi,input,m){
		var size=$("#show_prompt_div_"+Pi+" li[liType!=no]").length;
		if(size==0){return;}
		var l=$("li","#show_prompt_div_"+Pi);
		$(l).removeClass("ui-state-hover");
		if(m>0 && counter!=size){
			counter +=1;
			$(l[counter]).addClass("ui-state-hover");
		}
		if(m<0 && m<counter){
			counter -=1;
		$(l[counter]).addClass("ui-state-hover");
		}
	}
	changeList=function(url,Pi,Pj,input,k) {
		if( kCode == 46 || (kCode > 8 && kCode < 32) ) return;
		var p_d;
		$(input).addClass("prompt_load");
		if($("#show_prompt_div_"+Pi).attr("id")==null){
			p_d=document.createElement("div");
			$(p_d).css({"border":"1px solid #A9A7FE","display":"none","position":"absolute","backgroundColor":"#fff","overflowX":"hidden","overflowY":"scroll","height":"110px"}).attr("id","show_prompt_div_"+Pi);
			$(input).after(p_d);
		}else{
			p_d=$("#show_prompt_div_"+Pi);
		}
		li="";
		$.ajax({
			url:url,
			data:Pi+"="+Pj+"&"+k+"="+$("#"+Pi).val(),
			type:"post",
			dateType:"json",
			success:function(r){
				if(r.promptList==null || r.promptList.length==0){li="<li liType='no' style='color:#2E6E9E;padding-right:7px;padding-left:5px'>\u6ca1\u6709\u627e\u5230\u7b26\u5408\u6570\u636e</li>";$(p_d).css({"overflowY":"auto"});}
				$.each(r.promptList,function(i){
					li+="<li>"+r.promptList[i]+"</li>";
				});
				$(input).removeClass("prompt_load");
				$(p_d).html(li).fadeIn("fast");
				$("li[liType!=no]")
				.bind("mousemove",function(){
					$("li").removeClass("ui-state-hover");
					$(this).css("cursor","pointer").addClass("ui-state-hover");})
					.bind("mouseout",function(){$(this).removeClass("ui-state-hover");})
					.bind("click",function(){input.val($(this).text());$("#show_prompt_div_"+Pi).fadeOut("fast");});
				$("#show_prompt_div_"+Pi).scroll(function(){
					$("#show_prompt_div_"+Pi).stop().css({"opacity":"1","display":""});
					$("#"+Pi).focus();
				});
				}
			
		});
	};
	datepicker=function(id){
		$("#"+id).datepicker({
			showMonthAfterYear: true,
			changeMonth: false,
			changeYear: false,
			dateFormat: 'yy-mm-dd',  
			disabled:"true",  
			showButtonPanel: true, 
			closeText:'\u5173\u95ed',
			showOn:'button',  
	        buttonImage: 'calendar.gif',
	        buttonImageOnly: true,
	        showOn: 'focus',
			duration: 'fast',
			gotoCurrent:true
	});
		$("#"+id).after("<a style='cursor:pointer;margin-left:10px' onclick=clearByDatepicker('"+id+"')>清除</a>");
	};
	formatMoneyOfReport=function(s, n){
		if(s==null){return 0;}
	   n = n > 0 && n <= 20 ? n : 2;
	   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	   var l = s.split(".")[0].split("").reverse(),
	   r = s.split(".")[1];
	   t = "";
	   for(i = 0; i < l.length; i ++ )
	   {
	      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	   }
	   return t.split("").reverse().join("") + "." + r;
	}
	clearByDatepicker=function(id){
		$("#"+id).val("");
	};
	coordinate=function(){
		var rows=new Array();
		var rowsName=new Array();
		$("#showDialog").css("lineHeight","1px");
		$.each(o.colModel,function(i){
			if(this.coordinate!=null){
				var v=this.coordinate.split(",");
				var r=v[0];
				var c=v[1];
				if(rows[r]==null){rows[r]=new Array();rowsName[r]=new Array();}
				o.colModel[i].num=i;
				rows[r][c]=o.colModel[i];
				rowsName[r][c]=o.colNames[i];
			}});
		var tableCoor="<table>";
		for(var rr=0;rr<rows.length;rr++){
			tableCoor+="<tr>";
			$.each(rows[rr],function(i){
				if(rows[rr][i].name==o.sortname){$("#showDialog").append("<span style='display:none'><input type='checkbox' id='fields' name='fields' sort='true' index='"+rows[rr][i].num+"'>"+rowsName[rr][i]+"</span>");}
				else if(rows[rr][i].special){$("#showDialog").append("<span style='display:none'><input type='checkbox' checked = 'false' special='true' id='fields' name='fields' index='"+rows[rr][i].num+"'>"+rowsName[rr][i].name+"</span>");}
				else{tableCoor+="<td><input type='checkbox' id='fields' name='fields' index='"+rows[rr][i].num+"'>"+rowsName[rr][i]+"</td>";}
				fieldsRow[rows[rr][i].num]="0";
				rows[rr][i].hidden=true;
				if(rows[rr][i].defaultReport && !rows[rr][i].special){
					if(index!=0 && index%7==0){$("#selectDiv").append("<br/>");}index+=1;
					$("#selectDiv").append("<input checked=true type='checkbox' id='fields' name='fields' index='"+rows[rr][i].num+"'>"+rowsName[rr][i]);
					rows[rr][i].hidden=false;
					fieldsRow[rows[rr][i].num]="1";
				}
			});
			tableCoor+="</tr>";
			//$("#showDialog").append(br?"<br/>":"");
		}
		$("#showDialog").append(tableCoor);
		return fieldsRow;
	};
	stringBuffer=function(count, substr)
    {
        var buffer = [];
        for (var i = 0; i < count; ++i)
        {
            buffer.push(substr);
        }
        return buffer.join("");
    };
	if(o.datepicker!=null){
		$.each(o.datepicker,function(i){
			datepicker(o.datepicker[i]);
		});
	}
	if(o.showPrompt!=null){
		var sp=o.showPrompt;
		if(sp.url==null){return;}
		$.each(sp.inputPrompt,function(i,j){
			showPrompt(sp.url,i,j,sp.queryKey || "promptQueryDto.queryKey");
		});
	}
	var showCheckBox = (o.showCheckBox && true)?"":"none";
	$(d).before("<div id='jqgridDivOfReport'></div>");
	$("#jqgridDivOfReport").append($(d)).append($("#"+o.pager));
	$("body").prepend("<div id='showDialog'></div>");
	$(":[change=true]").before("<div id='selectDiv' style='display:"+showCheckBox+"'></div>");
	var defaultUrl = "&fields=";
	var num;var index = 0;var j=0;
	if(o.colModel[0].coordinate==null){
		$.each(o.colModel,function(i){
			if(!this.special){j+=1;}
			if(!this.special && j!=0 && j%5==0){$("#showDialog").append("<br/>");}
			if(o.colModel[i].name==o.sortname){$("#showDialog").append("<span style='display:none'><input type='checkbox' id='fields' name='fields' sort='true' index='"+i+"'>"+o.colNames[i]+"</span>");}
			else if(o.colModel[i].special){$("#showDialog").append("<span style='display:none'><input type='checkbox' checked = 'false' special='true' id='fields' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");}
			else{$("#showDialog").append("<span><input type='checkbox' id='fields' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");}
			num = "0";
			o.colModel[i].hidden=true;
			if(o.colModel[i].defaultReport && !o.colModel[i].special){
				if(index!=0 && index%7==0){$("#selectDiv").append("<br/>");}index+=1;
				$("#selectDiv").append("<span><input checked=true type='checkbox' id='fields' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");
				o.colModel[i].hidden=false;
				num = "1";
			}
			defaultUrl +=num;
		});
	}else{
		var fr=coordinate();
		$.each(fr,function(i){
			defaultUrl +=fr[i];
		});
		}
	$("#showDialog").dialog({
		title:"\u9009\u62e9\u663e\u793a\u7684\u5b57\u6bb5",
		modal: true,
		autoOpen: false,
		width:o.dialogWidth || 570,
		buttons: {
			'\u5168\u9009/\u53d6\u6d88':function(){
				if($("#showDialog input[sort!=true]:checked").size()==0){
					$("#showDialog input[type=checkbox][special!=true]").attr("checked",true);
					}
				else{
					$("#showDialog input[type=checkbox][sort!=true]").attr("checked",false);
					}},
			'\u9009\u62e9': function() {
				$("#selectDiv").html(""); 
				if($("#showDialog input:checked").size()!=0){
					$($("#showDialog input:checked")).each(function(i){
						$("#selectDiv").append($(this).clone());//.parent()
						var br='';
						if(i!=0 && i%6==0)$("#selectDiv").append("<br/>");
					});
					}
				$("#selectDiv input[type=checkbox]").attr("checked",true);
				$( this ).dialog( "close" );
			}
		}
	});
	$(":[change=true]").click(function(){
		if($("#selectDiv input:checked").size()!=0){
			var  selectDiv=$.makeArray($("#selectDiv input:checked"));
				$($("#showDialog input[type=checkbox]")).each(function(j){
					var showDiv = $(this);
					var t=false;
					$.each(selectDiv,function(i){
						if($(this).attr("index")==showDiv.attr("index")){
							t=true;
							showDiv.attr("checked",t);
						}
					});
					if(!t){showDiv.attr("checked",t)}
				});
			}else{$($("#showDialog input[type=checkbox]")).attr("checked",false);}
		$('#showDialog').dialog('open');
		return false;
	});
	jqgrid(d.attr("id"),o.url,defaultUrl);
	$(":[getReport=true]").click(function(){
		if(this.href==null || this.href==""){return;}
		var url=this.href+encodeURI(reportHref);
		this.href="#";
		$.ajax({
			url:url,
			type:"post",
			async:true,
			dataType:"text",
			success:function(){}
		});
	});
	$(":[find=true]").click(function(){
		var url="";
		var fields = "&fields=";
		$("form input[type=text]").each(function(){
			url += "&"+$(this).attr("name")+"="+$.trim($(this).val());
		});
		$("form select").each(function(){
			url += "&"+$(this).attr("name")+"="+$.trim($(this).val());
		});
		$("form input[type=radio]").each(function(){
			url += "&"+$(this).attr("name")+"="+$.trim($(this).val());
		});
		if($("#selectDiv input[sort!=true]:checked").size()!=0){
			var  selectDiv=$.makeArray($("#selectDiv input:checked"));
			if(o.colModel[0].coordinate!=null){
					$.each(o.colModel,function(j){
						var showDiv = this;
						var t=false;
						$.each(selectDiv,function(i){
							if($(this).attr("index")==showDiv.num){
								t=true;
								fields +="1";
								o.colModel[j].hidden=false;
							}
						});
						if(!t){fields +="0";o.colModel[j].hidden=true;}
					});
			}else{
				$($("#showDialog input[type=checkbox]")).each(function(j){
					var showDiv = $(this);
					var t=false;
					$.each(selectDiv,function(i){
						if($(this).attr("index")==showDiv.attr("index")){
							t=true;
							fields +="1";
							o.colModel[j].hidden=false;
						}
					});
					if(!t){fields +="0";o.colModel[j].hidden=true;}
				});
			}
			}else{//special
				$("#showDialog").html("");
				if(o.colModel[0].coordinate==null){
					var j=0;
					$.each(o.colModel,function(i){
						if(!this.special){j+=1;}
						if(!this.special && !this.special && j!=0 && j%5==0){$("#showDialog").append("<br/>");}
						if(o.colModel[i].name==o.sortname){$("#showDialog").append("<span style='display:none'><input type='checkbox' id='fields' name='fields' sort='true' index='"+i+"'>"+o.colNames[i]+"</span>");}
						else if(o.colModel[i].special){$("#showDialog").append("<span style='display:none'><input type='checkbox' special='true' id='fields' checked = 'false' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");}
						else{$("#showDialog").append("<span><input type='checkbox' id='fields' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");}
						if(o.colModel[i].defaultReport && !o.colModel[i].special){
							$("#selectDiv").append("<span><input checked=true type='checkbox' id='fields' name='fields' index='"+i+"'>"+o.colNames[i]+"</span>");
							o.colModel[i].hidden=false;
							fields +="1";
						}else{
							o.colModel[i].hidden=true;
							fields +="0";
						}
					});
				}else{
					var fr=coordinate();
					$.each(fr,function(i){
						fields +=fr[i];
					});
				}
				$("#selectDiv input[sort=true]:checked").remove();
			}
		$("#jqgridDivOfReport").html("<table id='"+d.attr("id")+"' /><div id='"+o.pager+"''/>");
		reportHref=url+fields;
		jqgrid($(d).attr("id"),o.url,url+fields);
	});
};})(jQuery); 