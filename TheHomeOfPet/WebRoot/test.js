

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
         //get��ʽ�ύ
    	 xmlhttp.open("get",url,true);
     }else{
         
         //post��ʽ�ύ
    	 xmlhttp.open("post",url,true);
     }
     
     //��������
     if(!postData){
         //get��ʽ�ύ
    	 xmlhttp.send();
     }else{
          //post��ʽ�ύ
    	 xmlhttp.send(postData);
     }
     
     
     //ע���¼�
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

