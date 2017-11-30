

function test11(){
	
	alert("test");
}


function ajax(url,postData,fnSucss,fnFaild){
		
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
     
     if(!postData){
         //get方式提交
    	 xmlhttp.open("get",url,true);
     }else{
         
         //post方式提交
    	 xmlhttp.open("post",url,true);
     }
     
     //发送请求
     if(!postData){
         //get方式提交
    	 xmlhttp.send();
     }else{
          //post方式提交
    	 xmlhttp.send(postData);
     }
     
     
     //注册事件
     xmlhttp.onreadystatechange=function(){
    	 
    	 if(xmlhttp.readyState==4){
            
             if(xmlhttp.status==200){
            		alert("success:"+xmlhttp.responseText);
            	 	fnSucss(xmlhttp.responseText); 
            	 
             	}
             else{
            	 	if(fnFaild)
            	 	{
            	 		fnFaild(xmlhttp.status);
            	 	}
            	 	alert("fail:"+xmlhttp.status);
            	 }
         }
    	 
    };
}

