mysql数据源配置示例
需要修改两个节点
server.xml 下的GlobalNamingResources中添加Resource节点
context.xml 下的Context节点下添加ResourceLink

使用在项目的web.xml下，根节点添加
   <resource-ref>
        <description>DB Connection</description>
        <res-ref-name>jdbc/mysql</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>
