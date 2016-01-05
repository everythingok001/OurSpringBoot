function initDataGrid(node,param){
    param || (param = "");
    var datagrid=$('#'+node).datagrid({
        url:'/User/getUser?'+param,
        pagination:true,
        pageSize:10,
        pageList:[10,20,30],
        fit:true,
        nowarp:false,
        border:false,
        fitColumns:true,
        striped:true,
        idField:'id',
        pagePosition:'bottom',
        columns:columns,
        loadMsg:"正在加载数据,请稍候...",
        rowStyler: function(index,row){
            if (index%2==1){
                return 'background-color:#F4F4F4;color:#000;';
            }
        },
        onRowContextMenu:function(e,index,rowData){
            e.preventDefault();
            datagrid.datagrid('unselectAll');
            datagrid.datagrid('selectRow',index);
            $('#menu').menu('show',{
                left:e.pageX,
                top:e.pageY
            })
        }
    });
    return datagrid;
}

/**
 * 用户根据form表单刷新grid数据
 * @param datagrid 要刷新的datagrid的ID
 * @param node     form表单的ID
 */
function queryForm(datagrid_id,node_id){
    $('#'+datagrid_id).datagrid('load',JSON.stringify($('#'+node_id).serializeArray()))
}
