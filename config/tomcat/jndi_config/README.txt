mysql����Դ����ʾ��
��Ҫ�޸������ڵ�
server.xml �µ�GlobalNamingResources�����Resource�ڵ�
context.xml �µ�Context�ڵ������ResourceLink

ʹ������Ŀ��web.xml�£����ڵ����
   <resource-ref>
        <description>DB Connection</description>
        <res-ref-name>jdbc/mysql</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>
