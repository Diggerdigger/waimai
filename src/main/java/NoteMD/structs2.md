struts2核心过滤器：StrutsPrepareAndExecuteFilter是自2.1.3开始就替代了FilterDispatcher的

extends ActionSupport

struts2提供的ActionContext是Action执行上下文的对象，保存了包括request，session，parameters，application

struts2还提供的了ServletActionContext类，用静态方法获取request、respond等对象