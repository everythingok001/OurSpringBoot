var columns=[[
	             {title:'ID',field:'id'},
		         {title:'用户名',field:'name',width:100}
	    		]];

function init(param){
	param || (param = "");
	datagrid=$('#datagrid').datagrid({
		url:'/other/getUser?'+param,
		pagination:true,
		pageSize:10,
		pageList:[10,20,30,40],
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
		toolbar:[
		         {text:'增加',iconCls:'icon-add',handler:function(){
		        	 add();
		         }},'-',
		         {text:'删除',iconCls:'icon-remove',handler:function(){
		        	 del();
		         }},'-',
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 edit();
		         }},'-',
		         {text:'取消选中',iconCls:'icon-redo',handler:function(){
		        	 datagrid.datagrid('unselectAll');
		        	 datagrid.datagrid('clearSelections');
		         }}
		         ],
		onRowContextMenu:function(e,index,rowData){
			e.preventDefault();
			datagrid.datagrid('unselectAll');
			datagrid.datagrid('selectRow',index);
			$('#menu').menu('show',{
				left:e.pageX,
				top:e.pageY
			});
		}
	});	
}

$(function(){
	init();
})
