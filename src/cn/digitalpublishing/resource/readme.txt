##MySQL切换Oracle
##1.config.properties
Dialect=org.hibernate.dialect.Oracle10gDialect

##2.proxool.xml
<driver-url>jdbc:oracle:thin:@localhost:1521:push</driver-url>
<driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
<driver-properties>
  <property name="user" value="system"/>
  <property name="password" value="systemdba"/>
</driver-properties>
	    
<house-keeping-test-sql>select sysdate from dual </house-keeping-test-sql>

##3.applicationContext.xml
<!-- 处理Oracle Clob数据 start -->
<property name="lobHandler">
    <ref bean="oracleLobHandler" />
</property>
<!-- 处理Oracle Clob数据 end -->

<!-- 处理Oracle Clob数据 start -->
<bean id="oracleLobHandler" class="org.springframework.jdbc.support.lob.OracleLobHandler">   
    <property name="nativeJdbcExtractor">
        <ref local="nativeJdbcExtractor"/>
    </property>   
</bean>

<bean id="nativeJdbcExtractor" class="org.springframework.jdbc.support.nativejdbc.SimpleNativeJdbcExtractor">   
</bean>
<!-- 处理Oracle Clob数据 end -->

##4.CSentence.hbm.xml
<property name="text" type="org.springframework.orm.hibernate3.support.ClobStringType">
    <column name="SEN_TEXT">
    	<comment>原始内容</comment>
    </column>
</property>
<property name="ttext" type="org.springframework.orm.hibernate3.support.ClobStringType">
    <column name="SEN_T_TEXT">
    	<comment>标注内容</comment>
    </column>
</property>

##5.CContent.hbm.xml
<!-- oracle start -->
<property name="contentAbstract" type="org.springframework.orm.hibernate3.support.ClobStringType">
    <column name="CONTENT_ABSTRACT">
    	<comment>摘要</comment>
    </column>
</property>
<!-- oracle end -->

##线上与本地环境切换
##1.Web.xml
WebGate的配置
param_linux.properties
##2.appContext.xml
config路径的配置
##3.pom.xml
pom文件中要加入opencv和javacv的lib包


