function fMainListToggle(obj){
	obj = obj.parentNode;
	var domselect = null;
	var domImg = null ;
	var tmp = obj.getElementsByTagName('div');
	for(var i=0;i<tmp.length;i++){
		if(tmp[i].className == 'mo_con'){
			domselect = tmp[i];
			domImg = tmp[i];
		}
	}

	if(domselect.style.display == 'none'){
		domselect.style.display = '';
		if(domImg!=null){
		domImg.style.backgroundPosition = '0 0'; 
		}
		document.getElementById("imgShang").src = jsCtx+"/images/shang_sj.png";
	}else{
		domselect.style.display = 'none';
		if(domImg!=null){
		domImg.style.backgroundPosition = '-16px 0'; 
		}
		
		document.getElementById("imgShang").src = jsCtx+"/images/xia_sj.png";
	}
	return ;
}