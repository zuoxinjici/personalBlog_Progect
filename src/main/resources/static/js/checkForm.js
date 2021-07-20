$('.ui.form').form({
    fields:{
        title: {
            identifier:'title',
            rules:[{
                type:'empty',
                prompt:'标题:请输入博客标题'
            }]
        }
    }
});