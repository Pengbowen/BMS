	function fillSelectData(json,objSelect,defaultOptions,selectKey)
	{
		objSelect.options.length = 0;
		var varItem,i = 0;
		if(defaultOptions)
		{
	        varItem = new Option(defaultOptions["text"], defaultOptions["value"]);      
	        objSelect.options.add(varItem);	
	        if(selectKey == defaultOptions["value"])
	        {
	        	objSelect[i].selected = true;
	        }
		}
		for(var j = 0, len = json.datalist.length; j< len; j++)
		{   
			var temp=json.datalist[j];
	        varItem = new Option(temp.content,temp.code);      
	        objSelect.options.add(varItem);
	        if(selectKey == temp.code)
	        {
	        	objSelect[i].selected = true;
	        }		        
	        i++;
		}
	}