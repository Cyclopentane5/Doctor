
function reloadView(){
       $.ajax({
            type : "get",
            url:"chat/",
            datatype:"json",

            success:function(data,status){
                var d = data["data"];
                var str = "";
                for(var i=0; i<d.length;i++){
                    str = str+d[i]+"<hr/>";
                    str = str.replace(/,/," ")
                }

                var $p = document.getElementById("a");
                $p.innerHTML=str;
            }
       })
 }

setInterval('reloadView()',100);