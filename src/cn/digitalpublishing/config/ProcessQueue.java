package cn.digitalpublishing.config;

public class ProcessQueue {
	/**
	 * rest:rest服务 ws:webservice服务 ftp:ftp服务
	 */
	public static String interfaceService = "rest";
	/**
	 * 是否加载 1-加载 2-不加载
	 */
	public static int load=2;
	/**
	 * 是否加载 1-加载 2-不加载
	 */
	public static int userLoad=2;
	/**
	 * 项目主目录
	 */
	public static String WEBROOT = "";
	/**
	 * 下载文件根目录
	 */
	public static String UPLOADROOT = "uploadRoot";
	/**
	 * 文件推送目录
	 */
	public static String PUSHHOME = "pushHome";
	/**
	 * 模板文件上传目录
	 */
	public static String USERTEMPLATEHOME = "userTemplate";
	
	/**
	 * 源数据下载目录
	 */
	public static String SOURCEDATAPATH = "sourcedatapath";
	
	/**
	 * 资源图书XML存放目录
	 */
	public static String BOOKXML = "bookxml";
	
	/**
	 * 资源图书图片存放目录
	 */
	public static String PHOTO = "photo";
	
	/**
	 * 订单导入进程
	 */
	public static int orderLoad=2;
	/**
	 * Dawson产品信息载入
	 */
	public static int dawsonProductLoad=2;
	/**
	 * 审查任务加载
	 */
	public static int checkTaskLoad=2;
	/**
	 * 自动解析数据转换成我们自己使用的数据
	 * */
	public static int contentDataLoad=2;

///////////////new///////////////////////////
	/**
	 * 自动创建本地任务加载
	 */
	public static int autoLocalTaskLoad=2;
	/**
	 * 本地任务批处理数量（默认50个）
	 */
	public static int batchLocalTaskCount=50;
	/**
	 * 自动创建远程任务加载
	 */
	public static int autoRemoteTaskLoad=2;
	/**
	 * 远程任务批处理数量（默认50个）
	 */
	public static int batchRemoteTaskCount=50;
	/**
	 * 领取任务请求加载
	 */
	public static int contentRequestLoad=1;
	/**
	 * #################################
	 */
	
	
	/**
	 * excel 模板路径
	 */
	public static String TEMP_PAHT_NAME = "/temp/temp.xlsx";
	/**
	 * 自动下载源数据 1-加载 2-不加载 
	 * */
	public static int SOURCEDATALOAD=2;
	/**
	 * 解析拆分
	 */
	public static int ANALYSISDATALOAD=2;
	/**
	 * 图书下载
	 */
	public static int DOWNLOAD=2;
	/**
	 * 数据转换
	 */
	public static int CONVERTDATALOAD=2;
	/**
	 * 推送
	 */
	public static int PUSHLOAD=2;
	/**
	 * 发件人邮箱用户名
	 */
	public static String EMAILUSERNAME;
	/**
	 * 发件人邮箱密码
	 */
	public static String EMAILPASSWORD;
	//邮箱配置
	/**
	 * 服务器验证
	 */
	public static String AUTH;
	/**
	 * 传输协议
	 */
	public static String PROTOCOL;
	/**
	 * 服务类型
	 */
	public static String HOST;
	/**
	 * 邮件主题
	 */
	public static String EMAILSUBJECT;
	
	
	/**
	 * 图书文件存储路径
	 */
	public static String BOOKIMG = "bookIMG";
	
}
