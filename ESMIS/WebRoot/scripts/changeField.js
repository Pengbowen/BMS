// 动态改变列头.  
var cmenu;  
function $onHeaderContextMenu(e){  
     e.preventDefault();  
     if (!cmenu){  
         createColumnMenu();  
     }  
     cmenu.menu('show', {  
         left:e.pageX,  
         top:e.pageY  
     });  
}  
  
function createColumnMenu() {  
    cmenu = $('<div/>').appendTo('body');  
    cmenu.menu({  
        onClick : function(item) {  
            if (item.iconCls == 'icon icon-check-sign') {  
                // .datagrid-f为datagrid的class名，是easyui自己加上的。  
                // 隐藏列。  
                $('.datagrid-f').datagrid('hideColumn', item.name);  
                cmenu.menu('setIcon', {  
                    target : item.target,  
                    iconCls : 'icon-empty'  
                });  
            } else {  
                // 显示列。  
                $('.datagrid-f').datagrid('showColumn', item.name);  
                cmenu.menu('setIcon', {  
                    target : item.target,  
                    iconCls : 'icon icon-check-sign'  
                });  
            }  
        },  
        onHide : function(){  
        }  
    });  
    var fields = $('.datagrid-f').datagrid('getColumnFields');  
    for (var i = 0; i < fields.length; i++) {  
        var field = fields[i];  
        var col = $('.datagrid-f').datagrid('getColumnOption', field);  
        cmenu.menu('appendItem', {  
            text : col.title,  
            name : field,  
            iconCls : 'icon icon-check-sign'  
        });  
    }  
} 